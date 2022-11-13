package net.alkitbrowser.page;

import javafx.scene.web.WebEngine;
import lombok.Value;
import net.alkitbrowser.AlkitBrowser;
import net.alkitbrowser.Network;

import java.io.IOException;

@Value
public class PageThread extends Thread {

    WebEngine webEngine;
    StringBuffer text;

    public PageThread(WebEngine webEngine, StringBuffer text) {

        this.webEngine = webEngine;
        this.text = text;

        try {

            if (!new Network().isInternetConnect())
                webEngine.load("https://www.google.com");

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public synchronized void run() {

        System.out.println("run");

        while (AlkitBrowser.getAlkitBrowser().isWork()) {

            System.out.println(webEngine.getLocation());

        }
    }

    @Override
    public synchronized void start() {

        System.out.println("start");

        webEngine.load(AlkitBrowser.getAlkitBrowser().getSettings().getSystem() + text);

    }
}