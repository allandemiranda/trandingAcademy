package br.bti.allandemiranda.forex.model;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

class CandlestickTest {

  private static final String DATE_TIME_FORMATTER = "yyyy-MM-dd kk:mm";

  @Test
  void getPricesAndDateAndCurrency() {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertEquals(openPrice, candlestick.getOpenPrice());
    Assertions.assertEquals(closePrice, candlestick.getClosePrice());
    Assertions.assertEquals(lowPrice, candlestick.getLowPrice());
    Assertions.assertEquals(highPrice, candlestick.getHighPrice());
    Assertions.assertEquals(localDateTime, candlestick.getLocalDateTime());
    Assertions.assertEquals(currencyPair, candlestick.getCurrencyPair());
  }

  @Test
  void checkCandlestickOpenPriceThrows() {
    // given
    double openPrice = 0.1;
    double closePrice = 0.4;
    double lowPrice = 0.3;
    double highPrice = 0.5;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void checkCandlestickClosePriceThrows() {
    // given
    double openPrice = 0.4;
    double closePrice = 0.7;
    double lowPrice = 0.3;
    double highPrice = 0.5;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void checkCandlestickLowHighThrows() {
    // given
    double openPrice = 0.4;
    double closePrice = 0.5;
    double lowPrice = 0.6;
    double highPrice = 0.1;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setCurrencyPairNullThrows() {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = null;

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setLocalDateTimeNullThrows() {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = null;
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setLocalDateTimeStringThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22:00";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(DateTimeException.class, executable);
  }

  @Test
  void setLocalDateTimeStringNullThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = null;
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setLocalDateTimeStringBlankThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setHighPriceNegativeThrows() {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = -0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setHighPriceStringNullThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = null;
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setHighPriceStringBlankThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = "";
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setHighPriceStringNegativeStringThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = "-0.4";
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setHighPriceStringFormatThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = "0,4";
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(NumberFormatException.class, executable);
  }

  @Test
  void setLowPriceNegativeThrows() {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = -0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setLowPriceStringNullThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = null;
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setLowPriceStringBlankThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = "";
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setLowPriceStringNegativeStringThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = "-0.1";
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setLowPriceStringFormatThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = "0,1";
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(NumberFormatException.class, executable);
  }

  @Test
  void setClosePriceNegativeThrows() {
    // given
    double openPrice = 0.2;
    double closePrice = -0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setClosePriceStringNullThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = null;
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setClosePriceStringBlankThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = "";
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setClosePriceStringNegativeStringThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = "-0.3";
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setClosePriceStringFormatThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = "0,3";
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(NumberFormatException.class, executable);
  }

  @Test
  void setOpenPriceNegativeThrows() {
    // given
    double openPrice = -0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setOpenPriceStringNullThrows() {
    // given
    String openPrice = null;
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setOpenPriceStringBlankThrows() {
    // given
    String openPrice = "";
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void setOpenPriceStringNegativeStringThrows() {
    // given
    String openPrice = "-0.2";
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void setOpenPriceStringFormatThrows() {
    // given
    String openPrice = "0,2";
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertThrows(NumberFormatException.class, executable);
  }

  @Test
  void getPricesAndDateAndCurrencyString() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);

    // when
    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice,localDateTime, currencyPair);

    // then
    Assertions.assertEquals(0.2, candlestick.getOpenPrice());
    Assertions.assertEquals(0.3, candlestick.getClosePrice());
    Assertions.assertEquals(0.1, candlestick.getLowPrice());
    Assertions.assertEquals(0.4, candlestick.getHighPrice());
    Assertions.assertEquals(LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER)), candlestick.getLocalDateTime());
    Assertions.assertEquals(currencyPair, candlestick.getCurrencyPair());
  }
}