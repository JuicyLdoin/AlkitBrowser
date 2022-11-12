package net.alkitbrowser;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class Network {

    public boolean isInternetConnect() throws IOException {

        try {

            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();

            conn.connect();
            conn.getInputStream().close();

            return true;

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }
}