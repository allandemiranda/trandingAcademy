package br.bti.allandemiranda.forex.model.analysis;

import br.bti.allandemiranda.forex.model.utils.Chart;
import br.bti.allandemiranda.forex.model.utils.CurrencyExchange;
import br.bti.allandemiranda.forex.model.utils.CurrencyPair;
import br.bti.allandemiranda.forex.parser.Metatrader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * The type Stochastic oscillator test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
class StochasticOscillatorTest {

  @Test
  void getStochastic() throws URISyntaxException, IOException {
    // given
    URL url = StochasticOscillatorTest.class.getResource("EURUSD_M15.csv");
    assert url != null;
    File file = new File(url.toURI());
    CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
    // when
    Mockito.when(currencyExchange.getCurrencyPair()).thenReturn(new CurrencyPair("EUR", "USD"));
    Chart chart = Metatrader.parser(file, currencyExchange);
    List<Pair<LocalDateTime, Pair<Double, Double>>> pairList = StochasticOscillator.getStochastic(5, 3,
        3, chart.getCandlestickList());
    // then
    List<String> stringList = List.of("(2020-02-05T13:00,(null,null))", "(2020-02-05T13:15,(null,null))",
        "(2020-02-05T13:30,(null,null))", "(2020-02-05T13:45,(null,null))",
        "(2020-02-05T14:00,(7.7922077922018,null))", "(2020-02-05T14:15,(2.1739130434803595,null))",
        "(2020-02-05T14:30,(4.166666666667218,null))", "(2020-02-05T14:45,(18.749999999998074,null))",
        "(2020-02-05T15:00,(44.117647058824296,11.8064089931094))",
        "(2020-02-05T15:15,(81.53846153846128,26.2812224481021))");
    for (int i = 0; i < stringList.size(); ++i) {
      Assertions.assertEquals(pairList.get(i).toString(), stringList.get(i));
    }
  }
}