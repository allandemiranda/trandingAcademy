package br.bti.allandemiranda.forex.chart;

import br.bti.allandemiranda.forex.candlestick.Candlestick;
import br.bti.allandemiranda.forex.candlestick.PriceField;
import br.bti.allandemiranda.forex.indicators.oscillators.MACD;
import br.bti.allandemiranda.forex.indicators.oscillators.Stochastic;
import br.bti.allandemiranda.forex.indicators.trends.EMA;
import br.bti.allandemiranda.forex.indicators.trends.SMA;
import com.google.common.collect.Lists;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Pair;

/**
 * The type Analysis.
 */
public class Analysis {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String PERIODS = "Periods";

  private final LinkedList<Candlestick> candlestickList;

  /**
   * Instantiates a new Analysis.
   *
   * @param candlestickList the candlestick list
   */
  public Analysis(List<Candlestick> candlestickList) {
    this.candlestickList = Lists.newLinkedList(candlestickList);
  }

  /**
   * Gets candlestick list.
   *
   * @return the candlestick list
   */
  public List<Candlestick> getCandlestickList() {
    return candlestickList;
  }

  /**
   * Gets price field list.
   *
   * @param priceField the price field
   *
   * @return the price field list
   */
  public Line getPriceFieldList(PriceField priceField) {
    List<Point> list = switch (priceField) {
      case OPEN_VALUE -> getCandlestickList().stream()
          .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getOpenPrice()))
          .toList();
      case LOW_VALUE -> getCandlestickList().stream()
          .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getLowPrice()))
          .toList();
      case HIGH_VALUE -> getCandlestickList().stream()
          .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getHighPrice()))
          .toList();
      case CLOSE_VALUE -> getCandlestickList().stream()
          .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getClosePrice()))
          .toList();
    };
    if (list.size() == getCandlestickList().size()) {
      return new Line(list);
    } else {
      throw new IllegalStateException("Different size to create a Price Field List");
    }
  }

  /**
   * Gets volume list.
   *
   * @return the volume list
   */
  public Line getVolumeList() {
    List<Point> list = getCandlestickList().stream()
        .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getVolume())).toList();
    if (list.size() == getCandlestickList().size()) {
      return new Line(list);
    } else {
      throw new IllegalStateException("Different size to create a list of volume");
    }
  }


  /**
   * Gets Simple Moving Average (SMA) list.
   *
   * @param periods   the periods
   * @param priceLine the price line
   *
   * @return the simple moving average list
   */
  private List<SMA> getSimpleMovingAverageList(int periods, Line priceLine) {
    if (periods > 0) {
      return IntStream.rangeClosed(0, priceLine.getPointLinkedList().size() - 1).boxed().toList()
          .parallelStream().map(i -> {
            if ((i + 1) >= periods) {
              DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
              for (int j = 0; j < periods; ++j) {
                if (Objects.isNull(priceLine.getPointLinkedList().get(i - j).getValue())) {
                  return new SMA(priceLine.getPointLinkedList().get(i).getLocalDateTime(),
                      (Double) null);
                } else {
                  statistics.accept(priceLine.getPointLinkedList().get(i - j).getValue());
                }
              }
              return new SMA(priceLine.getPointLinkedList().get(i).getLocalDateTime(),
                  statistics.getAverage());
            } else {
              return new SMA(priceLine.getPointLinkedList().get(i).getLocalDateTime(), (Double) null);
            }
          }).sorted(Comparator.comparing(SMA::getLocalDateTime)).toList();
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets Simple Moving Average (SMA) list.
   *
   * @param periods    the periods
   * @param priceField the price field
   *
   * @return the sma
   */
  public List<SMA> getSimpleMovingAverageList(int periods, PriceField priceField) {
    Line priceLine = getPriceFieldList(priceField);
    return getSimpleMovingAverageList(periods, priceLine);
  }

  /**
   * Gets Exponential Moving Average (EMA) list
   *
   * @param periods   the periods
   * @param smoothing the smoothing
   * @param priceLine the price line
   *
   * @return the exponential moving average list
   */
  public List<EMA> getExponentialMovingAverageList(int periods, int smoothing, Line priceLine) {
    if (periods > 0) {
      double percentage = (smoothing / (periods + 1.0));
      List<EMA> finalList = new ArrayList<>();
      for (int i = 0; i < priceLine.getPointLinkedList().size(); ++i) {
        if ((i + 1) >= periods && !finalList.isEmpty()) {
          if (Objects.isNull(finalList.get(i - 1).getValue())) {
            DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
            boolean flag = false;
            for (int j = 0; j < periods; ++j) {
              if (Objects.isNull(priceLine.getPointLinkedList().get(i - j).getValue())) {
                finalList.add(
                    new EMA(priceLine.getPointLinkedList().get(i).getLocalDateTime(), (Double) null));
                flag = true;
                break;
              } else {
                statistics.accept(priceLine.getPointLinkedList().get(i - j).getValue());
              }
            }
            if (!flag) {
              finalList.add(new EMA(priceLine.getPointLinkedList().get(i).getLocalDateTime(),
                  statistics.getAverage()));
            }
          } else {
            double ema = (priceLine.getPointLinkedList().get(i).getValue() * percentage) + (
                finalList.get(i - 1).getValue() * (1 - percentage));
            finalList.add(new EMA(priceLine.getPointLinkedList().get(i).getLocalDateTime(), ema));
          }
        } else {
          finalList.add(new EMA(priceLine.getPointLinkedList().get(i).getLocalDateTime(), null));
        }
      }
      return finalList;
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets Exponential Moving Average (EMA) list
   *
   * @param periods    the periods
   * @param priceField the price field
   *
   * @return the default ema
   */
  public List<EMA> getExponentialMovingAverageList(int periods, PriceField priceField) {
    Line priceLine = getPriceFieldList(priceField);
    return getExponentialMovingAverageList(periods, 2, priceLine);
  }


  /**
   * Gets Exponential Moving Average (EMA) list
   *
   * @param periods    the periods
   * @param smoothing  the smoothing
   * @param priceField the price field
   *
   * @return the exponential moving average list
   */
  public List<EMA> getExponentialMovingAverageList(int periods, int smoothing, PriceField priceField) {
    Line priceLine = getPriceFieldList(priceField);
    return getExponentialMovingAverageList(periods, smoothing, priceLine);
  }

  /**
   * Gets macd.
   *
   * @param fastEMA    the fast ema
   * @param slowEMA    the slow ema
   * @param macdSMA    the macd sma
   * @param priceField the price field
   *
   * @return the MACD List. DateTime -> Histogram - MACD - Signal
   */
  public List<MACD> getMACD(int fastEMA, int slowEMA, int macdSMA, PriceField priceField) {
    if (fastEMA > 0 && slowEMA > 0 && macdSMA > 0) {
      Line priceLine = getPriceFieldList(priceField);
      List<EMA> fastEmaList = getExponentialMovingAverageList(fastEMA, priceField);
      List<EMA> slowEmaList = getExponentialMovingAverageList(slowEMA, priceField);
      Line macdSmaList = getDifListA(priceLine, fastEmaList, slowEmaList);
      List<EMA> sinalList = getExponentialMovingAverageList(macdSMA, 2, macdSmaList);
      Line histogramList = getDifList(priceLine, macdSmaList, sinalList);

      return IntStream.rangeClosed(0, priceLine.getPointLinkedList().size() - 1).boxed().map(
          i -> new MACD(priceLine.getPointLinkedList().get(i).getLocalDateTime(),
              histogramList.getPointLinkedList().get(i).getValue(),
              macdSmaList.getPointLinkedList().get(i).getValue(), sinalList.get(i).getValue())).toList();
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
  }

  /**
   * Gets dif list a.
   *
   * @param priceLine the price line
   * @param fastList  the fast list
   * @param slowList  the slow list
   *
   * @return the dif list a
   */
  private Line getDifListA(Line priceLine, List<EMA> fastList, List<EMA> slowList) {
    return new Line(IntStream.rangeClosed(0, priceLine.getPointLinkedList().size() - 1).boxed().toList()
        .parallelStream().map(i -> {
          if (Objects.isNull(fastList.get(i).getValue()) || Objects.isNull(slowList.get(i).getValue())) {
            return new Point(priceLine.getPointLinkedList().get(i).getLocalDateTime(), (Double) null);
          } else {
            return new Point(priceLine.getPointLinkedList().get(i).getLocalDateTime(),
                fastList.get(i).getValue() - slowList.get(i).getValue());
          }
        }).sorted(Comparator.comparing(Point::getLocalDateTime)).toList());
  }

  /**
   * Gets dif list.
   *
   * @param priceLine the price line
   * @param fastList  the fast list
   * @param slowList  the slow list
   *
   * @return the dif list
   */
  private Line getDifList(Line priceLine, Line fastList, List<EMA> slowList) {
    return new Line(IntStream.rangeClosed(0, priceLine.getPointLinkedList().size() - 1).boxed().toList()
        .parallelStream().map(i -> {
          if (Objects.isNull(fastList.getPointLinkedList().get(i).getValue()) || Objects.isNull(
              slowList.get(i).getValue())) {
            return new Point(priceLine.getPointLinkedList().get(i).getLocalDateTime(), (Double) null);
          } else {
            return new Point(priceLine.getPointLinkedList().get(i).getLocalDateTime(),
                fastList.getPointLinkedList().get(i).getValue() - slowList.get(i).getValue());
          }
        }).sorted(Comparator.comparing(Point::getLocalDateTime)).toList());
  }

  /**
   * Gets stochastic.
   *
   * @param kPeriod the k period
   * @param dPeriod the d period
   * @param slowing the slowing
   * @param list    the list
   *
   * @return the stochastic - TIME -> K, Slow
   */
  public List<Stochastic> getStochastic(int kPeriod, int dPeriod, int slowing, List<Candlestick> list) {
    List<Pair<LocalDateTime, Double>> listK = IntStream.rangeClosed(0, list.size() - 1).boxed().toList()
        .parallelStream().map(i -> {
          if (i + 1 >= kPeriod) {
            double high = list.get(i).getHighPrice();
            double low = list.get(i).getLowPrice();

            for (int j = 1; j < kPeriod; ++j) {
              if (Objects.isNull(list.get(i - j))) {
                return Pair.of(list.get(i).getLocalDateTime(), (Double) null);
              } else {
                high = Math.max(high, list.get(i - j).getHighPrice());
                low = Math.min(low, list.get(i - j).getLowPrice());
              }
            }

            return Pair.of(list.get(i).getLocalDateTime(),
                (((list.get(i).getClosePrice() - low) / (high - low)) * 100));

          } else {
            return Pair.of(list.get(i).getLocalDateTime(), (Double) null);
          }
        }).toList();

    List<SMA> dList = getSimpleMovingAverageList(dPeriod, listK);

    List<SMA> listSlow = getSimpleMovingAverageList(slowing,
        dList.stream().map(sma -> Pair.of(sma.getLocalDateTime(), sma.getValue())).toList());

    return IntStream.rangeClosed(0, list.size() - 1).boxed().toList().parallelStream().map(
            i -> new Stochastic(list.get(i).getLocalDateTime(), listK.get(i).getRight(),
                listSlow.get(i).getValue())).sorted(Comparator.comparing(Stochastic::getLocalDateTime))
        .toList();
  }
}
