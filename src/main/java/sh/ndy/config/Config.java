package sh.ndy.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import sh.ndy.ToggleNametagsClient;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Config {
  public static File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "togglenametags.json");
  public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  public static Options options = new Options();

  public static Options getOptions() {
    return options;
  }

  public static void setOptions(Options options) {
    Config.options = options;
  }

  public static void saveConfig() {
    try {
      FileWriter fileWriter = new FileWriter(file);
      fileWriter.write(gson.toJson(getOptions()));
      fileWriter.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void loadConfig() {
    if (file.exists()) {
      try {
        Options options = gson.fromJson(new FileReader(file), Options.class);
        if (options == null) {
          ToggleNametagsClient.logger.warn("Failed to load config file due to malformed JSON. Recreating with defaults.");

          options = new Options();
          setOptions(options);
          saveConfig();
          return;
        }

        setOptions(options);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      saveConfig();
    }
  }
}
