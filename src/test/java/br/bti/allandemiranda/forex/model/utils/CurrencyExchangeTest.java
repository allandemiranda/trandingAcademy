package br.bti.allandemiranda.forex.model.utils;

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
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    CurrencyExchange currencyExchange = new CurrencyExchange(spread, digits, currencyPair, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertEquals(spread, currencyExchange.getSpread());
    Assertions.assertEquals(digits, currencyExchange.getDigits());
    Assertions.assertEquals(currencyPair, currencyExchange.getCurrencyPair());
    Assertions.assertEquals(swapLong, currencyExchange.getSwapLong());
    Assertions.assertEquals(swapShort, currencyExchange.getSwapShort());
    Assertions.assertEquals(swapThreeDays, currencyExchange.getSwapThreeDays());
  }

  @Test
  void setSwapThreeDaysErrorThrows() {
    // given
    String spread = "1";
    String digits = "5";
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    String swapLong = "-1.2";
    String swapShort = "3.7";
    String swapThreeDays = "abc1";
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, currencyPair, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setSwapThreeDaysNullThrows() {
    // given
    int spread = 1;
    int digits = 5;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = null;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, currencyPair, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setCurrencyPairNullThrows() {
    // given
    int spread = 1;
    int digits = 5;
    CurrencyPair currencyPair = null;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, currencyPair, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setDigitsNegativeThrows() {
    // given
    int spread = 1;
    int digits = -1;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, currencyPair, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setDigitsZeroThrows() {
    // given
    int spread = 1;
    int digits = 0;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, currencyPair, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setSpreedNegativeThrows() {
    // given
    int spread = -1;
    int digits = 5;
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new CurrencyExchange(spread, digits, currencyPair, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void getAllString() {
    // given
    String spread = "0";
    String digits = "5";
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    String swapLong = "-1.2";
    String swapShort = "3.7";
    String swapThreeDays = "monday";
    // when
    CurrencyExchange currencyExchange = new CurrencyExchange(spread, digits, currencyPair, swapLong, swapShort, swapThreeDays);
    // then
    Assertions.assertEquals(0, currencyExchange.getSpread());
    Assertions.assertEquals(5, currencyExchange.getDigits());
    Assertions.assertEquals(currencyPair, currencyExchange.getCurrencyPair());
    Assertions.assertEquals(-1.2, currencyExchange.getSwapLong());
    Assertions.assertEquals(3.7, currencyExchange.getSwapShort());
    Assertions.assertEquals(DayOfWeek.MONDAY, currencyExchange.getSwapThreeDays());
  }
}