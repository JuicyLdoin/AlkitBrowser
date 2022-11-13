package net.alkitbrowser;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Network {

    public boolean isInternetConnect() throws IOException {

        try {

            final URLConnection conn = new URL("https://www.google.com/").openConnection();

            conn.connect();
            conn.getInputStream().close();

            return true;

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }
}