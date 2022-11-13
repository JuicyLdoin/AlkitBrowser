package net.alkitbrowser;

import javafx.scene.web.WebEngine;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Extension {

    public static void startScript(WebEngine engine, String script) {

        engine.executeScript(script);

    }
}