package br.bti.allandemiranda.forex.model.analysis;

import br.bti.allandemiranda.forex.Processor;
import br.bti.allandemiranda.forex.model.utils.Chart;
import java.util.LinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WilliamsPercentRange {

  private static final String NOT_NULL = "can't be a NULL";
  private static final String CHART = "Application";

  protected static Logger LOGGER = LogManager.getLogger(Processor.class);
  private Chart chart;

  public WilliamsPercentRange(Chart chart) {
    setChart(chart);
  }

  private Chart getChart() {
    LOGGER.debug("Getting the Chart");
    return chart;
  }

  private void setChart(Chart chart) {
    LOGGER.debug("Setting the Chart");
    if (chart != null) {
      this.chart = chart;
    } else {
      throw new IllegalArgumentException(CHART + " " + NOT_NULL);
    }
  }

  public LinkedList<Double> getWilliamsPercent(int period) {

    LinkedList<Double> finalList = new LinkedList<>();

    for (int i = 0; i < getChart().getCandlestickList().size(); ++i) {
      if (i >= period) {
        if (getChart().getCandlestickList().get(i) != null) {
          double closeValue = getChart().getCandlestickList().get(i).getClosePrice();
          double maxValue = getChart().getCandlestickList().get(i).getHighPrice();
          double minValue = getChart().getCandlestickList().get(i).getLowPrice();

          boolean flag = false;

          for (int j = 1; j < period; ++j) {
            if (getChart().getCandlestickList().get(i - j) != null) {
              if (getChart().getCandlestickList().get(i - j).getHighPrice() > maxValue) {
                maxValue = getChart().getCandlestickList().get(i - j).getHighPrice();
              }
              if (getChart().getCandlestickList().get(i - j).getLowPrice() < minValue) {
                minValue = getChart().getCandlestickList().get(i - j).getLowPrice();
              }
            } else {
              flag = true;
              break;
            }
          }

          if (flag) {
            finalList.add(null);
          } else {
            Double valueFinal = ((((maxValue - closeValue) * (-1)) / (maxValue - minValue)) * 100);
            if (valueFinal.isNaN()) {
              throw new RuntimeException("We find a NaN value to a Willims Percent");
            } else {
              finalList.add(valueFinal);
            }
          }
        } else {
          finalList.add(null);
        }
      } else {
        finalList.add(null);
      }
    }
    return finalList;
  }
}
