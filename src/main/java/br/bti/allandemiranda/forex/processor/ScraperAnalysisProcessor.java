package br.bti.allandemiranda.forex.processor;

import br.bti.allandemiranda.forex.chart.TimeFrame;
import br.bti.allandemiranda.forex.copy.ScaraperComputer;
import br.bti.allandemiranda.forex.copy.SocketMetaTrader;
import com.beust.jcommander.Parameter;
import java.io.File;
import java.io.IOException;

/**
 * The type Scraper analysis processor.
 */
public class ScraperAnalysisProcessor extends Processor {

    @Parameter(names = {"-currencyPair"}, description = "The currency pair", required = true)
    private String currencyPair;

    @Parameter(names = {"-outputFolder"}, description = "Output folder to save the data", required = true)
    private File outputFolder;

    @Parameter(names = {"-driveFile"}, description = "Driver to open a scrap", required = true)
    private File driveFile;

    @Parameter(names = {"-port"}, description = "Port to MetaTrade socket", required = true)
    private int port;

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
        //! Creating a socket
        logger.info("Starting the Socket Meta Trader port {}", port);
        SocketMetaTrader socketMetaTrader = new SocketMetaTrader(port);
        socketMetaTrader.start();

        ScaraperComputer scaraperComputerM15 = new ScaraperComputer(currencyPair, outputFolder, driveFile, TimeFrame.M15, 300);
        scaraperComputerM15.setSocketMetaTrader(socketMetaTrader);
        scaraperComputerM15.start();
    }
}
