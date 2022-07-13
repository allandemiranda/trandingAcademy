//package br.bti.allandemiranda.forex.parser;
//
//import br.bti.allandemiranda.forex.candlestick.Candlestick;
//import br.bti.allandemiranda.forex.chart.Chart;
//import br.bti.allandemiranda.forex.currency.Exchange;
//import br.bti.allandemiranda.forex.currency.Pair;
//import java.io.File;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.net.URL;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import org.mockito.Mockito;
//
///**
// * The type Moving averages test.
// *
// * @author Allan de Miranda Silva
// * @version 1.0.1
// */
//class MetatraderTest {
//
//  @Test
//  void parser() throws URISyntaxException, IOException {
//    // given
//    URL url = MetatraderTest.class.getResource("EURUSD_M15.csv");
//    assert url != null;
//    File file = new File(url.toURI());
//    Exchange exchange = Mockito.mock(Exchange.class);
//    // when
//    Mockito.when(exchange.getCurrencyPair()).thenReturn(new Pair("EUR", "USD"));
//    Chart chart = Metatrader.parser(file, exchange);
//    // then
//    Assertions.assertEquals(18, chart.getCandlestickList().size());
//    Assertions.assertEquals(
//        new Candlestick("1.35442", "1.35502", "1.35437", "1.35502", "1269", "2014-01-23 03:00",
//            exchange.getCurrencyPair()), chart.getCandlestickList().get(4));
//  }
//
//  @Test
//  void parserTimeThrows() throws URISyntaxException {
//    // given
//    URL url = MetatraderTest.class.getResource("EURUSD_M15_ERRO_TIME.csv");
//    assert url != null;
//    File file = new File(url.toURI());
//    Exchange exchange = Mockito.mock(Exchange.class);
//    // when
//    Mockito.when(exchange.getCurrencyPair()).thenReturn(new Pair("EUR", "USD"));
//    Executable executable = () -> Metatrader.parser(file, exchange);
//    // then
//    Assertions.assertThrows(IllegalArgumentException.class, executable);
//  }
//
//  @Test
//  void parserCandlestickThrows() throws URISyntaxException {
//    // given
//    URL url = MetatraderTest.class.getResource("EURUSD_M15_ERRO_CANDLE.csv");
//    assert url != null;
//    File file = new File(url.toURI());
//    Exchange exchange = Mockito.mock(Exchange.class);
//    // when
//    Mockito.when(exchange.getCurrencyPair()).thenReturn(new Pair("EUR", "USD"));
//    Executable executable = () -> Metatrader.parser(file, exchange);
//    // then
//    Assertions.assertThrows(IllegalArgumentException.class, executable);
//  }
//}