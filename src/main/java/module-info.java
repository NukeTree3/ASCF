module com.nuketree3.example.testtoascf {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.desktop;


    opens com.nuketree3.example.testtoascf to javafx.fxml;
    exports com.nuketree3.example.testtoascf.model.graph;
    opens com.nuketree3.example.testtoascf.model.graph to javafx.fxml;
    exports com.nuketree3.example.testtoascf.model.plane;
    opens com.nuketree3.example.testtoascf.model.plane to javafx.fxml;
    exports com.nuketree3.example.testtoascf.view;
    opens com.nuketree3.example.testtoascf.view to javafx.fxml;
}