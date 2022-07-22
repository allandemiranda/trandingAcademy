package br.bti.allandemiranda.forex.copy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.jetbrains.annotations.NotNull;

/**
 * The type Trading view analysis write.
 */
public class TradingViewAnalysisWrite {

    private final File file;

    /**
     * Instantiates a new Trading view analysis write.
     *
     * @param file the file
     */
    public TradingViewAnalysisWrite(File file) {
        this.file = file;
    }

    /**
     * Sets line.
     *
     * @param tradingviewScraper the tradingview scraper
     * @param time               the time
     * @param price              the price
     * @param lastStatus         the last status
     */
    public void setLine(@NotNull TradingviewScraper tradingviewScraper, LocalDateTime time, Double price, String lastStatus) {
        if (Objects.nonNull(tradingviewScraper.getSummary())) {
            String value = String.valueOf(price).replace(".", ",");
            String dateTime = time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).substring(0,19);

            if (Objects.isNull(lastStatus)) {
                try (CSVPrinter printer = new CSVPrinter(new FileWriter(file), CSVFormat.TDF)) {
                    printer.printRecord("Time", "Price", "Summary", "Sell", "Neutral", "Buy", "Strong Buy");
                    switch (tradingviewScraper.getSummary()) {
                        case "STRONG SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), value, "", "", "", "");
                        case "SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", value, "", "", "");
                        case "NEUTRAL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", "", value, "", "");
                        case "BUY" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", "", "", value, "");
                        case "STRONG BUY" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", "", "", "", value);
                        default -> throw new IllegalStateException("Get a incorrect initial Oscillators value");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try (CSVPrinter printer = new CSVPrinter(new FileWriter(file, true), CSVFormat.TDF)) {
                    switch (tradingviewScraper.getSummary().concat(" - ").concat(lastStatus)) {
                        case "STRONG SELL - STRONG SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), value, "", "", "", "");
                        case "STRONG SELL - SELL", "SELL - STRONG SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), value, value, "", "", "");
                        case "STRONG SELL - NEUTRAL", "NEUTRAL - STRONG SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), value, "", value, "", "");
                        case "STRONG SELL - BUY", "BUY - STRONG SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), value, "", "", value, "");
                        case "STRONG SELL - STRONG BUY", "STRONG BUY - STRONG SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), value, "", "", "", value);

                        case "SELL - SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", value, "", "", "");
                        case "SELL - NEUTRAL", "NEUTRAL - SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", value, value, "", "");
                        case "SELL - BUY", "BUY - SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", value, "", value, "");
                        case "SELL - STRONG BUY", "STRONG BUY - SELL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", value, "", "", value);

                        case "NEUTRAL - NEUTRAL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", "", value, "", "");
                        case "NEUTRAL - BUY", "BUY - NEUTRAL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", "", value, value, "");
                        case "NEUTRAL - STRONG BUY", "STRONG BUY - NEUTRAL" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", "", value, "", value);

                        case "BUY - BUY" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", "", "", value, "");
                        case "BUY - STRONG BUY", "STRONG BUY - BUY" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", "", "", value, value);

                        case "STRONG BUY - STRONG BUY" ->
                                printer.printRecord(dateTime, value, tradingviewScraper.getSummary(), "", "", "", "", value);

                        default -> throw new IllegalStateException("Get a incorrect Oscillators value");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
