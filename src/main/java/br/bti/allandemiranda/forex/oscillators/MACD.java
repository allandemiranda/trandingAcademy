package br.bti.allandemiranda.forex.oscillators;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import java.time.LocalDateTime;

/**
 * The type Macd.
 */
public class MACD extends Oscillator {

  private final double histogram;
  private final double macdValue;
  private final double signal;

  private final int fastEMA;
  private final int slowEMA;
  private final int macdSMA;

  private MACD(LocalDateTime localDateTime, TimeFrame timeFrame, double histogram, double macd,
      double signal, int fastEMA, int slowEMA, int macdSMA) {
    super(localDateTime, timeFrame);
    this.histogram = histogram;
    this.macdValue = macd;
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
  public double getHistogram() {
    return histogram;
  }

  /**
   * Gets macd.
   *
   * @return the macd
   */
  public double getMacd() {
    return macdValue;
  }

  /**
   * Gets signal value.
   *
   * @return the signal value
   */
  public double getSignalValue() {
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
    return "MACD{" + "histogram=" + histogram + ", macd=" + macdValue + ", signal=" + signal
        + ", fastEMA=" + fastEMA + ", slowEMA=" + slowEMA + ", macdSMA=" + macdSMA + "}";
  }
}
