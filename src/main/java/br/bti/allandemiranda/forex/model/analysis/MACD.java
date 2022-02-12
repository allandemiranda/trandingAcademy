//package br.bti.allandemiranda.forex.model.analysis;
//
//import br.bti.allandemiranda.forex.Processor;
//import br.bti.allandemiranda.forex.model.utils.Chart;
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//import java.util.stream.Stream;
//import org.apache.commons.lang3.tuple.Pair;
//import org.apache.commons.lang3.tuple.Triple;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
///**
// * The type Macd.
// *
// * @author Allan de Miranda Silva
// * @version 0.3
// */
//public class MACD {
//
//  private static final String NOT_NULL = "can't be a NULL";
//  private static final String CHART = "CandlestickPriceField";
//
//  protected static Logger LOGGER = LogManager.getLogger(Processor.class);
//  private Chart chart;
//
//  /**
//   * Instantiates a new Macd.
//   *
//   * @param chart the chart
//   */
//  public MACD(Chart chart) {
//    setChart(chart);
//  }
//
//  /**
//   * Gets chart.
//   *
//   * @return the chart
//   */
//  private Chart getChart() {
//    return chart;
//  }
//
//  /**
//   * Sets chart.
//   *
//   * @param chart the chart
//   */
//  private void setChart(Chart chart) {
//    if (chart != null) {
//      this.chart = chart;
//    } else {
//      throw new IllegalArgumentException(CHART + " " + NOT_NULL);
//    }
//  }
//
//  /**
//   * Gets macd.
//   *
//   * @param fastEMA     the fast ema
//   * @param slowEMA     the slow ema
//   * @param macdSMA     the macd sma
//   * @param application the application
//   * @return the MACH and SIGNAL pair
//   * @throws InterruptedException the interrupted exception
//   */
//  public LinkedList<Triple<Double, Double,Double>> getMACD(int fastEMA, int slowEMA, int macdSMA, CandlestickPriceField application) throws InterruptedException {
//    LOGGER.info("Getting a MACD list - Fast EMA {} - Slow EMA {} - MACD SMA {} - CandlestickPriceField {}", fastEMA, slowEMA, macdSMA, application.toString());
//    MovingAverages movingAverages = new MovingAverages(getChart());
//
//    class ThreadEMA extends Thread {
//
//      private final int ema;
//      private LinkedList<Double> list = new LinkedList<>();
//
//      /**
//       * Instantiates a new Thread ema.
//       *
//       * @param ema the ema
//       */
//      public ThreadEMA(int ema) {
//        this.ema = ema;
//      }
//
//      @Override
//      public void run() {
//        super.run();
//        setList(movingAverages.getEMA(ema, application));
//      }
//
//      /**
//       * Gets list.
//       *
//       * @return the list
//       */
//      public LinkedList<Double> getList() {
//        return list;
//      }
//
//      /**
//       * Sets list.
//       *
//       * @param list the list
//       */
//      private void setList(LinkedList<Double> list) {
//        this.list = list;
//      }
//    }
//
//    ThreadEMA fast = new ThreadEMA(fastEMA);
//    ThreadEMA slow = new ThreadEMA(slowEMA);
//
//    fast.start();
//    slow.start();
//
//    try {
//      fast.join();
//      slow.join();
//    } catch (InterruptedException e) {
//      LOGGER.error(e);
//      throw new InterruptedException("Find a problem on get a EMA");
//    }
//
//    LinkedList<Double> fastList = fast.getList();
//    LinkedList<Double> slowList = slow.getList();
//
//    System.out.println("fastList = " + fastList);
//    System.out.println("slowList = " + slowList);
//
//    if(fastList.size() != getChart().getCandlestickList().size() && slowList.size() != getChart().getCandlestickList().size()){
//      throw new IllegalArgumentException("The Fast and Slow MACD size need be the same size of Chart");
//    }
//
//    Stream<Pair<Integer, ?>> macdStream =
//        IntStream.rangeClosed(0, chart.getCandlestickList().size() - 1)
//            .boxed().toList()
//            .parallelStream()
//            .map(i -> {
//              if (fastList.get(i) != null && slowList.get(i) != null) {
//                return Pair.of(i, fastList.get(i) - slowList.get(i));
//              } else {
//                return Pair.of(i, null);
//              }
//            })
//            .sorted(Comparator.comparingInt(Pair::getKey));
//
//    LinkedList<Double> macdList = macdStream
//        .map(pair -> (Double) pair.getValue())
//        .collect(Collectors.toCollection(LinkedList::new));
//
//    System.out.println("macdList = " + macdList);
//
//    if(macdList.size() != getChart().getCandlestickList().size()){
//      throw new IllegalArgumentException("The MACD size need be the same size of Chart");
//    }
//
//    LinkedList<Double> signalList = movingAverages.getSimpleToOthers(macdSMA, macdList);
//
//    System.out.println("signalList = " + signalList);
//
//    if (macdList.size() != signalList.size()) {
//      throw new IllegalArgumentException("The Sinal size need be the same size of MACD");
//    }
//
//    Stream<Pair<Integer, ?>> histogramStream =
//        IntStream.rangeClosed(0, chart.getCandlestickList().size() - 1)
//            .boxed().toList()
//            .parallelStream()
//            .map(i -> {
//              if (macdList.get(i) != null && signalList.get(i) != null) {
//                return Pair.of(i, macdList.get(i) - signalList.get(i));
//              } else {
//                return Pair.of(i, null);
//              }
//            })
//            .sorted(Comparator.comparingInt(Pair::getKey));
//
//    LinkedList<Double> histogramList = histogramStream
//        .map(pair -> (Double) pair.getValue())
//        .collect(Collectors.toCollection(LinkedList::new));
//
//    System.out.println("histogramList = " + histogramList);
//
//    if(histogramList.size() != chart.getCandlestickList().size()){
//      throw new IllegalArgumentException("The Histogram size need be the same size of Chart");
//    }
//
//    Stream<Pair<Integer, ?>> finalStream =
//        IntStream.rangeClosed(0, chart.getCandlestickList().size() - 1)
//            .boxed().toList()
//            .parallelStream()
//            .map(i -> {
//              if (macdList.get(i) == null || signalList.get(i) == null) {
//                return Pair.of(i, null);
//              } else {
//                return Pair.of(i, Triple.of(macdList.get(i), signalList.get(i), histogramList.get(i)));
//              }
//            })
//            .sorted(Comparator.comparingInt(Pair::getKey));
//
//    LinkedList<Triple<Double, Double,Double>> finalList = finalStream
//        .map(integerPair -> (Triple<Double, Double,Double>) integerPair.getValue())
//        .collect(Collectors.toCollection(LinkedList::new));
//
//    if (finalList.size() == chart.getCandlestickList().size()) {
//      return finalList;
//    } else {
//      throw new IllegalArgumentException("The chart size need be the same size of MACD");
//    }
//  }
//
//}
