package net.alkitbrowser.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.page.Page;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageController {

    @Setter
    MainController mainController;

    @FXML
    @Setter
    HBox body;
    @FXML
    @Getter
    Label titleLabel;

    Page currentPage;

    public void setCurrentPage(Page currentPage) {

        this.currentPage = currentPage;

        // сделать установку тайтла

    }

    // две функции для путешествий по истории, биндов на них нет. Мб сделать кнопки или контрл + скм
    public void backPage(WebEngine engine){

        WebHistory history = engine.getHistory();
        history.go(-1);

    }

    public void forwardPage(WebEngine engine){

        WebHistory history = engine.getHistory();
        history.go(1);

    }

    public void setTextLabel(){

        if (!mainController.getRequestField().getText().equals(""))
            titleLabel.setText(mainController.getRequestField().getText());

    }

    @FXML
    private void onPageClick() {

        mainController.setOpenedPage(currentPage);

    }

    @FXML
    private void onClosePage() {

        mainController.removePage(currentPage);

    }
}