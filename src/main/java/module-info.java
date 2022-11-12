module com.example.alkitbrowser {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    requires com.google.gson;

    requires lombok;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens net.alkitbrowser.controllers to javafx.fxml, javafx.web, lombok, com.google.gson;
    opens net.alkitbrowser.page to javafx.fxml, javafx.web, lombok, com.google.gson;
    opens net.alkitbrowser to javafx.fxml, javafx.graphics, javafx.web, lombok, com.google.gson;

}