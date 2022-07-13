package br.bti.allandemiranda.forex.indicators.trend;

import br.bti.allandemiranda.forex.chart.Line;
import br.bti.allandemiranda.forex.indicators.Signal;
import br.bti.allandemiranda.forex.indicators.Trend;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

/**
 * The type Cross signal.+
 */
public class CrossSignal {

  /**
   * Gets trend sinal.
   *
   * @param line1 the line 1
   * @param line2 the line 2
   *
   * @return the trend sinal
   */
  public static @NotNull List<Signal> getTrendSinal(Line line1, Line line2) {
    List<LocalDateTime> localDateTimes = line1.crossed(line2);
    if (localDateTimes.size() > 1) {
      int positionInitial;
      boolean flag = true;
      for (positionInitial = 0; positionInitial < line1.size(); ++positionInitial) {
        if (Objects.nonNull(line1.get(positionInitial).getValue()) && Objects.nonNull(
            line2.get(positionInitial).getValue())) {
          flag = false;
          break;
        }
      }
      if (flag) {
        return line1.parallelStream().map(point -> new Signal(point.getLocalDateTime(), Trend.NEUTRAL))
            .toList();
      } else {
        if (line1.get(positionInitial).getLocalDateTime().equals(localDateTimes.get(0))) {
          if (localDateTimes.size() > 2) {
            final Trend[] ini = {line1.get(positionInitial).getValue() < line1.parallelStream()
                .filter(point -> point.getLocalDateTime().equals(localDateTimes.get(1))).findFirst()
                .get().getValue() ? Trend.UPPER : Trend.DOWN};
            int finalPositionInitial2 = positionInitial;
            return IntStream.rangeClosed(0, line1.size() - 1).boxed().toList().stream().map(i -> {
              if (localDateTimes.isEmpty()) {
                return new Signal(line1.get(i).getLocalDateTime(), ini[0]);
              } else {
                return getSignal(line1, localDateTimes, ini, finalPositionInitial2, i);
              }
            }).toList();
          } else {
            Trend ini = line1.get(positionInitial).getValue() < line1.getLast().getValue() ? Trend.UPPER
                : Trend.DOWN;
            int finalPositionInitial1 = positionInitial;
            return IntStream.rangeClosed(0, line1.size() - 1).boxed().toList().parallelStream()
                .map(i -> {
                  if (i < finalPositionInitial1) {
                    return new Signal(line1.get(i).getLocalDateTime(), Trend.NEUTRAL);
                  } else {
                    return new Signal(line1.get(i).getLocalDateTime(), ini);
                  }
                }).toList();
          }
        } else {
          final Trend[] ini = {line1.get(positionInitial).getValue() < line1.parallelStream()
              .filter(point -> point.getLocalDateTime().equals(localDateTimes.get(0))).findFirst().get()
              .getValue() ? Trend.UPPER : Trend.DOWN};
          int finalPositionInitial = positionInitial;
          return IntStream.rangeClosed(0, line1.size() - 1).boxed().toList().stream().map(i -> {
            if (localDateTimes.isEmpty()) {
              return new Signal(line1.get(i).getLocalDateTime(), ini[0]);
            } else {
              if (i < finalPositionInitial) {
                return new Signal(line1.get(i).getLocalDateTime(), Trend.NEUTRAL);
              } else {
                return getSignal(line1, localDateTimes, ini, finalPositionInitial, i);
              }
            }
          }).toList();
        }
      }
    } else {
      return line1.parallelStream().map(point -> new Signal(point.getLocalDateTime(), Trend.NEUTRAL))
          .toList();
    }
  }

  @NotNull
  private static Signal getSignal(Line line1, List<LocalDateTime> localDateTimes, Trend[] ini,
      int finalPositionInitial, Integer i) {
    if (i == finalPositionInitial) {
      localDateTimes.remove(0);
      return new Signal(line1.get(i).getLocalDateTime(), ini[0]);
    } else {
      if (localDateTimes.get(0).equals(line1.get(i).getLocalDateTime())) {
        localDateTimes.remove(0);
        ini[0] = ini[0].equals(Trend.UPPER) ? Trend.DOWN : Trend.UPPER;
      }
      return new Signal(line1.get(i).getLocalDateTime(), ini[0]);
    }
  }
}
