package net.alkitbrowser.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
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
    final Settings settings = alkitBrowser.getSettings();

    @FXML
    WebView web;

    @Getter
    WebEngine webEngine;
    Page openedPage;
    final ObservableList<Page> pages = FXCollections.observableArrayList();
    @FXML
    private HBox tabs;

    @FXML
    @Getter
    TextField requestField;

    public void setOpenedPage(Page openedPage) {

        requestField.setText(openedPage.getRequest());
        openedPage.createNewPage(webEngine, openedPage.getRequest());

        if (this.openedPage != null)
            this.openedPage.getPageController().getBody().setId("background");

        openedPage.getPageController().getBody().setId("openedBackground");

        this.openedPage = openedPage;

    }

    public void addPage() throws MalformedURLException {

        requestField.setText(new Settings().getSystem());

        Page page = new Page(this);
        page.createNewPage(webEngine, requestField.getText());

        setOpenedPage(page);
        pages.add(page);

    }

    public void refreshPage() {

        openedPage.createNewPage(webEngine, openedPage.getRequest());
        webEngine.reload();

    }

    public void removePage(Page page) {

        if (pages.size() == 1)
            return;

        if (openedPage.equals(page))
            setOpenedPage(pages.get(0));

        pages.remove(page);

    }

    BorderPane settingsPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        web.setZoom(settings.getZoom());
        webEngine = web.getEngine();

        pages.addListener((javafx.beans.Observable observable) -> {

            tabs.getChildren().clear();
            pages.forEach(page -> tabs.getChildren().add(page.getPageController().getBody()));

        });

        try {

            addPage();

        } catch (MalformedURLException e) {

            throw new RuntimeException(e);

        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/settings.fxml"));

        try {

            settingsPage = fxmlLoader.load();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        SettingsController settingsController = fxmlLoader.getController();
        settingsController.setMainController(this);

    }

    public void updateRequest() {

        openedPage.createNewPage(webEngine, requestField.getText());

    }

    @FXML
    private void onFindClick() {

        updateRequest();

    }

    @FXML
    private void onFindEnter(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.ENTER))
            updateRequest();

    }

    @FXML
    private void onAddPage() throws MalformedURLException {

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
    private void onSettingsClick() {

        alkitBrowser.getScene().setRoot(settingsPage);

    }

    @FXML
    private void onHotKeys(KeyEvent keyEvent) {

        Settings settings = AlkitBrowser.getAlkitBrowser().getSettings();

        if (keyEvent.getCode().equals(KeyCode.F5))
            refreshPage();

        if (keyEvent.getCode().equals(KeyCode.CONTROL)) {

            web.setZoom(settings.getZoom() + 0.25);
            settings.setZoom(settings.getZoom() + 0.25f);

        }

        if (keyEvent.getCode().equals(KeyCode.ALT)) {

            web.setZoom(settings.getZoom() - 0.25);
            settings.setZoom(settings.getZoom() - 0.25f);

        }
    }
}