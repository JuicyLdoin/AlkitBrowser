package net.alkitbrowser;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.IOException;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AlkitBrowser extends Application {

    static AlkitBrowser alkitBrowser;

    public static AlkitBrowser getAlkitBrowser() {

        return alkitBrowser;

    }

    Parent main;
    Scene scene;

    Settings settings;

    boolean work;

    public void start(Stage stage) throws IOException {

        work = true;
        alkitBrowser = this;

        settings = new Gson().fromJson(System.getProperty("user.home") + "\\AlkitBrowser\\settings.json", Settings.class);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));

        main = fxmlLoader.load();
        scene = new Scene(main, 820, 480);

        // ==================================================
        // Set options to stage
        // ==================================================

        stage.setTitle("AlkitBrowser");
        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();

        System.out.println(Thread.currentThread().getName());

    }

    public void stop() {

        work = false;

        Settings settings = getAlkitBrowser().getSettings();

        try {
            settings.saveSettings();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}