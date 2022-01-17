package br.bti.allandemiranda.model;

import java.util.Objects;

/**
 * The type Candlestick.
 */
public class Candlestick {

  private static final String LOW = "LOW";
  private static final String HIGH = "HIGH";
  private static final String NEUTRAL = "NEUTRAL";
  private static final double NEUTRAL_SIZE = 0.0;
  private final Double openPrice;
  private final Double closePrice;
  private final Double lowPrice;
  private final Double highPrice;

  /**
   * Instantiates a new Candlestick.
   *
   * @param openPrice  the open price
   * @param closePrice the close price
   * @param lowPrice   the low price
   * @param highPrice  the high price
   */
  public Candlestick(double openPrice, double closePrice, double lowPrice, double highPrice) {
    this.openPrice = openPrice;
    this.closePrice = closePrice;
    this.lowPrice = lowPrice;
    this.highPrice = highPrice;
  }

  /**
   * Gets open price.
   *
   * @return the open price
   */
  public Double getOpenPrice() {
    return openPrice;
  }

  /**
   * Gets close price.
   *
   * @return the close price
   */
  public Double getClosePrice() {
    return closePrice;
  }

  /**
   * Gets low price.
   *
   * @return the low price
   */
  public Double getLowPrice() {
    return lowPrice;
  }

  /**
   * Gets high price.
   *
   * @return the high price
   */
  public Double getHighPrice() {
    return highPrice;
  }

  /**
   * Gets price action.
   *
   * @return the price action
   */
  public String getPriceAction() {
    if (getOpenPrice() > getClosePrice()) {
      return LOW;
    } else {
      if (getOpenPrice() < getClosePrice()) {
        return HIGH;
      } else {
        return NEUTRAL;
      }
    }
  }

  /**
   * Gets body size.
   *
   * @return the body size
   */
  public Double getBodySize() {
    return switch (getPriceAction()) {
      case NEUTRAL -> 0.0;
      case HIGH -> getClosePrice() - getOpenPrice();
      case LOW -> getOpenPrice() - getClosePrice();
      default -> throw new NullPointerException("Get Price Action need be HIGH, LOW or NEUTRAL");
    };
  }

  /**
   * Is body size neutral.
   *
   * @return the boolean
   */
  public Boolean isBodySizeNeutral() {
    return NEUTRAL.equals(getPriceAction());
  }

  /**
   * Gets upper shadow size.
   *
   * @return the upper shadow size
   */
  public Double getUpperShadowSize() {
    return switch (getPriceAction()) {
      case HIGH, NEUTRAL -> getHighPrice() - getClosePrice();
      case LOW -> getHighPrice() - getOpenPrice();
      default -> throw new NullPointerException("Get Price Action need be HIGH, LOW or NEUTRAL");
    };
  }

  /**
   * Is upper shadow size neutral.
   *
   * @return the boolean
   */
  public Boolean isUpperShadowSizeNeutral() {
    return getUpperShadowSize().equals(NEUTRAL_SIZE);
  }

  /**
   * Gets lower shadow size.
   *
   * @return the lower shadow size
   */
  public Double getLowerShadowSize() {
    return switch (getPriceAction()) {
      case HIGH, NEUTRAL -> getOpenPrice() - getLowPrice();
      case LOW -> getClosePrice() - getOpenPrice();
      default -> throw new NullPointerException("Get Price Action need be HIGH, LOW or NEUTRAL");
    };
  }

  /**
   * Is lower shadow size neutral.
   *
   * @return the boolean
   */
  public Boolean isLowerShadowSizeNeutral() {
    return getLowerShadowSize().equals(NEUTRAL_SIZE);
  }

  /**
   * Gets full size.
   *
   * @return the full size
   */
  public Double getFullSize() {
    return getUpperShadowSize() + getBodySize() + getLowerShadowSize();
  }

  /**
   * Is full size neutral.
   *
   * @return the boolean
   */
  public Boolean isFullSizeNeutral() {
    return getFullSize().equals(NEUTRAL_SIZE);
  }

  /**
   * Hash candlestick size hash.
   *
   * @return the hash
   */
  public int hashCandlestickSize() {
    return hashCandlestickSize(false);
  }

  /**
   * Hash candlestick size hash.
   *
   * @param includeFullSize the include full size
   * @return the hash
   */
  public int hashCandlestickSize(Boolean includeFullSize) {
    if (includeFullSize) {
      return Objects.hash(getUpperShadowSize(), getBodySize(), getLowerShadowSize(), getFullSize());
    } else {
      return Objects.hash(getUpperShadowSize(), getBodySize(), getLowerShadowSize());
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
    Candlestick that = (Candlestick) o;
    return openPrice.equals(that.openPrice) && closePrice.equals(that.closePrice)
        && lowPrice.equals(that.lowPrice) && highPrice.equals(that.highPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(openPrice, closePrice, lowPrice, highPrice);
  }

  @Override
  public String toString() {
    return "Candlestick{" +
        "openPrice=" + openPrice +
        ", closePrice=" + closePrice +
        ", lowPrice=" + lowPrice +
        ", highPrice=" + highPrice +
        '}';
  }
}
