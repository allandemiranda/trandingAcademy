package br.bti.allandemiranda.forex.model.analysis;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The type Moving averages.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class MovingAverages {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String PERIODS = "Periods";
  protected static Logger LOGGER = LogManager.getLogger(MovingAverages.class);

  /**
   * Gets sma.
   *
   * @param periods the periods
   * @param list    the list
   *
   * @return the sma
   */
  public static @NotNull LinkedList<Pair<LocalDateTime, Double>> getSMA(int periods,
      @NotNull LinkedList<Pair<LocalDateTime, Double>> list) {
    return getSimpleList(periods, list);
  }

  /**
   * Gets simple list.
   *
   * @param periods the periods
   * @param list    the list
   *
   * @return the simple list
   */
  private static @NotNull LinkedList<Pair<LocalDateTime, Double>> getSimpleList(int periods,
      @NotNull LinkedList<Pair<LocalDateTime, Double>> list) {
    LOGGER.info("Getting a Simple Moving Average (SMA) list - Periods {}", periods);
    if (periods > 0) {

      Stream<? extends Pair<Integer, ?>> simpleStream = IntStream.rangeClosed(0, list.size() - 1).boxed()
          .toList().parallelStream().map(i -> {
            if ((i + 1) >= periods) {
              DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
              for (int j = 0; j < periods; ++j) {
                if (Objects.isNull(list.get(i - j).getRight())) {
                  return Pair.of(i, Pair.of(list.get(i).getLeft(), null));
                } else {
                  statistics.accept(list.get(i - j).getRight());
                }
              }
              return Pair.of(i, Pair.of(list.get(i).getLeft(), statistics.getAverage()));
            } else {
              return Pair.of(i, Pair.of(list.get(i).getLeft(), null));
            }
          }).sorted(Comparator.comparingInt(Pair::getKey));

      return simpleStream.map(pair -> (Pair<LocalDateTime, Double>) pair.getValue())
          .collect(Collectors.toCollection(LinkedList::new));
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets exponential.
   *
   * @param periods   the periods
   * @param smoothing the smoothing
   * @param list      the list
   *
   * @return the exponential
   */
  private static @NotNull LinkedList<Pair<LocalDateTime, Double>> getExponential(int periods,
      int smoothing, @NotNull LinkedList<Pair<LocalDateTime, Double>> list) {
    LOGGER.info("Getting a Exponential Moving Average (EMA) list - Periods {} - Smoothing {}", periods,
        smoothing);
    if (periods > 0) {
      double percentage = (smoothing / (periods + 1.0));

      LinkedList<Pair<LocalDateTime, Double>> linkedList = new LinkedList<>();

      for (int i = 0; i < list.size(); ++i) {
        if ((i + 1) >= periods || !linkedList.isEmpty()) {
          if (Objects.isNull(linkedList.get(i - 1).getRight())) {
            DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
            boolean flag = false;
            for (int j = 0; j < periods; ++j) {
              if (Objects.isNull(list.get(i - j).getRight())) {
                linkedList.add(Pair.of(list.get(i).getLeft(), null));
                flag = true;
                break;
              } else {
                statistics.accept(list.get(i - j).getRight());
              }
            }
            if(!flag){
              linkedList.add(Pair.of(list.get(i).getLeft(), statistics.getAverage()));
            }
          } else {
            double ema = (list.get(i).getRight() * percentage) + (linkedList.get(i - 1).getRight() * (1
                - percentage));
            linkedList.add(Pair.of(list.get(i).getLeft(), ema));
          }
        } else {
          linkedList.add(Pair.of(list.get(i).getLeft(), null));
        }
      }

      return linkedList;

    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets ema.
   *
   * @param periods   the periods
   * @param smoothing the smoothing
   * @param list      the list
   *
   * @return the ema
   */
  public static @NotNull LinkedList<Pair<LocalDateTime, Double>> getEMA(int periods, int smoothing,
      LinkedList<Pair<LocalDateTime, Double>> list) {
    return getExponential(periods, smoothing, list);
  }
}
