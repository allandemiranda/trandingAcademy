//package br.bti.allandemiranda.forex.model.analysis;
//
//import br.bti.allandemiranda.forex.Processor;
//import br.bti.allandemiranda.forex.model.utils.Candlestick;
//import br.bti.allandemiranda.forex.model.utils.Chart;
//import java.util.Comparator;
//import java.util.LinkedList;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//import java.util.stream.Stream;
//import org.apache.commons.lang3.tuple.Pair;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
///**
// * The type Williams Percent Range.
// *
// * @author Allan de Miranda Silva
// * @version 0.3
// */
//public class WilliamsPercentRange {
//
//  private static final String NOT_NULL = "can't be a NULL";
//  private static final String CHART = "CandlestickPriceField";
//
//  protected static Logger LOGGER = LogManager.getLogger(Processor.class);
//  private Chart chart;
//
//  /**
//   * Instantiates a new Williams percent range.
//   *
//   * @param chart the chart
//   */
//  public WilliamsPercentRange(Chart chart) {
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
//   * Gets williams percent.
//   *
//   * @param period the period
//   * @return the williams percent
//   */
//  public LinkedList<Double> getWilliamsPercent(int period) {
//    LOGGER.info("Getting a Williams Percent Range list - Period {} ", period);
//    LinkedList<Candlestick> candlestickList = getChart().getCandlestickList();
//
//    Stream<Pair<Integer, ?>> williamsStream = IntStream.rangeClosed(0, candlestickList.size() - 1)
//        .boxed().toList()
//        .parallelStream()
//        .map(i -> {
//          if (i >= period) {
//            if (candlestickList.get(i) != null) {
//              double closeValue = candlestickList.get(i).getClosePrice();
//              final double[] maxValue = {candlestickList.get(i).getHighPrice()};
//              final double[] minValue = {candlestickList.get(i).getLowPrice()};
//
//              for (int j = 1; j < period; ++j) {
//                if (candlestickList.get(i - j) != null) {
//                  int finalJ = j;
//                  Thread threadToMaxValue = new Thread(){
//                    @Override
//                    public void run() {
//                      super.run();
//                      if (candlestickList.get(i - finalJ).getHighPrice() > maxValue[0]) {
//                        maxValue[0] = candlestickList.get(i - finalJ).getHighPrice();
//                      }
//                    }
//                  };
//
//                  Thread threadToMinValue = new Thread(){
//                    @Override
//                    public void run() {
//                      super.run();
//                      if (candlestickList.get(i - finalJ).getLowPrice() < minValue[0]) {
//                        minValue[0] = candlestickList.get(i - finalJ).getLowPrice();
//                      }
//                    }
//                  };
//
//                  threadToMaxValue.start();
//                  threadToMinValue.start();
//
//                  try {
//                    threadToMaxValue.join();
//                    threadToMinValue.join();
//                  } catch (InterruptedException e) {
//                    LOGGER.error(e);
//                    e.printStackTrace();
//                  }
////                  TODO: Guardado para se precisar rever o paralelismo nesse ponto
////                  if (candlestickList.get(i - j).getHighPrice() > maxValue) {
////                    maxValue = candlestickList.get(i - j).getHighPrice();
////                  }
////                  if (candlestickList.get(i - j).getLowPrice() < minValue) {
////                    minValue = candlestickList.get(i - j).getLowPrice();
////                  }
//
//                } else {
//                  return Pair.of(i, null);
//                }
//              }
//
//              Double valueFinal = ((((maxValue[0] - closeValue) * (-1)) / (maxValue[0] - minValue[0])) * 100);
//              if (valueFinal.isNaN()) {
//                throw new RuntimeException("We find a NaN value to a Willims Percent");
//              } else {
//                return Pair.of(i, valueFinal);
//              }
//
//            } else {
//              return Pair.of(i, null);
//            }
//          } else {
//            return Pair.of(i, null);
//          }
//        })
//        .sorted(Comparator.comparingInt(Pair::getKey));
//
//    LinkedList<Double> williamsList = williamsStream
//        .map(pair -> (Double) pair.getValue())
//        .collect(Collectors.toCollection(LinkedList::new));
//
//    if (williamsList.size() == candlestickList.size()) {
//      return williamsList;
//    } else {
//      throw new IllegalArgumentException("The chart size need be the same size of Williams Percent Range");
//    }
//  }
//}
