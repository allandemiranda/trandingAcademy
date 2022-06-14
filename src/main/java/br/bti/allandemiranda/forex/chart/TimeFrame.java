package br.bti.allandemiranda.forex.chart;

/**
 * The enum Time frame.
 */
public enum TimeFrame {
  M1(1),
  M5(5),
  M15(15),
  M30(30),
  H1(60),
  H4(240),
  D1(1440),
  W1(10080),
  MN(302400);

  private final int minutes;

  /**
   * Instantiates a new Time frame.
   *
   * @param minutes the minutes
   */
  TimeFrame(int minutes) {
    this.minutes = minutes;
  }

  /**
   * Gets minutes.
   *
   * @return the minutes
   */
  public int getMinutes() {
    return minutes;
  }
}
