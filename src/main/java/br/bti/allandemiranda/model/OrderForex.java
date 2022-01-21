package br.bti.allandemiranda.model;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class OrderForex {

  private CurrencyExchange currencyExchange;
  private String comment;

  private Double spreed;
  private Double lot;

  private Position position;
  private PositionStatus positionStatus;

  private Double priceOpen;
  private Double stopLoss;
  private Double takeProfit;
  private Double risk;

  private LinkedList<> interactions;

  private record Data(LocalDateTime localDateTime, )

}
