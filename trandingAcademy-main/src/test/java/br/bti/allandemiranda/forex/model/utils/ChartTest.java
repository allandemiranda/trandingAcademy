package br.bti.allandemiranda.forex.model.utils;

import java.time.DayOfWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

class ChartTest {

  @Test
  void getCandlestickList() {
    // given
    final int EXPECTED = 1;
    final int INDEX = 0;
    Candlestick candlestick = Mockito.mock(Candlestick.class);
    CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
    // when
    Chart chart = new Chart(currencyExchange);
    chart.getCandlestickList().add(candlestick);
    // then
    Assertions.assertEquals(EXPECTED, chart.getCandlestickList().size());
    Assertions.assertEquals(candlestick, chart.getCandlestickList().get(INDEX));
  }

  @Test
  void getCurrencyExchangeAndAccount() {
    // given
    CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
    // when
    Chart chart = new Chart(currencyExchange);
    // then
    Assertions.assertEquals(currencyExchange, chart.getCurrencyExchange());
  }

  @Test
  void getAccountNullThrow() {
    // given
    CurrencyExchange currencyExchange = null;
    // when
    Executable executable = () -> new Chart(currencyExchange);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void testEquals() {
    // given
    Candlestick candlestickA1 = Mockito.mock(Candlestick.class);
    Candlestick candlestickA2 = Mockito.mock(Candlestick.class);
    CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
    int point = 10;
    int point1 = 20;
    // when
    Chart chart = new Chart(currencyExchange);
    chart.getCandlestickList().add(candlestickA1);
    chart.getCandlestickList().add(candlestickA2);
    chart.addPoints(point);

    Chart chart1 = new Chart(currencyExchange);
    chart1.getCandlestickList().add(candlestickA1);
    chart1.getCandlestickList().add(candlestickA2);
    chart1.addPoints(point1);
    // then
    Assertions.assertEquals(chart, chart1);
    Assertions.assertEquals(chart.getCandlestickList(), chart1.getCandlestickList());
    Assertions.assertNotEquals(chart.getPoints(), chart1.getPoints());
  }

  @Test
  void testHashCode() {
    // given
    Candlestick candlestickA1 = Mockito.mock(Candlestick.class);
    Candlestick candlestickA2 = Mockito.mock(Candlestick.class);
    CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
    int point = 10;
    int point1 = 20;
    // when
    Chart chart = new Chart(currencyExchange);
    chart.getCandlestickList().add(candlestickA1);
    chart.getCandlestickList().add(candlestickA2);
    chart.addPoints(point);

    Chart chart1 = new Chart(currencyExchange);
    chart1.getCandlestickList().add(candlestickA1);
    chart1.getCandlestickList().add(candlestickA2);
    chart1.addPoints(point1);
    // then
    Assertions.assertEquals(chart.hashCode(), chart1.hashCode());
    Assertions.assertNotEquals(chart.getPoints(), chart1.getPoints());
  }

  @Test
  void testToString() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    String volume = "10";

    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume, localDateTime, currencyPair);

    CurrencyExchange currencyExchange = new CurrencyExchange(1, 1, currencyPair, 1, 1, DayOfWeek.MONDAY);
    // when
    Chart chart = new Chart(currencyExchange);
    chart.getCandlestickList().add(candlestick);
    // then
    String toString = "Chart{"
        + "candlestickList=["
        + "Candlestick{"
        + "openPrice=0.2,"
        + " closePrice=0.3,"
        + " lowPrice=0.1, highPrice=0.4"
        + ", volume=10,"
        + " localDateTime=2021-01-01T12:22,"
        + " currencyPair=CurrencyPair{base=EUR, profit=USD}}],"
        + " currencyExchange=CurrencyExchange{spread=1,"
        + " digits=1,"
        + " currencyPair=CurrencyPair{base=EUR, profit=USD},"
        + " swapLong=1.0,"
        + " swapShort=1.0,"
        + " swapThreeDays=MONDAY},"
        + " points=0.0}";
    Assertions.assertEquals(toString, chart.toString());
  }

  @Test
  void candlestickListIsGood() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    String volume = "10";

    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    CurrencyPair currencyPair1 = new CurrencyPair(Currency.AUD, Currency.USD);

    CurrencyExchange currencyExchange = new CurrencyExchange(1, 1, currencyPair, 1, 1, DayOfWeek.MONDAY);

    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume, localDateTime, currencyPair);
    Candlestick candlestick1 = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume, localDateTime, currencyPair1);
    // when
    Chart chart = new Chart(currencyExchange);
    chart.getCandlestickList().add(candlestick);
    chart.getCandlestickList().add(candlestick1);
    // then
    Assertions.assertFalse(chart.candlestickListIsGood());
  }
}