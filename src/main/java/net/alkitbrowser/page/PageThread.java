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

    WebEngine webEngine;
    StringBuffer text;

    @Override
    public synchronized void start() {

        webEngine.load(AlkitBrowser.getAlkitBrowser().getSettings().getSystem() + text);

        try {

            if (!new Network().isInternetConnect())
                webEngine.load("https://www.google.com"); //load google dino

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }
}