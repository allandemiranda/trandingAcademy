package br.bti.allandemiranda.forex.model.utils;

/**
 * The enum Order forex status.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public enum OrderForexStatus {
  //! Open a position
  OPEN,
  //! Close a position ->> "CLOSE_<CAUSE>"
  ClOSE_MANUALLY, ClOSE_MARGIN_LOSS, ClOSE_STOP_LOSS, ClOSE_TAKE_PROFIT
}
