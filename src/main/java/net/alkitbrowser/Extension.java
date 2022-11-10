package net.alkitbrowser;

import javafx.scene.web.WebEngine;

public class Extension {

    public void startScript(WebEngine engine, String script) {

        engine.executeScript(script);

    }

    public void startAdBlock(WebEngine engine){

        engine.executeScript("");
        //тут надо запускать ad-block скрипт который в resources, но я пока думаю над его переработкой да и у нас нет настроек для его влючения

    }
}