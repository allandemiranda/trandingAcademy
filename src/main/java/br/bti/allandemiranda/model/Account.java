package br.bti.allandemiranda.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

/**
 * The type Account.
 */
public class Account {

  private static final String LEVERAGE_ONE_PER = "1:";
  private Currency currency;
  private Integer leverage;
  private LinkedList<Pair<LocalDateTime, Double>> amountList;

  public Account(Currency currency, Integer leverage, Double amount) {
    setCurrency(currency);
    setLeverage(leverage);
    setInitialAmount(amount);
  }

  public Account(Currency currency, String leverage, Double amount) {
    setCurrency(currency);
    setLeverage(leverage);
    setInitialAmount(amount);
  }

  /**
   * Gets amount list.
   *
   * @return the amount list
   */
  public LinkedList<Pair<LocalDateTime, Double>> getAmountList() {
    return amountList;
  }

  /**
   * Set initial amount.
   *
   * @param amount the amount
   */
  private void setInitialAmount(@NotNull Double amount) {
    getAmountList().add(Pair.of(LocalDateTime.now(), amount));
  }

  public Double pushAmount(@NotNull Double addAmount, @NotNull LocalDateTime localDateTime) throws InterruptedException {
    if (getAmountList().size() > 1) {
      if (localDateTime.isAfter(getAmountList().getLast().getLeft())) {
        Double newAmount = getAmountList().getLast().getRight() + addAmount;
        getAmountList().add(Pair.of(localDateTime, newAmount));
        return newAmount;
      } else {
        throw new InterruptedException("Can't add this amount because the date is old");
      }
    } else {
      if (getAmountList().size() == 1) {
        Double amountTemp = getAmountList().getLast().getRight();
        getAmountList().clear();
        getAmountList().add(Pair.of(localDateTime.minusSeconds(60), amountTemp));
        Double newAmount = amountTemp + addAmount;
        getAmountList().add(Pair.of(localDateTime, newAmount));
        return newAmount;
      } else {
        throw new NoSuchElementException("Can't set a new amount because we don't find a fist value");
      }
    }
  }

  /**
   * Gets currency.
   *
   * @return the currency
   */
  public Currency getCurrency() {
    return currency;
  }

  /**
   * Sets currency.
   *
   * @param currency the currency
   */
  private void setCurrency(Currency currency) {
    if (currency == null) {
      throw new NullPointerException("Can't set a NULL Currency to Account");
    } else {
      this.currency = currency;
    }
  }

  /**
   * Sets currency.
   *
   * @param currency the currency
   */
  private void setCurrency(String currency) {
    try {
      Currency tempCurrency = Arrays.stream(Currency.values())
          .filter(o -> o.toString().equals(currency)).findFirst().get();
      setCurrency(tempCurrency);
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("Can't set a Currency because we don't have in the list");
    }
  }

  /**
   * Gets leverage.
   *
   * @return the leverage
   */
  public Integer getLeverage() {
    return leverage;
  }

  /**
   * Sets leverage.
   *
   * @param leverage the leverage (Ex 1:500)
   */
  private void setLeverage(@NotNull String leverage) {
    setLeverage(Integer.parseInt(leverage.replaceAll(LEVERAGE_ONE_PER, "")));
  }

  /**
   * Sets leverage.
   *
   * @param leverage the leverage
   */
  private void setLeverage(Integer leverage) {
    if (leverage == null) {
      throw new NullPointerException("Can't set a NULL Leverage to Account");
    } else {
      this.leverage = leverage;
    }
  }

  /**
   * Gets full leverage.
   *
   * @return the full leverage (Ex 1:500)
   */
  public String getFullLeverage() {
    return LEVERAGE_ONE_PER + leverage;
  }
}
