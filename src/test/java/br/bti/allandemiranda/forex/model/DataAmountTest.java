package br.bti.allandemiranda.forex.model;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

class DataAmountTest {

  @ParameterizedTest
  @ValueSource(doubles = { 100.0, 50.50, 0.01, 0, -100.0, -50.50, -0.01})
  void getAll(double netAmount) {
    // given
    Currency currency = Currency.EUR;
    LocalDateTime localDateTime = LocalDateTime.now();
    double grossAmount = Mockito.anyDouble();
    double price = Mockito.anyDouble();

    // when
    DataAmount dataAmount = new DataAmount(currency, localDateTime, netAmount, grossAmount, price);

    // then
    Assertions.assertEquals(currency, dataAmount.getCurrency());
    Assertions.assertEquals(localDateTime, dataAmount.getLocalDateTime());
    Assertions.assertEquals(netAmount, dataAmount.getNetAmount());
    Assertions.assertEquals(grossAmount, dataAmount.getGrossAmount());
    Assertions.assertEquals(price, dataAmount.getPrice());
  }

  @ParameterizedTest
  @ValueSource(doubles = { 100.0, 50.50, 0.01, 0, -100.0, -50.50, -0.01})
  void getAll2(double grossAmount){
    // given
    Currency currency = Currency.EUR;
    LocalDateTime localDateTime = LocalDateTime.now();
    double netAmount = Mockito.anyDouble();
    double price = Mockito.anyDouble();

    // when
    DataAmount dataAmount = new DataAmount(currency, localDateTime, netAmount, grossAmount, price);

    // then
    Assertions.assertEquals(currency, dataAmount.getCurrency());
    Assertions.assertEquals(localDateTime, dataAmount.getLocalDateTime());
    Assertions.assertEquals(netAmount, dataAmount.getNetAmount());
    Assertions.assertEquals(grossAmount, dataAmount.getGrossAmount());
  }

  @Test
  void setCurrencyNullThrows() {
    // given
    Currency currency = null;
    LocalDateTime localDateTime = LocalDateTime.now();

    // when
    Executable executable = () -> new DataAmount(currency, localDateTime, Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble());

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setLocalDateTimeNullThrows() {
    // given
    Currency currency = Currency.EUR;
    LocalDateTime localDateTime = null;

    // when
    Executable executable = () -> new DataAmount(currency, localDateTime, Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyDouble());

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setPriceNullThrows() {
    // given
    Currency currency = Currency.EUR;
    LocalDateTime localDateTime = LocalDateTime.now();
    double price = -1.1;

    // when
    Executable executable = () -> new DataAmount(currency, localDateTime, Mockito.anyDouble(), Mockito.anyDouble(), price);

    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }
}