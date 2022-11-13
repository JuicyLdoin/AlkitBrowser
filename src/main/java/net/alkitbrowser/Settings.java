package net.alkitbrowser;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.controllers.SettingsController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Settings {

    transient SettingsController settingsController;

    String systemNumber = "duckduckgo";
    float zoom = 1;

    String JSStart = "";

    public void setZoom(float zoom) {

        if (zoom <= 0.25 || zoom >= 1.75)
            return;

        this.zoom = zoom;

    }

    public void saveSettings() throws IOException {

        File filePath = new File(System.getProperty("user.home") + "\\AlkitBrowser");

        File fileHistoryJson = new File(filePath, "history.json");
        File fileSettingsJson = new File(filePath, "settings.json");

        if (!filePath.exists())
            filePath.mkdirs();

        if (!fileHistoryJson.exists())
            fileHistoryJson.createNewFile();

        FileWriter historyWriter = new FileWriter(fileHistoryJson);

        new Gson().toJson(settingsController.getHistory(), historyWriter);

        historyWriter.flush();
        historyWriter.close();

        if (!fileSettingsJson.exists())
            fileSettingsJson.createNewFile();

        FileWriter settingsWriter = new FileWriter(fileSettingsJson);

        new Gson().toJson(this, settingsWriter);

        settingsWriter.flush();
        settingsWriter.close();

    }

    public String getSystem(String systemNumber) {

        String system = "";

        switch (systemNumber) {

            case "duckduckgo" -> system = "https://duckduckgo.com/";
            case "google" -> system = "https://www.google.com/search?q=";
            case "yandex" -> system = "https://yandex.ru/search/?text=";
            case "brave" -> system = "https://search.brave.com/search?q=";
            case "yahoo" -> system = "https://search.yahoo.com/search?p=";

        }

        return system;

    }

    public String getSystem() {

        return getSystem(systemNumber);

    }
}