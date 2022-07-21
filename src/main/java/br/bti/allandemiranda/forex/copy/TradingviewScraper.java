package br.bti.allandemiranda.forex.copy;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import br.bti.allandemiranda.forex.currency.Pair;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TradingviewScraper {

  private static final String FULL_URL = "https://www.tradingview.com/symbols/EURUSD/technicals/";
  private static final String PAIR_DEFALT = "EURUSD";

  private String summary = "";
  private String summarySell = "";
  private String summaryNeutral = "";
  private String summaryBuy = "";
  private String oscillators = "";
  private String oscillatorsSell = "";
  private String oscillatorsNeutral = "";
  private String oscillatorsBuy = "";
  private String movingAveragesSell = "";
  private String movingAveragesNeutral = "";
  private String movingAveragesBuy = "";

  private String rsi = "";
  private String stochastic = "";
  private String commodityChannel = "";
  private String averageDirectional = "";
  private String awesomeOscillator = "";
  private String momentum = "";
  private String macd = "";
  private String stochasticRSI = "";
  private String williamsPercent = "";
  private String bullBearPower = "";
  private String ultimateOscillator = "";

  private String ema10 = "";
  private String sma10 = "";
  private String ema20 = "";
  private String sma20 = "";
  private String ema30 = "";
  private String sma30 = "";
  private String ema50 = "";
  private String sma50 = "";
  private String ema100 = "";
  private String sma100 = "";
  private String ema200 = "";
  private String sma200 = "";
  private String ichimoku = "";
  private String weightedMovingAverage = "";
  private String hullMovingAverage = "";

  public TradingviewScraper(File driverFile, TimeFrame timeFrame, Pair pair) {
    System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
    WebDriver driver = new ChromeDriver();

    if (!pair.getNameTogeder().equals(PAIR_DEFALT)) {
      driver.get(FULL_URL.replace(PAIR_DEFALT, pair.getNameTogeder()));
    } else {
      driver.get(FULL_URL);
    }

    //        driver.manage().window().setSize(new Dimension(1050, 718));
    //        driver.findElement(By.cssSelector(".acceptAll-W4Y0hWcd")).click();
    switch (timeFrame) {
      case M1 -> driver.findElement(By.id("1m")).click();
      case M5 -> driver.findElement(By.id("5m")).click();
      case M15 -> driver.findElement(By.id("15m")).click();
      case M30 -> driver.findElement(By.id("30m")).click();
      case H1 -> driver.findElement(By.id("1h")).click();
      case H4 -> driver.findElement(By.id("4h")).click();
      case D1 -> driver.findElement(By.id("1D")).click();
      case W1 -> driver.findElement(By.id("1W")).click();
      case MN -> driver.findElement(By.id("1M")).click();
      default -> throw new IllegalArgumentException("Can't get the time frame from the web aplication");
    }
    String summaryTxt = driver.findElement(
        By.cssSelector(".summary-RaUvtPLE > .speedometerSignal-RaUvtPLE")).getText();

    setSummary(checkValueGauges(summaryTxt));

    driver.quit();
  }

  private String checkValueGauges(String value) {
    if (value.equals("STRONG SELL") || value.equals("SELL") || value.equals("NEUTRA") || value.equals(
        "BUY") || value.equals("STRONG BUY")) {
      return value;
    }
    throw new IllegalStateException("Don't received a correct value");
  }

  private void setSummary(String summary) {
    this.summary = summary;
  }

  private void setSummarySell(String summarySell) {
    this.summarySell = summarySell;
  }

  private void setSummaryNeutral(String summaryNeutral) {
    this.summaryNeutral = summaryNeutral;
  }

  private void setSummaryBuy(String summaryBuy) {
    this.summaryBuy = summaryBuy;
  }

  private void setOscillators(String oscillators) {
    this.oscillators = oscillators;
  }

  private void setOscillatorsSell(String oscillatorsSell) {
    this.oscillatorsSell = oscillatorsSell;
  }

  private void setOscillatorsNeutral(String oscillatorsNeutral) {
    this.oscillatorsNeutral = oscillatorsNeutral;
  }

  private void setOscillatorsBuy(String oscillatorsBuy) {
    this.oscillatorsBuy = oscillatorsBuy;
  }

  private void setMovingAveragesSell(String movingAveragesSell) {
    this.movingAveragesSell = movingAveragesSell;
  }

  private void setMovingAveragesNeutral(String movingAveragesNeutral) {
    this.movingAveragesNeutral = movingAveragesNeutral;
  }

  private void setMovingAveragesBuy(String movingAveragesBuy) {
    this.movingAveragesBuy = movingAveragesBuy;
  }

  private void setRsi(String rsi) {
    this.rsi = rsi;
  }

  private void setStochastic(String stochastic) {
    this.stochastic = stochastic;
  }

  private void setCommodityChannel(String commodityChannel) {
    this.commodityChannel = commodityChannel;
  }

  private void setAverageDirectional(String averageDirectional) {
    this.averageDirectional = averageDirectional;
  }

  private void setAwesomeOscillator(String awesomeOscillator) {
    this.awesomeOscillator = awesomeOscillator;
  }

  private void setMomentum(String momentum) {
    this.momentum = momentum;
  }

  private void setMacd(String macd) {
    this.macd = macd;
  }

  private void setStochasticRSI(String stochasticRSI) {
    this.stochasticRSI = stochasticRSI;
  }

  private void setWilliamsPercent(String williamsPercent) {
    this.williamsPercent = williamsPercent;
  }

  private void setBullBearPower(String bullBearPower) {
    this.bullBearPower = bullBearPower;
  }

  private void setUltimateOscillator(String ultimateOscillator) {
    this.ultimateOscillator = ultimateOscillator;
  }

  private void setEma10(String ema10) {
    this.ema10 = ema10;
  }

  private void setSma10(String sma10) {
    this.sma10 = sma10;
  }

  private void setEma20(String ema20) {
    this.ema20 = ema20;
  }

  private void setSma20(String sma20) {
    this.sma20 = sma20;
  }

  private void setEma30(String ema30) {
    this.ema30 = ema30;
  }

  private void setSma30(String sma30) {
    this.sma30 = sma30;
  }

  private void setEma50(String ema50) {
    this.ema50 = ema50;
  }

  private void setSma50(String sma50) {
    this.sma50 = sma50;
  }

  private void setEma100(String ema100) {
    this.ema100 = ema100;
  }

  private void setSma100(String sma100) {
    this.sma100 = sma100;
  }

  private void setEma200(String ema200) {
    this.ema200 = ema200;
  }

  private void setSma200(String sma200) {
    this.sma200 = sma200;
  }

  private void setIchimoku(String ichimoku) {
    this.ichimoku = ichimoku;
  }

  private void setWeightedMovingAverage(String weightedMovingAverage) {
    this.weightedMovingAverage = weightedMovingAverage;
  }

  private void setHullMovingAverage(String hullMovingAverage) {
    this.hullMovingAverage = hullMovingAverage;
  }

  //  public TimeFrame getTimeFrame() {  //    
  //    return timeFrame;
  //  }

  //  public Pair getPair() {  //    
  //    return pair;
  //  }

  //  public LocalDateTime getLocalDateTimeBuilt() {  //    
  //    return localDateTimeBuilt;
  //  }

  public String getSummary() {
    return summary;
  }

  public String getSummarySell() {
    return summarySell;
  }

  public String getSummaryNeutral() {
    return summaryNeutral;
  }

  public String getSummaryBuy() {
    return summaryBuy;
  }

  public String getOscillators() {
    return oscillators;
  }

  public String getOscillatorsSell() {
    return oscillatorsSell;
  }

  public String getOscillatorsNeutral() {
    return oscillatorsNeutral;
  }

  public String getOscillatorsBuy() {
    return oscillatorsBuy;
  }

  public String getMovingAveragesSell() {
    return movingAveragesSell;
  }

  public String getMovingAveragesNeutral() {
    return movingAveragesNeutral;
  }

  public String getMovingAveragesBuy() {
    return movingAveragesBuy;
  }

  public String getRsi() {
    return rsi;
  }

  public String getStochastic() {
    return stochastic;
  }

  public String getCommodityChannel() {
    return commodityChannel;
  }

  public String getAverageDirectional() {
    return averageDirectional;
  }

  public String getAwesomeOscillator() {
    return awesomeOscillator;
  }

  public String getMomentum() {
    return momentum;
  }

  public String getMacd() {
    return macd;
  }

  public String getStochasticRSI() {
    return stochasticRSI;
  }

  public String getWilliamsPercent() {
    return williamsPercent;
  }

  public String getBullBearPower() {
    return bullBearPower;
  }

  public String getUltimateOscillator() {
    return ultimateOscillator;
  }

  public String getEma10() {
    return ema10;
  }

  public String getSma10() {
    return sma10;
  }

  public String getEma20() {
    return ema20;
  }

  public String getSma20() {
    return sma20;
  }

  public String getEma30() {
    return ema30;
  }

  public String getSma30() {
    return sma30;
  }

  public String getEma50() {
    return ema50;
  }

  public String getSma50() {
    return sma50;
  }

  public String getEma100() {
    return ema100;
  }

  public String getSma100() {
    return sma100;
  }

  public String getEma200() {
    return ema200;
  }

  public String getSma200() {
    return sma200;
  }

  public String getIchimoku() {
    return ichimoku;
  }

  public String getWeightedMovingAverage() {
    return weightedMovingAverage;
  }

  public String getHullMovingAverage() {
    return hullMovingAverage;
  }
}
