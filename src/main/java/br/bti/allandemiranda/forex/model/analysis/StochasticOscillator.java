//package br.bti.allandemiranda.forex.model.analysis;
//
//import br.bti.allandemiranda.forex.Processor;
//import br.bti.allandemiranda.forex.model.utils.Chart;
//import java.util.LinkedList;
//import org.apache.commons.lang3.tuple.Pair;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//public class StochasticOscillator {
//  private static final String NOT_NULL = "can't be a NULL";
//  private static final String CHART = "CandlestickPriceField";
//
//  protected static Logger LOGGER = LogManager.getLogger(Processor.class);
//  private Chart chart;
//
//  public StochasticOscillator(Chart chart) {
//    setChart(chart);
//  }
//
//  private Chart getChart() {
//    return chart;
//  }
//
//  private void setChart(Chart chart) {
//    if (chart != null) {
//      this.chart = chart;
//    } else {
//      throw new IllegalArgumentException(CHART + " " + NOT_NULL);
//    }
//  }
//
////  public LinkedList<Pair<Double, Double>> getStochasticOscillator(int kPeriod, int dPeriod, int slowing){
////
////  }
//}
