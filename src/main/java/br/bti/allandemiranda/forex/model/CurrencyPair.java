package br.bti.allandemiranda.forex.model;

import java.util.InputMismatchException;

/**
 * The type Currency pair. (BASE/PROFIT)
 */
public class CurrencyPair {

  private static final String NOT_NULL = "can't be a NULL";
  private static final String VALID_CURRENCY = "need be a valid Currency";
  private static final String NOT_EQUAL = "can't be equal";
  private static final String PROFIT = "Profit Currency";
  private static final String BASE = "Base Currency";

  private Currency base;
  private Currency profit;

  /**
   * Instantiates a new Currency pair.
   *
   * @param base   the base
   * @param profit the profit
   */
  public CurrencyPair(Currency base, Currency profit) {
    setBase(base);
    setProfit(profit);
  }

  /**
   * Instantiates a new Currency pair.
   *
   * @param base   the base
   * @param profit the profit
   */
  public CurrencyPair(String base, String profit) {
    setBase(base);
    setProfit(profit);
  }

  /**
   * Gets base.
   *
   * @return the base
   */
  public Currency getBase() {
    return base;
  }

  /**
   * Sets base.
   *
   * @param base the base
   */
  private void setBase(Currency base) {
    if (base != null) {
      if (base.equals(getProfit())) {
        throw new InputMismatchException(BASE + " " + NOT_EQUAL + " " + PROFIT);
      } else {
        this.base = base;
      }
    } else {
      throw new IllegalArgumentException(BASE + " " + NOT_NULL);
    }
  }

  /**
   * Sets base.
   *
   * @param base the base
   */
  private void setBase(String base) {
    if (base != null) {
      try {
        setBase(Currency.valueOf(base));
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(BASE + " " + VALID_CURRENCY);
      }
    } else {
      throw new IllegalArgumentException(BASE + " " + NOT_NULL);
    }
  }

  /**
   * Gets profit.
   *
   * @return the profit
   */
  public Currency getProfit() {
    return profit;
  }

  /**
   * Sets profit.
   *
   * @param profit the profit
   */
  private void setProfit(Currency profit) {
    if (profit != null) {
      if (profit.equals(getBase())) {
        throw new InputMismatchException(PROFIT + " " + NOT_EQUAL + " " + BASE);
      } else {
        this.profit = profit;
      }
    } else {
      throw new IllegalArgumentException(PROFIT + " " + NOT_NULL);
    }
  }

  /**
   * Sets profit.
   *
   * @param profit the profit
   */
  private void setProfit(String profit) {
    if (profit != null) {
      try {
        setProfit(Currency.valueOf(profit));
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(PROFIT + " " + VALID_CURRENCY);
      }
    } else {
      throw new IllegalArgumentException(PROFIT + "" + NOT_NULL);
    }
  }
}
