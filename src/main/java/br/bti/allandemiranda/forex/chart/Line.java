package br.bti.allandemiranda.forex.chart;

import com.beust.jcommander.internal.Lists;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * The type Line.
 *
 * <p> Note: Suppose that all lines have the same size, and the same start time, with the same X value
 * to each time step </p>
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
public class Line extends LinkedList<Point> {

  protected static Logger logger = LogManager.getLogger(Line.class);

  /**
   * Instantiates a new Line.
   */
  public Line() {
  }

  /**
   * Instantiates a new Line.
   *
   * @param c the c
   */
  public Line(@NotNull Collection<? extends Point> c) {
    super(c);
  }
}
