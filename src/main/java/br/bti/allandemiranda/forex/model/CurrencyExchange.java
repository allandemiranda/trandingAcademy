package br.bti.allandemiranda.forex.model;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/**
 * The type Currency exchange.
 */
public class CurrencyExchange {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String VALID_NUMBER = "need be a valid number";
  private static final String BE_GREATER_ZERO = "need be greater than zero";
  private static final String BE_NUMBER = "need be a number";
  private static final String NOT_NULL = "can't be a NULL";
  private static final String LOCAL_DATE_TIME = "Local Date Time";
  private static final String VALID_DATE = "need be a valid date";
  private static final String BE_DATE = "need be a date";


  private static final String SPREED = "Spreed";
  private static final String DIGITS = "Digits";
  private static final String CONTRACT_SIZE = "Contract Size";
  private static final String CURRENT_PAIR = "Currency Pair";
  private static final String MINIMAL_VOLUME = "Minimal Volume";
  private static final String MAXIMAL_VOLUME = "Maximal Volume";
  private static final String VOLUME_STEP = "Volume Step";


  private int spread; //! In points
  private int digits; //! In digits
  private int contractSize;
  private CurrencyPair currencyPair;
  private double minimalVolume;
  private double maximalVolume;
  private double volumeStep;
  private double swapLong;  //! In points
  private double swapShort; //! In points
  private DayOfWeek swapThreeDays;


  public int getSpread() {
    return spread;
  }

  private void setSpread(int spread) {
    if (Math.abs(spread) == spread) {
      this.spread = spread;
    } else {
      throw new InputMismatchException(SPREED + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  private void setSpread(String spread) {
    if (spread != null) {
      if (!spread.isBlank()) {
        try {
          setSpread(Integer.parseInt(spread));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(SPREED + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(SPREED + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(SPREED + " " + NOT_NULL);
    }
  }

  public int getDigits() {
    return digits;
  }

  private void setDigits(int digits) {
    if (digits > 0) {
      this.digits = digits;
    } else {
      throw new InputMismatchException(DIGITS + " " + BE_GREATER_ZERO);
    }
  }

  private void setDigits(String digits) {
    if (digits != null) {
      if (!digits.isBlank()) {
        try {
          setDigits(Integer.parseInt(digits));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(DIGITS + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(DIGITS + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(DIGITS + " " + NOT_NULL);
    }
  }

  public int getContractSize() {
    return contractSize;
  }

  private void setContractSize(int contractSize) {
    if (contractSize > 0) {
      this.contractSize = contractSize;
    } else {
      throw new InputMismatchException(CONTRACT_SIZE + " " + BE_GREATER_ZERO);
    }
  }

  private void setContractSize(String contractSize) {
    if (contractSize != null) {
      if (!contractSize.isBlank()) {
        try {
          setContractSize(Integer.parseInt(contractSize));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(CONTRACT_SIZE + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(CONTRACT_SIZE + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(CONTRACT_SIZE + " " + NOT_NULL);
    }
  }

  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }

  private void setCurrencyPair(CurrencyPair currencyPair) {
    if (currencyPair != null) {
      this.currencyPair = currencyPair;
    } else {
      throw new IllegalArgumentException(CURRENT_PAIR + " " + NOT_NULL);
    }
  }

  public double getMinimalVolume() {
    return minimalVolume;
  }

  private void setMinimalVolume(double minimalVolume) {
    if (minimalVolume > 0.0) {
      if (minimalVolume <= getMaximalVolume()) {
        this.minimalVolume = minimalVolume;
      } else {
        throw new IllegalArgumentException(MINIMAL_VOLUME + " need be low or equal than " + MAXIMAL_VOLUME);
      }
    } else {
      throw new InputMismatchException(MINIMAL_VOLUME + " " + BE_GREATER_ZERO);
    }
  }

  private void setMinimalVolume(String minimalVolume) {
    if (minimalVolume != null) {
      if (!minimalVolume.isBlank()) {
        try {
          setMinimalVolume(Double.parseDouble(minimalVolume));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(MINIMAL_VOLUME + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(MINIMAL_VOLUME + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(MINIMAL_VOLUME + " " + NOT_NULL);
    }
  }

  public double getMaximalVolume() {
    return maximalVolume;
  }

  private void setMaximalVolume(double maximalVolume) {
    if (maximalVolume > 0.0) {
      if (maximalVolume >= getMaximalVolume()) {
        this.maximalVolume = maximalVolume;
      } else {
        throw new IllegalArgumentException(MINIMAL_VOLUME + " need be low or equal than " + MAXIMAL_VOLUME);
      }
    } else {
      throw new InputMismatchException(MAXIMAL_VOLUME + " " + BE_GREATER_ZERO);
    }
  }

  private void setMaximalVolume(String maximalVolume) {
    if (maximalVolume != null) {
      if (!maximalVolume.isBlank()) {
        try {
          setMaximalVolume(Double.parseDouble(maximalVolume));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(MAXIMAL_VOLUME + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(MAXIMAL_VOLUME + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(MAXIMAL_VOLUME + " " + NOT_NULL);
    }
  }

  public double getVolumeStep() {
    return volumeStep;
  }

  public void setVolumeStep(double volumeStep) {
    if (volumeStep > 0.0) {
      this.volumeStep = volumeStep;
    } else {
      throw new InputMismatchException(VOLUME_STEP + " " + BE_GREATER_ZERO);
    }
  }

  //TODO --->

  public double getSwapLong() {
    return swapLong;
  }

  public void setSwapLong(double swapLong) {
    this.swapLong = swapLong;
  }

  public double getSwapShort() {
    return swapShort;
  }

  public void setSwapShort(double swapShort) {
    this.swapShort = swapShort;
  }

  public DayOfWeek getSwapThreeDays() {
    return swapThreeDays;
  }

  public void setSwapThreeDays(DayOfWeek swapThreeDays) {
    this.swapThreeDays = swapThreeDays;
  }
}