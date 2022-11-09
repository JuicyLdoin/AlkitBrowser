package net.alkitbrowser.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainController implements Initializable {

    @FXML
    public WebView web;
    private WebEngine webEngine;

    // поле окон
    @FXML
    private HBox tabs;

    // поле в котором пользователь будет вводить ссылку или запрос
    @FXML
    private TextField requestField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        webEngine = web.getEngine();

    }

    // обработка клика по кнопке поиска
    @FXML
    private void onFindClick() {
        Page page = new Page();
        try {
            page.createNewPage(webEngine, requestField);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}