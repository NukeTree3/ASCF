package com.nuketree3.example.testtoascf.view;

import com.nuketree3.example.testtoascf.model.graph.*;

import com.nuketree3.example.testtoascf.presenter.Presenter;
import javafx.application.Application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import javafx.stage.Stage;

import java.io.IOException;

public class WorkWindow extends Application {
    private final PointGraphAbstract pointGraph;
    private Presenter presenter;

    private final boolean setka;

    private final double OFFSET = 5.0;
    private final double ROTATION = 5.0;
    private final double ZOOM = 5.0;

    private final double FAR_CLIP = 100000.0;

    private final double CAMERA_X_START_POSITION = 0.0;
    private final double CAMERA_Y_START_POSITION = 0.0;
    private final double CAMERA_Z_START_POSITION = - 500.0;

    private double anchorX;
    private double anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;

    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private int smoothMedianParametr = 1;

    private final double yParameter;
    private final double xParameter;
    private final double zParameter;

    public WorkWindow(PointGraphAbstract graph, boolean setka, int smoothMedianParametr, double xParameter, double yParameter, double zParameter) {
        this.pointGraph = graph;
        this.setka = setka;
        this.smoothMedianParametr = smoothMedianParametr;
        this.xParameter = xParameter;
        this.yParameter = yParameter;
        this.zParameter = zParameter;
        presenter = new Presenter();
    }

    @Override
    public void start(Stage stage) throws IOException {

        Group group;

        group = presenter.get3dGraph(smoothMedianParametr, pointGraph, xParameter, yParameter, zParameter);

        if(setka) {
            System.out.println(pointGraph.getXMin()*xParameter +" "+ pointGraph.getXMax()*xParameter +" "+ pointGraph.getYMin()*yParameter +" "+ pointGraph.getYMax()*yParameter +" "+ pointGraph.getZMin()*zParameter +" "+ pointGraph.getZMax()*zParameter);
            group.getChildren().add(presenter.getAxisCube(pointGraph.getXMin()*xParameter, pointGraph.getXMax()*xParameter, pointGraph.getYMin()*yParameter, pointGraph.getYMax()*yParameter, pointGraph.getZMin()*zParameter, pointGraph.getZMax()*zParameter));
        }


        Camera camera = new PerspectiveCamera(true);
        Scene scene = new Scene(group, Config.WIDTH, Config.HIGH, true,  SceneAntialiasing.BALANCED);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        camera.setRotate(180);

        camera.translateXProperty().set(CAMERA_X_START_POSITION);
        camera.translateYProperty().set(CAMERA_Y_START_POSITION);
        camera.translateZProperty().set(CAMERA_Z_START_POSITION);

        camera.setNearClip(1);
        camera.setFarClip(FAR_CLIP);

        initMouseControl(group, scene, stage);



        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case R:
                    camera.translateZProperty().set(camera.getTranslateZ() - ZOOM);
                    break;
                case F:
                    camera.translateZProperty().set(camera.getTranslateZ() + ZOOM);
                    break;
                case D:
                    camera.translateXProperty().set(camera.getTranslateX() - OFFSET);
                    break;
                case A:
                    camera.translateXProperty().set(camera.getTranslateX() + OFFSET);
                    break;
                case W:
                    camera.translateYProperty().set(camera.getTranslateY() + OFFSET);
                    break;
                case S:
                    camera.translateYProperty().set(camera.getTranslateY() - OFFSET);
                    break;
                case Q:
                    camera.rotateProperty().set(camera.getRotate()+ROTATION);
                    break;
                case E:
                    camera.rotateProperty().set(camera.getRotate()-ROTATION);
                    break;
                case M:
                    SettingWindowToPointGraph  settingWindowToPointGraph = new SettingWindowToPointGraph();
                    try {
                        settingWindowToPointGraph.start(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

            }
        });

        stage.setTitle(pointGraph.getNameGraph());
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
            angleX.set(anchorAngleX + (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY - (anchorX - event.getSceneX()));
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double movemetn = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - movemetn);
        });
    }
}