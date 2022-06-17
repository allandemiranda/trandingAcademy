package br.bti.allandemiranda.forex.analysis;

import br.bti.allandemiranda.forex.indicators.trends.EMA;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The type MACD.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.1
 */
public class MACD {

  private static final Logger logger = LogManager.getLogger(MACD.class);
  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String PERIODS = "Periods";

  /**
   * Instantiates a new Macd.
   */
  @Contract(" -> fail")
  private MACD() {
    throw new IllegalStateException(MACD.class.toString());
  }

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
  public static @NotNull List<br.bti.allandemiranda.forex.indicators.oscillators.MACD> getMACD(
      int fastEMA, int slowEMA, int macdSMA, @NotNull List<Pair<LocalDateTime, Double>> list) {
    logger.info("MACD list - Fast EMA {} - Slow EMA {} - MACD SMA {}", fastEMA, slowEMA, macdSMA);
    if (fastEMA > 0 || slowEMA > 0 || macdSMA > 0) {
      List<EMA> fastEmaList = MovingAverages.getDefaultEMA(fastEMA, list);

      List<EMA> slowEmaList = MovingAverages.getDefaultEMA(slowEMA, list);

      List<Pair<LocalDateTime, Double>> macdSmaList = getDifListA(list, fastEmaList, slowEmaList);

      List<EMA> sinalList = MovingAverages.getDefaultEMA(macdSMA, macdSmaList);

      List<Pair<LocalDateTime, Double>> histogramList = getDifList(list, macdSmaList, sinalList);

      return IntStream.rangeClosed(0, list.size() - 1).boxed().map(
          i -> new br.bti.allandemiranda.forex.indicators.oscillators.MACD(list.get(i).getLeft(),
              histogramList.get(i).getRight(), macdSmaList.get(i).getRight(), sinalList.get(i).getEma(),
              fastEMA, slowEMA, macdSMA)).toList();
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
  private static @NotNull List<Pair<LocalDateTime, Double>> getDifListA(
      @NotNull List<Pair<LocalDateTime, Double>> list, @NotNull List<EMA> fastList,
      @NotNull List<EMA> slowList) {
    return IntStream.rangeClosed(0, list.size() - 1).boxed().toList().parallelStream().map(i -> {
      if (Objects.isNull(fastList.get(i).getEma()) || Objects.isNull(slowList.get(i).getEma())) {
        return Pair.of(list.get(i).getLeft(), (Double) null);
      } else {
        return Pair.of(list.get(i).getLeft(), fastList.get(i).getEma() - slowList.get(i).getEma());
      }
    }).sorted(Comparator.comparing(Pair::getLeft)).toList();
  }

  private static @NotNull List<Pair<LocalDateTime, Double>> getDifList(
      @NotNull List<Pair<LocalDateTime, Double>> list,
      @NotNull List<Pair<LocalDateTime, Double>> fastList, @NotNull List<EMA> slowList) {
    return IntStream.rangeClosed(0, list.size() - 1).boxed().toList().parallelStream().map(i -> {
      if (Objects.isNull(fastList.get(i).getRight()) || Objects.isNull(slowList.get(i).getEma())) {
        return Pair.of(list.get(i).getLeft(), (Double) null);
      } else {
        return Pair.of(list.get(i).getLeft(), fastList.get(i).getRight() - slowList.get(i).getEma());
      }
    }).sorted(Comparator.comparing(Pair::getLeft)).toList();
  }
}
