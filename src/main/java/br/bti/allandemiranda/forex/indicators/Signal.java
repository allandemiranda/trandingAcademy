package br.bti.allandemiranda.forex.indicators;

import java.time.LocalDateTime;
import org.jetbrains.annotations.NotNull;

/**
 * The type Signal.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Signal {

  private LocalDateTime localDateTime;
  private Trend trend;

  /**
   * Instantiates a new Signal.
   *
   * @param localDateTime the local date time
   * @param trend         the trend
   */
  public Signal(@NotNull LocalDateTime localDateTime, Trend trend) {
    this.localDateTime = localDateTime;
    this.trend = trend;
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
   * Sets local date time.
   *
   * @param localDateTime the local date time
   */
  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  /**
   * Gets trend.
   *
   * @return the trend
   */
  public Trend getTrend() {
    return trend;
  }

  /**
   * Sets trend.
   *
   * @param trend the trend
   */
  public void setTrend(Trend trend) {
    this.trend = trend;
  }
}
