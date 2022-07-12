package br.bti.allandemiranda.forex.indicators.oscillators;

import br.bti.allandemiranda.forex.chart.Line;
import br.bti.allandemiranda.forex.indicators.Signal;
import br.bti.allandemiranda.forex.indicators.Trend;
import br.bti.allandemiranda.forex.indicators.trend.CrossSignal;
import java.util.Comparator;
import java.util.List;

/**
 * The type Macd signal.
 */
public class MACDSignal {

  /**
   * Gets signal.
   *
   * @param macdList the macd list
   *
   * @return the signal
   */
  public static List<Signal> getSignal(List<MACD> macdList) {
    Line macdLine = new Line(macdList.stream().map(MACD::getMacd).toList());
    Line signalLine = new Line(macdList.stream().map(MACD::getSignal).toList());
    return CrossSignal.getTrendSinal(macdLine, signalLine);
  }

  /**
   * Gets trend.
   *
   * @param macdList the macd list
   *
   * @return the trend
   */
  public static List<Signal> getTrend(List<MACD> macdList) {
    return macdList.parallelStream().map(macd -> {
      if (macd.getHistogram().getValue() > 0) {
        return new Signal(macd.getLocalDateTime(), Trend.UP);
      } else {
        if (macd.getHistogram().getValue() < 0) {
          return new Signal(macd.getLocalDateTime(), Trend.DOWN);
        } else {
          return new Signal(macd.getLocalDateTime(), Trend.NON);
        }
      }
    }).sorted(Comparator.comparing(Signal::getLocalDateTime)).toList();
  }
}
