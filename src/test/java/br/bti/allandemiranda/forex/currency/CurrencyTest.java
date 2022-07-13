//package br.bti.allandemiranda.forex.currency;
//
//import java.util.Arrays;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
///**
// * The type Currency test.
// *
// * @author Allan de Miranda Silva
// * @version 1.0.0
// */
//class CurrencyTest {
//
//  @Test
//  void valuesSize() {
//    // given
//    int size = 35;
//    // when
//    int trueSize = Currency.values().length;
//    // then
//    Assertions.assertEquals(size, trueSize);
//  }
//
//  @ParameterizedTest
//  @ValueSource(strings = {"EUR", "USD", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "HKD", "NZD", "SEK",
//      "KRW", "SGD", "NOK", "MXN", "INR", "RUB", "ZAR", "TRY", "BRL", "TWD", "DKK", "PLN", "THB", "IDR",
//      "HUF", "CZK", "ILS", "CLP", "PHP", "AED", "COP", "SAR", "MYR", "RON"})
//  void valuesList(String currency) {
//    // given
//    // when
//    String currencyList = Arrays.toString(Currency.values());
//    // then
//    Assertions.assertTrue(currencyList.contains(currency));
//  }
//
//  @ParameterizedTest
//  @ValueSource(strings = {"BTC", "BTCB", "ETH", "ADA", "XRP", "DOGE", "IBEX35", "SMI20", "NASDAQ",
//      "IBOV", "PETR3", "USIM5"})
//  void valuesListFalse(String currency) {
//    // given
//    // when
//    String currencyList = Arrays.toString(Currency.values());
//    // then
//    Assertions.assertFalse(currencyList.contains(currency));
//  }
//
//  @Test
//  void valueOfThrow() {
//    // given
//    String eur = "EURO";
//    String usd = "DOLLAR";
//    // when
//    Executable executable = () -> Currency.valueOf(eur);
//    Executable executable2 = () -> Currency.valueOf(usd);
//
//    // then
//    Assertions.assertThrows(IllegalArgumentException.class, executable);
//    Assertions.assertThrows(IllegalArgumentException.class, executable2);
//  }
//
//  @Test
//  void valueOf() {
//    // given
//    String eur = "EUR";
//    String usd = "USD";
//    // when
//    Currency currencyEUR = Currency.valueOf(eur);
//    Currency currencyUSD = Currency.valueOf(usd);
//
//    // then
//    Assertions.assertEquals(Currency.EUR, currencyEUR);
//    Assertions.assertEquals(Currency.USD, currencyUSD);
//  }
//}