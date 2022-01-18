package br.bti.allandemiranda.model;

public class CurrencyExchange {

  private String baseCurrency;
  private String priceIn;

  private Integer spread;
  private Double point;

  private Double swapLong;
  private Double swapShort;
  private Integer swapThreeDays;



  public String getSymbol() {
    return baseCurrency + "/" + priceIn;
  }
}
