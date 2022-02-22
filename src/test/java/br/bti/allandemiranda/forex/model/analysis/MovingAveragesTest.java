package br.bti.allandemiranda.forex.model.analysis;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * The type Moving averages test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.1
 */
class MovingAveragesTest {

  LinkedList<Pair<LocalDateTime, Double>> closeList = new LinkedList<>();

  @BeforeEach
  void setUp() {
    LinkedList<Double> doubleList = new LinkedList<>(
        List.of(1.0, 2.0, 5.0, 6.0, 3.0, 8.0, 12.0, 12.0, 10.0, 7.0, 6.0, 5.0, 5.0, 6.0, 20.0, 8.0, 1.0,
            18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 16.0, 10.0, 11.0, 12.0, 50.0, 2.0, 3.0, 4.0,
            5.0, 8.0, 8.0, 8.0, 8.0, 8.0));
    AtomicLong i = new AtomicLong();
    closeList = doubleList.stream()
        .map(aDouble -> Pair.of(LocalDateTime.now().plusDays(i.getAndIncrement()), aDouble))
        .collect(Collectors.toCollection(LinkedList::new));
    closeList.addFirst(Pair.of(LocalDateTime.now(), null));
  }

  @ParameterizedTest
  @ValueSource(ints = {2, 4, 1000})
  void getSMA(int periods) {
    // given
    List<Double> sma2 = List.of(1.5, 3.5, 5.5, 4.5, 5.5, 10.0, 12.0, 11.0, 8.5, 6.5, 5.5, 5.0, 5.5, 13.0,
        14.0, 4.5, 9.5, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 18.0, 17.0, 13.0, 10.5, 11.5, 31.0, 26.0,
        2.5, 3.5, 4.5, 6.5, 8.0, 8.0, 8.0, 8.0);
    List<Double> sma4 = List.of(3.5, 4.0, 5.5, 7.25, 8.75, 10.5, 10.25, 8.75, 7.0, 5.75, 5.5, 9.0, 9.75,
        8.75, 11.75, 11.25, 13.75, 18.0, 18.0, 18.0, 18.0, 18.0, 17.5, 15.5, 13.75, 12.25, 20.75, 18.75,
        16.75, 14.75, 3.5, 5.0, 6.25, 7.25, 8.0, 8.0);
    // when
    List<Pair<LocalDateTime, Double>> result = MovingAverages.getSMA(periods, closeList);
    // then
    int nullPoint = (int) result.parallelStream().filter(pair -> pair.getRight() == null).count();
    switch (periods) {
      case 2 -> Assertions.assertEquals(2, nullPoint);
      case 4 -> Assertions.assertEquals(4, nullPoint);
      case 1000 -> Assertions.assertEquals(result.size(), nullPoint);
    }
    if (periods == 2) {
      for (int i = nullPoint; i < result.size(); ++i) {
        Assertions.assertEquals(sma2.get(i - nullPoint), result.get(i).getRight());
      }
    }
    if (periods == 4) {
      for (int i = nullPoint; i < result.size(); ++i) {
        Assertions.assertEquals(sma4.get(i - nullPoint), result.get(i).getRight());
      }
    }
  }

  @ParameterizedTest
  @ValueSource(ints = {2, 4})
  void getEMA(int periods) {
    // given
    List<Double> ema2 = List.of(1.5, 3.833333333333333, 5.277777777777778, 3.7592592592592595,
        6.586419753086419, 10.195473251028806, 11.39849108367627, 10.466163694558755, 8.155387898186252,
        6.718462632728751, 5.57282087757625, 5.190940292525417, 5.7303134308418056, 15.2434378102806,
        10.414479270093533, 4.138159756697845, 13.379386585565948, 16.459795528521983,
        17.486598509507328, 17.828866169835777, 17.94295538994526, 17.980985129981754, 17.99366170999392,
        17.99788723666464, 16.665962412221546, 12.221987470740515, 11.407329156913505,
        11.802443052304502, 37.26748101743483, 13.755827005811613, 6.585275668603871, 4.86175855620129,
        4.9539195187337635, 6.9846398395779214, 7.661546613192641, 7.887182204397547, 7.962394068132516,
        7.987464689377505);
    List<Double> ema4 = List.of(3.5, 3.3000000000000003, 5.18, 7.908, 9.5448, 9.726880000000001,
        8.636128000000001, 7.581676800000001, 6.549006080000001, 5.929403648, 5.9576421888,
        11.57458531328, 10.144751187968, 6.4868507127808, 11.09211042766848, 13.855266256601087,
        15.513159753960654, 16.507895852376393, 17.104737511425835, 17.4628425068555, 17.6777055041133,
        17.80662330246798, 17.08397398148079, 14.250384388888472, 12.950230633333083, 12.57013837999985,
        27.542083027999908, 17.325249816799946, 11.595149890079966, 8.55708993404798, 7.134253960428787,
        7.480552376257273, 7.688331425754364, 7.812998855452618, 7.887799313271571, 7.932679587962943);
    // when
    List<Pair<LocalDateTime, Double>> result = MovingAverages.getEMA(periods, 2, closeList);
    // then
    int nullPoint = (int) result.parallelStream().filter(pair -> pair.getRight() == null).count();
    switch (periods) {
      case 2 -> Assertions.assertEquals(2, nullPoint);
      case 4 -> Assertions.assertEquals(4, nullPoint);
    }
    if (periods == 2) {
      for (int i = nullPoint; i < result.size(); ++i) {
        Assertions.assertEquals(ema2.get(i - nullPoint), result.get(i).getRight());
      }
    }
    if (periods == 4) {
      for (int i = nullPoint; i < result.size(); ++i) {
        Assertions.assertEquals(ema4.get(i - nullPoint), result.get(i).getRight());
      }
    }
  }
}