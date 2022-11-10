package net.alkitbrowser.page;

import javafx.scene.web.WebEngine;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class PageThread extends Thread {

    Page page;

    WebEngine webEngine;
    StringBuffer text;

    int systemNumber = 0;

    @Override
    public synchronized void start() {

        switch (systemNumber) {

            case 0 -> webEngine.load("https://duckduckgo.com/" + text);
            case 1 -> webEngine.load("https://www.google.ru/search?q=" + text);
            case 2 -> webEngine.load("https://yandex.ru/search/?text=" + text);
            case 3 -> webEngine.load("https://search.brave.com/search?q=" + text);
            case 4 -> webEngine.load(" https://search.yahoo.com/search?p=" + text);

        }
    }
}