package net.alkitbrowser;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Network {
    boolean internetConnect;

    {
        try {
            internetConnect = isInternetConnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void internetConnect(){
        if (internetConnect){
            //инёрнет есть
        } else {
            // нету
        }
    }

    private boolean isInternetConnect() throws IOException {
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
