package net.alkitbrowser.controllers;

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
    @Getter
    Label titleLabel;

    @Setter
    Page currentPage;

    public void setTextLabel(){

        Pattern checkURL = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher checkURLM = checkURL.matcher(mainController.getRequestField().getText());

        if (!mainController.getRequestField().getText().equals(""))
            if (checkURLM.matches())
                try {

                    URL check = new URL(mainController.getRequestField().getText());
                    titleLabel.setText(check.getAuthority());

                } catch (MalformedURLException e) {

                    throw new RuntimeException(e);

                }
            else
                titleLabel.setText(mainController.getRequestField().getText());

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