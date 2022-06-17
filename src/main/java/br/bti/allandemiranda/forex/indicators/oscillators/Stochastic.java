package br.bti.allandemiranda.forex.indicators.oscillators;

import br.bti.allandemiranda.forex.indicators.Indicator;
import java.time.LocalDateTime;

/**
 * The type Stochastic.
 */
public class Stochastic extends Indicator {

  //! out
  private final Double k;
  private final Double slow;
  //! the stochastic - DateTime -> K, Slow

  //! in
  private final int kPeriod;
  private final int dPeriod;
  private final int slowing;

  /**
   * Instantiates a new Stochastic.
   *
   * @param localDateTime the local date time
   * @param k             the k
   * @param slow          the slow
   * @param kPeriod       the k period
   * @param dPeriod       the d period
   * @param slowing       the slowing
   */
  public Stochastic(LocalDateTime localDateTime, Double k, Double slow, int kPeriod, int dPeriod,
      int slowing) {
    super(localDateTime);
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
  public Double getK() {
    return k;
  }

  /**
   * Gets slow.
   *
   * @return the slow
   */
  public Double getSlow() {
    return slow;
  }

  /**
   * Gets period.
   *
   * @return the period
   */
  public int getkPeriod() {
    return kPeriod;
  }

  /**
   * Gets period.
   *
   * @return the period
   */
  public int getdPeriod() {
    return dPeriod;
  }

  /**
   * Gets slowing.
   *
   * @return the slowing
   */
  public int getSlowing() {
    return slowing;
  }

  @Override
  public String getConfiguration() {
    return "(" + getkPeriod() + ", " + getdPeriod() + ", " + getSlowing() + ")";
  }

  @Override
  public String getValues() {
    return "Stochastic{" + "k=" + getK() + ", slow=" + getSlow() + '}';
  }

  @Override
  public String toString() {
    return "Stochastic{" + "time=" + getLocalDateTime() + ", k=" + k + ", slow=" + slow + ", kPeriod="
        + kPeriod + ", dPeriod=" + dPeriod + ", slowing=" + slowing + '}';
  }
}
