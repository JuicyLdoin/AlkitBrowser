package net.alkitbrowser.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
    Label titleLabel;

    Page currentPage;

    public void setCurrentPage(Page currentPage) {

        this.currentPage = currentPage;

        // сделать установку тайтла

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