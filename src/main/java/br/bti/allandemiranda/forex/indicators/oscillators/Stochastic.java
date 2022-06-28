package br.bti.allandemiranda.forex.indicators.oscillators;

import br.bti.allandemiranda.forex.chart.Point;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Stochastic.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Stochastic {

  private final Point k;
  private final Point slow;
  private final LocalDateTime localDateTime;

  /**
   * Instantiates a new Stochastic.
   *
   * @param localDateTime the local date time
   * @param k             the k
   * @param slow          the slow
   */
  public Stochastic(LocalDateTime localDateTime, Double k, Double slow) {
    this.k = new Point(localDateTime, k);
    this.slow = new Point(localDateTime, slow);
    this.localDateTime = localDateTime;
  }

  /**
   * Gets k.
   *
   * @return the k
   */
  public Point getK() {
    return k;
  }

  /**
   * Gets slow.
   *
   * @return the slow
   */
  public Point getSlow() {
    return slow;
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
    Stochastic that = (Stochastic) o;
    return Objects.equals(k, that.k) && Objects.equals(slow, that.slow);
  }

  @Override
  public int hashCode() {
    return Objects.hash(k, slow);
  }

  @Override
  public String toString() {
    return "Stochastic{" + "k=" + k + ", slow=" + slow + '}';
  }
}
