package net.alkitbrowser.controllers;

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

import java.net.MalformedURLException;
import java.net.URL;

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
                contentBox.getChildren().add(new Label(check.getAuthority()));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}