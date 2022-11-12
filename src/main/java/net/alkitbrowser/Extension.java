package net.alkitbrowser;

import javafx.scene.web.WebEngine;

public class Extension {

    public void startScript(WebEngine engine, String script) {

        engine.executeScript(script);

    }
}