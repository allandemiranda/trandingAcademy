//package br.bti.allandemiranda.forex.model;
//
//import java.time.LocalDateTime;
//
///**
// * The type Data amount.
// */
//public class DataAmount {
//
//  private LocalDateTime localDateTime;
//  private Double amount;
//  private Double margin;
//
//  /**
//   * Instantiates a new Data amount.
//   *
//   * @param localDateTime the local date time
//   * @param amount        the amount
//   * @param margin        the margin
//   */
//  public DataAmount(LocalDateTime localDateTime, Double amount, Double margin) {
//    setLocalDateTime(localDateTime);
//    setAmount(amount);
//    setMargin(margin);
//  }
//
//  /**
//   * Gets local date time.
//   *
//   * @return the local date time
//   */
//  public LocalDateTime getLocalDateTime() {
//    return localDateTime;
//  }
//
//  private void setLocalDateTime(LocalDateTime localDateTime) {
//    if (localDateTime == null) {
//      throw new NullPointerException("Can't set a NULL Local Date Time to this Data Amount");
//    } else {
//      this.localDateTime = localDateTime;
//    }
//  }
//
//  /**
//   * Gets amount.
//   *
//   * @return the amount
//   */
//  public Double getAmount() {
//    return amount;
//  }
//
//  private void setAmount(Double amount) {
//    if (localDateTime == null) {
//      throw new NullPointerException("Can't set a NULL amount to this Data Amount");
//    } else {
//      this.amount = amount;
//    }
//  }
//
//  /**
//   * Gets margin.
//   *
//   * @return the margin
//   */
//  public Double getMargin() {
//    return margin;
//  }
//
//  private void setMargin(Double margin) {
//    if (localDateTime == null) {
//      throw new NullPointerException("Can't set a NULL margin to this Data Amount");
//    } else {
//      this.margin = margin;
//    }
//  }
//}
