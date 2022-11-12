package net.alkitbrowser.controllers.settings;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.AlkitBrowser;
import net.alkitbrowser.Settings;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GlobalSettings implements Initializable {

    final AlkitBrowser alkitBrowser = AlkitBrowser.getAlkitBrowser();
    final Settings settings = alkitBrowser.getSettings();

    @FXML
    ComboBox<String> systemNumber;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        systemNumber.getItems().addAll(Arrays.asList("duckduckgo", "google", "yandex", "brave", "yahoo"));

    }

    @FXML
    private void onSystemNumberChange() {

        settings.setSystemNumber(systemNumber.getValue());

    }
}