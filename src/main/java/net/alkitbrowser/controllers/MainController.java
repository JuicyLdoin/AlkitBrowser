package net.alkitbrowser.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private WebView web;
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

    public void updateRequest() {

        Page page = new Page();

        try {

            page.createNewPage(webEngine, requestField);

        } catch (MalformedURLException e) {

            throw new RuntimeException(e);

        }
    }

    // обработка клика по кнопке поиска
    @FXML
    private void onFindClick() {

        updateRequest();

    }

    // обработка запроса при нажатии кнопки Enter
    @FXML
    private void onFindEnter(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.ENTER))
            updateRequest();

    }
}