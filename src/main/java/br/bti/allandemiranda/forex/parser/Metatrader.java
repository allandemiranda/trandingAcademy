package br.bti.allandemiranda.forex.parser;

import br.bti.allandemiranda.forex.candlestick.Candlestick;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.LinkedList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

/**
 * The type Metatrader.
 */
public class Metatrader extends LinkedList<Candlestick> {

  private static final int OPEN_PRICE_POSITION = 1;
  private static final int CLOSE_PRICE_POSITION = 4;
  private static final int LOW_PRICE_POSITION = 3;
  private static final int HIGH_PRICE_POSITION = 2;
  private static final int VOLUME_POSITION = 5;
  private static final int TIME_POSITION = 0;

  /**
   * Instantiates a new Metatrader.
   *
   * @param file the file
   *
   * @throws IOException the io exception
   */
  private Metatrader(File file) throws IOException {
    if (file.exists() && file.canRead()) {
      try (CSVParser csvParser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.DEFAULT)) {
        addAll(csvParser.getRecords().parallelStream().map(
                csvRecord -> new Candlestick(csvRecord.get(OPEN_PRICE_POSITION),
                    csvRecord.get(CLOSE_PRICE_POSITION), csvRecord.get(LOW_PRICE_POSITION),
                    csvRecord.get(HIGH_PRICE_POSITION), csvRecord.get(VOLUME_POSITION),
                    csvRecord.get(TIME_POSITION)))
            .sorted(Comparator.comparing(Candlestick::getLocalDateTime)).toList());
      }
    } else {
      throw new IOException("File " + file.getName() + " not exist or can't read");
    }
  }
}
