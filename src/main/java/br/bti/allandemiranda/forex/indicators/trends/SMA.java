package br.bti.allandemiranda.forex.indicators.trends;

import br.bti.allandemiranda.forex.indicators.Indicator;
import java.time.LocalDateTime;

/**
 * The Simple Moving Average (SMA)
 */
public class SMA extends Indicator {

  //! out
  private final Double sma;

  //! in
  private final int periods;

  /**
   * Instantiates a new Sma.
   *
   * @param localDateTime the local date time
   * @param sma           the sma
   * @param periods       the periods
   */
  private SMA(LocalDateTime localDateTime, Double sma, int periods) {
    super(localDateTime);
    this.sma = sma;
    this.periods = periods;
  }

  /**
   * Gets sma.
   *
   * @return the sma
   */
  private Double getSma() {
    return sma;
  }

  /**
   * Gets periods.
   *
   * @return the periods
   */
  private int getPeriods() {
    return periods;
  }

  @Override
  public String getConfiguration() {
    return "(" + getPeriods() + ")";
  }

  @Override
  public String getValues() {
    return "SMA{" + "sma=" + getSma() + '}';
  }

  @Override
  public String toString() {
    return "SMA{" + "sma=" + sma + ", periods=" + periods + '}';
  }
}
