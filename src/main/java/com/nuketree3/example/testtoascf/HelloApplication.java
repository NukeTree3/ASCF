package com.nuketree3.example.testtoascf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Sphere sphere = new Sphere(50);

        Group root = new Group();
        root.getChildren().add(sphere);

        Camera camera = new PerspectiveCamera();


        Scene scene = new Scene(root, Config.WIDTH, Config.HIGTH);
        scene.setFill(Color.SILVER);

        scene.setCamera(camera);

        sphere.setTranslateX(Config.WIDTH/2);
        sphere.setTranslateY(Config.HIGTH/2);


        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case W:
                    sphere.translateZProperty().set(sphere.getTranslateZ() + 10);
                    break;
                case S:
                    sphere.translateZProperty().set(sphere.getTranslateZ() - 10);
                    break;
            }
        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}