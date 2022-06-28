package br.bti.allandemiranda.forex.chart;

import br.bti.allandemiranda.forex.candlestick.Candlestick;
import br.bti.allandemiranda.forex.currency.Exchange;
import java.util.List;
import java.util.stream.Stream;

/**
 * The type Chart.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Chart extends Analysis {

  private final Exchange exchange;
  private final TimeFrame timeFrame;

  /**
   * Instantiates a new Chart.
   *
   * @param candlestickList the candlestick list
   * @param exchange        the exchange
   * @param timeFrame       the time frame
   */
  public Chart(List<Candlestick> candlestickList, Exchange exchange, TimeFrame timeFrame) {
    super(candlestickList);
    this.exchange = exchange;
    this.timeFrame = timeFrame;
  }

  /**
   * Gets exchange.
   *
   * @return the exchange
   */
  public Exchange getExchange() {
    return exchange;
  }

  /**
   * Gets time frame.
   *
   * @return the time frame
   */
  public TimeFrame getTimeFrame() {
    return timeFrame;
  }

  /**
   * Gets candlestick list.
   *
   * @return the candlestick list
   */
  public Stream<Candlestick> getCandlestickList() {
    return super.stream();
  }
}
