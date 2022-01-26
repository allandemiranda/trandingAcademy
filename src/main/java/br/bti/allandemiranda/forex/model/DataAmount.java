package br.bti.allandemiranda.forex.model;

import java.time.LocalDateTime;
import java.util.InputMismatchException;

/**
 * The type Data amount.
 */
public class DataAmount {

  private static final String NOT_NULL = "can't be a NULL";
  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String CURRENCY = "Currency";
  private static final String DATE_TIME = "Local Date Time";
  private static final String PRICE = "Price";

  private Currency currency;
  private LocalDateTime localDateTime;
  private double netAmount;
  private double grossAmount;
  private double price;

  /**
   * Instantiates a new Data amount.
   *
   * @param currency      the currency
   * @param localDateTime the local date time
   * @param netAmount     the net amount
   * @param grossAmount   the gross amount
   * @param price         the price
   */
  public DataAmount(Currency currency, LocalDateTime localDateTime, double netAmount, double grossAmount, double price) {
    setCurrency(currency);
    setLocalDateTime(localDateTime);
    setNetAmount(netAmount);
    setGrossAmount(grossAmount);
    setPrice(price);
  }

  /**
   * Gets price.
   *
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets price.
   *
   * @param price the price
   */
  private void setPrice(double price) {
    if (Math.abs(price) == price) {
      this.price = price;
    } else {
      throw new InputMismatchException(PRICE + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets currency.
   *
   * @return the currency
   */
  public Currency getCurrency() {
    return currency;
  }

  /**
   * Sets currency.
   *
   * @param currency the currency
   */
  private void setCurrency(Currency currency) {
    if (currency == null) {
      throw new IllegalArgumentException(CURRENCY + " " + NOT_NULL);
    } else {
      this.currency = currency;
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
      throw new IllegalArgumentException(DATE_TIME + " " + NOT_NULL);
    } else {
      this.localDateTime = localDateTime;
    }
  }

  /**
   * Gets net amount.
   *
   * @return the net amount
   */
  public double getNetAmount() {
    return netAmount;
  }

  /**
   * Sets net amount.
   *
   * @param netAmount the net amount
   */
  private void setNetAmount(double netAmount) {
    this.netAmount = netAmount;
  }

  /**
   * Gets gross amount.
   *
   * @return the gross amount
   */
  public double getGrossAmount() {
    return grossAmount;
  }

  /**
   * Sets gross amount.
   *
   * @param grossAmount the gross amount
   */
  private void setGrossAmount(double grossAmount) {
    this.grossAmount = grossAmount;

  }
}
