package br.bti.allandemiranda.forex.model.analysis;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The type MACD.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class MACD {

  private static final Logger logger = LogManager.getLogger(MACD.class);
  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String PERIODS = "Periods";

  /**
   * Gets macd.
   *
   * @param fastEMA the fast ema
   * @param slowEMA the slow ema
   * @param macdSMA the macd sma
   * @param list    the list
   *
   * @return the MACD List. DateTime -> Histogram - MACD - Signal
   */
  public static @NotNull List<Pair<LocalDateTime, Triple<Double, Double, Double>>> getMACD(int fastEMA,
      int slowEMA, int macdSMA, @NotNull List<Pair<LocalDateTime, Double>> list) {
    logger.info("MACD list - Fast EMA {} - Slow EMA {} - MACD SMA {}", fastEMA, slowEMA, macdSMA);
    if (fastEMA > 0 || slowEMA > 0 || macdSMA > 0) {
      List<Pair<LocalDateTime, Double>> fastEmaList = MovingAverages.getDefaultEMA(fastEMA, list);

      List<Pair<LocalDateTime, Double>> slowEmaList = MovingAverages.getDefaultEMA(slowEMA, list);

      List<Pair<LocalDateTime, Double>> macdSmaList = getDifList(list, fastEmaList, slowEmaList);

      List<Pair<LocalDateTime, Double>> sinalList = MovingAverages.getDefaultEMA(macdSMA, macdSmaList);

      List<Pair<LocalDateTime, Double>> histogramList = getDifList(list, macdSmaList, sinalList);

      return IntStream.rangeClosed(0, list.size() - 1).boxed().map(i -> Pair.of(list.get(i).getLeft(),
          Triple.of(histogramList.get(i).getRight(), macdSmaList.get(i).getRight(),
              sinalList.get(i).getRight()))).toList();
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets dif list.
   *
   * @param list     the list
   * @param fastList the fast list
   * @param slowList the slow list
   *
   * @return the dif list
   */
  private static @NotNull List<Pair<LocalDateTime, Double>> getDifList(
      @NotNull List<Pair<LocalDateTime, Double>> list,
      @NotNull List<Pair<LocalDateTime, Double>> fastList,
      @NotNull List<Pair<LocalDateTime, Double>> slowList) {
    Stream<? extends Pair<Integer, ?>> streamList = IntStream.rangeClosed(0, list.size() - 1).boxed()
        .toList().parallelStream().map(i -> {
          if (Objects.isNull(fastList.get(i).getRight()) || Objects.isNull(slowList.get(i).getRight())) {
            return Pair.of(i, Pair.of(list.get(i).getLeft(), null));
          } else {
            return Pair.of(i,
                Pair.of(list.get(i).getLeft(), fastList.get(i).getRight() - slowList.get(i).getRight()));
          }
        }).sorted(Comparator.comparingInt(Pair::getKey));
    return streamList.sequential().map(pair -> (Pair<LocalDateTime, Double>) pair.getValue()).toList();
  }
}
