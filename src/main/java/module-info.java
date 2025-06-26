module com.mycompany.perpus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.perpus to javafx.fxml;
    opens com.mycompany.perpus.controller to javafx.fxml;
    exports com.mycompany.perpus;
}
