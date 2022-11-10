package net.alkitbrowser.page;

import javafx.scene.web.WebEngine;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.alkitbrowser.Settings;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageThread extends Thread {

    Page page;

    WebEngine webEngine;
    StringBuffer text;

    int systemNumber;

    @Override
    public synchronized void start() {

        webEngine.load(new Settings().getSystem(systemNumber) + text);

    }
}