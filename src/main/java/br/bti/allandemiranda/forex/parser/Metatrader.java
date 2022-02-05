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

/**
 * The type Metatrader.
 *
 * @author Allan de Miranda Silva
 * @version 0.2
 */
public class Metatrader {

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
  public static Chart parser(File file, CurrencyExchange currencyExchange) throws IOException {
    Reader reader;
    try {
      reader = Files.newBufferedReader(file.toPath());
    } catch (IOException e) {
      throw new IOException("Can't open the MetaTrade file: " + file.getAbsolutePath());
    }
    Iterable<CSVRecord> records;
    try {
      records = CSVFormat.DEFAULT.parse(reader);
    } catch (IOException e) {
      throw new IOException("Can't passer the MetaTrade file to CSV");
    }

    Chart chart = new Chart(currencyExchange);
    for (CSVRecord record : records) {
      Candlestick candlestick = new Candlestick(record.get(1), record.get(4), record.get(3), record.get(2), record.get(5), record.get(0),
          currencyExchange.getCurrencyPair());
      chart.getCandlestickList().add(candlestick);
    }
    return chart;
  }
}
