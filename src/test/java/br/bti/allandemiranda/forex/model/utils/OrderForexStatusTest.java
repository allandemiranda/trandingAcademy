package br.bti.allandemiranda.forex.model.utils;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderForexStatusTest {

  @Test
  void values() {
    // given
    int size = 5;
    // when
    int trueSize = OrderForexStatus.values().length;
    // then
    Assertions.assertEquals(size, trueSize);
  }

  @ParameterizedTest
  @ValueSource(strings = {"OPEN", "ClOSE_MANUALLY", "ClOSE_MARGIN_LOSS", "ClOSE_STOP_LOSS", "ClOSE_TAKE_PROFIT"})
  void valuesList(String status) {
    // given
    // when
    String list = Arrays.toString(OrderForexStatus.values());
    // then
    Assertions.assertTrue(list.contains(status));
  }

  @ParameterizedTest
  @ValueSource(strings = {"EUR", "USD", "LOW", "HIGH", "OPEN_MANUALLY", "CLOSE"})
  void valuesListFalse(String status) {
    // given
    // when
    String list = Arrays.toString(OrderForexStatus.values());
    // then
    Assertions.assertFalse(list.contains(status));
  }

  @Test
  void valueOf() {
    // given
    String eur = "OPEN";
    String usd = "ClOSE_MANUALLY";
    // when
    OrderForexStatus orderForexStatus = OrderForexStatus.valueOf(eur);
    OrderForexStatus orderForexStatus2 = OrderForexStatus.valueOf(usd);

    // then
    Assertions.assertEquals(OrderForexStatus.OPEN, orderForexStatus);
    Assertions.assertEquals(OrderForexStatus.ClOSE_MANUALLY, orderForexStatus2);
  }
}