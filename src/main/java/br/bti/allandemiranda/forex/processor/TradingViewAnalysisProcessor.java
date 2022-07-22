package br.bti.allandemiranda.forex.processor;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import br.bti.allandemiranda.forex.copy.SocketMetaTrader;
import br.bti.allandemiranda.forex.copy.TradingViewAnalysisWrite;
import br.bti.allandemiranda.forex.copy.TradingviewScraper;
import br.bti.allandemiranda.forex.currency.Pair;
import com.beust.jcommander.Parameter;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The type Trading view analysis processor.
 */
public class TradingViewAnalysisProcessor extends Processor {

    @Parameter(names = {"-currencyPair"}, description = "The currency pair", required = true)
    String currencyPair;

    @Parameter(names = {"-timeFrame"}, description = "The Chart time frame", required = true)
    String time;

    @Parameter(names = {"-outputFolder"}, description = "Output folder to save the data", required = true)
    File outputFolder;

    @Parameter(names = {"-driveFile"}, description = "Driver to open a scrap", required = true)
    File driveFile;

    @Parameter(names = {"-port"}, description = "Port to MetaTrade socket", required = true)
    int port;

    @Parameter(names = {"-sleepTimeSeconds"}, description = "In seconds, the time to sleep between each scrape request")
    int sleepTime = 30;

    /**
     * Instantiates a new Trading view analysis processor.
     *
     * @param args the args
     * @throws IOException the io exception
     */
    public TradingViewAnalysisProcessor(String... args) throws IOException {
        super(args);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        new TradingViewAnalysisProcessor(args).run();
    }

    @Override
    public void run() {
        //! Creating the checks and variables
        Pair pair = new Pair(currencyPair);
        TimeFrame timeFrame = TimeFrame.valueOf(time);
        if (!outputFolder.exists() || !outputFolder.isDirectory()) {
            throw new IllegalArgumentException("Set a valid output folder");
        }
        if (!driveFile.isFile() || !driveFile.canRead()) {
            throw new IllegalArgumentException("Set a valid drive file");
        }
        File outFile = new File(outputFolder, "TradingViewAnalysis_".concat(time).concat("_")
                .concat(pair.getNameTogeder()).concat("_")
                .concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd'-'HH'h'mm")))
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
            String lastSummary = null;
            while (socketMetaTrader.getState().equals(State.RUNNABLE)) {
                Double price = socketMetaTrader.getPrice();
                if (Objects.nonNull(price)) {
                    try {
                        TradingviewScraper tradingviewScraper = new TradingviewScraper(driveFile, timeFrame, pair);
                        new TradingViewAnalysisWrite(outFile).setLine(tradingviewScraper, LocalDateTime.now(), price, lastSummary);
                        lastSummary = tradingviewScraper.getSummary();
                    } catch (Exception e) {
                        logger.warn("Don't get a value in this time, because {}", e.getMessage());
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
    }
}
