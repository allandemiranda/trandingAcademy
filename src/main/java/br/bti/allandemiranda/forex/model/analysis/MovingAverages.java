package br.bti.allandemiranda.forex.model.analysis;

import br.bti.allandemiranda.forex.Processor;
import br.bti.allandemiranda.forex.model.utils.Candlestick;
import br.bti.allandemiranda.forex.model.utils.Chart;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Moving averages.
 *
 * @author Allan de Miranda Silva
 * @version 0.3
 */
public class MovingAverages {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String NOT_NULL = "can't be a NULL";
  private static final String PERIODS = "Periods";
  private static final String APPLICATION = "Application";
  private static final String CHART = "Application";
  private static final String BE_VALID_VALUE = "need be a valid value";
  protected static Logger LOGGER = LogManager.getLogger(Processor.class);
  private Chart chart;

  /**
   * Instantiates a new Moving averages.
   *
   * @param chart the chart
   */
  public MovingAverages(Chart chart) {
    setChart(chart);
  }

  /**
   * Gets chart.
   *
   * @return the chart
   */
  private Chart getChart() {
    LOGGER.debug("Getting the Chart");
    return chart;
  }

  /**
   * Sets chart.
   *
   * @param chart the chart
   */
  private void setChart(Chart chart) {
    LOGGER.debug("Setting the Chart");
    if (chart != null) {
      this.chart = chart;
    } else {
      throw new IllegalArgumentException(CHART + " " + NOT_NULL);
    }
  }

  /**
   * Gets lwma.
   *
   * @param periods     the periods
   * @param application the application
   * @return the lwma
   */
  public LinkedList<Double> getLWMA(int periods, Application application) {
    return getLinearWeighted(periods, application);
  }

  /**
   * Gets linear weighted.
   *
   * @param periods     the periods
   * @param application the application
   * @return the linear weighted
   */
  private LinkedList<Double> getLinearWeighted(int periods, Application application) {
    LOGGER.debug("Getting a Linear Weighted Moving Average (LWMA) list - Periods {} - Application {}", periods, application.toString());
    if (periods > 0) {
      LinkedList<Double> allValues = getAllValuesFromApplication(application);

      Stream<Pair<Integer, ?>> pairStream =
          IntStream.rangeClosed(0, allValues.size() - 1)
              .boxed().toList()
              .parallelStream()
              .map(i -> {
                if (i >= (periods - 1)) {
                  double sum = 0.0;
                  int sumDays = 0;
                  for (int j = 0; j < periods; ++j) {
                    int day = i - j + 1;
                    sum += day * allValues.get(i - j);
                    sumDays += day;
                  }
                  return Pair.of(i, (sum / sumDays));
                } else {
                  return Pair.of(i, null);
                }
              })
              .sorted(Comparator.comparingInt(Pair::getKey));

      LinkedList<Double> linearWeightedList = pairStream
          .map(pair -> (Double) pair.getValue())
          .collect(Collectors.toCollection(LinkedList::new));

      if (linearWeightedList.size() == allValues.size()) {
        return linearWeightedList;
      } else {
        throw new IllegalArgumentException("The chart size need be the same size of Linear Weighted Moving Average");
      }
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets smma.
   *
   * @param periods     the periods
   * @param application the application
   * @return the smma
   */
  public LinkedList<Double> getSMMA(int periods, Application application) {
    return getSmoothed(periods, application);
  }

  /**
   * Gets smoothed.
   *
   * @param periods     the periods
   * @param application the application
   * @return the smoothed
   */
  private LinkedList<Double> getSmoothed(int periods, Application application) {
    LOGGER.debug("Getting a Smoothed Moving Average (SMMA) list - Periods {} - Application {}", periods, application.toString());
    if (periods > 0) {
      LinkedList<Double> allValues = getAllValuesFromApplication(application);
      LinkedList<Double> simplesList = getSimple(periods, application);

      LinkedList<Double> smoothedList = new LinkedList<>();

      // ! Can't use a parallel stream in this for, because me need the last value
      for (int i = 0; i < allValues.size(); ++i) {
        if (simplesList.get(i) != null) {
          if (i > 0) {
            if (smoothedList.get(i - 1) != null) {
              Double result = (smoothedList.get(i - 1) * (periods - 1) + allValues.get(i)) / periods;
              if (result >= 0.0) {
                smoothedList.add(result);
              } else {
                LOGGER.warn("We get a negative number in  Smoothed Moving Average generation list - Number {} - Position Chart {}", result,
                    i);
                smoothedList.add(null);
              }
            } else {
              smoothedList.add(simplesList.get(i));
            }
          } else {
            smoothedList.add(null);
          }
        } else {
          smoothedList.add(null);
        }
      }

      if (smoothedList.size() == allValues.size()) {
        return smoothedList;
      } else {
        throw new IllegalArgumentException("The chart size need be the same size of Smoothed Moving Average");
      }
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets ema.
   *
   * @param periods     the periods
   * @param application the application
   * @return the ema
   */
  public LinkedList<Double> getEMA(int periods, Application application) {
    return getEMA(periods, 2, application);
  }

  /**
   * Gets ema.
   *
   * @param periods     the periods
   * @param smoothing   the smoothing
   * @param application the application
   * @return the ema
   */
  public LinkedList<Double> getEMA(int periods, int smoothing, Application application) {
    return getExponential(periods, smoothing, application);
  }

  /**
   * Gets exponential.
   *
   * @param periods     the periods
   * @param smoothing   the smoothing
   * @param application the application
   * @return the exponential
   */
  private LinkedList<Double> getExponential(int periods, int smoothing, Application application) {
    LOGGER.debug("Getting a Exponential Moving Average (EMA) list - Periods {} - Smoothing {} - Application {}", periods, smoothing,
        application.toString());
    if (periods > 0) {
      LinkedList<Double> allValues = getAllValuesFromApplication(application);
      LinkedList<Double> simplesList = getSimple(periods, application);
      double percentage = (smoothing / (periods + 1.0));

      Stream<Pair<Integer, ?>> pairStream =
          IntStream.rangeClosed(0, allValues.size() - 1)
              .boxed().toList()
              .parallelStream()
              .map(i -> {
                if (simplesList.get(i) != null) {
                  if (i > 0) {
                    if (simplesList.get(i - 1) != null) {
                      double result = (allValues.get(i) * percentage) + (simplesList.get(i - 1) * (1 - percentage));
                      if (result >= 0.0) {
                        return Pair.of(i, result);
                      } else {
                        LOGGER.warn("We get a negative number in Exponential Moving Average generation list - Number {} - Position Chart {}",
                            result, i);
                        return Pair.of(i, 0.0);
                      }
                    } else {
                      return Pair.of(i, null);
                    }
                  } else {
                    return Pair.of(i, null);
                  }
                } else {
                  return Pair.of(i, null);
                }
              })
              .sorted(Comparator.comparingInt(Pair::getKey));

      LinkedList<Double> exponentialList = pairStream
          .map(pair -> (Double) pair.getValue())
          .collect(Collectors.toCollection(LinkedList::new));

      if (exponentialList.size() == allValues.size()) {
        return exponentialList;
      } else {
        throw new IllegalArgumentException("The chart size need be the same size of Exponential Moving Average");
      }
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets simple.
   *
   * @param periods     the periods
   * @param application the application
   * @return the simple
   */
  private LinkedList<Double> getSimple(int periods, Application application) {
    LOGGER.debug("Getting a Simple Moving Average (SMA) list - Periods {} - Application {}", periods, application.toString());
    if (periods > 0) {
      LinkedList<Double> allValues = getAllValuesFromApplication(application);

      Stream<Pair<Integer, ?>> pairStream =
          IntStream.rangeClosed(0, allValues.size() - 1)
              .boxed().toList()
              .parallelStream()
              .map(i -> {
                if ((i + 1) >= periods) {
                  Double simple = 0.0;
                  for (int j = 0; j < periods; ++j) {
                    simple += allValues.get(i - j);
                  }
                  return Pair.of(i, (simple / periods));
                } else {
                  return Pair.of(i, null);
                }
              })
              .sorted(Comparator.comparingInt(Pair::getKey));

      LinkedList<Double> simpleList = pairStream
          .map(pair -> (Double) pair.getValue())
          .collect(Collectors.toCollection(LinkedList::new));

      if (allValues.size() == simpleList.size()) {
        return simpleList;
      } else {
        throw new IllegalArgumentException("The chart size need be the same size of Simple Moving Average");
      }
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets SMA.
   *
   * @param periods     the periods
   * @param application the application
   * @return the SMA
   */
  public LinkedList<Double> getSMA(int periods, Application application) {
    return getSimple(periods, application);
  }

  /**
   * Gets all values from application.
   *
   * @param application the application
   * @return the all values from application
   */
  private LinkedList<Double> getAllValuesFromApplication(Application application) {
    LOGGER.debug("Getting all position values by application {}", application.toString());
    LinkedList<Double> doubleLinkedList;
    switch (application) {
      case OPEN_VALUE -> doubleLinkedList = getChart().getCandlestickList().stream().map(Candlestick::getOpenPrice)
          .collect(Collectors.toCollection(LinkedList::new));
      case LOW_VALUE -> doubleLinkedList = getChart().getCandlestickList().stream().map(Candlestick::getLowPrice)
          .collect(Collectors.toCollection(LinkedList::new));
      case HIGH_VALUE -> doubleLinkedList = getChart().getCandlestickList().stream().map(Candlestick::getHighPrice)
          .collect(Collectors.toCollection(LinkedList::new));
      case CLOSE_VALUE -> doubleLinkedList = getChart().getCandlestickList().stream().map(Candlestick::getClosePrice)
          .collect(Collectors.toCollection(LinkedList::new));
      default -> throw new IllegalArgumentException(APPLICATION + " " + BE_VALID_VALUE);
    }

    if (getChart().getCandlestickList().size() == doubleLinkedList.size()) {
      LOGGER.debug("We have {} positions to plot", doubleLinkedList.size());
      return doubleLinkedList;
    } else {
      throw new IllegalArgumentException("The chart size need be the same size of results");
    }
  }
}
