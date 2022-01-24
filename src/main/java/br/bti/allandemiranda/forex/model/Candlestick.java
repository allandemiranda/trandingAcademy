package br.bti.allandemiranda.forex.model;

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
  private CurrencyExchange currencyExchange;

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice        the open price
   * @param closePrice       the close price
   * @param lowPrice         the low price
   * @param highPrice        the high price
   * @param localDateTime    the local date time
   * @param currencyExchange the currency exchange
   */
  public Candlestick(Double openPrice, Double closePrice, Double lowPrice, Double highPrice, LocalDateTime localDateTime,
      CurrencyExchange currencyExchange) {
    setOpenPrice(openPrice);
    setClosePrice(closePrice);
    setLowPrice(lowPrice);
    setHighPrice(highPrice);
    setLocalDateTime(localDateTime);
    setCurrencyExchange(currencyExchange);
  }

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice        the open price
   * @param closePrice       the close price
   * @param lowPrice         the low price
   * @param highPrice        the high price
   * @param localDateTime    the local date time
   * @param currencyExchange the currency exchange
   */
  public Candlestick(Double openPrice, Double closePrice, Double lowPrice, Double highPrice, String localDateTime,
      CurrencyExchange currencyExchange) {
    setOpenPrice(openPrice);
    setClosePrice(closePrice);
    setLowPrice(lowPrice);
    setHighPrice(highPrice);
    setLocalDateTime(localDateTime);
    setCurrencyExchange(currencyExchange);
  }

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice        the open price
   * @param closePrice       the close price
   * @param lowPrice         the low price
   * @param highPrice        the high price
   * @param localDateTime    the local date time
   * @param currencyExchange the currency exchange
   */
  public Candlestick(String openPrice, String closePrice, String lowPrice, String highPrice, String localDateTime,
      CurrencyExchange currencyExchange) {
    setOpenPrice(openPrice);
    setClosePrice(closePrice);
    setLowPrice(lowPrice);
    setHighPrice(highPrice);
    setLocalDateTime(localDateTime);
    setCurrencyExchange(currencyExchange);
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
  private void setOpenPrice(String openPrice) {
    if (openPrice != null) {
      double temp;
      try {
        temp = Double.parseDouble(openPrice);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Open Price not accept");
      }
      setOpenPrice(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Open Price to this Candlestick");
    }
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
  private void setClosePrice(String closePrice) {
    if (closePrice != null) {
      double temp;
      try {
        temp = Double.parseDouble(closePrice);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Close Price not accept");
      }
      setClosePrice(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Close Price to this Candlestick");
    }
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
    if (lowPrice != null) {
      double temp;
      try {
        temp = Double.parseDouble(lowPrice);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Low Price not accept");
      }
      setLowPrice(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Low Price to this Candlestick");
    }
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
    if (highPrice != null) {
      double temp;
      try {
        temp = Double.parseDouble(highPrice);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Low Price not accept");
      }
      setHighPrice(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Low Price to this Candlestick");
    }
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
   * Sets local date time.
   *
   * @param localDateTime the local date time (yyyy-MM-dd kk:mm)
   */
  private void setLocalDateTime(String localDateTime) {
    if (localDateTime == null) {
      throw new NullPointerException("Can't set a NULL Local Date Time to Candlestick");
    } else {
      setLocalDateTime(LocalDateTime.parse(localDateTime, getDateTimeFormatter()));
    }
  }

  /**
   * Gets date time formatter.
   *
   * @return the date time formatter
   */
  private DateTimeFormatter getDateTimeFormatter() {
    return DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER);
  }

  /**
   * Gets price action.
   *
   * @return the price action
   */
  public PriceAction getPriceAction() {
    if (getOpenPrice() > getClosePrice()) {
      return PriceAction.LOW;
    } else {
      if (getOpenPrice() < getClosePrice()) {
        return PriceAction.HIGH;
      } else {
        return PriceAction.NEUTRAL;
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
   * Gets currency exchange.
   *
   * @return the currency exchange
   */
  public CurrencyExchange getCurrencyExchange() {
    return currencyExchange;
  }

  /**
   * Sets currency exchange.
   *
   * @param currencyExchange the currency exchange
   */
  private void setCurrencyExchange(CurrencyExchange currencyExchange) {
    if (currencyExchange == null) {
      throw new NullPointerException("Can't set a NULL Currency to Candlestick");
    } else {
      this.currencyExchange = currencyExchange;
    }
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
   *
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
}
