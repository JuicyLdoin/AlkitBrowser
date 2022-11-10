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
    int zoom = 1;
    WebHistory history;
    String pageName = "New Page";
    //в page.fxml добавь эту переменную, а дальше с ней будем работать (я про pageName)
    public void saveSettings() throws IOException {
        File file = new File(System.getProperty("user.home") + "\\AlkitBrowser\\setting.json");
        if (!file.exists()) file.createNewFile();
        else {
            //Gson gson = new Gson();
            //позже тут будет логирование настроек, но блять у нас даже их настройки ещё нету
        }
    }
}