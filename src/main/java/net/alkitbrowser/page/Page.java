package net.alkitbrowser.page;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.Settings;
import net.alkitbrowser.controllers.MainController;
import net.alkitbrowser.controllers.PageController;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Page {

    final PageController pageController;
    PageThread pageThread;
    //StringBuffer pageName = new StringBuffer();
    String request;
    @SneakyThrows
    public Page(MainController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/page.fxml"));

        HBox body = fxmlLoader.load();

        pageController = fxmlLoader.getController();

        pageController.setMainController(mainController);
        pageController.setBody(body);

        pageController.setCurrentPage(this);

    }

    public void createNewPage(WebEngine webEngine, TextField requestField) throws MalformedURLException {

        createNewPage(webEngine, requestField.getText());

    }

    public void createNewPage(WebEngine webEngine, String request) {

        if (pageThread != null && pageThread.isAlive())
            pageThread.stop();

        this.request = request;

        webEngine.load(request);

        StringBuffer requestBuffer = new StringBuffer(request);

        Pattern checkURL = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher checkURLM = checkURL.matcher(requestBuffer);

        if (!request.equals("") && !checkURLM.matches()) {
            Settings mySetting = new Settings();

            pageThread = new PageThread(this, webEngine, requestBuffer, mySetting.getSystemNumber());
            pageThread.start();

        }
    }
}