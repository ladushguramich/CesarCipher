module org.lado.uiceaser {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.lado.uiceaser to javafx.fxml;
    exports org.lado.uiceaser;
}