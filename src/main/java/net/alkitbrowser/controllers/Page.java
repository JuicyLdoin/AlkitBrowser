package net.alkitbrowser.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class Page {
    private StringBuffer pageUrl = new StringBuffer("https://duckduckgo.com/");
    private StringBuffer pageName = new StringBuffer("DuckDuckGo");

    private static void createNewPage(){

    }
}
class newPage extends Thread{
    @Override
    public void run() {

    }
}