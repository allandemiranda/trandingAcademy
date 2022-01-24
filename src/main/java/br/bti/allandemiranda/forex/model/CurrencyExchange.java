package br.bti.allandemiranda.forex.model;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * The type Currency exchange.
 */
public class CurrencyExchange {

  private Integer spread;
  private Integer digits;
  private Integer contractSize;
  private Currency marginCurrency;
  private Currency profitCurrency;
  private Double minimalVolume;
  private Double maximalVolume;
  private Double volumeStep;
  private Double swapLong;
  private Double swapShort;
  private DayOfWeek swapThreeDays;

  /**
   * Instantiates a new Currency exchange.
   *
   * @param spread         the spread
   * @param digits         the digits
   * @param contractSize   the contract size
   * @param marginCurrency the margin currency
   * @param profitCurrency the profit currency
   * @param minimalVolume  the minimal volume
   * @param maximalVolume  the maximal volume
   * @param volumeStep     the volume step
   * @param swapLong       the swap long
   * @param swapShort      the swap short
   * @param swapThreeDays  the swap three days
   *
   * @throws InterruptedException the interrupted exception
   */
  public CurrencyExchange(Integer spread, Integer digits, Integer contractSize, Currency marginCurrency,
      Currency profitCurrency, Double minimalVolume, Double maximalVolume, Double volumeStep, Double swapLong, Double swapShort,
      DayOfWeek swapThreeDays) throws InterruptedException {
    setSpread(spread);
    setDigits(digits);
    setContractSize(contractSize);
    setCurrency(marginCurrency, profitCurrency);
    setVolumeRange(minimalVolume, maximalVolume);
    setVolumeStep(volumeStep);
    setSwapLong(swapLong);
    setSwapShort(swapShort);
    setSwapThreeDays(swapThreeDays);
  }

  /**
   * Instantiates a new Currency exchange.
   *
   * @param spread         the spread
   * @param digits         the digits
   * @param contractSize   the contract size
   * @param marginCurrency the margin currency
   * @param profitCurrency the profit currency
   * @param minimalVolume  the minimal volume
   * @param maximalVolume  the maximal volume
   * @param volumeStep     the volume step
   * @param swapLong       the swap long
   * @param swapShort      the swap short
   * @param swapThreeDays  the swap three days
   *
   * @throws InterruptedException the interrupted exception
   */
  public CurrencyExchange(Integer spread, Integer digits, Integer contractSize, Currency marginCurrency,
      Currency profitCurrency, Double minimalVolume, Double maximalVolume, Double volumeStep, Double swapLong, Double swapShort,
      Integer swapThreeDays) throws InterruptedException {
    setSpread(spread);
    setDigits(digits);
    setContractSize(contractSize);
    setCurrency(marginCurrency, profitCurrency);
    setVolumeRange(minimalVolume, maximalVolume);
    setVolumeStep(volumeStep);
    setSwapLong(swapLong);
    setSwapShort(swapShort);
    setSwapThreeDays(swapThreeDays);
  }

  /**
   * Instantiates a new Currency exchange.
   *
   * @param spread         the spread
   * @param digits         the digits
   * @param contractSize   the contract size
   * @param marginCurrency the margin currency
   * @param profitCurrency the profit currency
   * @param minimalVolume  the minimal volume
   * @param maximalVolume  the maximal volume
   * @param volumeStep     the volume step
   * @param swapLong       the swap long
   * @param swapShort      the swap short
   * @param swapThreeDays  the swap three days
   *
   * @throws InterruptedException the interrupted exception
   */
  public CurrencyExchange(String spread, String digits, String contractSize, String marginCurrency,
      String profitCurrency, String minimalVolume, String maximalVolume, String volumeStep, String swapLong, String swapShort,
      String swapThreeDays) throws InterruptedException {
    setSpread(spread);
    setDigits(digits);
    setContractSize(contractSize);
    setCurrency(marginCurrency, profitCurrency);
    setVolumeRange(minimalVolume, maximalVolume);
    setVolumeStep(volumeStep);
    setSwapLong(swapLong);
    setSwapShort(swapShort);
    setSwapThreeDays(swapThreeDays);
  }

  /**
   * Gets volume step.
   *
   * @return the volume step
   */
  public Double getVolumeStep() {
    return volumeStep;
  }

  /**
   * Sets volume step.
   *
   * @param volumeStep the volume step
   *
   * @throws InterruptedException the interrupted exception
   */
  private void setVolumeStep(Double volumeStep) throws InterruptedException {
    if (volumeStep == null) {
      throw new NullPointerException("Can't set a NULL Volume Step to this Currency Exchange");
    } else {
      if (volumeStep < 0.0) {
        throw new InterruptedException("Can't set a negative volume step");
      } else {
        this.volumeStep = volumeStep;
      }
    }
  }

  /**
   * Sets volume step.
   *
   * @param volumeStep the volume step
   *
   * @throws InterruptedException the interrupted exception
   */
  private void setVolumeStep(String volumeStep) throws InterruptedException {
    if (volumeStep != null) {
      double temp;
      try {
        temp = Double.parseDouble(volumeStep);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Maximal Volume not accept");
      }
      setVolumeStep(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Volume Step to this Currency Exchange");
    }
  }

  /**
   * Gets minimal volume.
   *
   * @return the minimal volume
   */
  public Double getMinimalVolume() {
    return minimalVolume;
  }

  /**
   * Sets minimal volume.
   *
   * @param minimalVolume the minimal volume
   */
  private void setMinimalVolume(Double minimalVolume) {
    if (minimalVolume == null) {
      throw new NullPointerException("Can't set a NULL Minimal Volume to this Currency Exchange");
    } else {
      this.minimalVolume = minimalVolume;
    }
  }

  /**
   * Gets maximal volume.
   *
   * @return the maximal volume
   */
  public Double getMaximalVolume() {
    return maximalVolume;
  }

  /**
   * Sets maximal volume.
   *
   * @param maximalVolume the maximal volume
   */
  private void setMaximalVolume(Double maximalVolume) {
    if (maximalVolume == null) {
      throw new NullPointerException("Can't set a NULL Maximal Volume to this Currency Exchange");
    } else {
      this.maximalVolume = maximalVolume;
    }
  }

  /**
   * Sets volume range.
   *
   * @param minimalVolume the minimal volume
   * @param maximalVolume the maximal volume
   *
   * @throws InterruptedException the interrupted exception
   */
  private void setVolumeRange(Double minimalVolume, Double maximalVolume) throws InterruptedException {
    if (minimalVolume != null && maximalVolume != null) {
      if (minimalVolume > maximalVolume) {
        setMinimalVolume(minimalVolume);
        setMaximalVolume(maximalVolume);
      } else {
        throw new InterruptedException("Ranger of volume in this Currency Exchange are improper");
      }
    } else {
      throw new NullPointerException("Can't set a NULL Volume to this Currency Exchange");
    }
  }

  /**
   * Sets volume range.
   *
   * @param minimalVolume the minimal volume
   * @param maximalVolume the maximal volume
   *
   * @throws InterruptedException the interrupted exception
   */
  private void setVolumeRange(String minimalVolume, String maximalVolume) throws InterruptedException {
    if (minimalVolume != null && maximalVolume != null) {
      double minimal, maximal;

      try {
        minimal = Double.parseDouble(minimalVolume);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Maximal Volume not accept");
      }

      try {
        maximal = Double.parseDouble(maximalVolume);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Minimal Volume not accept");
      }

      if (minimal > maximal) {
        throw new InterruptedException("Ranger of volume in this Currency Exchange are improper");
      } else {
        setMinimalVolume(minimal);
        setMaximalVolume(maximal);
      }
    } else {
      throw new NullPointerException("Can't set a NULL Volume to this Currency Exchange");
    }
  }

  /**
   * Gets profit currency.
   *
   * @return the profit currency
   */
  public Currency getProfitCurrency() {
    return profitCurrency;
  }

  /**
   * Sets profit currency.
   *
   * @param profitCurrency the profit currency
   */
  private void setProfitCurrency(Currency profitCurrency) {
    if (profitCurrency == null) {
      throw new NullPointerException("Can't set a NULL Profit Currency to this Currency Exchange");
    } else {
      this.profitCurrency = profitCurrency;
    }
  }

  /**
   * Sets currency.
   *
   * @param marginCurrency the margin currency
   * @param profitCurrency the profit currency
   *
   * @throws InterruptedException the interrupted exception
   */
  private void setCurrency(Currency marginCurrency, Currency profitCurrency) throws InterruptedException {
    if (marginCurrency == null || profitCurrency == null) {
      throw new NullPointerException("Can't set a NULL Currency to this Currency Exchange");
    } else {
      if (marginCurrency.equals(profitCurrency)) {
        throw new InterruptedException("Can't set a same currency " + marginCurrency + " " + profitCurrency);
      } else {
        setMarginCurrency(marginCurrency);
        setProfitCurrency(profitCurrency);
      }
    }
  }

  /**
   * Sets currency.
   *
   * @param marginCurrency the margin currency
   * @param profitCurrency the profit currency
   *
   * @throws InterruptedException the interrupted exception
   */
  private void setCurrency(String marginCurrency, String profitCurrency) throws InterruptedException {
    if (marginCurrency == null || profitCurrency == null) {
      throw new NullPointerException("Can't set a NULL Currency to this Currency Exchange");
    } else {
      if (marginCurrency.equals(profitCurrency)) {
        throw new InterruptedException("Can't set a same currency " + marginCurrency + " " + profitCurrency);
      } else {
        try {
          Currency margin = Arrays.stream(Currency.values()).filter(o -> o.toString().equals(marginCurrency)).findFirst().get();
          setMarginCurrency(margin);
          Currency profit = Arrays.stream(Currency.values()).filter(o -> o.toString().equals(profitCurrency)).findFirst().get();
          setProfitCurrency(profit);
        } catch (NoSuchElementException e) {
          throw new NoSuchElementException("Can't set a Currency because we don't have in the list");
        }
      }
    }
  }

  /**
   * Gets margin currency.
   *
   * @return the margin currency
   */
  public Currency getMarginCurrency() {
    return marginCurrency;
  }

  /**
   * Sets margin currency.
   *
   * @param marginCurrency the margin currency
   */
  private void setMarginCurrency(Currency marginCurrency) throws NullPointerException {
    if (marginCurrency == null) {
      throw new NullPointerException("Can't set a NULL Margin Currency to this Currency Exchange");
    } else {
      this.marginCurrency = marginCurrency;
    }
  }

  /**
   * Gets contract size.
   *
   * @return the contract size
   */
  public Integer getContractSize() {
    return contractSize;
  }

  /**
   * Sets contract size.
   *
   * @param contractSize the contract size
   */
  private void setContractSize(Integer contractSize) {
    if (contractSize == null) {
      throw new NullPointerException("Can't set a NULL Digits to this Currency Exchange");
    } else {
      if (contractSize < 1) {
        throw new NumberFormatException("Can't set a negative Digits to this Currency Exchange");
      } else {
        this.contractSize = contractSize;
      }
    }
  }

  /**
   * Sets contract size.
   *
   * @param contractSize the contract size
   */
  private void setContractSize(String contractSize) {
    if (contractSize != null) {
      int temp;
      try {
        temp = Integer.parseInt(contractSize);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Contract Size not accept");
      }
      setContractSize(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Contract Size to this Currency Exchange");
    }
  }

  /**
   * Gets digits.
   *
   * @return the digits
   */
  public Integer getDigits() {
    return digits;
  }

  /**
   * Sets digits.
   *
   * @param digits the digits
   */
  private void setDigits(Integer digits) {
    if (digits == null) {
      throw new NullPointerException("Can't set a NULL Digits to this Currency Exchange");
    } else {
      if (digits < 0) {
        throw new NumberFormatException("Can't set a negative Digits to this Currency Exchange");
      } else {
        this.digits = digits;
      }
    }
  }

  /**
   * Sets digits.
   *
   * @param digits the digits
   */
  private void setDigits(String digits) {
    if (digits != null) {
      int temp;
      try {
        temp = Integer.parseInt(digits);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Swap Long not accept");
      }
      setDigits(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Swap Long to this Currency Exchange");
    }
  }

  /**
   * Gets symbol.
   *
   * @return the symbol
   */
  public String getSymbol() {
    return getMarginCurrency().toString() + " " + getProfitCurrency().toString();
  }

  /**
   * Gets spread.
   *
   * @return the spread
   */
  public Integer getSpread() {
    return spread;
  }

  /**
   * Sets spread.
   *
   * @param spread the spread
   */
  private void setSpread(Integer spread) {
    if (spread == null) {
      throw new NullPointerException("Can't set a NULL Spreed to this Currency Exchange");
    } else {
      if (spread < 0) {
        throw new NumberFormatException("The format negative of Spreed not accept");
      } else {
        this.spread = spread;
      }
    }
  }

  /**
   * Sets spread.
   *
   * @param spread the spread
   */
  private void setSpread(String spread) {
    if (spread != null) {
      int temp;
      try {
        temp = Integer.parseInt(spread);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Swap Long not accept");
      }
      setSpread(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Swap Long to this Currency Exchange");
    }
  }

  /**
   * Gets swap long.
   *
   * @return the swap long
   */
  public Double getSwapLong() {
    return swapLong;
  }

  /**
   * Sets swap long.
   *
   * @param swapLong the swap long
   */
  private void setSwapLong(Double swapLong) {
    if (swapLong == null) {
      throw new NullPointerException("Can't set a NULL Swap Long to this Currency Exchange");
    } else {
      this.swapLong = swapLong;
    }
  }

  /**
   * Sets swap long.
   *
   * @param swapLong the swap long
   */
  private void setSwapLong(String swapLong) {
    if (swapLong != null) {
      double temp;
      try {
        temp = Double.parseDouble(swapLong);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Swap Long not accept");
      }
      setSwapLong(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Swap Long to this Currency Exchange");
    }
  }

  /**
   * Gets swap short.
   *
   * @return the swap short
   */
  public Double getSwapShort() {
    return swapShort;
  }

  /**
   * Sets swap short.
   *
   * @param swapShort the swap short
   */
  private void setSwapShort(Double swapShort) {
    if (swapShort != null) {
      this.swapShort = swapShort;
    } else {
      throw new NullPointerException("Can't set a NULL Swap Short to this Currency Exchange");
    }
  }

  /**
   * Sets swap short.
   *
   * @param swapShort the swap short
   */
  private void setSwapShort(String swapShort) {
    if (swapShort != null) {
      double temp;
      try {
        temp = Double.parseDouble(swapShort);
      } catch (NumberFormatException e) {
        throw new NumberFormatException("The format of Swap Short not accept");
      }
      setSwapShort(temp);
    } else {
      throw new NullPointerException("Can't set a NULL Swap Short to this Currency Exchange");
    }

  }

  /**
   * Gets swap three days.
   *
   * @return the swap three days
   */
  public DayOfWeek getSwapThreeDays() {
    return swapThreeDays;
  }

  /**
   * Sets swap three days.
   *
   * @param swapThreeDays the swap three days
   */
  private void setSwapThreeDays(DayOfWeek swapThreeDays) {
    if (swapThreeDays != null) {
      this.swapThreeDays = swapThreeDays;
    } else {
      throw new NullPointerException("Can't set a NULL Swap Three Days to this Currency Exchange");
    }

  }

  /**
   * Sets swap three days.
   *
   * @param swapThreeDays the swap three days (the day-of-week to represent, from 1 (Monday) to 7 (Sunday))
   */
  private void setSwapThreeDays(Integer swapThreeDays) {
    if (swapThreeDays != null) {
      if (swapThreeDays >= 1 && swapThreeDays <= 7) {
        setSwapThreeDays(DayOfWeek.of(swapThreeDays));
      } else {
        throw new NumberFormatException("Can't set a Swap Three Days negative, zero or more than 7 to this Currency Exchange");
      }
    } else {
      throw new NullPointerException("Can't set a NULL Swap Three Days to this Currency Exchange");
    }
  }

  /**
   * Sets swap three days.
   *
   * @param swapThreeDays the swap three days (the day-of-week to represent, from "1" (Monday, MONDAY, monday) to "7" (Sunday ...))
   *
   * @throws InterruptedException the interrupted exception
   */
  private void setSwapThreeDays(String swapThreeDays) throws InterruptedException {
    switch (swapThreeDays) {
      case "1", "MONDAY", "monday", "Monday" -> setSwapThreeDays(DayOfWeek.MONDAY);
      case "2", "TUESDAY", "tuesday", "Tuesday" -> setSwapThreeDays(DayOfWeek.TUESDAY);
      case "3", "WEDNESDAY", "wednesday", "Wednesday" -> setSwapThreeDays(DayOfWeek.WEDNESDAY);
      case "4", "THURSDAY", "thursday", "Thursday" -> setSwapThreeDays(DayOfWeek.THURSDAY);
      case "5", "FRIDAY", "friday", "Friday" -> setSwapThreeDays(DayOfWeek.FRIDAY);
      case "6", "SATURDAY", "saturday", "Saturday" -> setSwapThreeDays(DayOfWeek.SATURDAY);
      case "7", "SUNDAY", "sunday", "Sunday" -> setSwapThreeDays(DayOfWeek.SUNDAY);
      default -> throw new InterruptedException("The value " + swapThreeDays + " can't be set in this Currency Exchange");
    }
  }

  /**
   * Gets integer swap three days.
   *
   * @return the integer swap three days (the day-of-week to represent, from 1 (Monday) to 7 (Sunday))
   */
  public Integer getIntegerSwapThreeDays() {
    return swapThreeDays.getValue();
  }
}