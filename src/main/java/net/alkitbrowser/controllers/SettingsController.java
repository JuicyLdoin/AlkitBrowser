package net.alkitbrowser.controllers;

import javafx.fxml.FXML;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.AlkitBrowser;
import net.alkitbrowser.Settings;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SettingsController {

    final AlkitBrowser alkitBrowser = AlkitBrowser.getAlkitBrowser();

    Settings settings;

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
}