package br.bti.allandemiranda.forex.model;

/**
 * The enum Order forex position.
 *
 * @author Allan de Miranda Silva
 * @version 0.1
 */
public enum OrderForexPosition {
  BUY,  //! Open in Bid (Candlestick price) --> Ask
  SELL  //! Open in Ask --> Bid (Candlestick price)
}
