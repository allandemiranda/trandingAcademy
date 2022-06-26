package br.bti.allandemiranda.forex.chart;

import com.beust.jcommander.internal.Lists;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The type Line.
 *
 * <p> Note: Suppose that all lines have the same size, and the same start time, with the same X value to
 * each time step </p>
 */
public class Line {

  protected static Logger logger = LogManager.getLogger(Line.class);

  private final List<Point> pointLinkedList;

  /**
   * Instantiates a new Line.
   *
   * @param pointLinkedList the point linked list
   */
  public Line(List<Point> pointLinkedList) {
    this.pointLinkedList = pointLinkedList;
  }

  /**
   * Gets point linked list.
   *
   * @return the point linked list
   */
  public List<Point> getPointLinkedList() {
    return pointLinkedList;
  }

  /**
   * Crossed list.
   *
   * @param line the line
   *
   * @return the list
   */
  public List<LocalDateTime> crossed(@NotNull Line line) {
    if (line.getPointLinkedList().isEmpty() || getPointLinkedList().isEmpty()) {
      logger.warn("Trying to compare empty lines");
      return Lists.newArrayList();
    }
    if (line.getPointLinkedList().size() != getPointLinkedList().size()) {
      logger.warn("Trying to compare lines with different size");
      return Lists.newArrayList();
    }

    return IntStream.rangeClosed(0, line.getPointLinkedList().size() - 1).boxed().toList()
        .parallelStream().map(i -> {
          if ((Objects.nonNull(line.getPointLinkedList().get(i).getValue()) && Objects.nonNull(
              getPointLinkedList().get(i).getValue())) && ((line.getPointLinkedList().get(i).getValue()
              .equals(getPointLinkedList().get(i).getValue())) || ((i > 0) && (
              (line.getPointLinkedList().get(i - 1).isHigh(getPointLinkedList().get(i - 1))
                  && line.getPointLinkedList().get(i).isLow(getPointLinkedList().get(i))) || (
                  getPointLinkedList().get(i - 1).isHigh(line.getPointLinkedList().get(i - 1))
                      && getPointLinkedList().get(i).isLow(line.getPointLinkedList().get(i))))))) {
            return line.getPointLinkedList().get(i).getLocalDateTime();
          } else {
            return null;
          }
        }).filter(Objects::nonNull).toList();
  }
}
