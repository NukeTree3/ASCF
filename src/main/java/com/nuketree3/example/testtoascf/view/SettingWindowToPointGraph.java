package com.nuketree3.example.testtoascf.view;

import com.nuketree3.example.testtoascf.presenter.Presenter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SettingWindowToPointGraph extends Application {

    boolean setka;
    boolean aproksimation;
    double yAxisCor;
    int typeOfPointGraph;
    int typeOfServiceGraph;


    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Presenter presenter = new Presenter();

        ArrayList<String> fileList = presenter.getFileList();

        ArrayList<String> list = new ArrayList<>();
        list.add("PointGraph");
        list.add("PointGraphAbstract");
        list.add("PointGraphOneWaves");
        list.add("PointGraphRandom");
        list.add("PointGraphSaddle");
        list.add("PointGraphSambrero");
        list.add("PointGraphTable");
        list.add("PointGraphUnknow");
        list.add("PointGraphWaves");


        Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(50);
        slider.setLayoutX((double) Config.WIDTH/20);
        slider.setLayoutY((double) Config.HIGTH/2.5);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        yAxisCor = slider.valueProperty().doubleValue();


        Button button = new Button("Accept");
        button.setOnAction(e -> {
            try {
                change(stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button.setLayoutX((double) Config.WIDTH - ((double) Config.WIDTH /6));
        button.setLayoutY((double) Config.HIGTH /1.3);


        CheckBox cb1 = new CheckBox();
        cb1.selectedProperty().addListener(observable -> {setka = cb1.isSelected();});
        cb1.setLayoutX((double) Config.WIDTH/20);
        cb1.setLayoutY((double) Config.HIGTH/5);

        CheckBox cb2 = new CheckBox();
        cb2.selectedProperty().addListener(observable -> {aproksimation = cb2.isSelected();});
        cb2.setLayoutX((double) Config.WIDTH/20);
        cb2.setLayoutY((double) Config.HIGTH/3.5);


        group.getChildren().add(button);
        group.getChildren().add(cb1);
        group.getChildren().add(cb2);
        group.getChildren().add(slider);



        ChoiceBox<String> pointGraphChoiceBoxFile = new ChoiceBox<>(FXCollections.observableArrayList(fileList));
        pointGraphChoiceBoxFile.setVisible(false);
        pointGraphChoiceBoxFile.setLayoutX((double) Config.WIDTH - ((double) Config.WIDTH /3));
        pointGraphChoiceBoxFile.setLayoutY((double) Config.HIGTH /10);
        pointGraphChoiceBoxFile.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldV, newV) -> {
            System.out.println("old ))" + oldV);
            System.out.println("new++ " + newV);
            this.typeOfServiceGraph = (int) newV;
        });

        ChoiceBox<String> pointGraphChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(list));
        pointGraphChoiceBox.setLayoutX((double) Config.WIDTH /20);
        pointGraphChoiceBox.setLayoutY((double) Config.HIGTH /10);
        pointGraphChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldV, newV) -> {
            System.out.println("old " + oldV);
            System.out.println("new " + newV);
            if(newV.equals(0)){
                pointGraphChoiceBoxFile.setVisible(true);
            }else {
                pointGraphChoiceBoxFile.setVisible(false);
            }
            this.typeOfPointGraph = (int) newV;
        });

        group.getChildren().addAll(pointGraphChoiceBox);
        group.getChildren().addAll(pointGraphChoiceBoxFile);


        Scene scene = new Scene(group, Config.WIDTH, Config.HIGTH, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.SILVER);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void change(Stage stage) throws IOException {
        System.out.println(setka+" "+aproksimation+" "+yAxisCor+" "+typeOfPointGraph+" "+typeOfServiceGraph);
//        HelloApplication application = new HelloApplication();
//        application.start(stage);

    }

    public void errorCreatePointGraph() throws IOException {

    }
}
