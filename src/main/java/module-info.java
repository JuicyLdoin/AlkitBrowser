module com.example.alkitbrowser {

    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens net.alkitbrowser to javafx.fxml, lombok;

    exports net.alkitbrowser;

}