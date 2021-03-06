//package br.bti.allandemiranda.forex;
//
//import com.beust.jcommander.JCommander;
//import java.io.File;
//import java.io.IOException;
//import java.io.Reader;
//import java.nio.file.Files;
//import java.util.Arrays;
//import java.util.Map.Entry;
//import java.util.Properties;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.jetbrains.annotations.NotNull;
//
///**
// * The type Processor.
// *
// * @author Allan de Miranda Silva
// * @version 0.2
// */
//public abstract class Processor {
//
//  private static final String PROPERTIES_FILE = "-propertiesFile";
//  protected static Logger logger = LogManager.getLogger(Processor.class);
//  private File propertiesFile;
//  private boolean havePropertiesFile;
//
//  /**
//   * Instantiates a new Processor.
//   *
//   * @param args the args
//   *
//   * @throws IOException the io exception
//   */
//  public Processor(String... args) throws IOException {
//    setPropertiesFile(args);
//    buildProprieties();
//  }
//
//  /**
//   * Gets exception.
//   *
//   * @param e the e
//   */
//  protected static void getException(@NotNull Exception e) {
//    logger.error(e.toString());
//    if (logger.getLevel().equals(org.apache.logging.log4j.Level.DEBUG) || logger.getLevel().equals(org.apache.logging.log4j.Level.ALL)) {
//      e.printStackTrace();
//    }
//  }
//
//  /**
//   * Gets have properties file.
//   *
//   * @return the have properties file
//   */
//  private boolean getHavePropertiesFile() {
//    return havePropertiesFile;
//  }
//
//  /**
//   * Sets have properties file.
//   *
//   * @param flag the flag
//   */
//  private void setHavePropertiesFile(boolean flag) {
//    this.havePropertiesFile = flag;
//  }
//
//  /**
//   * Gets properties file.
//   *
//   * @return the properties file
//   */
//  private File getPropertiesFile() {
//    return propertiesFile;
//  }
//
//  /**
//   * Sets properties file.
//   *
//   * @param args the args
//   */
//  private void setPropertiesFile(String... args) {
//    logger.info("Starting the job {}", this.getClass());
//    if (Arrays.stream(args).toList().contains(PROPERTIES_FILE)) {
//      setHavePropertiesFile(true);
//      this.propertiesFile = new File(Arrays.stream(args).toList().get(Arrays.stream(args).toList().indexOf(PROPERTIES_FILE) + 1));
//    } else {
//      setHavePropertiesFile(false);
//      logger.warn("Can't find a properties file parameter");
//      logger.info("#########################");
//    }
//  }
//
//  /**
//   * Build proprieties.
//   *
//   * @throws IOException the io exception
//   */
//  private void buildProprieties() throws IOException {
//    if (getHavePropertiesFile()) {
//      Reader reader = Files.newBufferedReader(getPropertiesFile().toPath());
//
//      Properties properties = new Properties();
//      properties.load(reader);
//
//      JCommander jCommander = new JCommander(this);
//      jCommander.setAcceptUnknownOptions(true);
//
//      logger.info("### List of Parameter ###");
//      for (Entry<Object, Object> a : properties.entrySet()) {
//        logger.info("{}: {}", a.getKey().toString(), a.getValue().toString());
//        jCommander.parse("-" + a.getKey().toString(), a.getValue().toString());
//      }
//      logger.info("#########################");
//    }
//  }
//
//  /**
//   * Run.
//   */
//  public abstract void run() throws Exception;
//}
