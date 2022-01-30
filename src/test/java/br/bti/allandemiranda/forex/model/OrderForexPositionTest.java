package br.bti.allandemiranda.forex.model;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderForexPositionTest {

  @Test
  void values() {
    // given
    String buy = "BUY";
    String sell = "SELL";
    int size = 2;
    // when
    String array = Arrays.toString(OrderForexPosition.values());
    int arraySize = OrderForexPosition.values().length;
    // then
    Assertions.assertTrue(array.contains(buy));
    Assertions.assertTrue(array.contains(sell));
    Assertions.assertEquals(size, arraySize);
  }

  @Test
  void valueOf() {
    // given
    String buy = "BUY";
    String sell = "SELL";
    // when
    OrderForexPosition buyOrderForexPosition = OrderForexPosition.valueOf(buy);
    OrderForexPosition sellOrderForexPosition = OrderForexPosition.valueOf(sell);
    // then
    Assertions.assertEquals(OrderForexPosition.BUY, buyOrderForexPosition);
    Assertions.assertEquals(OrderForexPosition.SELL, sellOrderForexPosition);
  }

  @ParameterizedTest
  @ValueSource(strings = { "OPEN", "ClOSE_MANUALLY", "EUR", "USD", "HIGH", "LOW"})
  void valueOfThrows(String name) {
    // given
    // when
    Executable executable = () -> OrderForexPosition.valueOf(name);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }
}