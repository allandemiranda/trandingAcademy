package br.bti.allandemiranda.forex.candlestick;

import br.bti.allandemiranda.forex.currency.Pair;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Objects;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The type Candlestick.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Candlestick {

  private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd kk:mm";
  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String VALID_NUMBER = "need be a valid number";
  private static final String BE_NUMBER = "need be a number";
  private static final String LOCAL_DATE_TIME = "Local Date Time";
  private static final String VALID_DATE = "need be a valid date";
  private static final String BE_DATE = "need be a date";
  private static final String HIGH_PRICE = "High Price";
  private static final String LOW_PRICE = "Low Price";
  private static final String CLOSE_PRICE = "Close Price";
  private static final String OPEN_PRICE = "Open Price";
  private static final String VOLUME_NAME = "Volume";

  private double openPrice;
  private double closePrice;
  private double lowPrice;
  private double highPrice;
  private double volume;
  private LocalDateTime localDateTime;
  private Pair pair;

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice     the open price
   * @param closePrice    the close price
   * @param lowPrice      the low price
   * @param highPrice     the high price
   * @param volume        the volume
   * @param localDateTime the local date time
   * @param pair          the currency pair
   */
  public Candlestick(double openPrice, double closePrice, double lowPrice, double highPrice,
      double volume, @NotNull LocalDateTime localDateTime, @NotNull Pair pair) {
    setCandlestick(openPrice, closePrice, lowPrice, highPrice);
    setLocalDateTime(localDateTime);
    setCurrencyPair(pair);
    setVolume(volume);
  }

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice     the open price
   * @param closePrice    the close price
   * @param lowPrice      the low price
   * @param highPrice     the high price
   * @param localDateTime the local date time
   * @param pair          the currency pair
   */
  public Candlestick(@NotNull String openPrice, @NotNull String closePrice, @NotNull String lowPrice,
      @NotNull String highPrice, @NotNull String volume, @NotNull String localDateTime,
      @NotNull Pair pair) {
    setCandlestick(openPrice, closePrice, lowPrice, highPrice);
    setLocalDateTime(localDateTime);
    setCurrencyPair(pair);
    setVolume(volume);
  }

  /**
   * Gets open price.
   *
   * @return the open price
   */
  public double getOpenPrice() {
    return openPrice;
  }

  /**
   * Sets open price.
   *
   * @param openPrice the open price
   */
  private void setOpenPrice(double openPrice) {
    if (Math.abs(openPrice) == openPrice) {
      this.openPrice = openPrice;
    } else {
      throw new InputMismatchException(OPEN_PRICE + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Sets open price.
   *
   * @param openPrice the open price
   */
  private void setOpenPrice(@NotNull String openPrice) {
    if (!openPrice.isBlank()) {
      try {
        setOpenPrice(Double.parseDouble(openPrice));
      } catch (NumberFormatException e) {
        throw new NumberFormatException(OPEN_PRICE + " " + VALID_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(OPEN_PRICE + " " + BE_NUMBER);
    }
  }

  /**
   * Gets close price.
   *
   * @return the close price
   */
  public double getClosePrice() {
    return closePrice;
  }

  /**
   * Sets close price.
   *
   * @param closePrice the close price
   */
  private void setClosePrice(double closePrice) {
    if (Math.abs(closePrice) == closePrice) {
      this.closePrice = closePrice;
    } else {
      throw new InputMismatchException(CLOSE_PRICE + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Sets close price.
   *
   * @param closePrice the close price
   */
  private void setClosePrice(@NotNull String closePrice) {
    if (!closePrice.isBlank()) {
      try {
        setClosePrice(Double.parseDouble(closePrice));
      } catch (NumberFormatException e) {
        throw new NumberFormatException(CLOSE_PRICE + " " + VALID_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(CLOSE_PRICE + " " + BE_NUMBER);
    }
  }

  /**
   * Gets low price.
   *
   * @return the low price
   */
  public double getLowPrice() {
    return lowPrice;
  }

  /**
   * Sets low price.
   *
   * @param lowPrice the low price
   */
  private void setLowPrice(double lowPrice) {
    if (Math.abs(lowPrice) == lowPrice) {
      this.lowPrice = lowPrice;
    } else {
      throw new InputMismatchException(LOW_PRICE + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Sets low price.
   *
   * @param lowPrice the low price
   */
  private void setLowPrice(@NotNull String lowPrice) {
    if (!lowPrice.isBlank()) {
      try {
        setLowPrice(Double.parseDouble(lowPrice));
      } catch (NumberFormatException e) {
        throw new NumberFormatException(LOW_PRICE + " " + VALID_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(LOW_PRICE + " " + BE_NUMBER);
    }
  }

  /**
   * Gets high price.
   *
   * @return the high price
   */
  public double getHighPrice() {
    return highPrice;
  }

  /**
   * Sets high price.
   *
   * @param highPrice the high price
   */
  private void setHighPrice(double highPrice) {
    if (Math.abs(highPrice) == highPrice) {
      this.highPrice = highPrice;
    } else {
      throw new InputMismatchException(HIGH_PRICE + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Sets high price.
   *
   * @param highPrice the high price
   */
  private void setHighPrice(@NotNull String highPrice) {
    if (!highPrice.isBlank()) {
      try {
        setHighPrice(Double.parseDouble(highPrice));
      } catch (NumberFormatException e) {
        throw new NumberFormatException(HIGH_PRICE + " " + VALID_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(HIGH_PRICE + " " + BE_NUMBER);
    }
  }

  /**
   * Gets local date time.
   *
   * @return the local date time
   */
  public @NotNull LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  /**
   * Sets local date time.
   *
   * @param localDateTime the local date time
   */
  private void setLocalDateTime(@NotNull LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  /**
   * Sets local date time.
   *
   * @param localDateTime the local date time
   */
  private void setLocalDateTime(@NotNull String localDateTime) {
    try {
      if (!localDateTime.isBlank()) {
        this.localDateTime = LocalDateTime.parse(localDateTime, getDateTimeFormatter());
      } else {
        throw new IllegalArgumentException(LOCAL_DATE_TIME + " " + BE_DATE);
      }
    } catch (DateTimeException e) {
      throw new DateTimeException(LOCAL_DATE_TIME + " " + VALID_DATE);
    }
  }

  /**
   * Gets volume.
   *
   * @return the volume
   */
  public double getVolume() {
    return volume;
  }

  /**
   * Sets volume.
   *
   * @param volume the volume
   */
  private void setVolume(double volume) {
    if (Math.abs(volume) == volume) {
      this.volume = volume;
    } else {
      throw new InputMismatchException(VOLUME_NAME + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Sets volume.
   *
   * @param volume the volume
   */
  private void setVolume(@NotNull String volume) {
    if (!volume.isBlank()) {
      try {
        setVolume(Integer.parseInt(volume));
      } catch (NumberFormatException e) {
        throw new NumberFormatException(VOLUME_NAME + " " + VALID_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(VOLUME_NAME + " " + BE_NUMBER);
    }
  }

  /**
   * Gets date time formatter.
   *
   * @return the date time formatter
   */
  @Contract(" -> new")
  private @NotNull DateTimeFormatter getDateTimeFormatter() {
    return DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
  }

  /**
   * Gets currency exchange.
   *
   * @return the currency exchange
   */
  public @NotNull Pair getCurrencyPair() {
    return pair;
  }

  /**
   * Sets currency exchange.
   *
   * @param pair the currency pair
   */
  private void setCurrencyPair(@NotNull Pair pair) {
    this.pair = pair;
  }

  /**
   * Sets candlestick.
   *
   * @param openPrice  the open price
   * @param closePrice the close price
   * @param lowPrice   the low price
   * @param highPrice  the high price
   */
  private void setCandlestick(double openPrice, double closePrice, double lowPrice, double highPrice) {
    setOpenPrice(openPrice);
    setClosePrice(closePrice);
    setLowPrice(lowPrice);
    setHighPrice(highPrice);
    isCandlestick();
  }

  /**
   * Sets candlestick.
   *
   * @param openPrice  the open price
   * @param closePrice the close price
   * @param lowPrice   the low price
   * @param highPrice  the high price
   */
  private void setCandlestick(@NotNull String openPrice, @NotNull String closePrice,
      @NotNull String lowPrice, @NotNull String highPrice) {
    setOpenPrice(openPrice);
    setClosePrice(closePrice);
    setLowPrice(lowPrice);
    setHighPrice(highPrice);
    isCandlestick();
  }

  /**
   * Is candlestick boolean.
   */
  private void isCandlestick() {
    if (getLowPrice() <= getHighPrice()) {
      if (getOpenPrice() >= getLowPrice() && getOpenPrice() <= getHighPrice()) {
        if (!(getClosePrice() >= getLowPrice() && getClosePrice() <= getHighPrice())) {
          throw new IllegalArgumentException(
              CLOSE_PRICE + " need be between the " + HIGH_PRICE + " and " + LOW_PRICE);
        }
      } else {
        throw new IllegalArgumentException(
            OPEN_PRICE + " need be between the " + HIGH_PRICE + " and " + LOW_PRICE);
      }
    } else {
      throw new IllegalArgumentException(LOW_PRICE + " need be less or equal a " + HIGH_PRICE);
    }
  }

  /**
   * Gets candlestick action.
   *
   * @return the candlestick action
   */
  public Action getCandlestickAction() {
    double value = getOpenPrice() - getClosePrice();
    if (value <= 0.0) {
      if (value == 0.0) {
        return Action.NEUTRAL;
      } else {
        return Action.UPPER;
      }
    } else {
      return Action.DOWN;
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
    Candlestick that = (Candlestick) o;
    return Double.compare(that.openPrice, openPrice) == 0
        && Double.compare(that.closePrice, closePrice) == 0
        && Double.compare(that.lowPrice, lowPrice) == 0
        && Double.compare(that.highPrice, highPrice) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(openPrice, closePrice, lowPrice, highPrice);
  }

  @Override
  public String toString() {
    return "Candlestick{" + "openPrice=" + openPrice + ", closePrice=" + closePrice + ", lowPrice="
        + lowPrice + ", highPrice=" + highPrice + ", volume=" + volume + ", localDateTime="
        + localDateTime + ", pair=" + pair + '}';
  }
}
