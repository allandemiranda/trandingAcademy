package br.bti.allandemiranda.forex.model;

import com.sun.jdi.request.InvalidRequestStateException;
import java.util.InputMismatchException;

// TODO: refazer isso aqui tamb?m
public class OrderForex {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String VALID_NUMBER = "need be a valid number";
  private static final String BE_NUMBER = "need be a number";
  private static final String NOT_NULL = "can't be a NULL";
  private static final String LOCAL_DATE_TIME = "Local Date Time";
  private static final String VALID_DATE = "need be a valid date";
  private static final String BE_DATE = "need be a date";
  private static final String BE_VOLUME_STEP = "need be divisor of volume step";
  private static final String BE_DEFINED_BEFORE = "need be defined before define the";

  private static final String CURRENCY_EXCHANGE = "Currency Exchange";
  private static final String LOT = "Lot";
  private static final String POSITION = "OrderForexPosition";
  private static final String POSITION_STATUS = "OrderForexPosition Status";


  private CurrencyExchange currencyExchange;    // --> 1(set),3
  private double lot;                           // --> 1(get)
  private OrderForexPosition orderForexPosition;                    // --> 2
  private OrderForexStatus orderForexStatus = OrderForexStatus.OPEN;
  private double stopLoss;                      // --> 2
  private double takeProfit;                    // --> 2
  private Candlestick lastInteraction;            // --> 3
//  private LinkedList<DataAmount> interactionlist; // --> 3
  private double initialPrice; // TODO ->> adicionar o get e set
  private String comment;


  public CurrencyExchange getCurrencyExchange() {
    return currencyExchange;
  }

  private void setCurrencyExchange(CurrencyExchange currencyExchange) {
    if (currencyExchange != null) {
      this.currencyExchange = currencyExchange;
    } else {
      throw new IllegalArgumentException(CURRENCY_EXCHANGE + " " + NOT_NULL);
    }
  }

  public double getLot() {
    return lot;
  }

  private void setLot(double lot) {
    if (Math.abs(lot) == lot) {
      if (lot % getCurrencyExchange().getVolumeStep() == 0) {
        this.lot = lot;
      } else {
        throw new InputMismatchException(LOT + " " + BE_VOLUME_STEP);
      }
    } else {
      throw new InputMismatchException(LOT + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  private void setExchangeAndLot(double lot, boolean currencyExchangeDefined) {
    if (currencyExchangeDefined) {
      setLot(lot);
    } else {
      throw new IllegalStateException(CURRENCY_EXCHANGE + " " + BE_DEFINED_BEFORE + " " + LOT);
    }
  }

  public OrderForexPosition getPosition() {
    return orderForexPosition;
  }

  private void setPosition(OrderForexPosition orderForexPosition) {
    if (orderForexPosition != null) {
      this.orderForexPosition = orderForexPosition;
    } else {
      throw new IllegalArgumentException(POSITION + " " + NOT_NULL);
    }
  }

  public OrderForexStatus getPositionStatus() {
    return orderForexStatus;
  }

  private void setPositionStatus(OrderForexStatus orderForexStatus) {
    if (orderForexStatus != null) {
      this.orderForexStatus = orderForexStatus;
    } else {
      throw new IllegalArgumentException(POSITION_STATUS + " " + NOT_NULL);
    }
  }

  public void closePositionManual() {
    if (getPositionStatus().equals(OrderForexStatus.OPEN)) {
      this.orderForexStatus = OrderForexStatus.ClOSE_MANUALLY;
    } else {
      throw new InvalidRequestStateException("Can't close the orderForexPosition, because this is closed");
    }
  }

  private void closePositionMarginLoss() {
    if (getPositionStatus().equals(OrderForexStatus.OPEN)) {
      this.orderForexStatus = OrderForexStatus.ClOSE_MARGIN_LOSS;
    } else {
      throw new InvalidRequestStateException("Can't close the orderForexPosition, because this is closed");
    }
  }

  private void closePositionStopLoss() {
    if (getPositionStatus().equals(OrderForexStatus.OPEN)) {
      this.orderForexStatus = OrderForexStatus.ClOSE_STOP_LOSS;
    } else {
      throw new InvalidRequestStateException("Can't close the orderForexPosition, because this is closed");
    }
  }

  //TODO ->>>

  public double getStopLoss() {
    return stopLoss;
  }

  public void setStopLoss(double stopLoss) {
    this.stopLoss = stopLoss;
  }

  public double getTakeProfit() {
    return takeProfit;
  }

  public void setTakeProfit(double takeProfit) {
    this.takeProfit = takeProfit;
  }

  public Candlestick getLastInteraction() {
    return lastInteraction;
  }

  public void setLastInteraction(Candlestick lastInteraction) {
    this.lastInteraction = lastInteraction;
  }

//  public LinkedList<DataAmount> getInteractionlist() {
//    return interactionlist;
//  }
//
//  public void setInteractionlist(LinkedList<DataAmount> interactionlist) {
//    this.interactionlist = interactionlist;
//  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
