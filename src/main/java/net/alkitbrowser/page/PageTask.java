package net.alkitbrowser.page;

import javafx.concurrent.Task;
import javafx.scene.web.WebEngine;
import lombok.Value;
import net.alkitbrowser.AlkitBrowser;
import net.alkitbrowser.Network;

import java.io.IOException;

@Value
public class PageTask extends Task<Void> {

    Page page;

    WebEngine webEngine;
    String text;

    public PageTask(Page page, WebEngine webEngine, String text) {

        this.page = page;

        this.webEngine = webEngine;
        this.text = text;

        try {

            if (!new Network().isInternetConnect())
                webEngine.load("https://www.google.com"); //load google dino

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        webEngine.load(text);

    }

    @Override
    protected Void call() {

        while (AlkitBrowser.getAlkitBrowser().isWork()) {

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                throw new RuntimeException(e);

            }

            page.getPageController().setTitleLabel();

        }

        return null;

    }
}