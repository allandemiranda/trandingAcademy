package br.bti.allandemiranda.forex;

import br.bti.allandemiranda.forex.model.analysis.Application;
import br.bti.allandemiranda.forex.model.analysis.MovingAverages;
import br.bti.allandemiranda.forex.model.utils.Currency;
import br.bti.allandemiranda.forex.model.utils.CurrencyExchange;
import br.bti.allandemiranda.forex.model.utils.CurrencyPair;
import br.bti.allandemiranda.forex.parser.Metatrader;
import com.beust.jcommander.Parameter;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.naming.SizeLimitExceededException;
import org.mockito.Mockito;

public class AllanProcessor extends Processor {

  //TODO ISSO É SOMENTE UM TESTE PARA SABER SE DEU TUDO CERTO

  // TODO -->> MELHORAR METODOS PARA SEREM MAIS RÁPIDOS, PARALELISMO
  @Parameter(names = {"-ema1"})
  private int ema1;

  @Parameter(names = {"-ema2"})
  private int ema2;

  @Parameter(names = {"-historicFile"})
  private File historicFile;

  public AllanProcessor(String... args) throws IOException {
    super(args);
  }

  public static void main(String[] args) {
    try {
      new AllanProcessor(args).run();
    } catch (Exception e) {
      getException(e);
    }
  }

  @Override
  public void run() throws IOException, SizeLimitExceededException {
    CurrencyExchange currencyExchange = Mockito.mock(CurrencyExchange.class);
    Mockito.when(currencyExchange.getCurrencyPair()).thenReturn(new CurrencyPair(Currency.EUR, Currency.USD));
    MovingAverages movingAverages = new MovingAverages(Metatrader.parser(historicFile, currencyExchange));

    LOGGER.info("ema1 = {} , ema2 = {}", ema1, ema2);

    LinkedList<Double> ema50 = movingAverages.getEMA(ema1, Application.CLOSE_VALUE);
    LinkedList<Double> ema100 = movingAverages.getEMA(ema2, Application.CLOSE_VALUE);

    if (ema50.size() != ema100.size()) {
      throw new SizeLimitExceededException("Tamanho das médias móveis não bate");
    }

    int encontros = 0;

    for (int i = 120; i < ema50.size(); ++i) {
      int j = 1;
      if (ema50.get(i - j).equals(ema100.get(i - j))) {
        j++;
        LOGGER.info("ema100 ({}) = {} , ema50 ({}) = {}", i - j, ema50.get(i - j), i - j, ema100.get(i - j));
      }

      if (ema50.get(i - j) >= ema50.get(i)) {
        if (ema100.get(i - j) < ema100.get(i) && ema100.get(i) > ema50.get(i)) {
          encontros++;
          LOGGER.info("ema100 ({}) = {} , ema100 ({}) = {} , ema50 ({}) = {} ,  ema50 ({}) = {} , encontros = {}", i - j, ema100.get(i - j),
              i, ema100.get(i), i - j, ema50.get(i - j), i, ema50.get(i), encontros);
        }
      } else {
        if (ema50.get(i - j) <= ema50.get(i)) {
          if (ema100.get(i - j) > ema100.get(i) && ema100.get(i) < ema50.get(i)) {
            encontros++;
            LOGGER.info("ema100 ({}) = {} , ema100 ({}) = {} , ema50 ({}) = {} ,  ema50 ({}) = {} , encontros = {}", i - j,
                ema100.get(i - j),
                i, ema100.get(i), i - j, ema50.get(i - j), i, ema50.get(i), encontros);
          }
        }
      }
    }

  }

}
