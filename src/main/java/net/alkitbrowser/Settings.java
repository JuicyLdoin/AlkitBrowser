package net.alkitbrowser;

import javafx.scene.web.WebHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Settings {

    StringBuffer nameOS = new StringBuffer("Windows XP");
    int systemNumber = 0;
    List<String> searchSystemList = new ArrayList<>();
    float zoom = 1;
    WebHistory history;
    String pageName = "New Page";

    public void setZoom(float zoom) {

        if (zoom <= 0.25 || zoom >= 1.75)
            return;

        this.zoom = zoom;

    }
    public void saveSettings() throws IOException {

        File file = new File(System.getProperty("user.home") + "\\AlkitBrowser\\setting.json");


        if (!file.exists())
            file.createNewFile();
        else {
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