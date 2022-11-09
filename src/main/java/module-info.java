module com.example.alkitbrowser {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires lombok;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens net.alkitbrowser to javafx.fxml, lombok;
    opens net.alkitbrowser.controllers to javafx.fxml, javafx.web, lombok;

    exports net.alkitbrowser;

}