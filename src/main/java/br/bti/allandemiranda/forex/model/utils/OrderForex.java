package br.bti.allandemiranda.forex.model.utils;

import java.util.InputMismatchException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The type Order forex.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class OrderForex {

  //! TODO: NEED IMPROVE THE CONCEPT OF THIS CLASS

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String STOP_LOSS = "Stop Loss";
  private static final String TAKE_PROFIT = "Take Profit";
  private static final String IS_CLOSED = "Can't close the orderForexPosition, because this is closed";

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
  public OrderForex(@NotNull CurrencyExchange currencyExchange,
      @NotNull OrderForexPosition orderForexPosition, @NotNull OrderForexStatus orderForexStatus,
      double stopLoss, double takeProfit, double initialPoints, @NotNull String comment) {
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
  public @NotNull CurrencyExchange getCurrencyExchange() {
    return currencyExchange;
  }

  /**
   * Sets currency exchange.
   *
   * @param currencyExchange the currency exchange
   */
  private void setCurrencyExchange(@NotNull CurrencyExchange currencyExchange) {
    this.currencyExchange = currencyExchange;
  }

  /**
   * Gets position.
   *
   * @return the position
   */
  public @NotNull OrderForexPosition getPosition() {
    return orderForexPosition;
  }

  /**
   * Sets position.
   *
   * @param orderForexPosition the order forex position
   */
  private void setPosition(@NotNull OrderForexPosition orderForexPosition) {
    this.orderForexPosition = orderForexPosition;
  }

  /**
   * Gets position status.
   *
   * @return the position status
   */
  public @NotNull OrderForexStatus getPositionStatus() {
    return orderForexStatus;
  }

  /**
   * Sets position status.
   *
   * @param orderForexStatus the order forex status
   */
  private void setPositionStatus(@NotNull OrderForexStatus orderForexStatus) {
    this.orderForexStatus = orderForexStatus;
  }

  /**
   * Close position manual.
   */
  public void closePositionManual() {
    if (getPositionStatus().equals(OrderForexStatus.OPEN)) {
      this.orderForexStatus = OrderForexStatus.ClOSE_MANUALLY;
    } else {
      throw new IllegalStateException(IS_CLOSED);
    }
  }

  /**
   * Close position margin loss.
   */
  private void closePositionMarginLoss() {
    if (getPositionStatus().equals(OrderForexStatus.OPEN)) {
      this.orderForexStatus = OrderForexStatus.ClOSE_MARGIN_LOSS;
    } else {
      throw new IllegalStateException(IS_CLOSED);
    }
  }

  /**
   * Close position stop loss.
   */
  private void closePositionStopLoss() {
    if (getPositionStatus().equals(OrderForexStatus.OPEN)) {
      this.orderForexStatus = OrderForexStatus.ClOSE_STOP_LOSS;
    } else {
      throw new IllegalStateException(IS_CLOSED);
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
  private void setComment(@NotNull String comment) {
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
    return Double.compare(that.stopLoss, stopLoss) == 0
        && Double.compare(that.takeProfit, takeProfit) == 0 && Double.compare(that.points, points) == 0
        && currencyExchange.equals(that.currencyExchange)
        && orderForexPosition == that.orderForexPosition && orderForexStatus == that.orderForexStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(currencyExchange, orderForexPosition, orderForexStatus, stopLoss, takeProfit,
        points);
  }

  @Override
  public String toString() {
    return "OrderForex{" + "currencyExchange=" + currencyExchange + ", orderForexPosition="
        + orderForexPosition + ", orderForexStatus=" + orderForexStatus + ", stopLoss=" + stopLoss
        + ", takeProfit=" + takeProfit + ", points=" + points + ", comment='" + comment + '\'' + '}';
  }
}
