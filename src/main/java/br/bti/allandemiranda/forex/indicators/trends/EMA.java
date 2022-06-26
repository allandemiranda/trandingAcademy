package br.bti.allandemiranda.forex.indicators.trends;

import br.bti.allandemiranda.forex.chart.Point;
import java.time.LocalDateTime;

/**
 * The Exponential Moving Average (EMA)
 */
public class EMA extends Point {

  /**
   * Instantiates a new Ema.
   *
   * @param localDateTime the local date time
   * @param ema           the ema
   */
  public EMA(LocalDateTime localDateTime, Double ema) {
    super(localDateTime, ema);
  }
}
