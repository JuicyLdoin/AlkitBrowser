package net.alkitbrowser.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.AlkitBrowser;
import net.alkitbrowser.Settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SettingsController {

    @Setter
    MainController mainController;

    final AlkitBrowser alkitBrowser = AlkitBrowser.getAlkitBrowser();

    Settings settings;

    @FXML
    private VBox contentBox;

    public void setSettings(Settings settings) {

        this.settings = settings;

    }

    public void save() {



    }

    @FXML
    private void onSaveClick() {

        save();

    }

    @FXML
    private void onBackClick() {

        save();

        alkitBrowser.getScene().setRoot(alkitBrowser.getMain());

    }

    @FXML
    private void onHistoryClick() {

        contentBox.getChildren().clear();

        WebHistory history = mainController.getWebEngine().getHistory();
        settings.setHistory(history);

        for (WebHistory.Entry entry : history.getEntries()) {
            try {
                URL check = new URL(entry.getUrl());
                contentBox.getChildren().add(new Label(check.getAuthority() + "   -   " + entry.getUrl()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String getHistory(){
        contentBox.getChildren().clear();

        WebHistory history = mainController.getWebEngine().getHistory();
        settings.setHistory(history);

        for (WebHistory.Entry entry : history.getEntries()) {
            try {
                URL check = new URL(entry.getUrl());
                return check.getAuthority() + "   -   " + entry.getUrl();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}