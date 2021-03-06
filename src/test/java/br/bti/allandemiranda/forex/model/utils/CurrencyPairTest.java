package br.bti.allandemiranda.forex.model.utils;

import java.util.InputMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * The type Currency pair test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
class CurrencyPairTest {

  @Test
  void getBase() {
    // given
    Currency currencyBase = Currency.AUD;
    Currency currencyProfit = Currency.EUR;
    // when
    CurrencyPair currencyPair = new CurrencyPair(currencyBase, currencyProfit);
    // then
    Assertions.assertEquals(currencyBase, currencyPair.getBase());
  }

  @Test
  void getBaseString() {
    // given
    String currencyBase = "AUD";
    String currencyProfit = "EUR";
    // when
    CurrencyPair currencyPair = new CurrencyPair(currencyBase, currencyProfit);
    // then
    Assertions.assertEquals(Currency.AUD, currencyPair.getBase());
  }

  @Test
  void sameCurrencyThrows() {
    // given
    Currency currencyBase = Currency.AUD;
    Currency currencyProfit = Currency.AUD;
    // when
    Executable executable = () -> new CurrencyPair(currencyBase, currencyProfit);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void getProfit() {
    // given
    Currency currencyBase = Currency.AUD;
    Currency currencyProfit = Currency.EUR;
    // when
    CurrencyPair currencyPair = new CurrencyPair(currencyBase, currencyProfit);
    // then
    Assertions.assertEquals(currencyProfit, currencyPair.getProfit());
  }

  @Test
  void getProfitString() {
    // given
    String currencyBase = "AUD";
    String currencyProfit = "EUR";
    // when
    CurrencyPair currencyPair = new CurrencyPair(currencyBase, currencyProfit);
    // then
    Assertions.assertEquals(Currency.EUR, currencyPair.getProfit());
  }

  @Test
  void getProfitStringThrows() {
    // given
    String currencyBase = "###";
    String currencyProfit = "EUR";
    // when
    Executable executable = () -> new CurrencyPair(currencyBase, currencyProfit);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void getBaseStringThrows() {
    // given
    String currencyBase = "EUR";
    String currencyProfit = "###";
    // when
    Executable executable = () -> new CurrencyPair(currencyBase, currencyProfit);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void nullCurrencyThrows() {
    // given
    Currency currencyBase = null;
    Currency currencyProfit = Currency.AUD;
    // when
    Executable executable = () -> new CurrencyPair(currencyBase, currencyProfit);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void nullCurrencyThrows2() {
    // given
    Currency currencyBase = Currency.AUD;
    Currency currencyProfit = null;
    // when
    Executable executable = () -> new CurrencyPair(currencyBase, currencyProfit);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }
}