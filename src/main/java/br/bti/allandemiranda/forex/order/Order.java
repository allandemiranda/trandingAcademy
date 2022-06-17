package br.bti.allandemiranda.forex.order;

import br.bti.allandemiranda.forex.currency.Exchange;
import java.util.InputMismatchException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The type Order forex.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Order {

  //! TODO: NEED IMPROVE THE CONCEPT OF THIS CLASS
  //! TODO: CHECK IF IS A JPY CURRENCY

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String STOP_LOSS = "Stop Loss";
  private static final String TAKE_PROFIT = "Take Profit";
  private static final String IS_CLOSED = "Can't close the position, because this is closed";

  private Exchange exchange;
  private Position position;
  private Status status = Status.OPEN;
  private double stopLoss;
  private double takeProfit;
  private double points;
  private String comment;

  /**
   * Instantiates a new Order forex.
   *
   * @param exchange   the currency exchange
   * @param position   the order forex position
   * @param status     the order forex status
   * @param stopLoss   the stop loss
   * @param takeProfit the take profit
   * @param comment    the comment
   */
  public Order(@NotNull Exchange exchange, @NotNull Position position, @NotNull Status status,
      double stopLoss, double takeProfit, double initialPoints, @NotNull String comment) {
    setCurrencyExchange(exchange);
    setPosition(position);
    setPositionStatus(status);
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
  public @NotNull Exchange getCurrencyExchange() {
    return exchange;
  }

  /**
   * Sets currency exchange.
   *
   * @param exchange the currency exchange
   */
  private void setCurrencyExchange(@NotNull Exchange exchange) {
    this.exchange = exchange;
  }

  /**
   * Gets position.
   *
   * @return the position
   */
  public @NotNull Position getPosition() {
    return position;
  }

  /**
   * Sets position.
   *
   * @param position the order forex position
   */
  private void setPosition(@NotNull Position position) {
    this.position = position;
  }

  /**
   * Gets position status.
   *
   * @return the position status
   */
  public @NotNull Status getPositionStatus() {
    return status;
  }

  /**
   * Sets position status.
   *
   * @param status the order forex status
   */
  private void setPositionStatus(@NotNull Status status) {
    this.status = status;
  }

  /**
   * Close position manual.
   */
  public void closePositionManual() {
    if (getPositionStatus().equals(Status.OPEN)) {
      this.status = Status.ClOSE_MANUALLY;
    } else {
      throw new IllegalStateException(IS_CLOSED);
    }
  }

  /**
   * Close position margin loss.
   */
  private void closePositionMarginLoss() {
    if (getPositionStatus().equals(Status.OPEN)) {
      this.status = Status.ClOSE_MARGIN_LOSS;
    } else {
      throw new IllegalStateException(IS_CLOSED);
    }
  }

  /**
   * Close position stop loss.
   */
  private void closePositionStopLoss() {
    if (getPositionStatus().equals(Status.OPEN)) {
      this.status = Status.ClOSE_STOP_LOSS;
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
    Order that = (Order) o;
    return Double.compare(that.stopLoss, stopLoss) == 0
        && Double.compare(that.takeProfit, takeProfit) == 0 && Double.compare(that.points, points) == 0
        && exchange.equals(that.exchange) && position == that.position && status == that.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(exchange, position, status, stopLoss, takeProfit, points);
  }

  @Override
  public String toString() {
    return "Order{" + "exchange=" + exchange + ", position=" + position + ", status=" + status
        + ", stopLoss=" + stopLoss + ", takeProfit=" + takeProfit + ", points=" + points + ", comment='"
        + comment + '\'' + '}';
  }
}
