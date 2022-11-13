package net.alkitbrowser.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.page.Page;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageController {

    @Setter
    MainController mainController;

    @FXML
    @Setter
    HBox body;
    @FXML
    Label titleLabel;

    @Setter
    Page currentPage;

    public void setTitleLabel() {

        String location = mainController.getWebEngine().getLocation();

        if (location == null)
            return;

        Platform.runLater(() -> {

            if (!mainController.getRequestField().isFocused())
                mainController.getRequestField().setText(location);

        });

        Pattern checkURL = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher checkURLM = checkURL.matcher(location);

        if (!location.equals(""))
            if (checkURLM.matches())
                Platform.runLater(() -> {

                    try {

                        titleLabel.setText(new URL(location).getAuthority());

                    } catch (MalformedURLException e) {

                        throw new RuntimeException(e);

                    }
                });
            else
                Platform.runLater(() -> titleLabel.setText(location));

    }

    @FXML
    private void onPageClick() {

        mainController.setOpenedPage(currentPage);

    }

    @FXML
    private void onClosePage() {

        mainController.removePage(currentPage);

    }
}