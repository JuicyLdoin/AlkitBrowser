package net.alkitbrowser.page;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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

        HBox body = fxmlLoader.load();

        pageController = fxmlLoader.getController();

        pageController.setBody(body);
        pageController.setCurrentPage(this);

    }

    public void createNewPage(WebEngine webEngine, TextField requestField) throws MalformedURLException {

        createNewPage(webEngine, requestField.getText());

    }

    public void createNewPage(WebEngine webEngine, String request) {

        webEngine.load(request);

        StringBuffer requestBuffer = new StringBuffer(request);

        Pattern checkURL = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher checkURLM = checkURL.matcher(requestBuffer);

        if (!request.equals("") && !checkURLM.matches()) {

            PageThread page = new PageThread(webEngine, requestBuffer);
            page.start();

        }
    }
}