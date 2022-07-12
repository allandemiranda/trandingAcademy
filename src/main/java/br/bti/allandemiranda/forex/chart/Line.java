package br.bti.allandemiranda.forex.chart;

import com.beust.jcommander.internal.Lists;
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
    if (compatible(line) && this.size() > 1) {
      return IntStream.rangeClosed(1, this.size() - 1).boxed().toList().parallelStream().map(i -> {
        // TODO: can put all in one if
        if (Objects.nonNull(get(i).getValue()) && Objects.nonNull(line.get(i).getValue())
            && Objects.nonNull(get(i - 1).getValue()) && Objects.nonNull(line.get(i - 1).getValue())) {
          if (get(i).getValue().equals(line.get(i).getValue())) {
            return get(i).getLocalDateTime();
          } else {
            if ((get(i).getValue() > line.get(i).getValue()) && (get(i - 1).getValue() < line.get(i - 1)
                .getValue())) {
              return get(i).getLocalDateTime();
            } else {
              if ((get(i).getValue() < line.get(i).getValue()) && (get(i - 1).getValue() > line.get(
                  i - 1).getValue())) {
                return get(i).getLocalDateTime();
              } else {
                return null;
              }
            }
          }
        } else {
          return null;
        }
      }).filter(Objects::nonNull).toList();
    } else {
      return Lists.newArrayList();
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
    if (line.size() == this.size() && this.size() - 1 > 0 && consistency() && line.consistency()) {
      return IntStream.rangeClosed(0, this.size() - 1).boxed().toList().parallelStream()
          .allMatch(i -> this.get(i).getLocalDateTime().equals(line.get(i).getLocalDateTime()));
    } else {
      return false;
    }
  }

  /**
   * Consistency boolean.
   *
   * @return the boolean
   */
  public boolean consistency() {
    if(!isEmpty()) {
      boolean nun = Objects.isNull(getFirst().getValue());
      for (int i = 1; i < size(); ++i) {
        if(nun){
          nun = Objects.isNull(get(i).getValue());
        } else {
          return IntStream.rangeClosed(i, size() - 1).boxed().toList().parallelStream().noneMatch(integer -> Objects.isNull(get(integer).getValue()));
        }
      }
    }
    return true;
  }
}
