package br.bti.allandemiranda.forex.copy;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import br.bti.allandemiranda.forex.currency.Pair;
import java.io.File;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * The type Investing scraper.
 */
public class InvestingScraper {

    private static final String FULL_URL = "https://uk.investing.com/currencies/eur-usd-technical";
    private static final String PAIR_DEFALT = "eur-usd";

    private String movingAverages;
    private String technicalIndicators;

    /**
     * Instantiates a new Investing scraper.
     *
     * @param driverFile the driver file
     * @param timeFrame  the time frame
     * @param pair       the pair
     */
    public InvestingScraper(@NotNull File driverFile, TimeFrame timeFrame, @NotNull Pair pair) {
        //! starting the chrome drive
        System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
        WebDriver driver = new ChromeDriver();
        try {
            //! checking the correct url
            if (!pair.getInvestingName().equals(PAIR_DEFALT)) {
                driver.get(FULL_URL.replace(PAIR_DEFALT, pair.getInvestingName()));
            } else {
                driver.get(FULL_URL);
            }

            //! accept cookies information
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();

            //! selecting the time frame
            switch (timeFrame) {
                case M1 -> driver.findElement(By.linkText("1 Min")).click();
                case M5 -> driver.findElement(By.linkText("5 Min")).click();
                case M15 -> driver.findElement(By.linkText("15 Min")).click();
                case M30 -> driver.findElement(By.linkText("30 Min")).click();
                case H1 -> driver.findElement(By.linkText("Hourly")).click();
                case D1 -> driver.findElement(By.linkText("Daily")).click();
                case W1 -> driver.findElement(By.linkText("Weekly")).click();
                case MN -> driver.findElement(By.linkText("Monthly")).click();
                default -> throw new IllegalArgumentException("Can't get the time frame from the web aplication " + this.getClass().getName());
            }

            //! select the Moving Averages status
            setMovingAverages(checkValueStatus(driver.findElement(By.xpath("//div[@id=\'techStudiesInnerWrap\']/div[2]/span[2]")).getText()));

            //! select the Technical Indicators status
            setTechnicalIndicators(checkValueStatus(driver.findElement(By.xpath("//div[@id=\'techStudiesInnerWrap\']/div[3]/span[2]")).getText()));

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
    private String checkValueStatus(String value) {
        if ("STRONG SELL".equals(value) || "SELL".equals(value) || "NEUTRA".equals(value) || "BUY".equals(value) || "STRONG BUY".equals(value)) {
            return value;
        }
        throw new IllegalStateException("Don't received a correct value");
    }

    /**
     * Gets moving averages.
     *
     * @return the moving averages
     */
    public String getMovingAverages() {
        return movingAverages;
    }

    /**
     * Sets moving averages.
     *
     * @param movingAverages the moving averages
     */
    public void setMovingAverages(String movingAverages) {
        this.movingAverages = movingAverages;
    }

    /**
     * Gets technical indicators.
     *
     * @return the technical indicators
     */
    public String getTechnicalIndicators() {
        return technicalIndicators;
    }

    /**
     * Sets technical indicators.
     *
     * @param technicalIndicators the technical indicators
     */
    public void setTechnicalIndicators(String technicalIndicators) {
        this.technicalIndicators = technicalIndicators;
    }
}
