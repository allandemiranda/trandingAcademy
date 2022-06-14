package br.bti.allandemiranda.forex.chart;

import br.bti.allandemiranda.forex.candlestick.Candlestick;
import br.bti.allandemiranda.forex.currency.Currency;
import br.bti.allandemiranda.forex.currency.Exchange;
import br.bti.allandemiranda.forex.currency.Pair;
import java.time.DayOfWeek;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

/**
 * The type Chart test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
class ChartTest {

  @Test
  void getCandlestickList() {
    // given
    final int EXPECTED = 1;
    final int INDEX = 0;
    Candlestick candlestick = Mockito.mock(Candlestick.class);
    Exchange exchange = Mockito.mock(Exchange.class);
    // when
    Chart chart = new Chart(exchange);
    chart.getCandlestickList().add(candlestick);
    // then
    Assertions.assertEquals(EXPECTED, chart.getCandlestickList().size());
    Assertions.assertEquals(candlestick, chart.getCandlestickList().get(INDEX));
  }

  @Test
  void getCurrencyExchangeAndAccount() {
    // given
    Exchange exchange = Mockito.mock(Exchange.class);
    // when
    Chart chart = new Chart(exchange);
    // then
    Assertions.assertEquals(exchange, chart.getCurrencyExchange());
  }

  @Test
  void getAccountNullThrow() {
    // given
    Exchange exchange = null;
    // when
    Executable executable = () -> new Chart(exchange);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void testEquals() {
    // given
    Candlestick candlestickA1 = Mockito.mock(Candlestick.class);
    Candlestick candlestickA2 = Mockito.mock(Candlestick.class);
    Exchange exchange = Mockito.mock(Exchange.class);
    int point = 10;
    int point1 = 20;
    // when
    Chart chart = new Chart(exchange);
    chart.getCandlestickList().add(candlestickA1);
    chart.getCandlestickList().add(candlestickA2);

    Chart chart1 = new Chart(exchange);
    chart1.getCandlestickList().add(candlestickA1);
    chart1.getCandlestickList().add(candlestickA2);
    // then
    Assertions.assertEquals(chart, chart1);
    Assertions.assertEquals(chart.getCandlestickList(), chart1.getCandlestickList());
  }

  @Test
  void testHashCode() {
    // given
    Candlestick candlestickA1 = Mockito.mock(Candlestick.class);
    Candlestick candlestickA2 = Mockito.mock(Candlestick.class);
    Exchange exchange = Mockito.mock(Exchange.class);
    int point = 10;
    int point1 = 20;
    // when
    Chart chart = new Chart(exchange);
    chart.getCandlestickList().add(candlestickA1);
    chart.getCandlestickList().add(candlestickA2);

    Chart chart1 = new Chart(exchange);
    chart1.getCandlestickList().add(candlestickA1);
    chart1.getCandlestickList().add(candlestickA2);
    // then
    Assertions.assertEquals(chart.hashCode(), chart1.hashCode());
  }

  @Test
  void testToString() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    Pair pair = new Pair(Currency.EUR, Currency.USD);
    String volume = "10";

    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, pair);

    Exchange exchange = new Exchange(1, 1, pair, 1, 1, DayOfWeek.MONDAY);
    // when
    Chart chart = new Chart(exchange);
    chart.getCandlestickList().add(candlestick);
    // then
    String toString =
        "Chart{" + "candlestickList=[" + "Candlestick{" + "openPrice=0.2," + " closePrice=0.3,"
            + " lowPrice=0.1, highPrice=0.4" + ", volume=10," + " localDateTime=2021-01-01T12:22,"
            + " pair=Pair{base=EUR, profit=USD}}],"
            + " exchange=Exchange{spread=1," + " digits=1,"
            + " pair=Pair{base=EUR, profit=USD}," + " swapLong=1.0," + " swapShort=1.0,"
            + " swapThreeDays=MONDAY}}";
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

    Pair pair = new Pair(Currency.EUR, Currency.USD);
    Pair pair1 = new Pair(Currency.AUD, Currency.USD);

    Exchange exchange = new Exchange(1, 1, pair, 1, 1, DayOfWeek.MONDAY);

    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, pair);
    Candlestick candlestick1 = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, pair1);
    // when
    Chart chart = new Chart(exchange);
    chart.getCandlestickList().add(candlestick);
    chart.getCandlestickList().add(candlestick1);
    // then
    Assertions.assertFalse(chart.candlestickListIsGood());
  }
}