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

    int systemNumber = 0;
    float zoom = 1;

    public void setZoom(float zoom) {

        if (zoom <= 0.25 || zoom >= 1.75)
            return;

        this.zoom = zoom;

    }

    public void saveSettings() throws IOException {

        SettingsController control = new SettingsController();

        File filePath = new File(System.getProperty("user.home") + "\\AlkitBrowser");
        File fileHistoryJson = new File(filePath, "history.json");
        File fileSettingsJson = new File(filePath, "settings.json");

        if (!filePath.exists())
            filePath.createNewFile();
        else if (!fileHistoryJson.exists())
            fileHistoryJson.createNewFile();
        else
            new Gson().toJson(control.getHistory(), new FileWriter(fileHistoryJson));


        if (!fileSettingsJson.exists())
            fileSettingsJson.createNewFile();
        else if (!fileSettingsJson.exists())
            fileSettingsJson.createNewFile();
        else {
            new Gson().toJson(systemNumber, new FileWriter(fileSettingsJson));
            new Gson().toJson(zoom, new FileWriter(fileSettingsJson));
        }
    }

    public String getSystem(int systemNumber) {

        String system = "";

        switch (systemNumber) {

            case 0 -> system = "https://duckduckgo.com/";
            case 1 -> system = "https://www.google.com/search?q=";
            case 2 -> system = "https://yandex.ru/search/?text=";
            case 3 -> system = "https://search.brave.com/search?q=";
            case 4 -> system = "https://search.yahoo.com/search?p=";

        }

        return system;

    }

    public String getSystem() {

        return getSystem(systemNumber);

    }
}