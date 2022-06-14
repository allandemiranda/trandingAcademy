package br.bti.allandemiranda.forex.oscillators;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import java.time.LocalDateTime;

/**
 * The type Oscillator.
 */
public abstract class Oscillator implements Configuration, Values {

  private Signal signal;
  private final LocalDateTime localDateTime;
  private final TimeFrame timeFrame;

  /**
   * Instantiates a new Oscillator.
   *
   * @param localDateTime the local date time
   * @param timeFrame     the time frame
   */
  protected Oscillator(LocalDateTime localDateTime, TimeFrame timeFrame) {
    this.signal = Signal.NON;
    this.localDateTime = localDateTime;
    this.timeFrame = timeFrame;
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
   * Gets time frame.
   *
   * @return the time frame
   */
  public TimeFrame getTimeFrame() {
    return timeFrame;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return "[" + getTimeFrame() + "] " + getClass().getSimpleName() + getConfiguration();
  }
}
