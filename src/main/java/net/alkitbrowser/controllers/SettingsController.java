package net.alkitbrowser.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.AlkitBrowser;
import net.alkitbrowser.Settings;
import net.alkitbrowser.controllers.settings.GlobalSettingsController;

import java.io.IOException;
import java.net.URL;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SettingsController {

    @Setter
    MainController mainController;

    final AlkitBrowser alkitBrowser = AlkitBrowser.getAlkitBrowser();
    final Settings settings = alkitBrowser.getSettings();

    @FXML
    private VBox contentBox;

    public void save() {

        try {

            settings.saveSettings();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }

    public String getHistory() {

        StringBuilder stringBuilder = new StringBuilder();
        WebHistory history = mainController.getWebEngine().getHistory();

        for (WebHistory.Entry entry : history.getEntries())
            try {

                stringBuilder.append(new URL(entry.getUrl()).getAuthority()).append("   -   ").append(entry.getUrl()).append("\n");

            } catch (IOException e) {

                throw new RuntimeException(e);

            }

        return stringBuilder.toString();

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

        save();

        contentBox.getChildren().clear();

        WebHistory history = mainController.getWebEngine().getHistory();

        for (WebHistory.Entry entry : history.getEntries())
            try {

                contentBox.getChildren().add(new Label(new URL(entry.getUrl()).getAuthority() + "   -   " + entry.getUrl()));

            } catch (IOException e) {

                throw new RuntimeException(e);

            }
    }

    @FXML
    private void onSettingsClick() throws IOException {

        save();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/globalSettings.fxml"));

        contentBox.getChildren().clear();
        contentBox.getChildren().add(fxmlLoader.load());

    }
}