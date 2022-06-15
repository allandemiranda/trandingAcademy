package br.bti.allandemiranda.forex.analysis;

import br.bti.allandemiranda.forex.chart.Chart;
import br.bti.allandemiranda.forex.currency.Exchange;
import br.bti.allandemiranda.forex.currency.Pair;
import br.bti.allandemiranda.forex.parser.Metatrader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * The type Stochastic oscillator test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
class StochasticIndicatorTest {

  @Test
  void getStochastic() throws URISyntaxException, IOException {
    // given
    URL url = StochasticIndicatorTest.class.getResource("EURUSD_M15.csv");
    assert url != null;
    File file = new File(url.toURI());
    Exchange exchange = Mockito.mock(Exchange.class);
    // when
    Mockito.when(exchange.getCurrencyPair()).thenReturn(new Pair("EUR", "USD"));
    Chart chart = Metatrader.parser(file, exchange);
    List<org.apache.commons.lang3.tuple.Pair<LocalDateTime, org.apache.commons.lang3.tuple.Pair<Double, Double>>> pairList = StochasticOscillator.getStochastic(5, 3,
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