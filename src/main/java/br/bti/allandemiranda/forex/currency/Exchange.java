package br.bti.allandemiranda.forex.currency;

import java.time.DayOfWeek;
import java.util.InputMismatchException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The type Currency exchange.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Exchange extends Pair {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String VALID_NUMBER = "need be a valid number";
  private static final String BE_GREATER_ZERO = "need be greater than zero";
  private static final String BE_NUMBER = "need be a number";
  private static final String SPREED = "Spreed";
  private static final String DIGITS_NAME = "Digits";
  private static final String SWAP_LONG = "Swap Long";
  private static final String SWAP_SHORT = "Swap Short";
  private static final String SWAP_THREE_DAYS = "Swap Three Days";

  private int spread;               //! In points
  private int digits;               //! In digits
  private Pair pair;
  private double swapLong;          //! In points
  private double swapShort;         //! In points
  private DayOfWeek swapThreeDays;

  /**
   * Instantiates a new Exchange.
   *
   * @param spread        the spread
   * @param digits        the digits
   * @param base          the base
   * @param quote         the quote
   * @param swapLong      the swap long
   * @param swapShort     the swap short
   * @param swapThreeDays the swap three days
   */
  public Exchange(int spread, int digits, @NotNull Currency base, @NotNull Currency quote,
      double swapLong, double swapShort, @NotNull DayOfWeek swapThreeDays) {
    super(base, quote);
    setSpread(spread);
    setDigits(digits);
    setCurrencyPair(pair);
    setSwapLong(swapLong);
    setSwapShort(swapShort);
    setSwapThreeDays(swapThreeDays);
  }

  /**
   * Instantiates a new Exchange.
   *
   * @param spread        the spread
   * @param digits        the digits
   * @param base          the base
   * @param quote         the quote
   * @param swapLong      the swap long
   * @param swapShort     the swap short
   * @param swapThreeDays the swap three days
   */
  public Exchange(@NotNull String spread, @NotNull String digits, @NotNull String base,
      @NotNull String quote, @NotNull String swapLong, @NotNull String swapShort,
      @NotNull String swapThreeDays) {
    super(base, quote);
    setSpread(spread);
    setDigits(digits);
    setCurrencyPair(pair);
    setSwapLong(swapLong);
    setSwapShort(swapShort);
    setSwapThreeDays(swapThreeDays);
  }

  /**
   * Gets spread.
   *
   * @return the spread
   */
  public int getSpread() {
    return spread;
  }

  /**
   * Sets spread.
   *
   * @param spread the spread
   */
  private void setSpread(int spread) {
    if (Math.abs(spread) == spread) {
      this.spread = spread;
    } else {
      throw new InputMismatchException(SPREED + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Sets spread.
   *
   * @param spread the spread
   */
  private void setSpread(@NotNull String spread) {
    if (!spread.isBlank()) {
      try {
        setSpread(Integer.parseInt(spread));
      } catch (NumberFormatException e) {
        throw new NumberFormatException(SPREED + " " + VALID_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(SPREED + " " + BE_NUMBER);
    }
  }

  /**
   * Gets digits.
   *
   * @return the digits
   */
  public int getDigits() {
    return digits;
  }

  /**
   * Sets digits.
   *
   * @param digits the digits
   */
  private void setDigits(int digits) {
    if (digits > 0) {
      this.digits = digits;
    } else {
      throw new InputMismatchException(DIGITS_NAME + " " + BE_GREATER_ZERO);
    }
  }

  /**
   * Sets digits.
   *
   * @param digits the digits
   */
  private void setDigits(@NotNull String digits) {
    if (!digits.isBlank()) {
      try {
        setDigits(Integer.parseInt(digits));
      } catch (NumberFormatException e) {
        throw new NumberFormatException(DIGITS_NAME + " " + VALID_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(DIGITS_NAME + " " + BE_NUMBER);
    }
  }

  /**
   * Gets currency pair.
   *
   * @return the currency pair
   */
  public Pair getCurrencyPair() {
    return pair;
  }

  /**
   * Sets currency pair.
   *
   * @param pair the currency pair
   */
  private void setCurrencyPair(@NotNull Pair pair) {
    this.pair = pair;
  }

  /**
   * Gets swap long.
   *
   * @return the swap long
   */
  public double getSwapLong() {
    return swapLong;
  }

  /**
   * Sets swap long.
   *
   * @param swapLong the swap long
   */
  private void setSwapLong(double swapLong) {
    this.swapLong = swapLong;
  }

  /**
   * Sets swap long.
   *
   * @param swapLong the swap long
   */
  private void setSwapLong(@NotNull String swapLong) {
    if (!swapLong.isBlank()) {
      try {
        setSwapLong(Double.parseDouble(swapLong));
      } catch (NumberFormatException e) {
        throw new NumberFormatException(SWAP_LONG + " " + VALID_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(SWAP_LONG + " " + BE_NUMBER);
    }
  }

  /**
   * Gets swap short.
   *
   * @return the swap short
   */
  public double getSwapShort() {
    return swapShort;
  }

  /**
   * Sets swap short.
   *
   * @param swapShort the swap short
   */
  private void setSwapShort(double swapShort) {
    this.swapShort = swapShort;
  }

  /**
   * Sets swap short.
   *
   * @param swapShort the swap short
   */
  private void setSwapShort(@NotNull String swapShort) {
    if (!swapShort.isBlank()) {
      try {
        setSwapShort(Double.parseDouble(swapShort));
      } catch (NumberFormatException e) {
        throw new NumberFormatException(SWAP_SHORT + " " + VALID_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(SWAP_SHORT + " " + BE_NUMBER);
    }
  }

  /**
   * Gets swap three days.
   *
   * @return the swap three days
   */
  public @NotNull DayOfWeek getSwapThreeDays() {
    return swapThreeDays;
  }

  /**
   * Sets swap three days.
   *
   * @param swapThreeDays the swap three days
   */
  private void setSwapThreeDays(@NotNull DayOfWeek swapThreeDays) {
    this.swapThreeDays = swapThreeDays;
  }

  /**
   * Sets swap three days.
   *
   * @param swapThreeDays the swap three days
   */
  private void setSwapThreeDays(@NotNull String swapThreeDays) {
    if (!swapThreeDays.isBlank()) {
      try {
        setSwapThreeDays(DayOfWeek.valueOf(swapThreeDays));
      } catch (IllegalArgumentException e) {
        switch (swapThreeDays) {
          case "1", "MONDAY", "monday", "Monday" -> setSwapThreeDays(DayOfWeek.MONDAY);
          case "2", "TUESDAY", "tuesday", "Tuesday" -> setSwapThreeDays(DayOfWeek.TUESDAY);
          case "3", "WEDNESDAY", "wednesday", "Wednesday" -> setSwapThreeDays(DayOfWeek.WEDNESDAY);
          case "4", "THURSDAY", "thursday", "Thursday" -> setSwapThreeDays(DayOfWeek.THURSDAY);
          case "5", "FRIDAY", "friday", "Friday" -> setSwapThreeDays(DayOfWeek.FRIDAY);
          case "6", "SATURDAY", "saturday", "Saturday" -> setSwapThreeDays(DayOfWeek.SATURDAY);
          case "7", "SUNDAY", "sunday", "Sunday" -> setSwapThreeDays(DayOfWeek.SUNDAY);
          default -> throw new IllegalArgumentException(SWAP_THREE_DAYS + " need be a valid week name");
        }
      }
    } else {
      throw new IllegalArgumentException(SWAP_SHORT + " " + BE_NUMBER);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Exchange that = (Exchange) o;
    return spread == that.spread && digits == that.digits && Double.compare(that.swapLong, swapLong) == 0
        && Double.compare(that.swapShort, swapShort) == 0 && pair.equals(that.pair)
        && swapThreeDays == that.swapThreeDays;
  }

  @Override
  public int hashCode() {
    return Objects.hash(spread, digits, pair, swapLong, swapShort, swapThreeDays);
  }

  @Override
  public String toString() {
    return "Exchange{" + "spread=" + spread + ", digits=" + digits + ", pair=" + pair + ", swapLong="
        + swapLong + ", swapShort=" + swapShort + ", swapThreeDays=" + swapThreeDays + '}';
  }
}