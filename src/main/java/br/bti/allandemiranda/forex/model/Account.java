package br.bti.allandemiranda.forex.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * The type Account.
 */
public class Account {

  private static final String LEVERAGE_ONE_PER = "1:";
  private Currency currency;
  private Integer leverage;
  private LinkedList<DataAmount> amountList;

  /**
   * Instantiates a new Account.
   *
   * @param currency the currency
   * @param leverage the leverage
   * @param amount   the amount
   */
  public Account(Currency currency, Integer leverage, Double amount) {
    setCurrency(currency);
    setLeverage(leverage);
    setInitialAmount(amount);
  }

  /**
   * Instantiates a new Account.
   *
   * @param currency the currency
   * @param leverage the leverage
   * @param amount   the amount
   */
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
  public LinkedList<DataAmount> getAmountList() {
    return amountList;
  }

  /**
   * Set initial amount.
   *
   * @param amount the amount
   */
  private void setInitialAmount(Double amount) {
    if (amount != null) {
      getAmountList().add(new DataAmount(LocalDateTime.now(), amount, amount));
    } else {
      throw new NullPointerException("Can't set a NULL Initial Amount to Account");
    }
  }

  /**
   * Push amount and margin.
   *
   * @param addAmount     the add amount
   * @param addMargin     the add margin
   * @param localDateTime the local date time
   *
   * @throws InterruptedException the interrupted exception
   */
  public void pushAmountAndMargin(Double addAmount, Double addMargin, LocalDateTime localDateTime) throws InterruptedException {
    if (addAmount != null && localDateTime != null) {
      if (getAmountList().size() > 1) {
        if (localDateTime.isAfter(getAmountList().getLast().getLocalDateTime())) {
          Double newAmount = getAmountList().getLast().getAmount() + addAmount;
          Double margin = getAmountList().getLast().getMargin() + addMargin;
          getAmountList().add(new DataAmount(localDateTime, newAmount, margin));
        } else {
          throw new InterruptedException("Can't add this amount because the date is old");
        }
      } else {
        if (getAmountList().size() == 1) {
          Double amountTemp = getAmountList().getLast().getAmount();
          Double marginTemp = getAmountList().getLast().getMargin();
          getAmountList().clear();
          getAmountList().add(new DataAmount(localDateTime.minusSeconds(60), amountTemp, marginTemp));
          Double newAmount = amountTemp + addAmount;
          Double newmargin = marginTemp + addMargin;
          getAmountList().add(new DataAmount(localDateTime, newAmount, newmargin));
        } else {
          throw new NoSuchElementException("Can't set a new amount because we don't find a fist value");
        }
      }
    } else {
      throw new NullPointerException("Can't set a NULL Push Amount to Account");
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
    if (currency == null) {
      throw new NullPointerException("Can't set a NULL Currency to Account");
    } else {
      try {
        Currency tempCurrency = Arrays.stream(Currency.values())
            .filter(o -> o.toString().equals(currency)).findFirst().get();
        setCurrency(tempCurrency);
      } catch (NoSuchElementException e) {
        throw new NoSuchElementException("Can't set a Currency because we don't have in the list");
      }
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
  private void setLeverage(String leverage) {
    if (leverage != null) {
      int temp;
      try {
        temp = Integer.parseInt(leverage.replaceAll(LEVERAGE_ONE_PER, ""));
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Leverage not accept");
      }
      setLeverage(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Leverage to Account");
    }
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
    return LEVERAGE_ONE_PER + getLeverage().toString();
  }

  /**
   * The type Data amount.
   */
  private record DataAmount(LocalDateTime localDateTime, Double amount, Double margin) {

    /**
     * Gets local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getLocalDateTime() {
      return localDateTime;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public Double getAmount() {
      return amount;
    }

    /**
     * Gets margin.
     *
     * @return the margin
     */
    public Double getMargin() {
      return margin;
    }
  }
}