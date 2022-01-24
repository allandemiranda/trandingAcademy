//package br.bti.allandemiranda.forex.model;
//
//import java.util.LinkedList;
//import java.util.Objects;
//
//public class OrderForex {
//
//  private CurrencyExchange currencyExchange;
//  private String comment;
//
//  private Double lot;
//
//  private Position position;
//  private PositionStatus positionStatus;
//
//  private Double priceOpen;
//  private Double stopLoss;
//  private Double takeProfit;
//
//
//
//  private Candlestick lastInteraction;
//  private LinkedList<DataAmount> interactions;
//
//  public CurrencyExchange getCurrencyExchange() {
//    return currencyExchange;
//  }
//
//  private void setCurrencyExchange(CurrencyExchange currencyExchange) {
//    if (currencyExchange != null) {
//      this.currencyExchange = currencyExchange;
//    } else {
//      throw new NullPointerException("Can't set a NULL Currency Exchange to this Order Forex");
//    }
//  }
//
//  public String getComment() {
//    return comment;
//  }
//
//  private void setComment(String comment) {
//    this.comment = Objects.requireNonNullElse(comment, "");
//  }
//
//  public Double getLot() {
//    return lot;
//  }
//
//  private void setLot(Double lot) throws InterruptedException {
//    if (lot == null) {
//      throw new NullPointerException("Can't set a NULL Lot to this Order Forex");
//    } else {
//      if (getCurrencyExchange() == null) {
//        throw new NullPointerException("Set a Currency Exchange before set a Lot to this Order Forex");
//      } else {
//        if (getCurrencyExchange().getMaximalVolume() >= lot && getCurrencyExchange().getMinimalVolume() <= lot) {
//          this.lot = lot;
//        } else {
//          throw new InterruptedException("Ranger of Lot in this Currency Exchange are improper");
//        }
//      }
//    }
//  }
//
//  public Position getPosition() {
//    return position;
//  }
//
//  private void setPosition(Position position) {
//    if (position != null) {
//      this.position = position;
//    } else {
//      throw new NullPointerException("Can't set a NULL Position to this Order Forex");
//    }
//  }
//
//  public PositionStatus getPositionStatus() {
//    return positionStatus;
//  }
//
//  public void setPositionStatus(PositionStatus positionStatus) {
//    if (positionStatus != null) {
//      this.positionStatus = positionStatus;
//    } else {
//      throw new NullPointerException("Can't set a NULL Position Status to this Order Forex");
//    }
//  }
//
//  public Double getPriceOpen() {
//    return priceOpen;
//  }
//
//  private void setPriceOpen(Double priceOpen) {
//    if(priceOpen != null) {
//      this.priceOpen = priceOpen;
//    } else {
//      throw new NullPointerException("Can't set a NULL Price Open to this Order Forex");
//    }
//  }
//
//  public Double getStopLoss() {
//    return stopLoss;
//  }
//
//  public void setStopLoss(Double stopLoss) {
//    this.stopLoss = Objects.requireNonNullElse(stopLoss, 0.0);
//  }
//
//  public Double getTakeProfit() {
//    return takeProfit;
//  }
//
//  public void setTakeProfit(Double takeProfit) {
//    this.takeProfit = Objects.requireNonNullElse(takeProfit, 0.0);
//  }
//
//  public LinkedList<DataAmount> getInteractions() {
//    return interactions;
//  }
//
//  public void pushInteraction(Candlestick interaction) {
//
//  }
//
//  public void pushInteraction(Candlestick interaction, Double newTakeProfit, Double newStopLoss) {
//
//  }
//
//  public void closePosition(){
//
//  }
//
//  private DataAmount pushInteraction(Candlestick interaction, Boolean closeOrder) throws InterruptedException {
//    if(getPositionStatus().toString().contains("CLOSE_")){
//      throw new InterruptedException("This position are closed, can't modify");
//    } else {
//      if(interaction == null || closeOrder == null){
//        throw new NullPointerException("Can't set a NULL interaction or status order to this Order Forex");
//      } else {
//        PositionStatus tempPositionStatus = checkOrderStatus(interaction);
//        if(tempPositionStatus.equals(PositionStatus.OPEN)){
//
//        }
//      }
//    }
//  }
//
//  private Double applySwap(){
//
//  }
//
//  private PositionStatus checkOrderStatus(Candlestick interaction) {
//    if(getPosition().equals(Position.BUY)){
//      if(interaction.getHighPrice() >= getTakeProfit()){
//        return PositionStatus.ClOSE_TAKE_PROFIT;
//      } else {
//        if(interaction.getLowPrice() <= getStopLoss()) {
//          return  PositionStatus.ClOSE_STOP_LOSS;
//        } else {
//          return PositionStatus.OPEN;
//        }
//      }
//    } else {
//      if(getPosition().equals(Position.SELL)){
//        if(interaction.getHighPrice() >= getStopLoss()){
//          return PositionStatus.ClOSE_STOP_LOSS;
//        } else {
//          if(interaction.getLowPrice() <= getTakeProfit()) {
//            return  PositionStatus.ClOSE_TAKE_PROFIT;
//          } else {
//            return PositionStatus.OPEN;
//          }
//        }
//      } else {
//        throw new NumberFormatException("This position not are Sell or Buy");
//      }
//    }
//  }
//
//  private Candlestick getLastInteraction() {
//    return lastInteraction;
//  }
//
//  private void setLastInteraction(Candlestick lastInteraction) {
//    if(lastInteraction == null){
//      throw new NullPointerException("Can't set a NULL candlestick to this Order Forex");
//    } else {
//      this.lastInteraction = lastInteraction;
//    }
//  }
//}
