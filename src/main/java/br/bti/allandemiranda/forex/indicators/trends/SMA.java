package br.bti.allandemiranda.forex.indicators.trends;

import br.bti.allandemiranda.forex.chart.Point;
import java.time.LocalDateTime;

/**
 * The Simple Moving Average (SMA)
 */
public class SMA extends Point {

  /**
   * Instantiates a new Point.
   *
   * @param localDateTime the local date time
   * @param value         the value
   */
  public SMA(LocalDateTime localDateTime, Double value) {
    super(localDateTime, value);
  }
}
