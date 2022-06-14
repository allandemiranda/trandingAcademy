package br.bti.allandemiranda.forex.currency;

import java.time.DayOfWeek;
import java.util.InputMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * The type Currency exchange test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
class ExchangeTest {

  @Test
  void getAll() {
    // given
    int spread = 0;
    int digits = 5;
    Pair pair = new Pair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Exchange exchange = new Exchange(spread, digits, pair, swapLong,
        swapShort, swapThreeDays);
    // then
    Assertions.assertEquals(spread, exchange.getSpread());
    Assertions.assertEquals(digits, exchange.getDigits());
    Assertions.assertEquals(pair, exchange.getCurrencyPair());
    Assertions.assertEquals(swapLong, exchange.getSwapLong());
    Assertions.assertEquals(swapShort, exchange.getSwapShort());
    Assertions.assertEquals(swapThreeDays, exchange.getSwapThreeDays());
  }

  @Test
  void setSwapThreeDaysErrorThrows() {
    // given
    String spread = "1";
    String digits = "5";
    Pair pair = new Pair(Currency.EUR, Currency.USD);
    String swapLong = "-1.2";
    String swapShort = "3.7";
    String swapThreeDays = "abc1";
    // when
    Executable executable = () -> new Exchange(spread, digits, pair, swapLong, swapShort,
        swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setSwapThreeDaysNullThrows() {
    // given
    int spread = 1;
    int digits = 5;
    Pair pair = new Pair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = null;
    // when
    Executable executable = () -> new Exchange(spread, digits, pair, swapLong, swapShort,
        swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setCurrencyPairNullThrows() {
    // given
    int spread = 1;
    int digits = 5;
    Pair pair = null;
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new Exchange(spread, digits, pair, swapLong, swapShort,
        swapThreeDays);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setDigitsNegativeThrows() {
    // given
    int spread = 1;
    int digits = -1;
    Pair pair = new Pair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new Exchange(spread, digits, pair, swapLong, swapShort,
        swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setDigitsZeroThrows() {
    // given
    int spread = 1;
    int digits = 0;
    Pair pair = new Pair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new Exchange(spread, digits, pair, swapLong, swapShort,
        swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setSpreedNegativeThrows() {
    // given
    int spread = -1;
    int digits = 5;
    Pair pair = new Pair(Currency.EUR, Currency.USD);
    double swapLong = -1.2;
    double swapShort = 3.7;
    DayOfWeek swapThreeDays = DayOfWeek.MONDAY;
    // when
    Executable executable = () -> new Exchange(spread, digits, pair, swapLong, swapShort,
        swapThreeDays);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void getAllString() {
    // given
    String spread = "0";
    String digits = "5";
    Pair pair = new Pair(Currency.EUR, Currency.USD);
    String swapLong = "-1.2";
    String swapShort = "3.7";
    String swapThreeDays = "monday";
    // when
    Exchange exchange = new Exchange(spread, digits, pair, swapLong,
        swapShort, swapThreeDays);
    // then
    Assertions.assertEquals(0, exchange.getSpread());
    Assertions.assertEquals(5, exchange.getDigits());
    Assertions.assertEquals(pair, exchange.getCurrencyPair());
    Assertions.assertEquals(-1.2, exchange.getSwapLong());
    Assertions.assertEquals(3.7, exchange.getSwapShort());
    Assertions.assertEquals(DayOfWeek.MONDAY, exchange.getSwapThreeDays());
  }
}