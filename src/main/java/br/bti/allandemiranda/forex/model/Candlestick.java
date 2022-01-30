package br.bti.allandemiranda.forex.model;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

/**
 * The type Candlestick.
 *
 * @author Allan de Miranda Silva
 * @version 0.1
 */
public class Candlestick {

  private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd kk:mm";
  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String VALID_NUMBER = "need be a valid number";
  private static final String BE_NUMBER = "need be a number";
  private static final String NOT_NULL = "can't be a NULL";
  private static final String LOCAL_DATE_TIME = "Local Date Time";
  private static final String VALID_DATE = "need be a valid date";
  private static final String BE_DATE = "need be a date";
  private static final String CURRENCY_PAIR = "Currency Pair";
  private static final String HIGH_PRICE = "High Price";
  private static final String LOW_PRICE = "Low Price";
  private static final String CLOSE_PRICE = "Close Price";
  private static final String OPEN_PRICE = "Open Price";

  private double openPrice;
  private double closePrice;
  private double lowPrice;
  private double highPrice;
  private LocalDateTime localDateTime;
  private CurrencyPair currencyPair;

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice     the open price
   * @param closePrice    the close price
   * @param lowPrice      the low price
   * @param highPrice     the high price
   * @param localDateTime the local date time
   * @param currencyPair  the currency pair
   */
  public Candlestick(double openPrice, double closePrice, double lowPrice, double highPrice, LocalDateTime localDateTime,
      CurrencyPair currencyPair) {
    setCandlestick(openPrice, closePrice, lowPrice, highPrice);
    setLocalDateTime(localDateTime);
    setCurrencyPair(currencyPair);
  }

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice     the open price
   * @param closePrice    the close price
   * @param lowPrice      the low price
   * @param highPrice     the high price
   * @param localDateTime the local date time
   * @param currencyPair  the currency pair
   */
  public Candlestick(String openPrice, String closePrice, String lowPrice, String highPrice, String localDateTime,
      CurrencyPair currencyPair) {
    setCandlestick(openPrice, closePrice, lowPrice, highPrice);
    setLocalDateTime(localDateTime);
    setCurrencyPair(currencyPair);
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
  private void setOpenPrice(String openPrice) {
    if (openPrice != null) {
      if (!openPrice.isBlank()) {
        try {
          setOpenPrice(Double.parseDouble(openPrice));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(OPEN_PRICE + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(OPEN_PRICE + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(OPEN_PRICE + " " + NOT_NULL);
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
  private void setClosePrice(String closePrice) {
    if (closePrice != null) {
      if (!closePrice.isBlank()) {
        try {
          setClosePrice(Double.parseDouble(closePrice));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(CLOSE_PRICE + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(CLOSE_PRICE + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(CLOSE_PRICE + " " + NOT_NULL);
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
  private void setLowPrice(String lowPrice) {
    if (lowPrice != null) {
      if (!lowPrice.isBlank()) {
        try {
          setLowPrice(Double.parseDouble(lowPrice));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(LOW_PRICE + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(LOW_PRICE + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(LOW_PRICE + " " + NOT_NULL);
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
  private void setHighPrice(String highPrice) {
    if (highPrice != null) {
      if (!highPrice.isBlank()) {
        try {
          setHighPrice(Double.parseDouble(highPrice));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(HIGH_PRICE + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(HIGH_PRICE + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(HIGH_PRICE + " " + NOT_NULL);
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
    if (localDateTime != null) {
      this.localDateTime = localDateTime;
    } else {
      throw new IllegalArgumentException(LOCAL_DATE_TIME + " " + NOT_NULL);
    }
  }

  /**
   * Sets local date time.
   *
   * @param localDateTime the local date time
   */
  private void setLocalDateTime(String localDateTime) {
    if (localDateTime != null) {
      try {
        if (!localDateTime.isBlank()) {
          this.localDateTime = LocalDateTime.parse(localDateTime, getDateTimeFormatter());
        } else {
          throw new IllegalArgumentException(LOCAL_DATE_TIME + " " + BE_DATE);
        }
      } catch (DateTimeException e) {
        throw new DateTimeException(LOCAL_DATE_TIME + " " + VALID_DATE);
      }
    } else {
      throw new IllegalArgumentException(LOCAL_DATE_TIME + " " + NOT_NULL);
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
   * Gets currency exchange.
   *
   * @return the currency exchange
   */
  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }

  /**
   * Sets currency exchange.
   *
   * @param currencyPair the currency pair
   */
  private void setCurrencyPair(CurrencyPair currencyPair) {
    if (currencyPair != null) {
      this.currencyPair = currencyPair;
    } else {
      throw new IllegalArgumentException(CURRENCY_PAIR + " " + NOT_NULL);
    }
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
    if (lowPrice <= highPrice) {
      if (openPrice >= lowPrice && openPrice <= highPrice) {
        if (closePrice >= lowPrice && closePrice <= highPrice) {
          setOpenPrice(openPrice);
          setClosePrice(closePrice);
          setLowPrice(lowPrice);
          setHighPrice(highPrice);
        } else {
          throw new IllegalArgumentException(CLOSE_PRICE + " need be between the " + HIGH_PRICE + " and " + LOW_PRICE);
        }
      } else {
        throw new IllegalArgumentException(OPEN_PRICE + " need be between the " + HIGH_PRICE + " and " + LOW_PRICE);
      }
    } else {
      throw new IllegalArgumentException(LOW_PRICE + " need be less or equal a " + HIGH_PRICE);
    }
  }

  /**
   * Sets candlestick.
   *
   * @param openPrice  the open price
   * @param closePrice the close price
   * @param lowPrice   the low price
   * @param highPrice  the high price
   */
  private void setCandlestick(String openPrice, String closePrice, String lowPrice, String highPrice) {
    setOpenPrice(openPrice);
    setClosePrice(closePrice);
    setLowPrice(lowPrice);
    setHighPrice(highPrice);
    if (getLowPrice() <= getHighPrice()) {
      if (getOpenPrice() >= getLowPrice() && getOpenPrice() <= getHighPrice()) {
        if (!(getClosePrice() >= getLowPrice() && getClosePrice() <= getHighPrice())) {
          throw new IllegalArgumentException(CLOSE_PRICE + " need be between the " + HIGH_PRICE + " and " + LOW_PRICE);
        }
      } else {
        throw new IllegalArgumentException(OPEN_PRICE + " need be between the " + HIGH_PRICE + " and " + LOW_PRICE);
      }
    } else {
      throw new IllegalArgumentException(LOW_PRICE + " need be less or equal a " + HIGH_PRICE);
    }
  }
}
