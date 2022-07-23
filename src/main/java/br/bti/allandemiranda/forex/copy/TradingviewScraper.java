package br.bti.allandemiranda.forex.copy;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import br.bti.allandemiranda.forex.currency.Pair;
import java.io.File;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * The type Tradingview scraper.
 */
public class TradingviewScraper {

    private static final String FULL_URL = "https://www.tradingview.com/symbols/EURUSD/technicals/";
    private static final String PAIR_DEFALT = "EURUSD";

    private String summary;

    /**
     * Instantiates a new Tradingview scraper.
     *
     * @param driverFile the driver file
     * @param timeFrame  the time frame
     * @param pair       the pair
     */
    public TradingviewScraper(@NotNull File driverFile, TimeFrame timeFrame, @NotNull Pair pair) {
        //! starting the chrome drive
        System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
        WebDriver driver = new ChromeDriver();
        try {
            //! checking the correct url
            if (!pair.getTradingViewName().equals(PAIR_DEFALT)) {
                driver.get(FULL_URL.replace(PAIR_DEFALT, pair.getTradingViewName()));
            } else {
                driver.get(FULL_URL);
            }

            //! selecting the time frame
            switch (timeFrame) {
                case M1 -> driver.findElement(By.id("1m")).click();
                case M5 -> driver.findElement(By.id("5m")).click();
                case M15 -> driver.findElement(By.id("15m")).click();
                case M30 -> driver.findElement(By.id("30m")).click();
                case H1 -> driver.findElement(By.id("1h")).click();
                // case H4 -> driver.findElement(By.id("4h")).click(); //! We don't have this time frame in the investing
                case D1 -> driver.findElement(By.id("1D")).click();
                case W1 -> driver.findElement(By.id("1W")).click();
                case MN -> driver.findElement(By.id("1M")).click();
                default -> throw new IllegalArgumentException("Can't get the time frame from the web aplication " + this.getClass().getName());
            }

            // select the Summary status
            setSummary(checkValueStatus(driver.findElement(By.cssSelector(".summary-RaUvtPLE > .speedometerSignal-RaUvtPLE")).getText()));

        } finally {
            // ending the drive
            driver.quit();
        }
    }

    /**
     * Check value status string.
     *
     * @param value the value
     * @return the string
     */
    @Contract("_ -> param1")
    private @NotNull String checkValueStatus(@NotNull String value) {
        if ("STRONG SELL".equals(value) || "SELL".equals(value) || "NEUTRA".equals(value) || "BUY".equals(value) || "STRONG BUY".equals(value)) {
            return value;
        }
        throw new IllegalStateException("Don't received a correct value");
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    private void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets summary.
     *
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }
}
