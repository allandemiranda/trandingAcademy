package br.bti.allandemiranda.forex.model;

import com.sun.jdi.request.InvalidRequestStateException;
import java.util.InputMismatchException;
import java.util.LinkedList;

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
  private static final String POSITION = "Position";
  private static final String POSITION_STATUS = "Position Status";


  private CurrencyExchange currencyExchange;    // --> 1(set),3
  private double lot;                           // --> 1(get)
  private Position position;                    // --> 2
  private PositionStatus positionStatus = PositionStatus.OPEN;
  private double stopLoss;                      // --> 2
  private double takeProfit;                    // --> 2
  private Candlestick lastInteraction;            // --> 3
  private LinkedList<DataAmount> interactionlist; // --> 3
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

  public Position getPosition() {
    return position;
  }

  private void setPosition(Position position) {
    if (position != null) {
      this.position = position;
    } else {
      throw new IllegalArgumentException(POSITION + " " + NOT_NULL);
    }
  }

  public PositionStatus getPositionStatus() {
    return positionStatus;
  }

  private void setPositionStatus(PositionStatus positionStatus) {
    if (positionStatus != null) {
      this.positionStatus = positionStatus;
    } else {
      throw new IllegalArgumentException(POSITION_STATUS + " " + NOT_NULL);
    }
  }

  public void closePositionManual() {
    if (getPositionStatus().equals(PositionStatus.OPEN)) {
      this.positionStatus = PositionStatus.ClOSE_MANUALLY;
    } else {
      throw new InvalidRequestStateException("Can't close the position, because this is closed");
    }
  }

  private void closePositionMarginLoss() {
    if (getPositionStatus().equals(PositionStatus.OPEN)) {
      this.positionStatus = PositionStatus.ClOSE_MARGIN_LOSS;
    } else {
      throw new InvalidRequestStateException("Can't close the position, because this is closed");
    }
  }

  private void closePositionStopLoss() {
    if (getPositionStatus().equals(PositionStatus.OPEN)) {
      this.positionStatus = PositionStatus.ClOSE_STOP_LOSS;
    } else {
      throw new InvalidRequestStateException("Can't close the position, because this is closed");
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

  public LinkedList<DataAmount> getInteractionlist() {
    return interactionlist;
  }

  public void setInteractionlist(LinkedList<DataAmount> interactionlist) {
    this.interactionlist = interactionlist;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
