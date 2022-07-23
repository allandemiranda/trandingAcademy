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
 * The type Investing analysis write.
 */
public class InvestingAnalysisWrite {

    private final File fileMovingAverages;
    private final File fileTechnicalIndicators;

    /**
     * Instantiates a new Investing analysis write.
     *
     * @param fileMovingAverages      the file moving averages
     * @param fileTechnicalIndicators the file technical indicators
     */
    public InvestingAnalysisWrite(File fileMovingAverages, File fileTechnicalIndicators) {
        this.fileMovingAverages = fileMovingAverages;
        this.fileTechnicalIndicators = fileTechnicalIndicators;
    }

    /**
     * Sets line technical indicators.
     *
     * @param investingScraper        the investing scraper
     * @param time                    the time
     * @param price                   the price
     * @param lastTechnicalIndicators the last technical indicators
     */
    public void setLineTechnicalIndicators(@NotNull InvestingScraper investingScraper, LocalDateTime time, Double price, String lastTechnicalIndicators) {
        if (Objects.nonNull(investingScraper.getTechnicalIndicators())) {
            String value = String.valueOf(price).replace(".", ",");
            String dateTime = time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).substring(0, 19);
            try (CSVPrinter printer = new CSVPrinter(new FileWriter(fileTechnicalIndicators, true), CSVFormat.TDF)) {
                if (fileTechnicalIndicators.length() <= 0) {
                    printer.printRecord("Time", "Price", "Moving Averages", "Sell", "Neutral", "Buy", "Strong Buy");
                }
                switch (investingScraper.getTechnicalIndicators().concat(" - ").concat(lastTechnicalIndicators)) {
                    case "STRONG SELL - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), value, "", "", "", "");
                    case "STRONG SELL - SELL", "SELL - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), value, value, "", "", "");
                    case "STRONG SELL - NEUTRAL", "NEUTRAL - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), value, "", value, "", "");
                    case "STRONG SELL - BUY", "BUY - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), value, "", "", value, "");
                    case "STRONG SELL - STRONG BUY", "STRONG BUY - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), value, "", "", "", value);

                    case "SELL - SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", value, "", "", "");
                    case "SELL - NEUTRAL", "NEUTRAL - SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", value, value, "", "");
                    case "SELL - BUY", "BUY - SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", value, "", value, "");
                    case "SELL - STRONG BUY", "STRONG BUY - SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", value, "", "", value);

                    case "NEUTRAL - NEUTRAL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", "", value, "", "");
                    case "NEUTRAL - BUY", "BUY - NEUTRAL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", "", value, value, "");
                    case "NEUTRAL - STRONG BUY", "STRONG BUY - NEUTRAL" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", "", value, "", value);

                    case "BUY - BUY" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", "", "", value, "");
                    case "BUY - STRONG BUY", "STRONG BUY - BUY" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", "", "", value, value);

                    case "STRONG BUY - STRONG BUY" ->
                            printer.printRecord(dateTime, value, investingScraper.getTechnicalIndicators(), "", "", "", "", value);

                    default -> throw new IllegalStateException("Get a incorrect Moving Averages value");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Sets line moving averages.
     *
     * @param investingScraper   the investing scraper
     * @param time               the time
     * @param price              the price
     * @param lastMovingAverages the last moving averages
     */
    public void setLineMovingAverages(@NotNull InvestingScraper investingScraper, LocalDateTime time, Double price, String lastMovingAverages) {
        if (Objects.nonNull(investingScraper.getMovingAverages())) {
            String value = String.valueOf(price).replace(".", ",");
            String dateTime = time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).substring(0, 19);
            try (CSVPrinter printer = new CSVPrinter(new FileWriter(fileMovingAverages, true), CSVFormat.TDF)) {
                if (fileMovingAverages.length() <= 0) {
                    printer.printRecord("Time", "Price", "Moving Averages", "Sell", "Neutral", "Buy", "Strong Buy");
                }
                switch (investingScraper.getMovingAverages().concat(" - ").concat(lastMovingAverages)) {
                    case "STRONG SELL - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), value, "", "", "", "");
                    case "STRONG SELL - SELL", "SELL - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), value, value, "", "", "");
                    case "STRONG SELL - NEUTRAL", "NEUTRAL - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), value, "", value, "", "");
                    case "STRONG SELL - BUY", "BUY - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), value, "", "", value, "");
                    case "STRONG SELL - STRONG BUY", "STRONG BUY - STRONG SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), value, "", "", "", value);

                    case "SELL - SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", value, "", "", "");
                    case "SELL - NEUTRAL", "NEUTRAL - SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", value, value, "", "");
                    case "SELL - BUY", "BUY - SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", value, "", value, "");
                    case "SELL - STRONG BUY", "STRONG BUY - SELL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", value, "", "", value);

                    case "NEUTRAL - NEUTRAL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", "", value, "", "");
                    case "NEUTRAL - BUY", "BUY - NEUTRAL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", "", value, value, "");
                    case "NEUTRAL - STRONG BUY", "STRONG BUY - NEUTRAL" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", "", value, "", value);

                    case "BUY - BUY" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", "", "", value, "");
                    case "BUY - STRONG BUY", "STRONG BUY - BUY" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", "", "", value, value);

                    case "STRONG BUY - STRONG BUY" ->
                            printer.printRecord(dateTime, value, investingScraper.getMovingAverages(), "", "", "", "", value);

                    default -> throw new IllegalStateException("Get a incorrect Moving Averages value");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
