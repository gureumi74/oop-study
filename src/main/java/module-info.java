module com.example.oopstudy {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.oopstudy to javafx.fxml;
    exports com.example.oopstudy;
}