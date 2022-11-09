package net.alkitbrowser.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lombok.Getter;
import net.alkitbrowser.page.Page;

@Getter
public class TabController {

    public TabController(Page currentPage) {

        setCurrentPage(currentPage);

    }

    @FXML
    private Label titleLabel;
    private Page currentPage;

    public void setCurrentPage(Page currentPage) {

        this.currentPage = currentPage;

        // сделать установку тайтла

    }

    @FXML
    private void onCloseTab() {

    }
}