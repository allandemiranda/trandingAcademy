package br.bti.allandemiranda.forex.copy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Socket meta trader.
 */
public class SocketMetaTrader extends Thread {

    private static final Logger logger = LogManager.getLogger(SocketMetaTrader.class);

    private final int port;
    private Double price = null;
    private State state = Thread.State.RUNNABLE;

    /**
     * Instantiates a new Socket meta trader.
     *
     * @param port the port
     */
    public SocketMetaTrader(int port) {
        if (port > 100) {
            this.port = port;
        } else {
            throw new IllegalArgumentException("Can't set a port lest than 100");
        }
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(port); Socket client = server.accept(); Scanner entrant = new Scanner(client.getInputStream())) {
            logger.info("Socket started at port {}", port);
            logger.info("Client connected IP {}", client.getInetAddress().getHostAddress());
            while (state.equals(Thread.State.RUNNABLE) && entrant.hasNextLine()) {
                setPrice(entrant.nextLine());
            }
            logger.warn("Socket stopped");
        } catch (IOException e) {
            logger.warn("Socket stopped, {}", e.getMessage());
        } finally {
            state = State.TERMINATED;
        }
    }

    /**
     * Sets price.
     *
     * @param line the line
     */
    private void setPrice(String line) {
        try {
            price = Double.parseDouble(line);
        } catch (Exception e) {
            logger.warn("Can't get Socket price: {}", line);
        }
    }

    /**
     * Sets stop.
     */
    public void setStop() {
        this.state = State.TERMINATED;
    }

    @Override
    public State getState() {
        return state;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    public int getPort() {
        return port;
    }
}
