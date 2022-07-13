package br.bti.allandemiranda.forex.order;

import br.bti.allandemiranda.forex.currency.Exchange;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The type Order forex.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Order implements Cloneable {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String STOP_LOSS = "Stop Loss";
  private static final String TAKE_PROFIT = "Take Profit";
  private static final String IS_CLOSED = "Can't close the position, because this is closed";

  private Exchange exchange;
  private Position position;
  private Status status = Status.OPEN;
  private Double stopLoss;
  private Double takeProfit;
  private Double lastPoint = null;
  private double actualPoint;
  private double gain;
  private String comment;
  private LocalDateTime time;

  /**
   * Instantiates a new Order forex.
   *
   * @param exchange   the currency exchange
   * @param position   the order forex position
   * @param stopLoss   the stop loss
   * @param takeProfit the take profit
   * @param comment    the comment
   */
  public Order(@NotNull Exchange exchange, @NotNull Position position,
      Double stopLoss, Double takeProfit, double initialPoints, @NotNull String comment,
      LocalDateTime time) {
    setCurrencyExchange(exchange);
    setPosition(position);
    setStopLoss(stopLoss);
    setTakeProfit(takeProfit);
    setComment(comment);
    this.actualPoint = initialPoints;
    this.gain = exchange.getSpread() * exchange.getPip() * (-1);
    this.time = time;
  }

  public Status setNewPoints(double pointActual) {
    if(pointActual > 0.0 && getPositionStatus().equals(Status.OPEN)) {
      this.lastPoint = this.actualPoint;
      this.actualPoint = pointActual;

      if(getPosition().equals(Position.BUY)){
        if(Objects.nonNull(getTakeProfit()) && getTakeProfit() <= pointActual) {
          closePositioTakeProfit();
        } else {
          if(Objects.nonNull(getStopLoss()) && getStopLoss() >= pointActual){
            closePositionStopLoss();
          }
        }
        this.gain = this.gain + (this.actualPoint - this.lastPoint);
      } else {
        if(Objects.nonNull(getTakeProfit()) && getTakeProfit() >= pointActual) {
          closePositioTakeProfit();
        } else {
          if(Objects.nonNull(getStopLoss()) && getStopLoss() <= pointActual){
            closePositionStopLoss();
          }
        }
        this.gain = this.gain + (this.lastPoint - this.actualPoint);
      }
    } else {
      throw new IllegalArgumentException("Can't set a new value");
    }

    return getPositionStatus();
  }

  private double getGain() {
    return gain;
  }

  private LocalDateTime getTime() {
    return time;
  }

  private void setTime(LocalDateTime time) {
    this.time = time;
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
   * Close positio take profit.
   */
  private void closePositioTakeProfit() {
    if (getPositionStatus().equals(Status.OPEN)) {
      this.status = Status.ClOSE_TAKE_PROFIT;
    } else {
      throw new IllegalStateException(IS_CLOSED);
    }
  }

  /**
   * Gets stop loss.
   *
   * @return the stop loss
   */
  public Double getStopLoss() {
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
  public Double getTakeProfit() {
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
}
