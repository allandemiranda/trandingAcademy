package br.bti.allandemiranda.forex.candlestick;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * The type Chart test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
class ActionTest {

  @Test
  void valuesSize() {
    // given
    int size = 3;
    // when
    int trueSize = Action.values().length;
    // then
    Assertions.assertEquals(size, trueSize);
  }

  @ParameterizedTest
  @ValueSource(strings = {"UPPER", "DOWN", "NEUTRAL"})
  void valuesList(String value) {
    // given
    // when
    String list = Arrays.toString(Action.values());
    // then
    Assertions.assertTrue(list.contains(value));
  }

  @ParameterizedTest
  @ValueSource(strings = {"OPEN_VALUE", "CLOSE_VALUE", "EUR", "ClOSE_MANUALLY"})
  void valuesNotList(String value) {
    // given
    String list = Arrays.toString(Action.values());
    // when
    Executable executable = () -> Action.valueOf(value);
    // then
    Assertions.assertFalse(list.contains(value));
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

}