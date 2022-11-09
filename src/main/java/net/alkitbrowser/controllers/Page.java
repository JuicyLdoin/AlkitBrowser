package net.alkitbrowser.controllers;

import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
@Setter
public class Page {
    public void createNewPage(WebEngine webEngine, TextField requestField) throws MalformedURLException {
        webEngine.load(requestField.getText());
        StringBuffer text = new StringBuffer(requestField.getText());
        Pattern checkURL = Pattern.compile("\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher checkURLM = checkURL.matcher(text);
        if (!text.equals("")){
            if (!checkURLM.matches()){
                webEngine.load("https://duckduckgo.com/" + text);
//                newPage page = new newPage(text);
//                page.run();
            }
        }
    }
}
@AllArgsConstructor
class newPage extends Thread{
    private StringBuffer url;
    @Override
    public void run() {
    }
}
