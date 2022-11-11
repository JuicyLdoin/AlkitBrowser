package net.alkitbrowser.controllers;

import javafx.collections.ObservableList;
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

        for (WebHistory.Entry entry : history.getEntries())
            contentBox.getChildren().add(new Label(entry.getUrl()));

    }
}