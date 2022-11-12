package net.alkitbrowser;

import com.google.gson.Gson;
import javafx.scene.web.WebHistory;
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
    WebHistory history;

    public void setZoom(float zoom) {

        if (zoom <= 0.25 || zoom >= 1.75)
            return;

        this.zoom = zoom;

    }

    public void saveSettings() throws IOException {

        SettingsController control = new SettingsController();

        File file = new File(System.getProperty("user.home") + "\\AlkitBrowser\\history.json");
        File filePath = new File(System.getProperty("user.home") + "\\AlkitBrowser");

        if (!filePath.exists())
            file.createNewFile();
        else {
            if (!file.exists())
                file.createNewFile();
            else
                new Gson().toJson(control.getHistory(), new FileWriter(file));
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