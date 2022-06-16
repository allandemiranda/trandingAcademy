package br.bti.allandemiranda.forex.indicators;

import java.time.LocalDateTime;

/**
 * The type Indicator.
 */
public abstract class Indicator implements Configuration, Values {

  private Signal signal;
  private final LocalDateTime localDateTime;

  /**
   * Instantiates a new Indicator.
   *
   * @param localDateTime the local date time
   */
  protected Indicator(LocalDateTime localDateTime) {
    this.signal = Signal.NON;
    this.localDateTime = localDateTime;
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
   * Gets signal.
   *
   * @return the signal
   */
  public Signal getSignal() {
    return signal;
  }

  /**
   * Sets sinal.
   *
   * @param signal the signal
   */
  public void setSinal(Signal signal) {
    this.signal = signal;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return getClass().getSimpleName() + getConfiguration();
  }
}
