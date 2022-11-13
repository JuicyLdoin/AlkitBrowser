package net.alkitbrowser.page;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.AlkitBrowser;
import net.alkitbrowser.controllers.MainController;
import net.alkitbrowser.controllers.PageController;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Page {

    final PageController pageController;

    PageTask pageTask;
    Thread pageThread;

    String request;

    @SneakyThrows
    public Page(MainController mainController) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/page.fxml"));

        HBox body = fxmlLoader.load();

        pageController = fxmlLoader.getController();

        pageController.setMainController(mainController);

        pageController.setBody(body);
        pageController.setCurrentPage(this);

        pageController.setTitleLabel();

    }

    public void createNewPage(WebEngine webEngine, TextField requestField) {

        createNewPage(webEngine, requestField.getText());

    }

    public void createNewPage(WebEngine webEngine, @NonNull String request) {

        if (request.equals(""))
            return;

        Pattern checkURL = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher checkURLM = checkURL.matcher(new StringBuffer(request));

        if (!checkURLM.matches())
            request = AlkitBrowser.getAlkitBrowser().getSettings().getSystem() + request;

        this.request = request;

        webEngine.load(request);

        if (pageThread != null)
            pageThread.stop();

        pageTask = new PageTask(this, webEngine, request);

        pageThread = new Thread(pageTask);
        pageThread.start();

        pageController.setTitleLabel();

    }

    public void downloadFileFromPage(URL url) {

        url.getRef();

    }
}