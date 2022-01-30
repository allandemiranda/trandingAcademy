package br.bti.allandemiranda.forex.model;

import java.time.DayOfWeek;
import java.util.InputMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class CurrencyExchangeTest {

  @Test
  void getAll() {
    // given
    int spread = 0;
    int digits = 5;
    int contractSize = 100000;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = 0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    CurrencyExchange currencyExchange = new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertEquals(spread, currencyExchange.getSpread());
    Assertions.assertEquals(digits, currencyExchange.getDigits());
    Assertions.assertEquals(contractSize, currencyExchange.getContractSize());
    Assertions.assertEquals(currencyPair, currencyExchange.getCurrencyPair());
    Assertions.assertEquals(minimalVolume, currencyExchange.getMinimalVolume());
    Assertions.assertEquals(maximalVolume, currencyExchange.getMaximalVolume());
    Assertions.assertEquals(volumeStep, currencyExchange.getVolumeStep());
    Assertions.assertEquals(swapLong, currencyExchange.getSwapLong());
    Assertions.assertEquals(swapShort, currencyExchange.getSwapShort());
    Assertions.assertEquals(swapThreeDays, currencyExchange.getSwapThreeDays());
  }

  @Test
  void setSwapThreeDaysErrorThrows() {
    // given
    String spread = "1";
    String digits = "5";
    String contractSize = "100000";
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    String minimalVolume = "0.01";
    String maximalVolume = "500";
    String volumeStep = "0.01";
    String swapLong = "-1.2";
    String swapShort = "3.7";
    String swapThreeDays = "abc1";
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setSwapThreeDaysNullThrows() {
    // given
    int spread = 1;
    int digits = 5;
    int contractSize = 100000;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = 0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = null;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setVolumeStepNegativeThrows() {
    // given
    int spread = 1;
    int digits = 5;
    int contractSize = 100000;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = -0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setVolumeStepZeroThrows() {
    // given
    int spread = 1;
    int digits = 5;
    int contractSize = 100000;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = 0;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setVolumeRangeThrows() {
    // given
    int spread = 1;
    int digits = 5;
    int contractSize = 100000;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 500;
    double maximalVolume = 0.01;
    double volumeStep = 0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setCurrencyPairNullThrows() {
    // given
    int spread = 1;
    int digits = 5;
    int contractSize = 100000;
    CurrencyPair currencyPair = null;
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = 0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setContractSizeZeroThrows() {
    // given
    int spread = 1;
    int digits = 5;
    int contractSize = 0;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = 0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setContractSizeNegativeThrows() {
    // given
    int spread = 1;
    int digits = 5;
    int contractSize = -100000;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = 0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setDigitsNegativeThrows() {
    // given
    int spread = 1;
    int digits = -1;
    int contractSize = 100000;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = 0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setDigitsZeroThrows() {
    // given
    int spread = 1;
    int digits = 0;
    int contractSize = 100000;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = 0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setSpreedNegativeThrows() {
    // given
    int spread = -1;
    int digits = 5;
    int contractSize = 100000;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double minimalVolume = 0.01;
    double maximalVolume = 500;
    double volumeStep = 0.01;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void getAllString() {
    // given
    String spread = "0";
    String digits = "5";
    String contractSize = "100000";
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    String minimalVolume = "0.01";
    String maximalVolume = "500";
    String volumeStep = "0.01";
    String swapLong = "-1.2";
    String swapShort = "3.7";
    String swapThreeDays = "monday";
    // when
    CurrencyExchange currencyExchange = new CurrencyExchange(spread, digits, contractSize, currencyPair, minimalVolume, maximalVolume,
        volumeStep, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertEquals(0, currencyExchange.getSpread());
    Assertions.assertEquals(5, currencyExchange.getDigits());
    Assertions.assertEquals(100000, currencyExchange.getContractSize());
    Assertions.assertEquals(currencyPair, currencyExchange.getCurrencyPair());
    Assertions.assertEquals(0.01, currencyExchange.getMinimalVolume());
    Assertions.assertEquals(500, currencyExchange.getMaximalVolume());
    Assertions.assertEquals(0.01, currencyExchange.getVolumeStep());
    Assertions.assertEquals(-1.2, currencyExchange.getSwapLong());
    Assertions.assertEquals(3.7, currencyExchange.getSwapShort());
    Assertions.assertEquals(DayOfWeek.MONDAY, currencyExchange.getSwapThreeDays());
  }
}