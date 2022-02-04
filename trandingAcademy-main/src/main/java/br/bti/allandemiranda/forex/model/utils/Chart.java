package br.bti.allandemiranda.forex.model.utils;

import java.util.LinkedList;
import java.util.Objects;

/**
 * The type Chart.
 *
 * @author Allan de Miranda Silva
 * @version 0.2
 */
public class Chart {

  private static final String NOT_NULL = "can't be a NULL";
  private static final String CURRENCY_EXCHANGE = "Currency Exchange";

  private final LinkedList<Candlestick> candlestickList = new LinkedList<>();
  private CurrencyExchange currencyExchange;
  private double points = 0.0;

  /**
   * Instantiates a new Chart.
   *
   * @param currencyExchange the currency exchange
   */
  public Chart(CurrencyExchange currencyExchange) {
    setCurrencyExchange(currencyExchange);
  }

  /**
   * Gets candlestick list.
   *
   * @return the candlestick list
   */
  public LinkedList<Candlestick> getCandlestickList() {
    return candlestickList;
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
    if (currencyExchange != null) {
      this.currencyExchange = currencyExchange;
    } else {
      throw new IllegalArgumentException(CURRENCY_EXCHANGE + " " + NOT_NULL);
    }
  }

  /**
   * Gets points.
   *
   * @return the points
   */
  public double getPoints() {
    return points;
  }

  /**
   * Sets points.
   *
   * @param points the points
   */
  private void setPoints(double points) {
    this.points = points;
  }

  /**
   * Add points.
   *
   * @param points the points
   */
  public void addPoints(double points) {
    setPoints(getPoints() + points);
  }

  /**
   * Check candlestick list boolean.
   *
   * @return the boolean - TRUE to is good - FALSE to is bad
   */
  public boolean candlestickListIsGood() {
    return
        getCandlestickList().stream().filter(candlestick -> !candlestick.getCurrencyPair().equals(getCurrencyExchange().getCurrencyPair()))
            .toList().size() == 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Chart chart = (Chart) o;
    return candlestickList.equals(chart.candlestickList) && currencyExchange.equals(chart.currencyExchange);
  }

  @Override
  public int hashCode() {
    return Objects.hash(candlestickList, currencyExchange);
  }

  @Override
  public String toString() {
    return "Chart{" + "candlestickList=" + candlestickList + ", currencyExchange=" + currencyExchange + ", points=" + points + '}';
  }
}
