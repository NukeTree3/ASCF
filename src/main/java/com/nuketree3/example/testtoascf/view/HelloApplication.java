package com.nuketree3.example.testtoascf.view;

import com.nuketree3.example.testtoascf.model.graph.PointGraphRandom;
import com.nuketree3.example.testtoascf.model.plane.Plane;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private double anchorX;
    private double anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    public Group createSetka(int size){
        double zTranslate = 0;
        double lineWidth = 1.0;
        Color gridColor = Color.WHITE;
        Group group1 = new Group();

        for (int y = -size; y <= size; y += size / 10) {
            //System.out.println(y);
            Line line = new Line(-100, 0, size, 0);

            line.setStroke(gridColor);
            line.setFill(gridColor);
            line.setTranslateY(y);
            line.setTranslateZ(zTranslate);
            line.setStrokeWidth(lineWidth);

            group1.getChildren().add(line);
        }

        for (int x = -size; x <= size; x += size / 10) {

            Line line = new Line(0, -100, 0, size);
            line.setStroke(gridColor);
            line.setFill(gridColor);
            line.setTranslateX(x);
            line.setTranslateZ(zTranslate);
            line.setStrokeWidth(lineWidth);

            group1.getChildren().add(line);

        }
        return group1;
    }

    public Group createText(int size){
        Group group = new Group();
        for (int y = -size; y <= size; y += size / 10) {

            Text text = new Text(""+y);
            text.setFont(new Font("Arial", 6));
            text.setFill(Color.BLACK);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(size);
            textGroup.setTranslateX(-size-size/8);
            textGroup.setTranslateZ(y);
            textGroup.setRotationAxis(Rotate.Y_AXIS);
            textGroup.setRotate(90);
            group.getChildren().add(textGroup);
        }
        for (int x = -size; x <= size; x += size / 10) {

            Text text = new Text(""+x);
            text.setFont(new Font("Arial", 6));
            text.setFill(Color.BLACK);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(x);
            textGroup.setTranslateX(-size-size/8);
            textGroup.setTranslateZ(size);
            group.getChildren().add(textGroup);
        }
        for (int z = -size; z <= size; z += size / 10) {

            Text text = new Text(""+z);
            text.setFont(new Font("Arial", 6));
            text.setFill(Color.BLACK);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(size);
            textGroup.setTranslateX(z);
            textGroup.setTranslateZ(-size-size/8);
            group.getChildren().add(textGroup);
        }
        return group;
    }

    public Group textToAllAxis(){
        Group group = new Group();
        group.getChildren().add(createText(100));
        group.getChildren().add(createText(100));
        group.getChildren().add(createText(100));
        return group;
    }

    public Group returnCube(){
        Group group = new Group();

        Group groupXY = createSetka(100);
        groupXY.setRotationAxis(Rotate.Y_AXIS);
        groupXY.setTranslateX(100);
        groupXY.setRotate(90);
        group.getChildren().add(groupXY);

        Group groupXZ = createSetka(100);
        groupXZ.setRotationAxis(Rotate.X_AXIS);
        groupXZ.setTranslateY(100);
        groupXZ.setRotate(90);
        group.getChildren().add(groupXZ);

        Group groupZY = createSetka(100);
        groupZY.setRotationAxis(Rotate.Z_AXIS);
        groupZY.setTranslateZ(100);
        groupZY.setRotate(90);
        group.getChildren().add(groupZY);

        return group;
    }

    @Override
    public void start(Stage stage) throws IOException {


        Plane plane = new Plane(new PointGraphRandom());


        Group group = plane.generatePlane();
        group.getChildren().add(returnCube());
        group.getChildren().add(textToAllAxis());

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