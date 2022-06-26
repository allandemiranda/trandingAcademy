//package br.bti.allandemiranda.forex.analysis;
//
//import br.bti.allandemiranda.forex.candlestick.Candlestick;
//import br.bti.allandemiranda.forex.indicators.oscillators.Stochastic;
//import br.bti.allandemiranda.forex.indicators.trends.SMA;
//import java.time.LocalDateTime;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.IntStream;
//import org.apache.commons.lang3.tuple.Pair;
//import org.jetbrains.annotations.NotNull;
//
///**
// * The type Stochastic oscillator.
// *
// * @author Allan de Miranda Silva
// * @version 1.0.1
// */
//public class StochasticOscillator {
//
//  /**
//   * Gets stochastic.
//   *
//   * @param kPeriod the k period
//   * @param dPeriod the d period
//   * @param slowing the slowing
//   * @param list    the list
//   *
//   * @return the stochastic - TIME -> K, Slow
//   */
//  @NotNull
//  public static List<Stochastic> getStochastic(int kPeriod, int dPeriod, int slowing,
//      @NotNull List<Candlestick> list) {
//    List<Pair<LocalDateTime, Double>> listK = IntStream.rangeClosed(0, list.size() - 1).boxed().toList()
//        .parallelStream().map(i -> {
//          if (i + 1 >= kPeriod) {
//            double high = list.get(i).getHighPrice();
//            double low = list.get(i).getLowPrice();
//
//            for (int j = 1; j < kPeriod; ++j) {
//              if (Objects.isNull(list.get(i - j))) {
//                return Pair.of(list.get(i).getLocalDateTime(), (Double) null);
//              } else {
//                high = Math.max(high, list.get(i - j).getHighPrice());
//                low = Math.min(low, list.get(i - j).getLowPrice());
//              }
//            }
//
//            return Pair.of(list.get(i).getLocalDateTime(),
//                (((list.get(i).getClosePrice() - low) / (high - low)) * 100));
//
//          } else {
//            return Pair.of(list.get(i).getLocalDateTime(), (Double) null);
//          }
//        }).toList();
//
//    List<SMA> dList = MovingAverages.getSMA(dPeriod, listK);
//
//    List<SMA> listSlow = MovingAverages.getSMA(slowing,
//        dList.stream().map(sma -> Pair.of(sma.getLocalDateTime(), sma.getSma())).toList());
//
//    return IntStream.rangeClosed(0, list.size() - 1).boxed().toList().parallelStream().map(
//            i -> new Stochastic(list.get(i).getLocalDateTime(), listK.get(i).getRight(),
//                listSlow.get(i).getSma(), kPeriod, dPeriod, slowing))
//        .sorted(Comparator.comparing(Stochastic::getLocalDateTime)).toList();
//  }
//}
