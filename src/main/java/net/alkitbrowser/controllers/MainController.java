package net.alkitbrowser.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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

    // обработка клика по кнопке поиска
    @FXML
    private void onFindClick() {

        String text = requestField.getText();

        if (!text.equals(""))
            webEngine.load(text);

    }
}