package br.bti.allandemiranda.forex.chart;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The type Line.
 *
 * <p> Note: Suppose that all lines have the same size, and the same start time, with the same X value
 * to each time step </p>
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Line extends LinkedList<Point> {

  protected static Logger logger = LogManager.getLogger(Line.class);

  /**
   * Instantiates a new Line.
   */
  public Line() {
  }

  /**
   * Instantiates a new Line.
   *
   * @param c the c
   */
  public Line(@NotNull Collection<? extends Point> c) {
    super(c);
  }

  /**
   * Crossed list.
   *
   * @param line the line
   *
   * @return the list
   */
  public List<LocalDateTime> crossed(@NotNull Line line) {
    if (compatible(line)) {
      return IntStream.rangeClosed(0, line.size() - 1).boxed().toList().parallelStream().map(i -> {
        if ((Objects.nonNull(line.get(i).getValue()) && Objects.nonNull(this.get(i).getValue())) && (
            (line.get(i).getValue().equals(this.get(i).getValue())) || ((i > 0) && (
                (line.get(i - 1).isHigh(this.get(i - 1)) && line.get(i).isLow(this.get(i))) || (
                    this.get(i - 1).isHigh(line.get(i - 1)) && this.get(i).isLow(line.get(i))))))) {
          return line.get(i).getLocalDateTime();
        } else {
          return null;
        }
      }).filter(Objects::nonNull).toList();
    } else {
      throw new IllegalStateException("Trying to compare lines incompatibility");
    }
  }

  /**
   * Compatible boolean.
   *
   * @param line the line
   *
   * @return the boolean
   */
  public boolean compatible(@NotNull Line line) {
    if (line.size() == this.size()) {
      return IntStream.rangeClosed(0, line.size() - 1).boxed().toList().parallelStream()
          .map(i -> line.get(i).equals(this.get(i))).anyMatch(aBoolean -> !aBoolean);
    } else {
      return false;
    }
  }
}
