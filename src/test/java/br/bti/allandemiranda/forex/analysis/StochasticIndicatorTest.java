//package br.bti.allandemiranda.forex.analysis;
//
//import br.bti.allandemiranda.forex.chart.Chart;
//import br.bti.allandemiranda.forex.currency.Exchange;
//import br.bti.allandemiranda.forex.currency.Pair;
//import br.bti.allandemiranda.forex.indicators.oscillators.Stochastic;
//import br.bti.allandemiranda.forex.parser.Metatrader;
//import java.io.File;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.List;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
///**
// * The type Stochastic oscillator test.
// *
// * @author Allan de Miranda Silva
// * @version 1.0.0
// */
//class StochasticIndicatorTest {
//
//  @Test
//  void getStochastic() throws URISyntaxException, IOException {
//    // given
//    URL url = StochasticIndicatorTest.class.getResource("EURUSD_M15.csv");
//    assert url != null;
//    File file = new File(url.toURI());
//    Exchange exchange = Mockito.mock(Exchange.class);
//    // when
//    Mockito.when(exchange.getCurrencyPair()).thenReturn(new Pair("EUR", "USD"));
//    Chart chart = Metatrader.parser(file, exchange);
//    List<Stochastic> pairList = StochasticOscillator.getStochastic(5, 3, 3, chart.getCandlestickList());
//    // then
//    List<String> stringList = List.of(
//        "Stochastic{time=2020-02-05T13:00, k=null, slow=null, kPeriod=5, dPeriod=3, slowing=3}",
//        "Stochastic{time=2020-02-05T13:15, k=null, slow=null, kPeriod=5, dPeriod=3, slowing=3}",
//        "Stochastic{time=2020-02-05T13:30, k=null, slow=null, kPeriod=5, dPeriod=3, slowing=3}",
//        "Stochastic{time=2020-02-05T13:45, k=null, slow=null, kPeriod=5, dPeriod=3, slowing=3}",
//        "Stochastic{time=2020-02-05T14:00, k=7.7922077922018, slow=null, kPeriod=5, dPeriod=3, slowing=3}",
//        "Stochastic{time=2020-02-05T14:15, k=2.1739130434803595, slow=null, kPeriod=5, dPeriod=3, slowing=3}",
//        "Stochastic{time=2020-02-05T14:30, k=4.166666666667218, slow=null, kPeriod=5, dPeriod=3, slowing=3}",
//        "Stochastic{time=2020-02-05T14:45, k=18.749999999998074, slow=null, kPeriod=5, dPeriod=3, slowing=3}",
//        "Stochastic{time=2020-02-05T15:00, k=44.117647058824296, slow=11.8064089931094, kPeriod=5, dPeriod=3, slowing=3}",
//        "Stochastic{time=2020-02-05T15:15, k=81.53846153846128, slow=26.2812224481021, kPeriod=5, dPeriod=3, slowing=3}");
//    for (int i = 0; i < stringList.size(); ++i) {
//      Assertions.assertEquals(pairList.get(i).toString(), stringList.get(i));
//    }
//  }
//}