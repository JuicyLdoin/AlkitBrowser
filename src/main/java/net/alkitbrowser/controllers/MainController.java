package net.alkitbrowser.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.AlkitBrowser;
import net.alkitbrowser.Settings;
import net.alkitbrowser.page.Page;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController implements Initializable {

    final AlkitBrowser alkitBrowser = AlkitBrowser.getAlkitBrowser();

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
    @Getter
    TextField requestField;

    public void setHistory(WebEngine engine) {

        Settings settings = new Settings();

        WebHistory history = engine.getHistory();
        settings.setHistory(history);

        ObservableList<WebHistory.Entry> entries = history.getEntries();

        for (WebHistory.Entry entry : entries) {

            //тут выводить entry - историю поиска

        }
    }

    public void setOpenedPage(Page openedPage) {

        requestField.setText(openedPage.getRequest());
        openedPage.createNewPage(webEngine, openedPage.getRequest());

        if (this.openedPage != null)
            this.openedPage.getPageController().getBody().setId("background");

        openedPage.getPageController().getBody().setId("openedBackground");

        this.openedPage = openedPage;

    }

    // добавление нового окна
    public void addPage() {

        Page page = new Page(this);
        page.createNewPage(webEngine, "");

        setOpenedPage(page);
        pages.add(page);

    }

    public void refreshPage() {

        webEngine.reload();

    }

    // удаление окна
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

    // обновление запроса
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

    @FXML
    private void onRefreshClick() {

        refreshPage();

    }

    @FXML
    private void onHomeClick() {

        requestField.setText(new Settings().getSystem());
        updateRequest();

    }

    @FXML
    private void onSettingsClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/settings.fxml"));
        alkitBrowser.getScene().setRoot(fxmlLoader.load());

        SettingsController settingsController = fxmlLoader.getController();
        settingsController.setSettings(alkitBrowser.getSettings());

    }

    // горячие клавиши

    @FXML
    private void onHotKeys(KeyEvent keyEvent) {

        Settings settings = new Settings();

        if (keyEvent.getCode().equals(KeyCode.F5))
            refreshPage();

        if (keyEvent.getCode().equals(KeyCode.CONTROL)) {

            web.setZoom(settings.getZoom() + 0.25);
            settings.setZoom((int) (settings.getZoom() + 0.25));

        }

        if (keyEvent.getCode().equals(KeyCode.ALT)) {

            web.setZoom(settings.getZoom() - 0.25);
            settings.setZoom((int) (settings.getZoom() - 0.25));

        }
    }
}