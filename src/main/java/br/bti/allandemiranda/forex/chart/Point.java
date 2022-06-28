package br.bti.allandemiranda.forex.chart;

import java.time.LocalDateTime;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The type Point.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Point {

  private final LocalDateTime localDateTime;
  private Double value;

  /**
   * Instantiates a new Point.
   *
   * @param localDateTime the local date time
   * @param value         the value
   */
  public Point(@NotNull LocalDateTime localDateTime, Double value) {
    this.localDateTime = localDateTime;
    this.value = value;
  }

  /**
   * Gets local date time.
   *
   * @return the local date time
   */
  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  /**
   * Gets value.
   *
   * @return the value
   */
  public Double getValue() {
    return value;
  }

  /**
   * Sets value.
   *
   * @param value the value
   */
  public void setValue(Double value) {
    this.value = value;
  }

  /**
   * Is before boolean.
   *
   * @param point the value
   *
   * @return the boolean
   */
  public boolean isBefore(@NotNull Point point) {
    return getLocalDateTime().isBefore(point.getLocalDateTime());
  }

  /**
   * Is after boolean.
   *
   * @param point the value
   *
   * @return the boolean
   */
  public boolean isAfter(@NotNull Point point) {
    return getLocalDateTime().isAfter(point.getLocalDateTime());
  }

  /**
   * Is high boolean.
   *
   * @param point the value
   *
   * @return the boolean
   */
  public Boolean isHigh(@NotNull Point point) {
    return Objects.isNull(getValue()) || Objects.isNull(point.getValue()) ? null
        : getValue() > point.getValue();
  }

  /**
   * Is low boolean.
   *
   * @param point the value
   *
   * @return the boolean
   */
  public Boolean isLow(@NotNull Point point) {
    return Objects.isNull(getValue()) || Objects.isNull(point.getValue()) ? null
        : getValue() < point.getValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Point point1 = (Point) o;
    return Objects.equals(localDateTime, point1.localDateTime) && Objects.equals(value, point1.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(localDateTime, value);
  }

  @Override
  public String toString() {
    return "Point{" + "localDateTime=" + localDateTime + ", value=" + value + '}';
  }
}
