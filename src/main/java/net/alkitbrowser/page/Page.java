package net.alkitbrowser.page;

import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.controllers.TabController;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Page {

    final TabController tabController;

    public Page() {

        tabController = new TabController(this);

    }

    public void createNewPage(WebEngine webEngine, TextField requestField) throws MalformedURLException {

        webEngine.load(requestField.getText());

        StringBuffer text = new StringBuffer(requestField.getText());

        Pattern checkURL = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher checkURLM = checkURL.matcher(text);

        if (!text.toString().equals("") && !checkURLM.matches()){
            PageThread page = new PageThread(webEngine, text);
            page.start();
        }
    }
}