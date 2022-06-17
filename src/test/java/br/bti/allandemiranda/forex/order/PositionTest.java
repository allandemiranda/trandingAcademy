package br.bti.allandemiranda.forex.order;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * The type Order forex position test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
class PositionTest {

  @Test
  void values() {
    // given
    String buy = "BUY";
    String sell = "SELL";
    int size = 2;
    // when
    String array = Arrays.toString(Position.values());
    int arraySize = Position.values().length;
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
    Position buyPosition = Position.valueOf(buy);
    Position sellPosition = Position.valueOf(sell);
    // then
    Assertions.assertEquals(Position.BUY, buyPosition);
    Assertions.assertEquals(Position.SELL, sellPosition);
  }

  @ParameterizedTest
  @ValueSource(strings = {"OPEN", "ClOSE_MANUALLY", "EUR", "USD", "HIGH", "LOW"})
  void valueOfThrows(String name) {
    // given
    // when
    Executable executable = () -> Position.valueOf(name);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }
}