package net.alkitbrowser.page;

import javafx.scene.web.WebEngine;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class PageThread extends Thread {

    WebEngine webEngine;
    StringBuffer text;

    @Override
    public void run() {

        webEngine.load("https://duckduckgo.com/" + text);

    }
}