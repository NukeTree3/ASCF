package com.nuketree3.example.testtoascf.view;

import com.nuketree3.example.testtoascf.model.axiscube.AxisCube;
import com.nuketree3.example.testtoascf.model.graph.*;
import com.nuketree3.example.testtoascf.model.plane.Plane;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    private Scene scene;
    private double anchorX;
    private double anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    @Override
    public void start(Stage stage) throws IOException {




        PointGraphAbstract a = new PointGraphSambrero();

        Plane plane = new Plane(a);

        Group group = plane.generatePlane();
        AxisCube axisCube = new AxisCube();
        group.getChildren().add(axisCube.returnCubeWithAxis(a.getxMin(),a.getxMax(),a.getyMin(),a.getyMax(),a.getzMin(),a.getzMax()));
        //Group camera = new Group();

        Camera camera = new PerspectiveCamera(true);
        Scene scene = new Scene(group, Config.WIDTH, Config.HIGTH, true,  SceneAntialiasing.BALANCED);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        camera.translateXProperty().set(0);
        camera.translateYProperty().set(0);
        camera.translateZProperty().set(-500);

        camera.setNearClip(1);
        camera.setFarClip(100000);

        initMouseControl(group, scene, stage);



        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case R:
                    camera.translateZProperty().set(camera.getTranslateZ() + 5);
                    break;
                case F:
                    camera.translateZProperty().set(camera.getTranslateZ() - 5);
                    break;
                case D:
                    camera.translateXProperty().set(camera.getTranslateX() + 5);
                    break;
                case A:
                    camera.translateXProperty().set(camera.getTranslateX() - 5);
                    break;
                case W:
                    camera.translateYProperty().set(camera.getTranslateY() - 5);
                    break;
                case S:
                    camera.translateYProperty().set(camera.getTranslateY() + 5);
                    break;
                case Q:
                    camera.rotateProperty().set(camera.getRotate()-5);
                    break;
                case E:
                    camera.rotateProperty().set(camera.getRotate()+5);
                    break;
            }
        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void initMouseControl(Group group, Scene scene, Stage stage) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(xRotate = new Rotate(0, Rotate.X_AXIS), yRotate = new Rotate(0, Rotate.Y_AXIS));
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + (anchorX - event.getSceneX()));
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double movemetn = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - movemetn);
        });
    }


    public static void main(String[] args) {
        launch();
    }
}