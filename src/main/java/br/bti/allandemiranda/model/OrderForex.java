package br.bti.allandemiranda.model;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class OrderForex {

  private CurrencyExchange currencyExchange;
  private String comment;

  private Candlestick openCandlestick;

  private Double spreed;
  private Double lot;

  private Position position;
  private PositionStatus positionStatus;

  private Double stopLoss;
  private Double takeProfit;
  private Double risk;

  private List<Pair<LocalDateTime, Double>> interactions;

}
