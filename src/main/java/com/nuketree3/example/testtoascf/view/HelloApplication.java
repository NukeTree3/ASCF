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

public class HelloApplication extends Application {
    private final PointGraphAbstract pointGraph;
    private Presenter presenter;

    private final boolean setka;
    private final boolean aproksimation;

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

    public HelloApplication(PointGraphAbstract graph, boolean setka, boolean aproksimation, int smoothMedianParametr, double xParameter, double yParameter, double zParameter) {
        this.pointGraph = graph;
        this.setka = setka;
        this.aproksimation = aproksimation;
        this.smoothMedianParametr = smoothMedianParametr;
        this.xParameter = xParameter;
        this.yParameter = yParameter;
        this.zParameter = zParameter;
        presenter = new Presenter();
    }

    @Override
    public void start(Stage stage) throws IOException {

        presenter.get3dGraph(pointGraph, xParameter, yParameter, zParameter);


        Group group;


        if(aproksimation) {
            group = presenter.get3dGraphWithSmoothMedian(pointGraph, smoothMedianParametr, xParameter, yParameter, zParameter);
        }else {
            group = presenter.get3dGraph(pointGraph, xParameter, yParameter, zParameter);
        }

        if(setka) {
            System.out.println(pointGraph.getxMin()*xParameter +" "+ pointGraph.getxMax()*xParameter +" "+ pointGraph.getyMin()*yParameter +" "+ pointGraph.getyMax()*yParameter +" "+ pointGraph.getzMin()*zParameter +" "+ pointGraph.getzMax()*zParameter);
            group.getChildren().add(presenter.getAxisCube(pointGraph.getxMin()*xParameter, pointGraph.getxMax()*xParameter, pointGraph.getyMin()*yParameter, pointGraph.getyMax()*yParameter, pointGraph.getzMin()*zParameter, pointGraph.getzMax()*zParameter));
        }


        Camera camera = new PerspectiveCamera(true);
        Scene scene = new Scene(group, Config.WIDTH, Config.HIGTH, true,  SceneAntialiasing.BALANCED);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        camera.setRotate(180);

        camera.translateXProperty().set(0);
        camera.translateYProperty().set(0);
        camera.translateZProperty().set(-500);

        camera.setNearClip(1);
        camera.setFarClip(100000);

        initMouseControl(group, scene, stage);



        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case R:
                    camera.translateZProperty().set(camera.getTranslateZ() - 5);
                    break;
                case F:
                    camera.translateZProperty().set(camera.getTranslateZ() + 5);
                    break;
                case D:
                    camera.translateXProperty().set(camera.getTranslateX() - 5);
                    break;
                case A:
                    camera.translateXProperty().set(camera.getTranslateX() + 5);
                    break;
                case W:
                    camera.translateYProperty().set(camera.getTranslateY() + 5);
                    break;
                case S:
                    camera.translateYProperty().set(camera.getTranslateY() - 5);
                    break;
                case Q:
                    camera.rotateProperty().set(camera.getRotate()+5);
                    break;
                case E:
                    camera.rotateProperty().set(camera.getRotate()-5);
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