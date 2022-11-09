package net.alkitbrowser.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class Page {
    private volatile StringBuffer pageUrl = new StringBuffer("https://duckduckgo.com/");
    private volatile StringBuffer pageName = new StringBuffer("DuckDuckGo");

    private static void createNewPage(){

    }
}
class newPage extends Thread{
    @Override
    public void run() {

    }
}
