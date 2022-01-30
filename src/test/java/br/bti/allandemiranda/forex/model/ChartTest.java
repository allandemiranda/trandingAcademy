package br.bti.allandemiranda.forex.model;

import java.util.LinkedList;
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
    Account account = Mockito.mock(Account.class);
    // when
    Chart chart = new Chart(currencyExchange, account);
    chart.getCandlestickList().add(candlestick);
    // then
    Assertions.assertEquals(EXPECTED, chart.getCandlestickList().size());
    Assertions.assertEquals(candlestick, chart.getCandlestickList().get(INDEX));
  }

  @Test
  void getCurrencyExchangeAndAccount() {
    // given
    CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
    Account account = Mockito.mock(Account.class);
    // when
    Chart chart = new Chart(currencyExchange, account);
    // then
    Assertions.assertEquals(currencyExchange, chart.getCurrencyExchange());
    Assertions.assertEquals(account, chart.getAccount());
  }

  @Test
  void getCurrencyExchangeNullThrow() {
    // given
    CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
    Account account = null;
    // when
    Executable executable = () -> new Chart(currencyExchange, account);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);

  }

  @Test
  void getAccountNullThrow() {
    // given
    CurrencyExchange currencyExchange = null;
    Account account = Mockito.mock(Account.class);
    // when
    Executable executable = () -> new Chart(currencyExchange, account);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }
}