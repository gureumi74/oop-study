module com.example.oopstudy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.oopstudy to javafx.fxml;
    exports com.example.oopstudy;
}