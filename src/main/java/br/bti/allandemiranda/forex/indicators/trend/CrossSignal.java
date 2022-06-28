package br.bti.allandemiranda.forex.indicators.trend;

import br.bti.allandemiranda.forex.chart.Line;
import com.beust.jcommander.internal.Lists;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

public class CrossSignal {
//  /**
//   * Crossed list.
//   *
//   * @param line the line
//   *
//   * @return the list
//   */
//  public List<LocalDateTime> crossed(@NotNull Line line) {
//    if (line.isEmpty() || this.isEmpty()) {
//      logger.warn("Trying to compare empty lines");
//      return Lists.newArrayList();
//    }
//    if (line.size() != this.size()) {
//      logger.warn("Trying to compare lines with different size");
//      return Lists.newArrayList();
//    }
//
//    return IntStream.rangeClosed(0, line.size() - 1).boxed().toList().parallelStream().map(i -> {
//      if ((Objects.nonNull(line.get(i).getValue()) && Objects.nonNull(this.get(i).getValue())) && (
//          (line.get(i).getValue().equals(this.get(i).getValue())) || ((i > 0) && (
//              (line.get(i - 1).isHigh(this.get(i - 1)) && line.get(i).isLow(this.get(i))) || (
//                  this.get(i - 1).isHigh(line.get(i - 1)) && this.get(i).isLow(line.get(i))))))) {
//        return line.get(i).getLocalDateTime();
//      } else {
//        return null;
//      }
//    }).filter(Objects::nonNull).toList();
//  }
//
//  /**
//   * Compatible boolean.
//   *
//   * @param line the line
//   *
//   * @return the boolean
//   */
//  public boolean compatible(@NotNull Line line) {
//    if (line.size() == this.size()) {
//      return IntStream.rangeClosed(0, line.size() - 1).boxed().toList().parallelStream()
//          .map(i -> line.get(i).equals(this.get(i))).anyMatch(aBoolean -> !aBoolean);
//    } else {
//      return false;
//    }
//  }
}
