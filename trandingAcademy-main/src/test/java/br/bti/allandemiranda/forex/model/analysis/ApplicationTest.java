package br.bti.allandemiranda.forex.model.analysis;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest {

  @ParameterizedTest
  @ValueSource(strings = {"PEN_VALUE", "CLOSE_VALUE", "HIGH_VALUE", "LOW_VALUE"})
  void values(String application) {
    // given
    int size = 4;
    // when
    int trueSize = Application.values().length;
    String applicationList = Arrays.toString(Application.values());
    // then
    Assertions.assertEquals(size, trueSize);
    Assertions.assertTrue(applicationList.contains(application));
  }

  @Test
  void valueOf() {
    // given
    Application close = Application.CLOSE_VALUE;
    // when
    String result = "CLOSE_VALUE";
    // then
    Assertions.assertEquals(close, Application.valueOf(result));

  }
}