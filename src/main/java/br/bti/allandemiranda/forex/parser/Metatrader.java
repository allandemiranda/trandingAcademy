package br.bti.allandemiranda.forex.parser;

import br.bti.allandemiranda.forex.model.utils.Candlestick;
import br.bti.allandemiranda.forex.model.utils.Chart;
import br.bti.allandemiranda.forex.model.utils.CurrencyExchange;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The type Metatrader.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Metatrader {

  private static final int OPEN_PRICE_POSITION = 1;
  private static final int CLOSE_PRICE_POSITION = 4;
  private static final int LOW_PRICE_POSITION = 3;
  private static final int HIGH_PRICE_POSITION = 2;
  private static final int VOLUME_POSITION = 5;
  private static final int TIME_POSITION = 0;

  protected static Logger LOGGER = LogManager.getLogger(Metatrader.class);

  /**
   * Parser chart.
   *
   * @param file             the file
   * @param currencyExchange the currency exchange
   *
   * @return the chart
   *
   * @throws IOException the io exception
   */
  @NotNull
  public static Chart parser(@NotNull File file, @NotNull CurrencyExchange currencyExchange)
  throws IOException {
    Reader reader;
    try {
      reader = Files.newBufferedReader(file.toPath());
    } catch (IOException e) {
      throw new IOException("Can't open the MetaTrade file: " + file.getPath());
    }
    LOGGER.debug("Created a buffer to read the file {}", file.getPath());

    Iterable<CSVRecord> records;
    try {
      records = CSVFormat.DEFAULT.parse(reader);
    } catch (IOException e) {
      reader.close();
      LOGGER.debug("Closing the file {}", file.getPath());
      throw new IOException("Can't passer the MetaTrade file to CSV");
    }
    LOGGER.debug("Readied a buffer of the file {} in CSV format", file.getPath());

    Chart chart = new Chart(currencyExchange);
    try {
      for (CSVRecord record : records) {
        Candlestick candlestick = new Candlestick(
            record.get(OPEN_PRICE_POSITION),  //! openPrice
            record.get(CLOSE_PRICE_POSITION), //! closePrice
            record.get(LOW_PRICE_POSITION),   //! lowPrice
            record.get(HIGH_PRICE_POSITION),  //! highPrice
            record.get(VOLUME_POSITION),      //! volume
            record.get(TIME_POSITION),        //! localDateTime
            currencyExchange.getCurrencyPair());
        chart.getCandlestickList().add(candlestick);
      }
      LOGGER.debug("Created a chart with {} candlestick and {}",
          chart.getCandlestickList().size(), currencyExchange.getCurrencyPair().toString());
    } catch (Exception e) {
      LOGGER.error(e.toString());
      throw new IllegalArgumentException("Can't parser this list of candlestick");
    } finally {
      reader.close();
      LOGGER.debug("Closing the file {}", file.getPath());
    }

    return chart;
  }
}
