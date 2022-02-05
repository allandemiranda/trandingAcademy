package br.bti.allandemiranda.forex.model.analysis;

import br.bti.allandemiranda.forex.model.utils.Candlestick;
import br.bti.allandemiranda.forex.model.utils.Chart;
import br.bti.allandemiranda.forex.model.utils.CurrencyExchange;
import br.bti.allandemiranda.forex.model.utils.CurrencyPair;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

class MovingAveragesTest {

  private static final String EUR = "EUR";
  private static final String USD = "USD";
  private final CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
  private final Chart chart = new Chart(currencyExchange);

  private final CurrencyPair currencyPair = new CurrencyPair(EUR, USD);
  private final List<Double> list = List.of(1.0, 2.0, 5.0, 6.0, 3.0, 8.0, 12.0, 12.0, 10.0, 7.0, 6.0, 5.0, 5.0, 6.0, 20.0, 8.0, 1.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 16.0, 10.0, 11.0, 12.0, 50.0, 2.0, 3.0, 4.0, 5.0, 8.0, 8.0, 8.0, 8.0, 8.0);

  @BeforeEach
  void setUp() {
    for (Double value : list) {
      chart.getCandlestickList().add(new Candlestick(value, value, value, value, 1, LocalDateTime.now(), currencyPair));
    }
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 4, 1000})
  void getLWMA(int periods) {
    // given
    LinkedList<Double> result1 = new LinkedList<>(List.of(1.0, 2.0, 5.0, 6.0, 3.0, 8.0, 12.0, 12.0, 10.0, 7.0, 6.0, 5.0, 5.0, 6.0, 20.0, 8.0, 1.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 16.0, 10.0, 11.0, 12.0, 50.0, 2.0, 3.0, 4.0, 5.0, 8.0, 8.0, 8.0, 8.0, 8.0));

    LinkedList<Double> result3 = new LinkedList<>(
        List.of(3.3333333333333335, 4.777777777777778, 4.5, 5.8, 8.166666666666666, 10.857142857142858, 11.25, 9.481481481481481, 7.533333333333333, 5.9393939393939394, 5.305555555555555, 5.358974358974359, 10.69047619047619, 11.377777777777778, 9.270833333333334, 9.196078431372548, 12.648148148148149, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 17.30666666666667, 14.564102564102564, 12.271604938271604, 11.023809523809524, 24.7816091954023, 21.22222222222222, 17.827956989247312, 3.0208333333333335, 4.02020202020202, 5.705882352941177, 7.0285714285714285, 8.0, 8.0, 8.0));
    result3.addFirst(null);
    result3.addFirst(null);

    LinkedList<Double> result4 = new LinkedList<>(
        List.of(4.4, 4.142857142857143, 5.666666666666667, 7.7727272727272725, 9.346153846153847, 10.6, 10.0, 8.473684210526315, 6.809523809523809, 5.673913043478261, 5.5, 9.425925925925926, 9.948275862068966, 8.53225806451613, 11.651515151515152, 11.585714285714285, 14.094594594594595, 18.0, 18.0, 18.0, 18.0, 18.0, 17.46938775510204, 15.372549019607844, 13.622641509433961, 12.2, 21.280701754385966, 18.796610169491526, 16.442622950819672, 14.206349206349206, 3.5384615384615383, 5.059701492537314, 6.304347826086956, 7.28169014084507, 8.0, 8.0));
    result4.addFirst(null);
    result4.addFirst(null);
    result4.addFirst(null);
    // when
    LinkedList<Double> result = new MovingAverages(chart).getLWMA(periods, Application.CLOSE_VALUE);
    // then
    if (periods > 1) {
      if (periods >= chart.getCandlestickList().size()) {
        Assertions.assertEquals(0, result.stream().filter(Objects::nonNull).count());
      } else {
        Assertions.assertEquals(periods - 1, result.stream().filter(Objects::isNull).count());
      }
    } else {
      Assertions.assertEquals(0, result.stream().filter(Objects::isNull).count());
    }

    switch (periods) {
      case 1 -> Assertions.assertEquals(result1, result);
      case 3 -> Assertions.assertEquals(result3, result);
      case 4 -> Assertions.assertEquals(result4, result);
    }
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 4, 1000})
  void getSMMA(int periods) {
    // given
    LinkedList<Double> result1 = new LinkedList<>(List.of(2.0, 5.0, 6.0, 3.0, 8.0, 12.0, 12.0, 10.0, 7.0, 6.0, 5.0, 5.0, 6.0, 20.0, 8.0, 1.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 16.0, 10.0, 11.0, 12.0, 50.0, 2.0, 3.0, 4.0, 5.0, 8.0, 8.0, 8.0, 8.0, 8.0));
    result1.addFirst(null);

    LinkedList<Double> result3 = new LinkedList<>(
        List.of(2.6666666666666665, 3.7777777777777772, 3.5185185185185177, 5.012345679012345, 7.34156378600823, 8.89437585733882, 9.26291723822588, 8.508611492150587, 7.672407661433724, 6.781605107622482, 6.187736738414988, 6.125157825609992, 10.750105217073328, 9.833403478048885, 6.88893565203259, 10.592623768021726, 13.061749178681149, 14.707832785787431, 15.80522185719162, 16.53681457146108, 17.02454304764072, 17.34969536509381, 17.56646357672921, 17.044309051152805, 14.69620603410187, 13.464137356067914, 12.976091570711944, 25.317394380474628, 17.544929586983084, 12.69661972465539, 9.797746483103593, 8.198497655402395, 8.132331770268264, 8.088221180178843, 8.058814120119228, 8.039209413412818, 8.026139608941879));
    result3.addFirst(null);
    result3.addFirst(null);

    LinkedList<Double> result4 = new LinkedList<>(
        List.of( 3.5, 3.375, 4.53125, 6.3984375, 7.798828125, 8.34912109375, 8.0118408203125, 7.508880615234375, 6.881660461425781, 6.411245346069336, 6.308434009552002, 9.731325507164001, 9.298494130373001, 7.223870597779751, 9.917902948334813, 11.93842721125111, 13.453820408438332, 14.59036530632875, 15.442773979746562, 16.08208048480992, 16.56156036360744, 16.92117027270558, 16.690877704529186, 15.01815827839689, 14.013618708797667, 13.51021403159825, 22.63266052369869, 17.474495392774017, 13.855871544580513, 11.391903658435385, 9.79392774382654, 9.345445807869904, 9.009084355902427, 8.75681326692682, 8.567609950195115, 8.425707462646336));
    result4.addFirst(null);
    result4.addFirst(null);
    result4.addFirst(null);
    // when
    LinkedList<Double> result = new MovingAverages(chart).getSMMA(periods, Application.CLOSE_VALUE);
    // then
    if (periods > 1) {
      if (periods >= chart.getCandlestickList().size()) {
        Assertions.assertEquals(0, result.stream().filter(Objects::nonNull).count());
      } else {
        Assertions.assertEquals(periods - 1, result.stream().filter(Objects::isNull).count());
      }
    } else {
      Assertions.assertEquals(1, result.stream().filter(Objects::isNull).count());
    }

    switch (periods) {
      case 1 -> Assertions.assertEquals(result1, result);
      case 3 -> Assertions.assertEquals(result3, result);
      case 4 -> Assertions.assertEquals(result4, result);
    }
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 4, 1000})
  void getEMA(int periods) {
    // given
    LinkedList<Double> result1 = new LinkedList<>(List.of(2.5, 6.5, 6.5, 1.5, 10.5, 14.0, 12.0, 9.0, 5.5, 5.5, 4.5, 5.0, 6.5, 27.0, 2.0, 0.0, 26.5, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 15.0, 7.0, 11.5, 12.5, 69.0, 0.0, 3.5, 4.5, 5.5, 9.5, 8.0, 8.0, 8.0, 8.0));
    result1.addFirst(null);
    int smoothing = 3;
    // when
    LinkedList<Double> result = new MovingAverages(chart).getEMA(periods, smoothing, Application.CLOSE_VALUE);
    // then
    if (periods > 1) {
      if (periods >= chart.getCandlestickList().size()) {
        Assertions.assertEquals(0, result.stream().filter(Objects::nonNull).count());
      } else {
        Assertions.assertEquals(periods, result.stream().filter(Objects::isNull).count());
      }
    } else {
      Assertions.assertEquals(1, result.stream().filter(Objects::isNull).count());
    }

    if (periods == 1) {
      Assertions.assertEquals(result1, result);
    }
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 4, 1000})
  void getEMA2(int periods) {
    // given
    LinkedList<Double> result1 = new LinkedList<>(List.of(2.0, 5.0, 6.0, 3.0, 8.0, 12.0, 12.0, 10.0, 7.0, 6.0, 5.0, 5.0, 6.0, 20.0, 8.0, 1.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 16.0, 10.0, 11.0, 12.0, 50.0, 2.0, 3.0, 4.0, 5.0, 8.0, 8.0, 8.0, 8.0, 8.0));
    result1.addFirst(null);

    LinkedList<Double> result3 = new LinkedList<>(
        List.of(4.333333333333333, 3.6666666666666665, 6.333333333333334, 8.833333333333334, 9.833333333333334, 10.333333333333332, 9.166666666666668, 7.833333333333333, 6.333333333333334, 5.5, 5.666666666666666, 12.666666666666666, 9.166666666666668, 6.166666666666667, 13.833333333333332, 13.5, 15.166666666666668, 18.0, 18.0, 18.0, 18.0, 18.0, 17.0, 13.666666666666666, 12.833333333333332, 12.166666666666668, 30.5, 13.166666666666666, 12.166666666666666, 11.166666666666666, 4.0, 6.0, 6.833333333333334, 7.5, 8.0, 8.0));
    result3.addFirst(null);
    result3.addFirst(null);
    result3.addFirst(null);

    LinkedList<Double> result4 = new LinkedList<>(
        List.of(3.3000000000000003, 5.6, 8.100000000000001, 9.15, 9.25, 9.1, 8.55, 7.25, 6.2, 5.85, 11.3, 8.6, 6.25, 12.45, 14.25, 13.95, 15.45, 18.0, 18.0, 18.0, 18.0, 17.2, 14.5, 13.7, 13.05, 27.35, 13.25, 12.45, 11.649999999999999, 10.85, 5.300000000000001, 6.2, 6.95, 7.55, 8.0));
    result4.addFirst(null);
    result4.addFirst(null);
    result4.addFirst(null);
    result4.addFirst(null);
    // when
    LinkedList<Double> result = new MovingAverages(chart).getEMA(periods, Application.CLOSE_VALUE);
    // then
    if (periods > 1) {
      if (periods >= chart.getCandlestickList().size()) {
        Assertions.assertEquals(0, result.stream().filter(Objects::nonNull).count());
      } else {
        Assertions.assertEquals(periods, result.stream().filter(Objects::isNull).count());
      }
    } else {
      Assertions.assertEquals(1, result.stream().filter(Objects::isNull).count());
    }

    switch (periods) {
      case 1 -> Assertions.assertEquals(result1, result);
      case 3 -> Assertions.assertEquals(result3, result);
      case 4 -> Assertions.assertEquals(result4, result);
    }
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 4, 1000})
  void getSMA(int periods) {
    // given
    LinkedList<Double> result1 = new LinkedList<>(List.of(1.0,2.0, 5.0, 6.0, 3.0, 8.0, 12.0, 12.0, 10.0, 7.0, 6.0, 5.0, 5.0, 6.0, 20.0, 8.0, 1.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 16.0, 10.0, 11.0, 12.0, 50.0, 2.0, 3.0, 4.0, 5.0, 8.0, 8.0, 8.0, 8.0, 8.0));

    LinkedList<Double> result3 = new LinkedList<>(
        List.of(2.6666666666666665, 4.333333333333333, 4.666666666666667, 5.666666666666667, 7.666666666666667, 10.666666666666666, 11.333333333333334, 9.666666666666666, 7.666666666666667, 6.0, 5.333333333333333, 5.333333333333333, 10.333333333333334, 11.333333333333334, 9.666666666666666, 9.0, 12.333333333333334, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 17.333333333333332, 14.666666666666666, 12.333333333333334, 11.0, 24.333333333333332, 21.333333333333332, 18.333333333333332, 3.0, 4.0, 5.666666666666667, 7.0, 8.0, 8.0, 8.0));
    result3.addFirst(null);
    result3.addFirst(null);

    LinkedList<Double> result4 = new LinkedList<>(
        List.of( 3.5, 4.0, 5.5, 7.25, 8.75, 10.5, 10.25, 8.75, 7.0, 5.75, 5.5, 9.0, 9.75, 8.75, 11.75, 11.25, 13.75, 18.0, 18.0, 18.0, 18.0, 18.0, 17.5, 15.5, 13.75, 12.25, 20.75, 18.75, 16.75, 14.75, 3.5, 5.0, 6.25, 7.25, 8.0, 8.0));
    result4.addFirst(null);
    result4.addFirst(null);
    result4.addFirst(null);
    // when
    LinkedList<Double> result = new MovingAverages(chart).getSMA(periods, Application.CLOSE_VALUE);
    // then
    if (periods > 1) {
      if (periods >= chart.getCandlestickList().size()) {
        Assertions.assertEquals(0, result.stream().filter(Objects::nonNull).count());
      } else {
        Assertions.assertEquals(periods - 1, result.stream().filter(Objects::isNull).count());
      }
    } else {
      Assertions.assertEquals(0, result.stream().filter(Objects::isNull).count());
    }

    switch (periods) {
      case 1 -> Assertions.assertEquals(result1, result);
      case 3 -> Assertions.assertEquals(result3, result);
      case 4 -> Assertions.assertEquals(result4, result);
    }
  }


}