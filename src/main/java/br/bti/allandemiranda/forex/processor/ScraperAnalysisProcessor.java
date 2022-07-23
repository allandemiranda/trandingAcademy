package br.bti.allandemiranda.forex.processor;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import br.bti.allandemiranda.forex.copy.*;
import br.bti.allandemiranda.forex.currency.Pair;
import com.beust.jcommander.Parameter;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The type Scraper analysis processor.
 */
public class ScraperAnalysisProcessor extends Processor {

    private static final String TIME_FORMATTER = "yyyyMMdd'-'HH'h'mm";

    @Parameter(names = {"-currencyPair"}, description = "The currency pair", required = true)
    private String currencyPair;

    @Parameter(names = {"-timeFrame"}, description = "The Chart time frame", required = true)
    private String time;

    @Parameter(names = {"-outputFolder"}, description = "Output folder to save the data", required = true)
    private File outputFolder;

    @Parameter(names = {"-driveFile"}, description = "Driver to open a scrap", required = true)
    private File driveFile;

    @Parameter(names = {"-port"}, description = "Port to MetaTrade socket", required = true)
    private int port;

    @Parameter(names = {"-sleepTimeSeconds"}, description = "In seconds, the time to sleep between each scrape request")
    private int sleepTime = 30;

    @Parameter(names = {"-investing"}, description = "Select the execution to Investing scraper platform")
    private boolean investingProcess;

    @Parameter(names = {"-tradingView"}, description = "Select the execution to TradingView scraper platform")
    private boolean tradingViewProcess;

    /**
     * Instantiates a new Trading view analysis processor.
     *
     * @param args the args
     * @throws IOException the io exception
     */
    public ScraperAnalysisProcessor(String... args) throws IOException {
        super(args);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        new ScraperAnalysisProcessor(args).run();
    }

    @Override
    public void run() {
        if (investingProcess || tradingViewProcess) {
            //! Creating the checks and variables
            Pair pair = new Pair(currencyPair);
            TimeFrame timeFrame = TimeFrame.valueOf(time);
            if (!outputFolder.exists() || !outputFolder.isDirectory()) {
                throw new IllegalArgumentException("Set a valid output folder");
            }
            if (!driveFile.isFile() || !driveFile.canRead()) {
                throw new IllegalArgumentException("Set a valid drive file");
            }
            //! Out file to the TradingView
            File outFileTradingView = new File(outputFolder, "TradingViewAnalysis_".concat(time).concat("_")
                    .concat(pair.getTradingViewName()).concat("_")
                    .concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMATTER)))
                    .concat(".csv"));
            //! Out file to the InvestingMA
            File outFileInvestingMA = new File(outputFolder, "InvestingMAAnalysis_".concat(time).concat("_")
                    .concat(pair.getTradingViewName()).concat("_")
                    .concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMATTER)))
                    .concat(".csv"));
            //! Out file to the InvestingMA
            File outFileInvestingTI = new File(outputFolder, "InvestingTIAnalysis_".concat(time).concat("_")
                    .concat(pair.getTradingViewName()).concat("_")
                    .concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMATTER)))
                    .concat(".csv"));
            if (sleepTime > 1) {
                sleepTime = sleepTime * 1000; //! Convert the time in seconds to nanoseconds
            } else {
                throw new IllegalArgumentException("Set a valid second time");
            }

            //! Creating a socket
            logger.info("Starting the Socket Meta Trader port {}", port);
            SocketMetaTrader socketMetaTrader = new SocketMetaTrader(port);
            socketMetaTrader.start();

            //! Creating a scraper
            logger.info("Starting the Tradingview Scraper loop");
            try {
                String lastSummary;
                String lastMovingAverages;
                String lastTechnicalIndicators;
                while (socketMetaTrader.getState().equals(State.RUNNABLE)) {
                    Double price = socketMetaTrader.getPrice();
                    if (Objects.nonNull(price)) {
                        try {
                            try {
                                if (tradingViewProcess) {
                                    TradingviewScraper tradingviewScraper = new TradingviewScraper(driveFile, timeFrame, pair);
                                    lastSummary = tradingviewScraper.getSummary();
                                    try {
                                        new TradingViewAnalysisWrite(outFileTradingView).setLine(tradingviewScraper, LocalDateTime.now(), price, lastSummary);
                                    } catch (Exception e) {
                                        logger.warn("Can't print the last value in the file {}", outFileTradingView.getName());
                                    }
                                }
                            } catch (Exception e) {
                                logger.warn("Problem to get the value in the TradingView, {}", e.getMessage());
                            }
                            try {
                                if (investingProcess) {
                                    InvestingScraper investingScraper = new InvestingScraper(driveFile, timeFrame, pair);
                                    lastMovingAverages = investingScraper.getMovingAverages();
                                    lastTechnicalIndicators = investingScraper.getTechnicalIndicators();
                                    InvestingAnalysisWrite investingAnalysisWrite = new InvestingAnalysisWrite(outFileInvestingMA, outFileInvestingTI);
                                    try {
                                        investingAnalysisWrite.setLineMovingAverages(investingScraper, LocalDateTime.now(), price, lastMovingAverages);
                                    } catch (Exception e) {
                                        logger.warn("Can't print the last value in the file {}", outFileInvestingMA.getName());
                                    }
                                    try {
                                        investingAnalysisWrite.setLineTechnicalIndicators(investingScraper, LocalDateTime.now(), price, lastTechnicalIndicators);
                                    } catch (Exception e) {
                                        logger.warn("Can't print the last value in the file {}", outFileInvestingTI.getName());
                                    }
                                }
                            } catch (Exception e) {
                                logger.warn("Problem to get the value in the Investing, {}", e.getMessage());
                            }
                        } finally {
                            Thread.sleep(sleepTime);
                        }
                    } else {
                        logger.warn("Please, start the Meta Trader Server");
                        Thread.sleep(3000);
                    }
                }
            } catch (Exception e) {
                logger.info("Stopping the scraper, {}", e.getMessage());
            } finally {
                socketMetaTrader.setStop();
            }
        } else {
            logger.info("Ending because we don't have any the scraper selected");
        }
    }
}
