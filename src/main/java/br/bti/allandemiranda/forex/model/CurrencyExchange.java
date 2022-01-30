package br.bti.allandemiranda.forex.model;

import java.time.DayOfWeek;
import java.util.InputMismatchException;

/**
 * The type Currency exchange.
 *
 * @author Allan de Miranda Silva
 * @version 0.1
 */
public class CurrencyExchange {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String VALID_NUMBER = "need be a valid number";
  private static final String BE_GREATER_ZERO = "need be greater than zero";
  private static final String BE_NUMBER = "need be a number";
  private static final String NOT_NULL = "can't be a NULL";
  private static final String SPREED = "Spreed";
  private static final String DIGITS = "Digits";
  private static final String CONTRACT_SIZE = "Contract Size";
  private static final String CURRENT_PAIR = "Currency Pair";
  private static final String MINIMAL_VOLUME = "Minimal Volume";
  private static final String MAXIMAL_VOLUME = "Maximal Volume";
  private static final String VOLUME_STEP = "Volume Step";
  private static final String SWAP_LONG = "Swap Long";
  private static final String SWAP_SHORT = "Swap Short";
  private static final String SWAP_THREE_DAYS = "Swap Three Days";

  private int spread;               //! In points
  private int digits;               //! In digits
  private int contractSize;
  private CurrencyPair currencyPair;
  private double minimalVolume;
  private double maximalVolume;
  private double volumeStep;
  private double swapLong;          //! In points
  private double swapShort;         //! In points
  private DayOfWeek swapThreeDays;

  /**
   * Instantiates a new Currency exchange.
   *
   * @param spread        the spread
   * @param digits        the digits
   * @param contractSize  the contract size
   * @param currencyPair  the currency pair
   * @param minimalVolume the minimal volume
   * @param maximalVolume the maximal volume
   * @param volumeStep    the volume step
   * @param swapLong      the swap long
   * @param swapShort     the swap short
   * @param swapThreeDays the swap three days
   */
  public CurrencyExchange(int spread, int digits, int contractSize, CurrencyPair currencyPair, double minimalVolume, double maximalVolume,
      double volumeStep, double swapLong, double swapShort, DayOfWeek swapThreeDays) {
    setSpread(spread);
    setDigits(digits);
    setContractSize(contractSize);
    setCurrencyPair(currencyPair);
    setVolumeRange(minimalVolume, maximalVolume);
    setVolumeStep(volumeStep);
    setSwapLong(swapLong);
    setSwapShort(swapShort);
    setSwapThreeDays(swapThreeDays);
  }

  /**
   * Instantiates a new Currency exchange.
   *
   * @param spread        the spread
   * @param digits        the digits
   * @param contractSize  the contract size
   * @param currencyPair  the currency pair
   * @param minimalVolume the minimal volume
   * @param maximalVolume the maximal volume
   * @param volumeStep    the volume step
   * @param swapLong      the swap long
   * @param swapShort     the swap short
   * @param swapThreeDays the swap three days
   */
  public CurrencyExchange(String spread, String digits, String contractSize, CurrencyPair currencyPair, String minimalVolume,
      String maximalVolume, String volumeStep, String swapLong, String swapShort, String swapThreeDays) {
    setSpread(spread);
    setDigits(digits);
    setContractSize(contractSize);
    setCurrencyPair(currencyPair);
    setVolumeRange(minimalVolume, maximalVolume);
    setVolumeStep(volumeStep);
    setSwapLong(swapLong);
    setSwapShort(swapShort);
    setSwapThreeDays(swapThreeDays);
  }

  /**
   * Gets spread.
   *
   * @return the spread
   */
  public int getSpread() {
    return spread;
  }

  /**
   * Sets spread.
   *
   * @param spread the spread
   */
  private void setSpread(int spread) {
    if (Math.abs(spread) == spread) {
      this.spread = spread;
    } else {
      throw new InputMismatchException(SPREED + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Sets spread.
   *
   * @param spread the spread
   */
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

  /**
   * Gets digits.
   *
   * @return the digits
   */
  public int getDigits() {
    return digits;
  }

  /**
   * Sets digits.
   *
   * @param digits the digits
   */
  private void setDigits(int digits) {
    if (digits > 0) {
      this.digits = digits;
    } else {
      throw new InputMismatchException(DIGITS + " " + BE_GREATER_ZERO);
    }
  }

  /**
   * Sets digits.
   *
   * @param digits the digits
   */
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

  /**
   * Gets contract size.
   *
   * @return the contract size
   */
  public int getContractSize() {
    return contractSize;
  }

  /**
   * Sets contract size.
   *
   * @param contractSize the contract size
   */
  private void setContractSize(int contractSize) {
    if (contractSize > 0) {
      this.contractSize = contractSize;
    } else {
      throw new InputMismatchException(CONTRACT_SIZE + " " + BE_GREATER_ZERO);
    }
  }

  /**
   * Sets contract size.
   *
   * @param contractSize the contract size
   */
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

  /**
   * Gets currency pair.
   *
   * @return the currency pair
   */
  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }

  /**
   * Sets currency pair.
   *
   * @param currencyPair the currency pair
   */
  private void setCurrencyPair(CurrencyPair currencyPair) {
    if (currencyPair != null) {
      this.currencyPair = currencyPair;
    } else {
      throw new IllegalArgumentException(CURRENT_PAIR + " " + NOT_NULL);
    }
  }

  /**
   * Gets minimal volume.
   *
   * @return the minimal volume
   */
  public double getMinimalVolume() {
    return minimalVolume;
  }

  /**
   * Sets minimal volume.
   *
   * @param minimalVolume the minimal volume
   */
  private void setMinimalVolume(double minimalVolume) {
    if (minimalVolume > 0.0) {
      this.minimalVolume = minimalVolume;
    } else {
      throw new InputMismatchException(MINIMAL_VOLUME + " " + BE_GREATER_ZERO);
    }
  }

  /**
   * Sets minimal volume.
   *
   * @param minimalVolume the minimal volume
   */
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

  /**
   * Gets maximal volume.
   *
   * @return the maximal volume
   */
  public double getMaximalVolume() {
    return maximalVolume;
  }

  /**
   * Sets maximal volume.
   *
   * @param maximalVolume the maximal volume
   */
  private void setMaximalVolume(double maximalVolume) {
    if (maximalVolume > 0.0) {
      this.maximalVolume = maximalVolume;
    } else {
      throw new InputMismatchException(MAXIMAL_VOLUME + " " + BE_GREATER_ZERO);
    }
  }

  /**
   * Sets maximal volume.
   *
   * @param maximalVolume the maximal volume
   */
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

  /**
   * Sets volume range.
   *
   * @param minimalVolume the minimal volume
   * @param maximalVolume the maximal volume
   */
  private void setVolumeRange(double minimalVolume, double maximalVolume) {
    if (maximalVolume >= minimalVolume) {
      setMinimalVolume(minimalVolume);
      setMaximalVolume(maximalVolume);
    } else {
      throw new IllegalArgumentException(MINIMAL_VOLUME + " need be low or equal than " + MAXIMAL_VOLUME);
    }
  }

  /**
   * Sets volume range.
   *
   * @param minimalVolume the minimal volume
   * @param maximalVolume the maximal volume
   */
  private void setVolumeRange(String minimalVolume, String maximalVolume) {
    setMinimalVolume(minimalVolume);
    setMaximalVolume(maximalVolume);
    if (!(getMaximalVolume() >= getMinimalVolume())) {
      throw new IllegalArgumentException(MINIMAL_VOLUME + " need be low or equal than " + MAXIMAL_VOLUME);
    }
  }

  /**
   * Gets volume step.
   *
   * @return the volume step
   */
  public double getVolumeStep() {
    return volumeStep;
  }

  /**
   * Sets volume step.
   *
   * @param volumeStep the volume step
   */
  private void setVolumeStep(double volumeStep) {
    if (volumeStep > 0.0) {
      this.volumeStep = volumeStep;
    } else {
      throw new InputMismatchException(VOLUME_STEP + " " + BE_GREATER_ZERO);
    }
  }

  /**
   * Sets volume step.
   *
   * @param volumeStep the volume step
   */
  private void setVolumeStep(String volumeStep) {
    if (volumeStep != null) {
      if (!volumeStep.isBlank()) {
        try {
          setVolumeStep(Double.parseDouble(volumeStep));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(VOLUME_STEP + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(VOLUME_STEP + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(VOLUME_STEP + " " + NOT_NULL);
    }
  }

  /**
   * Gets swap long.
   *
   * @return the swap long
   */
  public double getSwapLong() {
    return swapLong;
  }

  /**
   * Sets swap long.
   *
   * @param swapLong the swap long
   */
  private void setSwapLong(double swapLong) {
    this.swapLong = swapLong;
  }

  /**
   * Sets swap long.
   *
   * @param swapLong the swap long
   */
  private void setSwapLong(String swapLong) {
    if (swapLong != null) {
      if (!swapLong.isBlank()) {
        try {
          setSwapLong(Double.parseDouble(swapLong));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(SWAP_LONG + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(SWAP_LONG + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(SWAP_LONG + " " + NOT_NULL);
    }
  }

  /**
   * Gets swap short.
   *
   * @return the swap short
   */
  public double getSwapShort() {
    return swapShort;
  }

  /**
   * Sets swap short.
   *
   * @param swapShort the swap short
   */
  private void setSwapShort(double swapShort) {
    this.swapShort = swapShort;
  }

  /**
   * Sets swap short.
   *
   * @param swapShort the swap short
   */
  private void setSwapShort(String swapShort) {
    if (swapShort != null) {
      if (!swapShort.isBlank()) {
        try {
          setSwapShort(Double.parseDouble(swapShort));
        } catch (NumberFormatException e) {
          throw new NumberFormatException(SWAP_SHORT + " " + VALID_NUMBER);
        }
      } else {
        throw new IllegalArgumentException(SWAP_SHORT + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(SWAP_SHORT + " " + NOT_NULL);
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
      throw new IllegalArgumentException(SWAP_THREE_DAYS + " " + NOT_NULL);
    }
  }

  /**
   * Sets swap three days.
   *
   * @param swapThreeDays the swap three days
   */
  private void setSwapThreeDays(String swapThreeDays) {
    if (swapThreeDays != null) {
      if (!swapThreeDays.isBlank()) {
        try {
          setSwapThreeDays(DayOfWeek.valueOf(swapThreeDays));
        } catch (IllegalArgumentException e) {
          switch (swapThreeDays) {
            case "1", "MONDAY", "monday", "Monday" -> setSwapThreeDays(DayOfWeek.MONDAY);
            case "2", "TUESDAY", "tuesday", "Tuesday" -> setSwapThreeDays(DayOfWeek.TUESDAY);
            case "3", "WEDNESDAY", "wednesday", "Wednesday" -> setSwapThreeDays(DayOfWeek.WEDNESDAY);
            case "4", "THURSDAY", "thursday", "Thursday" -> setSwapThreeDays(DayOfWeek.THURSDAY);
            case "5", "FRIDAY", "friday", "Friday" -> setSwapThreeDays(DayOfWeek.FRIDAY);
            case "6", "SATURDAY", "saturday", "Saturday" -> setSwapThreeDays(DayOfWeek.SATURDAY);
            case "7", "SUNDAY", "sunday", "Sunday" -> setSwapThreeDays(DayOfWeek.SUNDAY);
            default -> throw new IllegalArgumentException(SWAP_THREE_DAYS + " need be a valid week name");
          }
        }
      } else {
        throw new IllegalArgumentException(SWAP_SHORT + " " + BE_NUMBER);
      }
    } else {
      throw new IllegalArgumentException(SWAP_SHORT + " " + NOT_NULL);
    }
  }
}