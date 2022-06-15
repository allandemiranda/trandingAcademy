package br.bti.allandemiranda.forex.indicators.oscillators;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import br.bti.allandemiranda.forex.indicators.Indicator;
import java.time.LocalDateTime;

/**
 * The type Stochastic.
 */
public class Stochastic extends Indicator {

  //! out
  private Double k;
  private Double slow;
  //! the stochastic - DateTime -> K, Slow

  //! in
  private int kPeriod;
  private int dPeriod;
  private int slowing;

  /**
   * Instantiates a new Stochastic.
   *
   * @param localDateTime the local date time
   * @param timeFrame     the time frame
   * @param k             the k
   * @param slow          the slow
   * @param kPeriod       the k period
   * @param dPeriod       the d period
   * @param slowing       the slowing
   */
  private Stochastic(LocalDateTime localDateTime, TimeFrame timeFrame, Double k, Double slow,
      int kPeriod, int dPeriod, int slowing) {
    super(localDateTime, timeFrame);
    this.k = k;
    this.slow = slow;
    this.kPeriod = kPeriod;
    this.dPeriod = dPeriod;
    this.slowing = slowing;
  }

  /**
   * Gets k.
   *
   * @return the k
   */
  private Double getK() {
    return k;
  }

  /**
   * Gets slow.
   *
   * @return the slow
   */
  private Double getSlow() {
    return slow;
  }

  /**
   * Gets period.
   *
   * @return the period
   */
  private int getkPeriod() {
    return kPeriod;
  }

  /**
   * Gets period.
   *
   * @return the period
   */
  private int getdPeriod() {
    return dPeriod;
  }

  /**
   * Gets slowing.
   *
   * @return the slowing
   */
  private int getSlowing() {
    return slowing;
  }

  @Override
  public String getConfiguration() {
    return "(" + getkPeriod() + ", " + getdPeriod() + ", " + getSlowing() + ")";
  }

  @Override
  public String getValues() {
    return "Stochastic{" + "k=" + k + ", slow=" + slow + '}';
  }

  @Override
  public String toString() {
    return "Stochastic{" + "k=" + k + ", slow=" + slow + ", kPeriod=" + kPeriod + ", dPeriod=" + dPeriod
        + ", slowing=" + slowing + '}';
  }
}
