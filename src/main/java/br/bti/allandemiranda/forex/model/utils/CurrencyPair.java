package br.bti.allandemiranda.forex.model.utils;

import java.util.InputMismatchException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The type Currency pair.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class CurrencyPair {

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
  public CurrencyPair(@NotNull Currency base, @NotNull Currency profit) {
    setBase(base);
    setProfit(profit);
  }

  /**
   * Instantiates a new Currency pair.
   *
   * @param base   the base
   * @param profit the profit
   */
  public CurrencyPair(@NotNull String base, @NotNull String profit) {
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
  private void setBase(@NotNull Currency base) {
    if (base.equals(getProfit())) {
      throw new InputMismatchException(BASE + " " + NOT_EQUAL + " " + PROFIT);
    } else {
      this.base = base;
    }
  }

  /**
   * Sets base.
   *
   * @param base the base
   */
  private void setBase(@NotNull String base) {
    try {
      setBase(Currency.valueOf(base));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(BASE + " " + VALID_CURRENCY);
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
  private void setProfit(@NotNull Currency profit) {
    if (profit.equals(getBase())) {
      throw new InputMismatchException(PROFIT + " " + NOT_EQUAL + " " + BASE);
    } else {
      this.profit = profit;
    }
  }

  /**
   * Sets profit.
   *
   * @param profit the profit
   */
  private void setProfit(@NotNull String profit) {
    try {
      setProfit(Currency.valueOf(profit));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(PROFIT + " " + VALID_CURRENCY);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CurrencyPair that = (CurrencyPair) o;
    return base == that.base && profit == that.profit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(base, profit);
  }

  @Override
  public String toString() {
    return "CurrencyPair{" + "base=" + base + ", profit=" + profit + '}';
  }
}
