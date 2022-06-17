package br.bti.allandemiranda.forex.order;

/**
 * The enum Order forex position.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public enum Position {
  BUY,  //! Open in Bid (Candlestick price) --> Ask
  SELL  //! Open in Ask --> Bid (Candlestick price)
}
