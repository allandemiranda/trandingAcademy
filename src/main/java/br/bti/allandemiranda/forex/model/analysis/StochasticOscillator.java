package br.bti.allandemiranda.forex.model.analysis;

import br.bti.allandemiranda.forex.model.utils.Candlestick;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

/**
 * The type Stochastic oscillator.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class StochasticOscillator {

  /**
   * Gets stochastic.
   *
   * @param kPeriod the k period
   * @param dPeriod the d period
   * @param slowing the slowing
   * @param list    the list
   *
   * @return the stochastic - TIME -> K, Slow
   */
  @NotNull
  public static List<Pair<LocalDateTime, Pair<Double, Double>>> getStochastic(int kPeriod, int dPeriod,
      int slowing, @NotNull List<Candlestick> list) {
    List<Pair<LocalDateTime, Double>> listK = IntStream.rangeClosed(0, list.size() - 1).boxed().toList()
        .parallelStream().map(i -> {
          double high = list.get(i).getHighPrice();
          double low = list.get(i).getLowPrice();
          if (i + 1 >= kPeriod) {
            for (int j = 0; j < kPeriod; ++j) {
              if (Objects.isNull(list.get(i - j))) {
                return Pair.of(list.get(i).getLocalDateTime(), (Double) null);
              } else {
                high = Math.max(high, list.get(i).getHighPrice());
                low = Math.min(low, list.get(i).getLowPrice());
              }
            }

            return Pair.of(list.get(i).getLocalDateTime(),
                (((list.get(i).getClosePrice() - low) / (high - low)) * 100));

          } else {
            return Pair.of(list.get(i).getLocalDateTime(), (Double) null);
          }
        }).toList();

    List<Pair<LocalDateTime, Double>> listSlow = MovingAverages.getSMA(slowing,
        MovingAverages.getSMA(dPeriod, list.stream()
            .map(candlestick -> Pair.of(candlestick.getLocalDateTime(), candlestick.getClosePrice()))
            .toList()));

    return IntStream.rangeClosed(0, list.size() - 1).boxed().toList().parallelStream().map(
            i -> Pair.of(list.get(i).getLocalDateTime(),
                Pair.of(listK.get(i).getRight(), listSlow.get(i).getRight())))
        .sorted(Comparator.comparing(Pair::getLeft)).toList();
  }
}
