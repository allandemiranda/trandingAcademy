package br.bti.allandemiranda.forex.indicators.trend;

import br.bti.allandemiranda.forex.chart.Line;
import br.bti.allandemiranda.forex.indicators.Signal;
import br.bti.allandemiranda.forex.indicators.Trend;
import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

public class CrossSignal {

  public static @NotNull List<Signal> getTrendSinal(@NotNull Line line1, Line line2) {
    if (line1.compatible(line2)) {
      boolean up = false;
      for (int i = 0; (i + 1) < line1.size(); i++) {
        if (Objects.nonNull(line1.get(i).getValue()) && Objects.nonNull(line1.get(i + 1).getValue())) {
          if (line1.get(i).getValue() > line2.get(i).getValue()) {
            up = true;
          }
          break;
        }
        if ((i + 2) < line1.size()) {
          throw new IllegalStateException("Impossible decide the trend to this line");
        }
      }
      boolean finalUp = up;
      return IntStream.rangeClosed(0, line1.size() - 1).boxed().toList().parallelStream().map(i -> {
        if (Objects.nonNull(line1.get(i).getValue()) && Objects.nonNull(line2.get(i).getValue())) {
          if (line1.get(i).getValue() > line2.get(i).getValue()) {
            return new Signal(line1.get(i).getLocalDateTime(), finalUp ? Trend.UP : Trend.DOWN);
          } else {
            if (line1.get(i).getValue() < line2.get(i).getValue()) {
              return new Signal(line1.get(i).getLocalDateTime(), finalUp ? Trend.DOWN : Trend.UP);
            }
          }
        }
        return new Signal(line1.get(i).getLocalDateTime(), Trend.NON);
      }).sorted(Comparator.comparing(Signal::getLocalDateTime)).collect(Collectors.toList());
    } else {
      throw new InvalidParameterException("Incompatible lines to get trend");
    }
  }
}
