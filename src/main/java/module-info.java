module com.nuketree3.example.testtoascf {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nuketree3.example.testtoascf to javafx.fxml;
    exports com.nuketree3.example.testtoascf;
}