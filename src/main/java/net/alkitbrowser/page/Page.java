package net.alkitbrowser.page;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.controllers.PageController;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Page {

    final PageController pageController;

    @SneakyThrows
    public Page() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/page.fxml"));

        pageController = fxmlLoader.getController();

        pageController.setBody(fxmlLoader.load());
        pageController.setCurrentPage(this);

    }

    public void createNewPage(WebEngine webEngine, TextField requestField) throws MalformedURLException {

        webEngine.load(requestField.getText());

        StringBuffer text = new StringBuffer(requestField.getText());

        Pattern checkURL = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher checkURLM = checkURL.matcher(text);

        if (!text.toString().equals("") && !checkURLM.matches()) {

            PageThread page = new PageThread(webEngine, text);
            page.start();

        }
    }
}