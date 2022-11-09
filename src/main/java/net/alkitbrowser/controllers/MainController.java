package net.alkitbrowser.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.page.Page;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController implements Initializable {

    @FXML
    WebView web;
    WebEngine webEngine;

    // открытое окно
    Page openedPage;

    // список окон
    final ObservableList<Page> pages = FXCollections.observableArrayList();
    @FXML
    private HBox tabs;

    // поле в котором пользователь будет вводить ссылку или запрос
    @FXML
    TextField requestField;

    public void setOpenedPage(Page openedPage) {

        requestField.setText(openedPage.getRequest());
        webEngine.load(openedPage.getRequest());

        this.openedPage = openedPage;

    }

    // добавление нового окна
    public void addPage() {

        Page page = new Page(this);
        page.createNewPage(webEngine, "");

        setOpenedPage(page);
        pages.add(page);

    }

    public void removePage(Page page) {

        if (pages.size() == 1)
            return;

        if (openedPage.equals(page))
            setOpenedPage(pages.get(0));

        pages.remove(page);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        webEngine = web.getEngine();

        // при изменении списка окон обновляем их отображение
        pages.addListener((javafx.beans.Observable observable) -> {

            tabs.getChildren().clear();
            pages.forEach(page -> tabs.getChildren().add(page.getPageController().getBody()));

        });

        addPage();

    }

    public void updateRequest() {

        try {

            openedPage.createNewPage(webEngine, requestField);

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

    @FXML
    private void onAddPage() {

        addPage();

    }
}