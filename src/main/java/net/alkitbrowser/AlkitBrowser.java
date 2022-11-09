package net.alkitbrowser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

@Getter
public class AlkitBrowser extends Application {

    private Parent main;
    private Scene scene;

    public void start(Stage stage) throws IOException {

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

    }
}