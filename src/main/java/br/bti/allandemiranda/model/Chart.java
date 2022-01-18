package br.bti.allandemiranda.model;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * The type Chart.
 */
public class Chart {

  private final LinkedList<Candlestick> candlestickLinkedList = new LinkedList<>();

  /**
   * Instantiates a new Chart.
   *
   * @param firstCandlestick the first candlestick
   * @throws NullPointerException the null pointer exception
   * @throws InterruptedException the interrupted exception
   */
  public Chart(Candlestick firstCandlestick) throws NullPointerException, InterruptedException {
    pushCandlestick(firstCandlestick);
  }

  /**
   * Gets candlestick list.
   *
   * @return the candlestick list
   */
  private LinkedList<Candlestick> getCandlestickList() {
    return candlestickLinkedList;
  }

  /**
   * Gets size index.
   *
   * @return the size index
   * @throws InterruptedException the interrupted exception
   */
  private int getSizeIndex() throws InterruptedException {
    if (getCandlestickList().isEmpty()) {
      throw new InterruptedException("The Chart is empty");
    } else {
      return getCandlestickList().size() - 1;
    }
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public int getSize() {
    return getCandlestickList().size();
  }

  /**
   * Gets candlestick position.
   *
   * @param candlestick the candlestick
   * @return the candlestick position
   */
  public int getCandlestickPosition(Candlestick candlestick) {
    int position = getCandlestickList().indexOf(candlestick);
    if (position > -1) {
      return position;
    } else {
      throw new NullPointerException(
          "Candlestick " + candlestick.toString() + " not exist on this Chart");
    }
  }

  /**
   * Gets candlestick.
   *
   * @param position the position
   * @return the candlestick
   * @throws ArrayIndexOutOfBoundsException the array index out of bounds exception
   */
  public Candlestick getCandlestick(int position) throws ArrayIndexOutOfBoundsException {
    if (position <= 0) {
      throw new ArrayIndexOutOfBoundsException(
          "Get Candlestick position " + position + " not exist on this Chart");
    } else {
      return getCandlestickList().get(position - 1);
    }
  }

  /**
   * Gets candlestick.
   *
   * @param localDateTime the local date time
   * @return the candlestick
   * @throws NullPointerException the null pointer exception
   * @throws InterruptedException the interrupted exception
   */
  public Candlestick getCandlestick(LocalDateTime localDateTime)
      throws NullPointerException, InterruptedException {
    List<Candlestick> candlestickList =
        getCandlestickList().parallelStream()
            .filter(o -> localDateTime.equals(o.getLocalDateTime())).toList();
    if (candlestickList.isEmpty()) {
      throw new NullPointerException(
          "Get Candlestick at " + localDateTime.toString() + " not exist on this Chart");
    } else {
      if (candlestickList.size() > 1) {
        throw new InterruptedException(
            "Get more than one Candlestick at " + localDateTime.toString());
      } else {
        return candlestickList.get(0);
      }
    }
  }

  /**
   * Gets previous candlestick.
   *
   * @param candlestick the candlestick
   * @return the previous candlestick
   * @throws NullPointerException the null pointer exception
   */
  public Candlestick getPreviousCandlestick(Candlestick candlestick) throws NullPointerException {
    int position = getCandlestickList().indexOf(candlestick);
    if (position > -1) {
      if (position == 0) {
        throw new NullPointerException(
            "Candlestick " + candlestick.toString() + " don't have previous on this Chart");
      } else {
        return getCandlestick(position - 1);
      }
    } else {
      throw new NullPointerException(
          "Candlestick " + candlestick.toString() + " not exist on this Chart");
    }
  }

  /**
   * Gets next candlestick.
   *
   * @param candlestick the candlestick
   * @return the next candlestick
   * @throws NullPointerException the null pointer exception
   * @throws InterruptedException the interrupted exception
   */
  public Candlestick getNextCandlestick(Candlestick candlestick)
      throws NullPointerException, InterruptedException {
    int position = getCandlestickList().indexOf(candlestick);
    if (position > -1) {
      if (position == getSizeIndex()) {
        throw new NullPointerException(
            "Candlestick " + candlestick.toString() + " don't have next on this Chart");
      } else {
        return getCandlestick(position + 1);
      }
    } else {
      throw new NullPointerException(
          "Candlestick " + candlestick.toString() + " not exist on this Chart");
    }
  }

  /**
   * Gets list of candlestick on same price action.
   *
   * @param priceAction the price action
   * @return the list of candlestick on same price action
   */
  public List<Candlestick> getListOfCandlestickOnSamePriceAction(PriceAction priceAction) {
    return getCandlestickList().parallelStream().filter(o -> priceAction.equals(o.getPriceAction()))
        .toList();
  }

  /**
   * Gets list of candlestick on same body size.
   *
   * @param size the size
   * @return the list of candlestick on same body size
   */
  public List<Candlestick> getListOfCandlestickOnSameBodySize(Double size) {
    return getCandlestickList().parallelStream().filter(o -> size.equals(o.getBodySize())).toList();
  }

  /**
   * Gets list of candlestick on same upper shadow size.
   *
   * @param size the size
   * @return the list of candlestick on same upper shadow size
   */
  public List<Candlestick> getListOfCandlestickOnSameUpperShadowSize(Double size) {
    return getCandlestickList().parallelStream().filter(o -> size.equals(o.getUpperShadowSize()))
        .toList();
  }

  /**
   * Gets list of candlestick on same lower shadow size.
   *
   * @param size the size
   * @return the list of candlestick on same lower shadow size
   */
  public List<Candlestick> getListOfCandlestickOnSameLowerShadowSize(Double size) {
    return getCandlestickList().parallelStream().filter(o -> size.equals(o.getLowerShadowSize()))
        .toList();
  }

  /**
   * Gets list of candlestick on same full size.
   *
   * @param size the size
   * @return the list of candlestick on same full size
   */
  public List<Candlestick> getListOfCandlestickOnSameFullSize(Double size) {
    return getCandlestickList().parallelStream().filter(o -> size.equals(o.getFullSize())).toList();
  }

  /**
   * Gets list of candlestick on same division size.
   *
   * @param candlestick the candlestick
   * @return the list of candlestick on same division size
   */
  public List<Candlestick> getListOfCandlestickOnSameDivisionSize(Candlestick candlestick) {
    return getCandlestickList().parallelStream()
        .filter(o -> candlestick.hashCandlestickSize() == o.hashCandlestickSize()).toList();
  }

  /**
   * Gets list of candlestick on same full division size.
   *
   * @param candlestick the candlestick
   * @return the list of candlestick on same full division size
   */
  public List<Candlestick> getListOfCandlestickOnSameFullDivisionSize(Candlestick candlestick) {
    return getCandlestickList().parallelStream()
        .filter(o -> candlestick.hashCandlestickSize(true) == o.hashCandlestickSize()).toList();
  }

  /**
   * Gets list of candlestick on same positions price.
   *
   * @param candlestick the candlestick
   * @return the list of candlestick on same positions price
   */
  public List<Candlestick> getListOfCandlestickOnSamePositionsPrice(Candlestick candlestick) {
    return getCandlestickList().parallelStream().filter(
            o -> candlestick.hashCandlestickPositionsPrice() == o.hashCandlestickPositionsPrice())
        .toList();
  }

  /**
   * Gets list of candlestick on same price.
   *
   * @param candlestick the candlestick
   * @return the list of candlestick on same price
   */
  public List<Candlestick> getListOfCandlestickOnSamePrice(Candlestick candlestick) {
    return getCandlestickList().parallelStream()
        .filter(o -> candlestick.hashCandlestickPrice() == o.hashCandlestickPrice()).toList();
  }

  /**
   * Sort candlestick graphic by date.
   */
  public void sortCandlestickGraphicByDate() {
    getCandlestickList().sort(Comparator.comparing(Candlestick::getLocalDateTime));
  }

  /**
   * Add candlestick.
   *
   * @param candlestick the candlestick
   * @throws InterruptedException the interrupted exception
   */
  public void addCandlestick(Candlestick candlestick)
      throws NullPointerException, InterruptedException {
    if (candlestick == null) {
      throw new NullPointerException("Can't add a new NULL Candlestick to this Chart");
    } else {
      List<Candlestick> candlestickList =
          getCandlestickList().parallelStream()
              .filter(o -> candlestick.getLocalDateTime().equals(o.getLocalDateTime())).toList();
      if (candlestickList.isEmpty()) {
        getCandlestickList().add(candlestick);
        sortCandlestickGraphicByDate();
      } else {
        throw new InterruptedException(
            "Have in the Chart a same Candlestick date at " + candlestick.getLocalDateTime()
                .toString());
      }
    }
  }

  /**
   * Push candlestick.
   *
   * @param candlestick the candlestick
   * @throws InterruptedException the interrupted exception
   */
  public void pushCandlestick(Candlestick candlestick)
      throws NullPointerException, InterruptedException {
    if (candlestick == null) {
      throw new NullPointerException("Can't push a new NULL Candlestick to this Chart");
    } else {
      List<Candlestick> candlestickList =
          getCandlestickList().parallelStream()
              .filter(o -> candlestick.getLocalDateTime().equals(o.getLocalDateTime())).toList();
      if (candlestickList.isEmpty()) {
        getCandlestickList().add(candlestick);
      } else {
        throw new InterruptedException(
            "Have in the Chart a same Candlestick date at " + candlestick.getLocalDateTime()
                .toString());
      }
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
    return Objects.equals(candlestickLinkedList, chart.candlestickLinkedList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(candlestickLinkedList);
  }

  @Override
  public String toString() {
    return "Chart{" +
        "candlestickLinkedList=" + candlestickLinkedList +
        '}';
  }
}
