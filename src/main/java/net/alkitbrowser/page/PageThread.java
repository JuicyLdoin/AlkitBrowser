package net.alkitbrowser.page;

import javafx.scene.web.WebEngine;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.AlkitBrowser;

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

    }
}