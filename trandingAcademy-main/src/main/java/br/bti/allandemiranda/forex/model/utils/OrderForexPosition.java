package br.bti.allandemiranda.forex.model.utils;

/**
 * The enum Order forex position.
 *
 * @author Allan de Miranda Silva
 * @version 0.2
 */
public enum OrderForexPosition {
  BUY,  //! Open in Bid (Candlestick price) --> Ask
  SELL  //! Open in Ask --> Bid (Candlestick price)
}
