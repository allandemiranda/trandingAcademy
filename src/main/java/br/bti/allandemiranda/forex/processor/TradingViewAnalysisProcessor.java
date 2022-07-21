package br.bti.allandemiranda.forex.processor;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import br.bti.allandemiranda.forex.copy.SocketMetaTrader;
import br.bti.allandemiranda.forex.copy.TradingviewScraper;
import br.bti.allandemiranda.forex.currency.Pair;
import com.beust.jcommander.Parameter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Thread.State;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TradingViewAnalysisProcessor extends Processor {

  private static final String TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

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

  @Parameter(names = {
      "-sleepTimeSeconds"}, description = "In seconds, the time to sleep between each scrape request")
  int sleepTime = 30;

  public TradingViewAnalysisProcessor(String... args) throws IOException {
    super(args);
  }

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
    File outFile = new File(outputFolder,
        "TradingViewAnalysis_".concat(time).concat("_").concat(pair.getNameTogeder()).concat(".csv"));
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
      while (socketMetaTrader.getState().equals(State.RUNNABLE)) {
        Double price = socketMetaTrader.getPrice();
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        if (Objects.nonNull(price)) {
          TradingviewScraper tradingviewScraper = new TradingviewScraper(driveFile, timeFrame, pair);
          String valor = tradingviewScraper.getSummary();
          try (FileWriter fw = new FileWriter(outFile, true)) {
            fw.append(
                localDateTime.concat(";").concat(String.valueOf(price).replace(".", ",")).concat(";")
                    .concat(valor).concat("\n"));
          } catch (IOException e) {
            logger.warn("Can't write the line in the file {}", outFile.getName());
          } finally {
            logger.info("TIME: {} PRICE: {} STATUS: {}", localDateTime, price, valor);
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
