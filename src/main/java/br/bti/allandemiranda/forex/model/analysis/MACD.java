package br.bti.allandemiranda.forex.model.analysis;

import br.bti.allandemiranda.forex.Processor;
import br.bti.allandemiranda.forex.model.utils.Chart;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Macd.
 *
 * @author Allan de Miranda Silva
 * @version 0.3
 */
public class MACD {

  private static final String NOT_NULL = "can't be a NULL";
  private static final String CHART = "Application";

  protected static Logger LOGGER = LogManager.getLogger(Processor.class);
  private Chart chart;

  /**
   * Instantiates a new Macd.
   *
   * @param chart the chart
   */
  public MACD(Chart chart) {
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
   * Gets macd.
   *
   * @param fastEMA     the fast ema
   * @param slowEMA     the slow ema
   * @param macdSMA     the macd sma
   * @param application the application
   * @return the MACH and SIGNAL pair
   * @throws InterruptedException the interrupted exception
   */
  public LinkedList<Pair<Double, Double>> getMACD(int fastEMA, int slowEMA, int macdSMA, Application application) throws InterruptedException {
    MovingAverages movingAverages = new MovingAverages(getChart());

    class ThreadEMA extends Thread {

      private final int ema;
      private LinkedList<Double> list = new LinkedList<>();

      /**
       * Instantiates a new Thread ema.
       *
       * @param ema the ema
       */
      public ThreadEMA(int ema) {
        this.ema = ema;
      }

      @Override
      public void run() {
        super.run();
        setList(movingAverages.getEMA(ema, application));
      }

      /**
       * Gets list.
       *
       * @return the list
       */
      public LinkedList<Double> getList() {
        return list;
      }

      /**
       * Sets list.
       *
       * @param list the list
       */
      private void setList(LinkedList<Double> list) {
        this.list = list;
      }
    }

    ThreadEMA fast = new ThreadEMA(fastEMA);
    ThreadEMA slow = new ThreadEMA(slowEMA);

    fast.start();
    slow.start();

    try {
      fast.join();
      slow.join();
    } catch (InterruptedException e) {
      LOGGER.error(e);
      throw new InterruptedException("Find a problem on get a EMA");
    }

    LinkedList<Double> fastList = fast.getList();
    LinkedList<Double> slowList = slow.getList();

    Stream<Pair<Integer, ?>> macdStream =
        IntStream.rangeClosed(0, chart.getCandlestickList().size() - 1)
            .boxed().toList()
            .parallelStream()
            .map(i -> {
              if (fastList.get(i) != null && slowList.get(i) != null) {
                return Pair.of(i, fastList.get(i) - slowList.get(i));
              } else {
                return Pair.of(i, null);
              }
            })
            .sorted(Comparator.comparingInt(Pair::getKey));

    LinkedList<Double> macdList = macdStream
        .map(pair -> (Double) pair.getValue())
        .collect(Collectors.toCollection(LinkedList::new));

    LinkedList<Double> signalList = movingAverages.getSimpleToMACD(macdSMA, macdList);

    if (macdList.size() != signalList.size()) {
      throw new IllegalArgumentException("The MACD size need be the same size of Sinal");
    } else {

      Stream<Pair<Integer, ?>> finalStream =
          IntStream.rangeClosed(0, chart.getCandlestickList().size() - 1)
              .boxed().toList()
              .parallelStream()
              .map(i -> {
                if (macdList.get(i) == null || signalList.get(i) == null) {
                  return Pair.of(i, null);
                } else {
                  return Pair.of(i, Pair.of(macdList.get(i), signalList.get(i)));
                }
              })
              .sorted(Comparator.comparingInt(Pair::getKey));

      LinkedList<Pair<Double, Double>> finalList = finalStream
          .map(integerPair -> (Pair<Double, Double>) integerPair.getValue())
          .collect(Collectors.toCollection(LinkedList::new));

      if (finalList.size() == chart.getCandlestickList().size()) {
        return finalList;
      } else {
        throw new IllegalArgumentException("The chart size need be the same size of MACD");
      }
    }
  }

}
