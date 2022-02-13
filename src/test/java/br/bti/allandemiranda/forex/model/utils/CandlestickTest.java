package br.bti.allandemiranda.forex.model.utils;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

/**
 * The type Candlestick test.
 *
 * @author Allan de Miranda Silva
 * @version 1.0.0
 */
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
    int volume = 10;
    // when
    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertEquals(openPrice, candlestick.getOpenPrice());
    Assertions.assertEquals(closePrice, candlestick.getClosePrice());
    Assertions.assertEquals(lowPrice, candlestick.getLowPrice());
    Assertions.assertEquals(highPrice, candlestick.getHighPrice());
    Assertions.assertEquals(localDateTime, candlestick.getLocalDateTime());
    Assertions.assertEquals(currencyPair, candlestick.getCurrencyPair());
    Assertions.assertEquals(volume, candlestick.getVolume());
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 10, 20})
  void getVolume(int volume) {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);
    // when
    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertEquals(openPrice, candlestick.getOpenPrice());
    Assertions.assertEquals(closePrice, candlestick.getClosePrice());
    Assertions.assertEquals(lowPrice, candlestick.getLowPrice());
    Assertions.assertEquals(highPrice, candlestick.getHighPrice());
    Assertions.assertEquals(localDateTime, candlestick.getLocalDateTime());
    Assertions.assertEquals(currencyPair, candlestick.getCurrencyPair());
    Assertions.assertEquals(volume, candlestick.getVolume());
  }

  @ParameterizedTest
  @ValueSource(ints = {-10, -20})
  void getVolumeNegativeThrows(int volume) {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @ParameterizedTest
  @ValueSource(strings = {"-10", "-20"})
  void getVolumeNegativeThrows(String volume) {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertThrows(InputMismatchException.class, executable);
  }

  @Test
  void getVolumeStringNullThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);
    String volume = null;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void getVolumeStringEmptyThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);
    String volume = "";
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertThrows(IllegalArgumentException.class, executable);
  }

  @Test
  void getVolumeStringNotNumberThrows() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);
    String volume = "abc8";
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertThrows(NumberFormatException.class, executable);
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
    int volume = 10;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    int volume = 10;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    int volume = 10;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    int volume = 10;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    int volume = 10;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    int volume = 10;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    int volume = 10;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    int volume = 10;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    int volume = 10;
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
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
    String volume = Integer.toString(10);
    // when
    Executable executable = () -> new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertThrows(NumberFormatException.class, executable);
  }

  @ParameterizedTest
  @ValueSource(strings = {"0", "10", "20"})
  void getPricesAndDateAndCurrencyString(String volume) {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = Mockito.mock(CurrencyPair.class);
    // when
    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertEquals(0.2, candlestick.getOpenPrice());
    Assertions.assertEquals(0.3, candlestick.getClosePrice());
    Assertions.assertEquals(0.1, candlestick.getLowPrice());
    Assertions.assertEquals(0.4, candlestick.getHighPrice());
    Assertions.assertEquals(
        LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER)),
        candlestick.getLocalDateTime());
    Assertions.assertEquals(currencyPair, candlestick.getCurrencyPair());
    Assertions.assertEquals(Integer.parseInt(volume), candlestick.getVolume());
  }

  @Test
  void testEquals() {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;

    CurrencyPair currencyPair1 = Mockito.mock(CurrencyPair.class);
    CurrencyPair currencyPair2 = Mockito.mock(CurrencyPair.class);
    LocalDateTime localDateTime1 = LocalDateTime.now();
    LocalDateTime localDateTime2 = LocalDateTime.now().plusHours(1);
    int volume1 = 11;
    int volume2 = 22;
    // when
    Candlestick candlestick1 = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume1,
        localDateTime1, currencyPair1);
    Candlestick candlestick2 = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume2,
        localDateTime2, currencyPair2);
    // then
    Assertions.assertNotEquals(currencyPair1, currencyPair2);
    Assertions.assertNotEquals(localDateTime1, localDateTime2);
    Assertions.assertNotEquals(volume1, volume2);
    Assertions.assertEquals(candlestick1, candlestick2);
  }

  @Test
  void testHashCode() {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;

    CurrencyPair currencyPair1 = Mockito.mock(CurrencyPair.class);
    CurrencyPair currencyPair2 = Mockito.mock(CurrencyPair.class);
    LocalDateTime localDateTime1 = LocalDateTime.now();
    LocalDateTime localDateTime2 = LocalDateTime.now().plusHours(1);
    int volume1 = 11;
    int volume2 = 22;
    // when
    Candlestick candlestick1 = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume1,
        localDateTime1, currencyPair1);
    Candlestick candlestick2 = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume2,
        localDateTime2, currencyPair2);
    // then
    Assertions.assertNotEquals(currencyPair1, currencyPair2);
    Assertions.assertNotEquals(localDateTime1, localDateTime2);
    Assertions.assertNotEquals(volume1, volume2);
    Assertions.assertEquals(candlestick1, candlestick2);
  }

  @Test
  void testToString() {
    // given
    String openPrice = Double.toString(0.2);
    String closePrice = Double.toString(0.3);
    String lowPrice = Double.toString(0.1);
    String highPrice = Double.toString(0.4);
    String localDateTime = "2021-01-01 12:22";
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    String volume = Integer.toString(10);
    // when
    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    String toString =
        "Candlestick{openPrice=0.2," + " closePrice=0.3," + " lowPrice=0.1," + " highPrice=0.4,"
            + " volume=10," + " localDateTime=2021-01-01T12:22,"
            + " currencyPair=CurrencyPair{base=EUR, profit=USD}}";
    Assertions.assertEquals(toString, candlestick.toString());
  }

  @Test
  void getCandlestickActionUpper() {
    // given
    double openPrice = 0.2;
    double closePrice = 0.3;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    int volume = 10;
    // when
    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertEquals(CandlestickAction.UPPER, candlestick.getCandlestickAction());
  }

  @Test
  void getCandlestickActionNeutral() {
    // given
    double openPrice = 0.2;
    double closePrice = 0.2;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    int volume = 10;
    // when
    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertEquals(CandlestickAction.NEUTRAL, candlestick.getCandlestickAction());
  }

  @Test
  void getCandlestickActionDown() {
    // given
    double openPrice = 0.3;
    double closePrice = 0.2;
    double lowPrice = 0.1;
    double highPrice = 0.4;
    LocalDateTime localDateTime = LocalDateTime.now();
    CurrencyPair currencyPair = new CurrencyPair(Currency.EUR, Currency.USD);
    int volume = 10;
    // when
    Candlestick candlestick = new Candlestick(openPrice, closePrice, lowPrice, highPrice, volume,
        localDateTime, currencyPair);
    // then
    Assertions.assertEquals(CandlestickAction.DOWN, candlestick.getCandlestickAction());
  }
}