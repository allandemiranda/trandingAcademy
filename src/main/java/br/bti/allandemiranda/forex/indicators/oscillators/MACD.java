package br.bti.allandemiranda.forex.indicators.oscillators;

import br.bti.allandemiranda.forex.chart.Point;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Macd.
 */
public class MACD {

  private final Point histogram;
  private final Point macd;
  private final Point signal;
  private final LocalDateTime localDateTime;

  /**
   * Instantiates a new Macd.
   *
   * @param localDateTime the local date time
   * @param histogram     the histogram
   * @param macd          the macd
   * @param signal        the signal
   */
  public MACD(LocalDateTime localDateTime, Double histogram, Double macd, Double signal) {
    this.histogram = new Point(localDateTime, histogram);
    this.macd = new Point(localDateTime, macd);
    this.signal = new Point(localDateTime, signal);
    this.localDateTime = localDateTime;
  }

  /**
   * Gets histogram.
   *
   * @return the histogram
   */
  public Point getHistogram() {
    return histogram;
  }

  /**
   * Gets macd.
   *
   * @return the macd
   */
  public Point getMacd() {
    return macd;
  }

  /**
   * Gets signal.
   *
   * @return the signal
   */
  public Point getSignal() {
    return signal;
  }

  /**
   * Gets local date time.
   *
   * @return the local date time
   */
  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MACD macd1 = (MACD) o;
    return Objects.equals(histogram, macd1.histogram) && Objects.equals(macd, macd1.macd)
        && Objects.equals(signal, macd1.signal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(histogram, macd, signal);
  }

  @Override
  public String toString() {
    return "MACD{" + "histogram=" + histogram + ", macd=" + macd + ", signal=" + signal + '}';
  }
}
