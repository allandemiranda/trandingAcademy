package br.bti.allandemiranda.forex.analysis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
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
 * The type Moving averages.
 *
 * @author Allan de Miranda Silva
 * @version 1.1.1
 */
public class MovingAverages {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String PERIODS = "Periods";
  private static final Logger logger = LogManager.getLogger(MovingAverages.class);

  /**
   * Instantiates a new Moving averages.
   */
  @Contract(" -> fail")
  private MovingAverages() {
    throw new IllegalStateException(MovingAverages.class.toString());
  }

  /**
   * Gets sma.
   *
   * @param periods the periods
   * @param list    the list
   *
   * @return the sma
   */
  public static @NotNull List<Pair<LocalDateTime, Double>> getSMA(int periods,
      @NotNull List<Pair<LocalDateTime, Double>> list) {
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
  private static @NotNull List<Pair<LocalDateTime, Double>> getSimpleList(int periods,
      @NotNull List<Pair<LocalDateTime, Double>> list) {
    logger.info("Getting a Simple Moving Average (SMA) list - Periods {}", periods);
    if (periods > 0) {
      return IntStream.rangeClosed(0, list.size() - 1).boxed().toList().parallelStream().map(i -> {
        if ((i + 1) >= periods) {
          DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
          for (int j = 0; j < periods; ++j) {
            if (Objects.isNull(list.get(i - j).getRight())) {
              return Pair.of(list.get(i).getLeft(), (Double) null);
            } else {
              statistics.accept(list.get(i - j).getRight());
            }
          }
          return Pair.of(list.get(i).getLeft(), statistics.getAverage());
        } else {
          return Pair.of(list.get(i).getLeft(), (Double) null);
        }
      }).sorted(Comparator.comparing(Pair::getLeft)).toList();
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
  private static @NotNull List<Pair<LocalDateTime, Double>> getExponential(int periods, int smoothing,
      @NotNull List<Pair<LocalDateTime, Double>> list) {
    logger.info("Getting a Exponential Moving Average (EMA) list - Periods {} - Smoothing {}", periods,
        smoothing);
    if (periods > 0) {
      double percentage = (smoothing / (periods + 1.0));

      List<Pair<LocalDateTime, Double>> finalList = new ArrayList<>();

      for (int i = 0; i < list.size(); ++i) {
        if ((i + 1) >= periods && !finalList.isEmpty()) {
          if (Objects.isNull(finalList.get(i - 1).getRight())) {
            DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
            boolean flag = false;
            for (int j = 0; j < periods; ++j) {
              if (Objects.isNull(list.get(i - j).getRight())) {
                finalList.add(Pair.of(list.get(i).getLeft(), null));
                flag = true;
                break;
              } else {
                statistics.accept(list.get(i - j).getRight());
              }
            }
            if (!flag) {
              finalList.add(Pair.of(list.get(i).getLeft(), statistics.getAverage()));
            }
          } else {
            double ema = (list.get(i).getRight() * percentage) + (finalList.get(i - 1).getRight() * (1
                - percentage));
            finalList.add(Pair.of(list.get(i).getLeft(), ema));
          }
        } else {
          finalList.add(Pair.of(list.get(i).getLeft(), null));
        }
      }

      return finalList;

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
  public static @NotNull List<Pair<LocalDateTime, Double>> getEMA(int periods, int smoothing,
      List<Pair<LocalDateTime, Double>> list) {
    return getExponential(periods, smoothing, list);
  }

  /**
   * Gets EMA with the smoothing 2.
   *
   * @param periods the periods
   * @param list    the list
   *
   * @return the default ema
   *
   * @since 1.1.0
   */
  public static @NotNull List<Pair<LocalDateTime, Double>> getDefaultEMA(int periods,
      List<Pair<LocalDateTime, Double>> list) {
    return getExponential(periods, 2, list);
  }
}
