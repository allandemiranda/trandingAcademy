package br.bti.allandemiranda.forex.model.utils;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

/**
 * The type Chart.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Chart {

  private static final String BE_VALID_VALUE = "need be a valid value";
  private static final String PRICEFIELD = "Price Field";

  private final LinkedList<Candlestick> candlestickList = new LinkedList<>();
  private CurrencyExchange currencyExchange;

  /**
   * Instantiates a new Chart.
   *
   * @param currencyExchange the currency exchange
   */
  public Chart(@NotNull CurrencyExchange currencyExchange) {
    setCurrencyExchange(currencyExchange);
  }

  /**
   * Gets candlestick list.
   *
   * @return the candlestick list
   */
  public @NotNull List<Candlestick> getCandlestickList() {
    return candlestickList;
  }

  /**
   * Gets currency exchange.
   *
   * @return the currency exchange
   */
  public @NotNull CurrencyExchange getCurrencyExchange() {
    return currencyExchange;
  }

  /**
   * Sets currency exchange.
   *
   * @param currencyExchange the currency exchange
   */
  private void setCurrencyExchange(@NotNull CurrencyExchange currencyExchange) {
    this.currencyExchange = currencyExchange;
  }

  /**
   * Check candlestick list boolean.
   *
   * @return the boolean. TRUE to all values is the same current pair and have a correct sequence of
   * dates. FALSE to not all
   */
  protected boolean candlestickListIsGood() {
    return getCandlestickList().parallelStream().allMatch(candlestick -> {
      int index = getCandlestickList().indexOf(candlestick);
      if (index >= 0) {
        if (index == 0) {
          return true;
        } else {
          return getCandlestickList().get(index - 1).getLocalDateTime()
              .isBefore(candlestick.getLocalDateTime());
        }
      } else {
        return false;
      }
    }) && getCandlestickList().parallelStream().allMatch(
        candlestick -> candlestick.getCurrencyPair().equals(getCurrencyExchange().getCurrencyPair()));
  }

  /**
   * Gets price field list.
   *
   * @param candlestickPriceField the price field
   *
   * @return the price field list. On the left: The Time. On the right: The price.
   */
  public @NotNull List<Pair<LocalDateTime, Double>> getPriceFieldList(
      @NotNull CandlestickPriceField candlestickPriceField) {
    List<Pair<LocalDateTime, Double>> doubleList;
    switch (candlestickPriceField) {
      case OPEN_VALUE -> doubleList = getCandlestickList().stream()
          .map(candlestick -> Pair.of(candlestick.getLocalDateTime(), candlestick.getOpenPrice()))
          .toList();
      case LOW_VALUE -> doubleList = getCandlestickList().stream()
          .map(candlestick -> Pair.of(candlestick.getLocalDateTime(), candlestick.getLowPrice()))
          .toList();
      case HIGH_VALUE -> doubleList = getCandlestickList().stream()
          .map(candlestick -> Pair.of(candlestick.getLocalDateTime(), candlestick.getHighPrice()))
          .toList();
      case CLOSE_VALUE -> doubleList = getCandlestickList().stream()
          .map(candlestick -> Pair.of(candlestick.getLocalDateTime(), candlestick.getClosePrice()))
          .toList();
      default -> throw new IllegalArgumentException(PRICEFIELD + " " + BE_VALID_VALUE);
    }
    if (doubleList.size() == getCandlestickList().size()) {
      return doubleList;
    } else {
      throw new IllegalStateException("Different size to create a list of price field");
    }
  }

  /**
   * Gets volume list.
   *
   * @return the volume list. On the left: The Time. On the right: The Volume.
   */
  public @NotNull List<Pair<LocalDateTime, Integer>> getVolumeList() {
    List<Pair<LocalDateTime, Integer>> list = getCandlestickList().stream()
        .map(candlestick -> Pair.of(candlestick.getLocalDateTime(), candlestick.getVolume())).toList();
    if (list.size() == getCandlestickList().size()) {
      return list;
    } else {
      throw new IllegalStateException("Different size to create a list of volume");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Chart chart = (Chart) o;
    return candlestickList.equals(chart.candlestickList) && currencyExchange.equals(
        chart.currencyExchange);
  }

  @Override
  public int hashCode() {
    return Objects.hash(candlestickList, currencyExchange);
  }

  @Override
  public String toString() {
    return "Chart{" + "candlestickList=" + candlestickList + ", currencyExchange=" + currencyExchange
        + '}';
  }
}
