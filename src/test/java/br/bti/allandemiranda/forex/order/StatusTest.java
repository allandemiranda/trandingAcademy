package br.bti.allandemiranda.forex.order;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * The type Order forex status test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
class StatusTest {

  @Test
  void values() {
    // given
    int size = 5;
    // when
    int trueSize = Status.values().length;
    // then
    Assertions.assertEquals(size, trueSize);
  }

  @ParameterizedTest
  @ValueSource(strings = {"OPEN", "ClOSE_MANUALLY", "ClOSE_MARGIN_LOSS", "ClOSE_STOP_LOSS",
      "ClOSE_TAKE_PROFIT"})
  void valuesList(String status) {
    // given
    // when
    String list = Arrays.toString(Status.values());
    // then
    Assertions.assertTrue(list.contains(status));
  }

  @ParameterizedTest
  @ValueSource(strings = {"EUR", "USD", "LOW", "HIGH", "OPEN_MANUALLY", "CLOSE"})
  void valuesListFalse(String status) {
    // given
    // when
    String list = Arrays.toString(Status.values());
    // then
    Assertions.assertFalse(list.contains(status));
  }

  @Test
  void valueOf() {
    // given
    String eur = "OPEN";
    String usd = "ClOSE_MANUALLY";
    // when
    Status status = Status.valueOf(eur);
    Status status2 = Status.valueOf(usd);

    // then
    Assertions.assertEquals(Status.OPEN, status);
    Assertions.assertEquals(Status.ClOSE_MANUALLY, status2);
  }
}