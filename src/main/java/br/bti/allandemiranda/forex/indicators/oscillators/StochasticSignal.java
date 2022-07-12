package br.bti.allandemiranda.forex.indicators.oscillators;

import br.bti.allandemiranda.forex.chart.Line;
import br.bti.allandemiranda.forex.indicators.Signal;
import br.bti.allandemiranda.forex.indicators.Trend;
import java.util.List;

/**
 * The type Stochastic signal.
 */
public class StochasticSignal {

  /**
   * Gets signal.
   *
   * @param stochasticList the stochastic list
   * @param area           the area
   *
   * @return the signal
   */
  public static List<Signal> getSignal(List<Stochastic> stochasticList, double area) {
    double top = 100.0 - area;

    Line kLine = new Line(stochasticList.stream().map(Stochastic::getK).toList());
    Line slowLine = new Line(stochasticList.stream().map(Stochastic::getSlow).toList());

    return kLine.crossed(slowLine).parallelStream().map(
        localDateTime -> kLine.parallelStream()
            .filter(point -> point.getLocalDateTime().equals(localDateTime)).findFirst().get()).map(
        point -> {
          if(point.getValue() >= top) {
            return new Signal(point.getLocalDateTime(), Trend.DOWN);
          } else {
            if(point.getValue() <= area){
              return new Signal(point.getLocalDateTime(), Trend.UP);
            } else {
              return new Signal(point.getLocalDateTime(), Trend.NON);
            }
          }
        }).toList();
  }
}
