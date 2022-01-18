package br.bti.allandemiranda.model;

import static br.bti.allandemiranda.model.PriceAction.HIGH;
import static br.bti.allandemiranda.model.PriceAction.LOW;
import static br.bti.allandemiranda.model.PriceAction.NEUTRAL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The type Candlestick.
 */
public class Candlestick {

  private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd kk:mm";
  private static final double NEUTRAL_VALUE = 0.0;
  private Double openPrice;
  private Double closePrice;
  private Double lowPrice;
  private Double highPrice;
  private LocalDateTime localDateTime;

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice  the open price
   * @param closePrice the close price
   * @param lowPrice   the low price
   * @param highPrice  the high price
   */
  public Candlestick(Double openPrice, Double closePrice, Double lowPrice, Double highPrice,
      LocalDateTime localDateTime) {
    setOpenPrice(openPrice);
    setClosePrice(closePrice);
    setLowPrice(lowPrice);
    setHighPrice(highPrice);
    setLocalDateTime(localDateTime);
  }

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice     the open price
   * @param closePrice    the close price
   * @param lowPrice      the low price
   * @param highPrice     the high price
   * @param localDateTime the local date time
   */
  public Candlestick(Double openPrice, Double closePrice, Double lowPrice, Double highPrice,
      String localDateTime) {
    setOpenPrice(openPrice);
    setClosePrice(closePrice);
    setLowPrice(lowPrice);
    setHighPrice(highPrice);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
    setLocalDateTime(LocalDateTime.parse(localDateTime, formatter));
  }

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice     the open price
   * @param closePrice    the close price
   * @param lowPrice      the low price
   * @param highPrice     the high price
   * @param localDateTime the local date time
   */
  public Candlestick(String openPrice, String closePrice, String lowPrice, String highPrice,
      String localDateTime) {
    setOpenPrice(openPrice);
    setClosePrice(closePrice);
    setLowPrice(lowPrice);
    setHighPrice(highPrice);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
    setLocalDateTime(LocalDateTime.parse(localDateTime, formatter));
  }

  /**
   * Gets open price.
   *
   * @return the open price
   */
  public Double getOpenPrice() {
    return openPrice;
  }

  /**
   * Sets open price.
   *
   * @param openPrice the open price
   */
  private void setOpenPrice(Double openPrice) {
    if (openPrice == null) {
      throw new NullPointerException("Can't set a NULL Open Price to Candlestick");
    } else {
      this.openPrice = openPrice;
    }
  }

  /**
   * Sets open price.
   *
   * @param openPrice the open price
   */
  private void setOpenPrice(String openPrice) throws NumberFormatException {
    this.openPrice = Double.parseDouble(openPrice);
  }

  /**
   * Gets close price.
   *
   * @return the close price
   */
  public Double getClosePrice() {
    return closePrice;
  }

  /**
   * Sets close price.
   *
   * @param closePrice the close price
   */
  private void setClosePrice(Double closePrice) {
    if (closePrice == null) {
      throw new NullPointerException("Can't set a NULL Close Price to Candlestick");
    } else {
      this.closePrice = closePrice;
    }
  }

  /**
   * Sets close price.
   *
   * @param closePrice the close price
   */
  private void setClosePrice(String closePrice) throws NumberFormatException {
    this.closePrice = Double.parseDouble(closePrice);
  }

  /**
   * Gets low price.
   *
   * @return the low price
   */
  public Double getLowPrice() {
    return lowPrice;
  }

  /**
   * Sets low price.
   *
   * @param lowPrice the low price
   */
  private void setLowPrice(Double lowPrice) {
    if (lowPrice == null) {
      throw new NullPointerException("Can't set a NULL Low Price to Candlestick");
    } else {
      this.lowPrice = lowPrice;
    }
  }

  /**
   * Sets low price.
   *
   * @param lowPrice the low price
   */
  private void setLowPrice(String lowPrice) {
    this.lowPrice = Double.parseDouble(lowPrice);
  }

  /**
   * Gets high price.
   *
   * @return the high price
   */
  public Double getHighPrice() {
    return highPrice;
  }

  /**
   * Sets high price.
   *
   * @param highPrice the high price
   */
  private void setHighPrice(Double highPrice) {
    if (highPrice == null) {
      throw new NullPointerException("Can't set a NULL High Price to Candlestick");
    } else {
      this.highPrice = highPrice;
    }
  }

  /**
   * Sets high price.
   *
   * @param highPrice the high price
   */
  private void setHighPrice(String highPrice) {
    this.highPrice = Double.parseDouble(highPrice);
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
   * Sets local date time.
   *
   * @param localDateTime the local date time
   */
  private void setLocalDateTime(LocalDateTime localDateTime) {
    if (localDateTime == null) {
      throw new NullPointerException("Can't set a NULL Local Date Time to Candlestick");
    } else {
      this.localDateTime = localDateTime;
    }
  }

  /**
   * Gets price action.
   *
   * @return the price action
   */
  public PriceAction getPriceAction() {
    if (getOpenPrice() > getClosePrice()) {
      return LOW;
    } else {
      if (getOpenPrice() < getClosePrice()) {
        return HIGH;
      } else {
        return NEUTRAL;
      }
    }
  }

  /**
   * Gets body size.
   *
   * @return the body size
   */
  public Double getBodySize() {
    return switch (getPriceAction()) {
      case NEUTRAL -> NEUTRAL_VALUE;
      case HIGH -> getClosePrice() - getOpenPrice();
      case LOW -> getOpenPrice() - getClosePrice();
    };
  }

  /**
   * Gets upper shadow size.
   *
   * @return the upper shadow size
   */
  public Double getUpperShadowSize() {
    return switch (getPriceAction()) {
      case HIGH, NEUTRAL -> getHighPrice() - getClosePrice();
      case LOW -> getHighPrice() - getOpenPrice();
    };
  }

  /**
   * Gets lower shadow size.
   *
   * @return the lower shadow size
   */
  public Double getLowerShadowSize() {
    return switch (getPriceAction()) {
      case HIGH, NEUTRAL -> getOpenPrice() - getLowPrice();
      case LOW -> getClosePrice() - getOpenPrice();
    };
  }

  /**
   * Gets full size.
   *
   * @return the full size
   */
  public Double getFullSize() {
    return getUpperShadowSize() + getBodySize() + getLowerShadowSize();
  }

  /**
   * Hash candlestick size hash.
   *
   * @return the hash
   */
  public int hashCandlestickSize() {
    return hashCandlestickSize(false);
  }

  /**
   * Hash candlestick size.
   *
   * @param includeFullSize the include full size
   * @return the hash
   */
  public int hashCandlestickSize(Boolean includeFullSize) {
    if (includeFullSize) {
      return Objects.hash(getUpperShadowSize(), getBodySize(), getLowerShadowSize(), getFullSize());
    } else {
      return Objects.hash(getUpperShadowSize(), getBodySize(), getLowerShadowSize());
    }
  }

  /**
   * Hash candlestick position of the price.
   *
   * @return the hash
   */
  public int hashCandlestickPositionsPrice() {
    return Objects.hash(openPrice, closePrice, lowPrice, highPrice);
  }

  /**
   * Hash candlestick position open and close of the price.
   *
   * @return the hash
   */
  public int hashCandlestickPrice() {
    return Objects.hash(openPrice, closePrice);
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
    return openPrice.equals(that.openPrice) && closePrice.equals(that.closePrice)
        && lowPrice.equals(that.lowPrice) && highPrice.equals(
        that.highPrice) && localDateTime.equals(that.localDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(openPrice, closePrice, lowPrice, highPrice, localDateTime);
  }

  @Override
  public String toString() {
    return "Candlestick{" +
        "openPrice=" + openPrice +
        ", closePrice=" + closePrice +
        ", lowPrice=" + lowPrice +
        ", highPrice=" + highPrice +
        ", localDateTime=" + localDateTime +
        '}';
  }
}
