package br.bti.allandemiranda.forex.copy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SocketMetaTrader extends Thread{

  private static final Logger logger = LogManager.getLogger(SocketMetaTrader.class);

  private final int port;
  private Double price = null;
  private State state = Thread.State.RUNNABLE;

  public SocketMetaTrader(int port) {
    if (port > 100) {
      this.port = port;
    } else {
      throw new IllegalArgumentException("Can't set a port lest than 100");
    }
  }

  @Override
  public void run() {
    try (ServerSocket server = new ServerSocket(port);
        Socket client = server.accept();
        Scanner entrant = new Scanner(client.getInputStream())) {
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

  private void setPrice(String line) {
    try {
      price = Double.parseDouble(line);
    } catch (Exception e) {
      logger.warn("Can't get Socket price: {}", line);
    }
  }

  public void setStop() {
    this.state = State.TERMINATED;
  }

  @Override
  public State getState() {
    return state;
  }

  public Double getPrice() {
    return price;
  }
}
