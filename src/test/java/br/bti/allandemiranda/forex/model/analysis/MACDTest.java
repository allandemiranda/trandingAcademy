package br.bti.allandemiranda.forex.model.analysis;

import br.bti.allandemiranda.forex.model.utils.Candlestick;
import br.bti.allandemiranda.forex.model.utils.Chart;
import br.bti.allandemiranda.forex.model.utils.CurrencyExchange;
import br.bti.allandemiranda.forex.model.utils.CurrencyPair;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MACDTest {

  private static final String EUR = "EUR";
  private static final String USD = "USD";
  private final CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
  private final Chart chart = new Chart(currencyExchange);

  private final CurrencyPair currencyPair = new CurrencyPair(EUR, USD);
  private final List<Double> list = List.of(1.0, 2.0, 5.0, 6.0, 3.0, 8.0, 12.0, 12.0, 10.0, 7.0, 6.0, 5.0, 5.0, 6.0, 20.0, 8.0, 1.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 16.0, 10.0, 11.0, 12.0, 50.0, 2.0, 3.0, 4.0, 5.0,
      8.0, 8.0, 8.0, 8.0, 8.0);

  @BeforeEach
  void setUp() {
    for (Double value : list) {
      chart.getCandlestickList().add(new Candlestick(value, value, value, value, 1, LocalDateTime.now(), currencyPair));
    }
  }

  @Test
  void getMACD() throws InterruptedException {
    // given
    String finalList = "[null, null, null, null, null, null, null, null, null, (-0.7666666666666675,1.0555555555555551), (-1.7166666666666668,0.6805555555555552), (-1.75,0.183333333333333), (-1.033333333333334,-0.2777777777777781), (-0.18333333333333268,-0.6722222222222225), (3.8666666666666654,-0.2638888888888893), (1.0666666666666682,0.041666666666666664), (-0.9166666666666661,0.17500000000000013), (1.0500000000000007,0.6416666666666669), (0.9166666666666679,0.9666666666666672), (4.050000000000001,1.6722222222222227), (2.5500000000000007,1.4527777777777786), (0.0,1.2750000000000006), (0.0,1.4277777777777783), (0.0,1.2527777777777782), (0.0,1.1000000000000003), (-0.5333333333333314,0.3361111111111115), (-2.166666666666668,-0.4499999999999999), (-2.0333333333333314,-0.7888888888888884), (-1.5500000000000007,-1.0472222222222218), (9.816666666666663,0.5888888888888886), (-1.5833333333333321,0.3249999999999999), (-1.7833333333333314,0.11666666666666654), (-8.149999999999999,-0.8805555555555552), (-6.35,-1.6000000000000003), (1.5333333333333323,-1.086111111111111), (1.2999999999999998,-2.505555555555555), (1.0499999999999998,-2.0666666666666664), (0.4500000000000002,-1.6944444444444444), (0.0,-0.33611111111111125)]";
    // when
    LinkedList<Pair<Double, Double>> pairLinkedList = new MACD(chart).getMACD(2, 4, 6, Application.CLOSE_VALUE);
    // then
    Assertions.assertEquals(finalList, pairLinkedList.toString());
  }
}