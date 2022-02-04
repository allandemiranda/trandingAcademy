package br.bti.allandemiranda.forex.model.utils;

import com.sun.jdi.request.InvalidRequestStateException;
import java.util.InputMismatchException;
import java.util.Objects;

/**
 * The type Order forex.
 *
 * @author Allan de Miranda Silva
 * @version 0.2
 */
public class OrderForex {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String NOT_NULL = "can't be a NULL";
  private static final String CURRENCY_EXCHANGE = "Currency Exchange";
  private static final String POSITION = "OrderForexPosition";
  private static final String POSITION_STATUS = "OrderForexPosition Status";
  private static final String STOP_LOSS = "Stop Loss";
  private static final String TAKE_PROFIT = "Take Profit";

  private CurrencyExchange currencyExchange;
  private OrderForexPosition orderForexPosition;
  private OrderForexStatus orderForexStatus = OrderForexStatus.OPEN;
  private double stopLoss;
  private double takeProfit;
  private double points;
  private String comment;

  /**
   * Instantiates a new Order forex.
   *
   * @param currencyExchange   the currency exchange
   * @param orderForexPosition the order forex position
   * @param orderForexStatus   the order forex status
   * @param stopLoss           the stop loss
   * @param takeProfit         the take profit
   * @param comment            the comment
   */
  public OrderForex(CurrencyExchange currencyExchange, OrderForexPosition orderForexPosition, OrderForexStatus orderForexStatus,
      double stopLoss, double takeProfit, double initialPoints, String comment) {
    setCurrencyExchange(currencyExchange);
    setPosition(orderForexPosition);
    setPositionStatus(orderForexStatus);
    setStopLoss(stopLoss);
    setTakeProfit(takeProfit);
    setComment(comment);
    setPoints(initialPoints);
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
   * Gets position.
   *
   * @return the position
   */
  public OrderForexPosition getPosition() {
    return orderForexPosition;
  }

  /**
   * Sets position.
   *
   * @param orderForexPosition the order forex position
   */
  private void setPosition(OrderForexPosition orderForexPosition) {
    if (orderForexPosition != null) {
      this.orderForexPosition = orderForexPosition;
    } else {
      throw new IllegalArgumentException(POSITION + " " + NOT_NULL);
    }
  }

  /**
   * Gets position status.
   *
   * @return the position status
   */
  public OrderForexStatus getPositionStatus() {
    return orderForexStatus;
  }

  /**
   * Sets position status.
   *
   * @param orderForexStatus the order forex status
   */
  private void setPositionStatus(OrderForexStatus orderForexStatus) {
    if (orderForexStatus != null) {
      this.orderForexStatus = orderForexStatus;
    } else {
      throw new IllegalArgumentException(POSITION_STATUS + " " + NOT_NULL);
    }
  }

  /**
   * Close position manual.
   */
  public void closePositionManual() {
    if (getPositionStatus().equals(OrderForexStatus.OPEN)) {
      this.orderForexStatus = OrderForexStatus.ClOSE_MANUALLY;
    } else {
      throw new InvalidRequestStateException("Can't close the orderForexPosition, because this is closed");
    }
  }

  /**
   * Close position margin loss.
   */
  private void closePositionMarginLoss() {
    if (getPositionStatus().equals(OrderForexStatus.OPEN)) {
      this.orderForexStatus = OrderForexStatus.ClOSE_MARGIN_LOSS;
    } else {
      throw new InvalidRequestStateException("Can't close the orderForexPosition, because this is closed");
    }
  }

  /**
   * Close position stop loss.
   */
  private void closePositionStopLoss() {
    if (getPositionStatus().equals(OrderForexStatus.OPEN)) {
      this.orderForexStatus = OrderForexStatus.ClOSE_STOP_LOSS;
    } else {
      throw new InvalidRequestStateException("Can't close the orderForexPosition, because this is closed");
    }
  }

  /**
   * Gets stop loss.
   *
   * @return the stop loss
   */
  public double getStopLoss() {
    return stopLoss;
  }

  /**
   * Sets stop loss.
   *
   * @param stopLoss the stop loss
   */
  private void setStopLoss(double stopLoss) {
    if (Math.abs(stopLoss) == stopLoss) {
      this.stopLoss = stopLoss;
    } else {
      throw new InputMismatchException(STOP_LOSS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets take profit.
   *
   * @return the take profit
   */
  public double getTakeProfit() {
    return takeProfit;
  }

  /**
   * Sets take profit.
   *
   * @param takeProfit the take profit
   */
  private void setTakeProfit(double takeProfit) {
    if (Math.abs(takeProfit) == takeProfit) {
      this.takeProfit = takeProfit;
    } else {
      throw new InputMismatchException(TAKE_PROFIT + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets comment.
   *
   * @return the comment
   */
  public String getComment() {
    return comment;
  }

  /**
   * Sets comment.
   *
   * @param comment the comment
   */
  private void setComment(String comment) {
    this.comment = comment;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderForex that = (OrderForex) o;
    return Double.compare(that.stopLoss, stopLoss) == 0 && Double.compare(that.takeProfit, takeProfit) == 0
        && Double.compare(that.points, points) == 0 && currencyExchange.equals(that.currencyExchange)
        && orderForexPosition == that.orderForexPosition && orderForexStatus == that.orderForexStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(currencyExchange, orderForexPosition, orderForexStatus, stopLoss, takeProfit, points);
  }

  @Override
  public String toString() {
    return "OrderForex{" + "currencyExchange=" + currencyExchange + ", orderForexPosition=" + orderForexPosition + ", orderForexStatus="
        + orderForexStatus + ", stopLoss=" + stopLoss + ", takeProfit=" + takeProfit + ", points=" + points + ", comment='" + comment + '\''
        + '}';
  }
}
