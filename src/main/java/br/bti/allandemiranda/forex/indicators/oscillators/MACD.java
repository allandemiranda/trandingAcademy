package br.bti.allandemiranda.forex.indicators.oscillators;

import br.bti.allandemiranda.forex.indicators.Indicator;
import java.time.LocalDateTime;

/**
 * The type Macd.
 */
public class MACD extends Indicator {

  //! out
  private final Double histogram;
  private final Double macd;
  private final Double signal;
  //! the MACD List. DateTime -> Histogram - MACD - Signal

  //! in
  private final int fastEMA;
  private final int slowEMA;
  private final int macdSMA;

  /**
   * Instantiates a new Macd.
   *
   * @param localDateTime the local date time
   * @param histogram     the histogram
   * @param macd          the macd
   * @param signal        the signal
   * @param fastEMA       the fast ema
   * @param slowEMA       the slow ema
   * @param macdSMA       the macd sma
   */
  public MACD(LocalDateTime localDateTime, Double histogram, Double macd, Double signal, int fastEMA,
      int slowEMA, int macdSMA) {
    super(localDateTime);
    this.histogram = histogram;
    this.macd = macd;
    this.signal = signal;
    this.fastEMA = fastEMA;
    this.slowEMA = slowEMA;
    this.macdSMA = macdSMA;
  }

  /**
   * Gets histogram.
   *
   * @return the histogram
   */
  public Double getHistogram() {
    return histogram;
  }

  /**
   * Gets macd.
   *
   * @return the macd
   */
  public Double getMacd() {
    return macd;
  }

  /**
   * Gets signal value.
   *
   * @return the signal value
   */
  public Double getSignalValue() {
    return signal;
  }

  /**
   * Gets fast ema.
   *
   * @return the fast ema
   */
  public int getFastEMA() {
    return fastEMA;
  }

  /**
   * Gets slow ema.
   *
   * @return the slow ema
   */
  public int getSlowEMA() {
    return slowEMA;
  }

  /**
   * Gets macd sma.
   *
   * @return the macd sma
   */
  public int getMacdSMA() {
    return macdSMA;
  }

  @Override
  public String getValues() {
    return "MACD{" + "histogram=" + getHistogram() + ", macd=" + getMacd() + ", signal="
        + getSignalValue() + "}";
  }

  @Override
  public String getConfiguration() {
    return "(" + getFastEMA() + ", " + getSlowEMA() + ", " + getMacdSMA() + ")";
  }

  @Override
  public String toString() {
    return "MACD{" + "time=" + getLocalDateTime() + ", histogram=" + histogram + ", macd=" + macd
        + ", signal=" + signal + ", fastEMA=" + fastEMA + ", slowEMA=" + slowEMA + ", macdSMA=" + macdSMA
        + "}";
  }
}
