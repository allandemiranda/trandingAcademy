package br.bti.allandemiranda.forex.processor;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.internal.Lists;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Processor.
 */
public abstract class Processor {

  private static final String PROPERTIES_FILE = "-propertiesFile";
  protected Logger logger = LogManager.getLogger(this.getClass());

  /**
   * Instantiates a new Processor.
   *
   * @param args the args
   * @throws IOException the io exception
   */
  protected Processor(String... args) throws IOException {
    logger.info("Starting the job {}", this.getClass());

    logger.info("#########################");

    Properties properties = new Properties();
    if (Arrays.stream(args).toList().contains(PROPERTIES_FILE)) {
      File propertiesFile = new File(
          Arrays.stream(args).toList().get(Arrays.stream(args).toList().indexOf(PROPERTIES_FILE) + 1));
      if (propertiesFile.exists() && propertiesFile.canRead()) {
        try (Reader reader = Files.newBufferedReader(propertiesFile.toPath());) {
          properties.load(reader);
          List<String> b = Lists.newArrayList();
          for (int i = 0; i < args.length; ++i) {
            if (PROPERTIES_FILE.equals(args[i])) {
              ++i;
            } else {
              b.add(args[i]);
            }
          }
          String[] a = Stream.concat(b.stream(), properties.entrySet().stream().flatMap(
              objectObjectEntry -> Lists.newArrayList("-".concat(objectObjectEntry.getKey().toString()),
                  objectObjectEntry.getValue().toString()).stream())).toList().toArray(String[]::new);
          JCommander.newBuilder().addObject(this).build().parse(a);
          logger.info(Arrays.stream(a).reduce("", (s, s2) -> {
            if(s2.startsWith("-") && !s.equals("")){
              logger.info(s.startsWith(" ") ? s.substring(1, s.length()-1) : s);
              return s2;
            } else {
              return s.concat(" ").concat(s2);
            }
          }));
        }
      } else {
        throw new InvalidPropertiesFormatException("Can't find the Properties File");
      }
    } else {
      JCommander.newBuilder().addObject(this).build().parse(args);
      logger.info(Arrays.stream(args).reduce("", (s, s2) -> {
        if(s2.startsWith("-") && !s.equals("")){
          logger.info(s.startsWith(" ") ? s.substring(1, s.length()-1) : s);
          return s2;
        } else {
          return s.concat(" ").concat(s2);
        }
      }));
    }

    logger.info("#########################");
  }

  /**
   * Run.
   */
  public abstract void run();
}
