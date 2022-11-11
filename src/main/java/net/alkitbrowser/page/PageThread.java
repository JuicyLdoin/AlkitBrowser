package net.alkitbrowser.page;

import javafx.scene.web.WebEngine;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.AlkitBrowser;
import net.alkitbrowser.Network;

import java.io.IOException;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageThread extends Thread {

    Page page;

    WebEngine webEngine;
    StringBuffer text;

    int systemNumber;

    @Override
    public synchronized void start() {

        webEngine.load(AlkitBrowser.getAlkitBrowser().getSettings().getSystem(systemNumber) + text);

        try {

            if (!new Network().isInternetConnect())
                webEngine.load("https://www.google.com");

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }
}