package br.bti.allandemiranda.forex.parser;


import br.bti.allandemiranda.forex.candlestick.Candlestick;
import br.bti.allandemiranda.forex.chart.Chart;
import br.bti.allandemiranda.forex.currency.Exchange;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.stream.StreamSupport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The type Metatrader.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.1
 */
public class Metatrader {

  private static final int OPEN_PRICE_POSITION = 1;
  private static final int CLOSE_PRICE_POSITION = 4;
  private static final int LOW_PRICE_POSITION = 3;
  private static final int HIGH_PRICE_POSITION = 2;
  private static final int VOLUME_POSITION = 5;
  private static final int TIME_POSITION = 0;

  protected static Logger logger = LogManager.getLogger(Metatrader.class);

  /**
   * Instantiates a new Metatrader.
   */
  private Metatrader() {
    throw new IllegalStateException(Metatrader.class.toString());
  }

  /**
   * Parser chart.
   *
   * @param file             the file
   * @param exchange the currency exchange
   *
   * @return the chart
   *
   * @throws IOException the io exception
   */
  @NotNull
  public static Chart parser(@NotNull File file, @NotNull Exchange exchange)
  throws IOException {
    if (file.isFile() && file.canRead()) {
      logger.debug("Parsing the file {} to a Chart {}", file.getName(), exchange);
      Reader reader;
      try {
        reader = Files.newBufferedReader(file.toPath());
      } catch (IOException e) {
        throw new IOException("Can't open the MetaTrade file: " + file.getPath());
      }
      logger.debug("Created a buffer to read the file {}", file.getPath());

      Iterable<CSVRecord> records;
      try {
        records = CSVFormat.DEFAULT.parse(reader);
      } catch (IOException e) {
        reader.close();
        logger.debug("Closing the file {}", file.getPath());
        throw new IOException("Can't passer the MetaTrade file to CSV");
      }
      logger.debug("Readied a buffer of the file {} in CSV format", file.getPath());

      Chart chart = new Chart(exchange);
      try {
        chart.getCandlestickList().addAll(StreamSupport.stream(records.spliterator(), true).parallel()
            .map(string -> new Candlestick(string.get(OPEN_PRICE_POSITION),  //! openPrice
                string.get(CLOSE_PRICE_POSITION), //! closePrice
                string.get(LOW_PRICE_POSITION),   //! lowPrice
                string.get(HIGH_PRICE_POSITION),  //! highPrice
                string.get(VOLUME_POSITION),      //! volume
                string.get(TIME_POSITION),        //! localDateTime
                exchange.getCurrencyPair()))
            .sorted(Comparator.comparing(Candlestick::getLocalDateTime)).toList());
        logger.debug("Created a chart with {} candlestick and {}", chart.getCandlestickList().size(),
            exchange.getCurrencyPair());

      } catch (Exception e) {
        logger.error(e.toString());
        throw new IllegalArgumentException("Can't parser this list of candlestick");
      } finally {
        reader.close();
        logger.debug("Closing the file {}", file.getPath());
        if (chart.getCandlestickList().isEmpty()) {
          logger.warn("Don't find any candlestick from this file {}", file.getName());
        }
      }

      return chart;
    } else {
      throw new IllegalArgumentException("We can't read a file " + file.getName());
    }
  }
}
