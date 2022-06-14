package br.bti.allandemiranda.forex.currency;

import java.util.InputMismatchException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * The type Currency pair.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Pair {

  private static final String VALID_CURRENCY = "need be a valid Currency";
  private static final String NOT_EQUAL = "can't be equal";
  private static final String PROFIT_NAME = "Profit Currency";
  private static final String BASE_NAME = "Base Currency";

  private Currency base;
  private Currency quote;
  private double pip;

  /**
   * Instantiates a new Currency pair.
   *
   * @param base  the base
   * @param quote the quote
   */
  public Pair(@NotNull Currency base, @NotNull Currency quote) {
    setBase(base);
    setQuote(quote);
    setPip(getQuote());
  }

  /**
   * Instantiates a new Currency pair.
   *
   * @param base  the base
   * @param quote the quote
   */
  public Pair(@NotNull String base, @NotNull String quote) {
    setBase(base);
    setProfit(quote);
    setPip(getQuote());
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
    if (base.equals(getQuote())) {
      throw new InputMismatchException(BASE_NAME + " " + NOT_EQUAL + " " + PROFIT_NAME);
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
      throw new IllegalArgumentException(BASE_NAME + " " + VALID_CURRENCY);
    }
  }

  /**
   * Gets quote.
   *
   * @return the quote
   */
  public Currency getQuote() {
    return quote;
  }

  /**
   * Sets quote.
   *
   * @param quote the quote
   */
  private void setQuote(@NotNull Currency quote) {
    if (quote.equals(getBase())) {
      throw new InputMismatchException(PROFIT_NAME + " " + NOT_EQUAL + " " + BASE_NAME);
    } else {
      this.quote = quote;
    }
  }

  /**
   * Sets quote.
   *
   * @param profit the quote
   */
  private void setProfit(@NotNull String profit) {
    try {
      setQuote(Currency.valueOf(profit));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(PROFIT_NAME + " " + VALID_CURRENCY);
    }
  }

  /**
   * Sets pip.
   *
   * @param quote the quote
   */
  private void setPip(Currency quote) {
    if (Currency.JPY.equals(quote)) {
      this.pip = 0.01;
    } else {
      this.pip = 0.0001;
    }
  }

  /**
   * Gets pip.
   *
   * @return the pip
   */
  public double getPip() {
    return pip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Pair that = (Pair) o;
    return base == that.base && quote == that.quote;
  }

  @Override
  public int hashCode() {
    return Objects.hash(base, quote);
  }

  @Override
  public String toString() {
    return "Pair{" + "base=" + base + ", quote=" + quote + '}';
  }
}
