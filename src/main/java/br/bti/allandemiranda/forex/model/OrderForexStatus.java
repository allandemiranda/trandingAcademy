package br.bti.allandemiranda.forex.model;

/**
 * The enum Order forex status.
 *
 * @author Allan de Miranda Silva
 * @version 0.1
 */
public enum OrderForexStatus {
  //! Open a position
  OPEN,
  //! Close a position ->> "CLOSE_<CAUSE>"
  ClOSE_MANUALLY,
  ClOSE_MARGIN_LOSS,
  ClOSE_STOP_LOSS,
  ClOSE_TAKE_PROFIT
}
