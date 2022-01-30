package br.bti.allandemiranda.forex.model;

import java.util.LinkedList;

/**
 * The type Chart.
 *
 * @author Allan de Miranda Silva
 * @version 0.1
 */
public class Chart {

  private static final String NOT_NULL = "can't be a NULL";
  private static final String CURRENCY_EXCHANGE = "Currency Exchange";
  private static final String ACCOUNT = "Account";

  private LinkedList<Candlestick> candlestickList = new LinkedList<>();
  private CurrencyExchange currencyExchange;
  private Account account;

  /**
   * Instantiates a new Chart.
   *
   * @param currencyExchange the currency exchange
   * @param account          the account
   */
  public Chart(CurrencyExchange currencyExchange, Account account) {
    setCurrencyExchange(currencyExchange);
    setAccount(account);
  }

  /**
   * Gets candlestick list.
   *
   * @return the candlestick list
   */
  public LinkedList<Candlestick> getCandlestickList() {
    return candlestickList;
  }

  /**
   * Sets candlestick list.
   *
   * @param candlestickList the candlestick list
   */
  private void setCandlestickList(LinkedList<Candlestick> candlestickList) {
    this.candlestickList = candlestickList;
  }

  /**
   * Gets currency exchange.
   *
   * @return the currency exchange
   */
  public CurrencyExchange getCurrencyExchange() {
    return currencyExchange;
  }

  /**
   * Sets currency exchange.
   *
   * @param currencyExchange the currency exchange
   */
  private void setCurrencyExchange(CurrencyExchange currencyExchange) {
    if(currencyExchange != null) {
      this.currencyExchange = currencyExchange;
    } else {
      throw new IllegalArgumentException(CURRENCY_EXCHANGE + " " + NOT_NULL);
    }
  }

  /**
   * Gets account.
   *
   * @return the account
   */
  public Account getAccount() {
    return account;
  }

  /**
   * Sets account.
   *
   * @param account the account
   */
  private void setAccount(Account account) {
    if(account != null) {
      this.account = account;
    } else {
      throw new IllegalArgumentException(ACCOUNT + " " + NOT_NULL);
    }
  }
}
