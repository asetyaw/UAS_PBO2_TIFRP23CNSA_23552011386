module com.mycompany.perpus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;

    opens com.mycompany.perpus to javafx.fxml;
    opens com.mycompany.perpus.controller to javafx.fxml;
    opens com.mycompany.perpus.model to javafx.base; 
    exports com.mycompany.perpus;
}
