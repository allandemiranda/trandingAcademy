package br.bti.allandemiranda.forex.indicators.trends;

import br.bti.allandemiranda.forex.indicators.Indicator;
import java.time.LocalDateTime;

/**
 * The Exponential Moving Average (EMA)
 */
public class EMA extends Indicator {

  //! out
  private final Double ema;

  //! in
  private final int periods;
  private final int smoothing; //! default value = 2

  /**
   * Instantiates a new Ema.
   *
   * @param localDateTime the local date time
   * @param ema           the ema
   * @param periods       the periods
   * @param smoothing     the smoothing
   */
  private EMA(LocalDateTime localDateTime, Double ema, int periods, int smoothing) {
    super(localDateTime);
    this.ema = ema;
    this.periods = periods;
    this.smoothing = smoothing;
  }

  /**
   * Instantiates a new Ema (default smoothing).
   *
   * @param localDateTime the local date time
   * @param ema           the ema
   * @param periods       the periods
   */
  private EMA(LocalDateTime localDateTime, Double ema, int periods) {
    super(localDateTime);
    this.ema = ema;
    this.periods = periods;
    this.smoothing = 2;
  }

  /**
   * Gets ema.
   *
   * @return the ema
   */
  private Double getEma() {
    return ema;
  }

  /**
   * Gets periods.
   *
   * @return the periods
   */
  private int getPeriods() {
    return periods;
  }

  /**
   * Gets smoothing.
   *
   * @return the smoothing
   */
  private int getSmoothing() {
    return smoothing;
  }

  @Override
  public String getConfiguration() {
    return "[" + getSmoothing() + "](" + getEma() + ")";
  }

  @Override
  public String getValues() {
    if (getSmoothing() == 2) {
      return "EMA{" + "ema=" + getEma() + '}';
    } else {
      return "EMA[" + getSmoothing() + "]{" + "ema=" + getEma() + '}';
    }
  }

  @Override
  public String toString() {
    return "EMA{" + "ema=" + ema + ", periods=" + periods + ", smoothing=" + smoothing + '}';
  }
}
