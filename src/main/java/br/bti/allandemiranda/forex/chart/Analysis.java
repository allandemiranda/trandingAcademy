package br.bti.allandemiranda.forex.chart;

import br.bti.allandemiranda.forex.candlestick.Candlestick;
import br.bti.allandemiranda.forex.candlestick.PriceField;
import br.bti.allandemiranda.forex.indicators.oscillators.MACD;
import br.bti.allandemiranda.forex.indicators.oscillators.Stochastic;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The type Analysis.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Analysis extends LinkedList<Candlestick> {

  private static final String NOT_NEGATIVE_NUMBER = "can't be a negative number";
  private static final String PERIODS = "Periods";

  /**
   * Instantiates a new Analysis.
   *
   * @param c the c
   */
  public Analysis(@NotNull Collection<? extends Candlestick> c) {
    super(c);
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
      case OPEN_VALUE -> this.stream()
          .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getOpenPrice()))
          .toList();
      case LOW_VALUE -> this.stream()
          .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getLowPrice()))
          .toList();
      case HIGH_VALUE -> this.stream()
          .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getHighPrice()))
          .toList();
      case CLOSE_VALUE -> this.stream()
          .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getClosePrice()))
          .toList();
    };
    if (list.size() == this.size()) {
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
    List<Point> list = this.stream()
        .map(candlestick -> new Point(candlestick.getLocalDateTime(), candlestick.getVolume())).toList();
    if (list.size() == this.size()) {
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
  @Contract("_, _ -> new")
  private @NotNull Line getSimpleMovingAverageList(int periods, Line priceLine) {
    if (periods > 0) {
      return new Line(
          IntStream.rangeClosed(0, priceLine.size() - 1).boxed().toList().parallelStream().map(i -> {
            if ((i + 1) >= periods) {
              DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
              for (int j = 0; j < periods; ++j) {
                if (Objects.isNull(priceLine.get(i - j).getValue())) {
                  return new Point(priceLine.get(i).getLocalDateTime(), (Double) null);
                } else {
                  statistics.accept(priceLine.get(i - j).getValue());
                }
              }
              return new Point(priceLine.get(i).getLocalDateTime(), statistics.getAverage());
            } else {
              return new Point(priceLine.get(i).getLocalDateTime(), (Double) null);
            }
          }).sorted(Comparator.comparing(Point::getLocalDateTime)).toList());
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
  public Line getSimpleMovingAverageList(int periods, PriceField priceField) {
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
  public Line getExponentialMovingAverageList(int periods, int smoothing, Line priceLine) {
    if (periods > 0) {
      double percentage = (smoothing / (periods + 1.0));
      Line finalList = new Line();
      for (int i = 0; i < priceLine.size(); ++i) {
        if ((i + 1) >= periods && !finalList.isEmpty()) {
          if (Objects.isNull(finalList.get(i - 1).getValue())) {
            DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
            boolean flag = false;
            for (int j = 0; j < periods; ++j) {
              if (Objects.isNull(priceLine.get(i - j).getValue())) {
                finalList.add(new Point(priceLine.get(i).getLocalDateTime(), (Double) null));
                flag = true;
                break;
              } else {
                statistics.accept(priceLine.get(i - j).getValue());
              }
            }
            if (!flag) {
              finalList.add(new Point(priceLine.get(i).getLocalDateTime(), statistics.getAverage()));
            }
          } else {
            double ema =
                (priceLine.get(i).getValue() * percentage) + (finalList.get(i - 1).getValue() * (1
                    - percentage));
            finalList.add(new Point(priceLine.get(i).getLocalDateTime(), ema));
          }
        } else {
          finalList.add(new Point(priceLine.get(i).getLocalDateTime(), null));
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
  public Line getExponentialMovingAverageList(int periods, PriceField priceField) {
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
  public Line getExponentialMovingAverageList(int periods, int smoothing, PriceField priceField) {
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
      Line fastEmaList = new Line(getExponentialMovingAverageList(fastEMA, priceField));
      Line slowEmaList = getExponentialMovingAverageList(slowEMA, priceField);
      Line macdSmaList = getDifList(priceLine, fastEmaList, slowEmaList);
      Line sinalList = getExponentialMovingAverageList(macdSMA, 2, macdSmaList);
      Line histogramList = getDifList(priceLine, macdSmaList, sinalList);

      return IntStream.rangeClosed(0, priceLine.size() - 1).boxed().map(
          i -> new MACD(priceLine.get(i).getLocalDateTime(), histogramList.get(i).getValue(),
              macdSmaList.get(i).getValue(), sinalList.get(i).getValue())).toList();
    } else {
      throw new InputMismatchException(PERIODS + " " + NOT_NEGATIVE_NUMBER);
    }
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
  @Contract("_, _, _ -> new")
  private @NotNull Line getDifList(@NotNull Line priceLine, Line fastList, Line slowList) {
    return new Line(
        IntStream.rangeClosed(0, priceLine.size() - 1).boxed().toList().parallelStream().map(i -> {
          if (Objects.isNull(fastList.get(i).getValue()) || Objects.isNull(slowList.get(i).getValue())) {
            return new Point(priceLine.get(i).getLocalDateTime(), (Double) null);
          } else {
            return new Point(priceLine.get(i).getLocalDateTime(),
                fastList.get(i).getValue() - slowList.get(i).getValue());
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
  public List<Stochastic> getStochastic(int kPeriod, int dPeriod, int slowing, @NotNull List<Candlestick> list) {
    Line listK = new Line(
        IntStream.rangeClosed(0, list.size() - 1).boxed().toList().parallelStream().map(i -> {
          if (i + 1 >= kPeriod) {
            double high = list.get(i).getHighPrice();
            double low = list.get(i).getLowPrice();

            for (int j = 1; j < kPeriod; ++j) {
              if (Objects.isNull(list.get(i - j))) {
                return new Point(list.get(i).getLocalDateTime(), (Double) null);
              } else {
                high = Math.max(high, list.get(i - j).getHighPrice());
                low = Math.min(low, list.get(i - j).getLowPrice());
              }
            }

            return new Point(list.get(i).getLocalDateTime(),
                (((list.get(i).getClosePrice() - low) / (high - low)) * 100));

          } else {
            return new Point(list.get(i).getLocalDateTime(), (Double) null);
          }
        }).toList());

    Line dList = getSimpleMovingAverageList(dPeriod, listK);

    Line listSlow = getSimpleMovingAverageList(slowing,
        new Line(dList.stream().map(sma -> new Point(sma.getLocalDateTime(), sma.getValue())).toList()));

    return IntStream.rangeClosed(0, list.size() - 1).boxed().toList().parallelStream().map(
            i -> new Stochastic(list.get(i).getLocalDateTime(), listK.get(i).getValue(),
                listSlow.get(i).getValue())).sorted(Comparator.comparing(Stochastic::getLocalDateTime))
        .toList();
  }
}
