package com.nuketree3.example.testtoascf.view;

import com.nuketree3.example.testtoascf.model.graph.PointGraphAbstract;
import com.nuketree3.example.testtoascf.presenter.Presenter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class SettingWindowToPointGraph extends Application {

    Presenter presenter = new Presenter();

    boolean setka;
    boolean aproksimation;
    double yAxisCor;
    PointGraphAbstract typeOfPointGraph;
    //int typeOfServiceGraph;

    private double yMin;
    private double yMax;
    private double xMin;
    private double xMax;
    private double zMin;
    private double zMax;

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();


        ArrayList<String> fileList = presenter.getFileList();

        ArrayList<String> list = new ArrayList<>();
        list.addFirst("Загрузка из файла");

        for(PointGraphAbstract graph : presenter.getGraphs()) {
            //System.out.println(graph.getNameGraph());
            list.add(graph.getNameGraph());
        }

        Text text = new Text();
        text.setText("Модели");
        Slider slider = new Slider();
        slider.setVisible(false);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        //slider.setValue(50);
        slider.setLayoutX((double) Config.WIDTH/20);
        slider.setLayoutY((double) Config.HIGTH/2.5);
        text.setLayoutX((double) Config.WIDTH /20);
        text.setLayoutY((double) Config.HIGTH/10-5);

        //yAxisCor = slider.valueProperty().doubleValue();


        Button button = new Button("Accept");
        button.setOnAction(e -> {
            try {
                yAxisCor = slider.getValue();
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

        Text text1 = new Text();
        text1.setText("Отображение сетки");
        text1.setLayoutX((double) Config.WIDTH /20+20);
        text1.setLayoutY((double) Config.HIGTH/4.3);

        CheckBox cb2 = new CheckBox();
        cb2.selectedProperty().addListener(observable -> {aproksimation = cb2.isSelected();});
        cb2.setLayoutX((double) Config.WIDTH/20);
        cb2.setLayoutY((double) Config.HIGTH/3.5);

        Text text2 = new Text();
        text2.setText("Функция апроксимации");
        text2.setLayoutX((double) Config.WIDTH /20+20);
        text2.setLayoutY((double) Config.HIGTH/3.1);





        Text text3 = new Text();
        text3.setText("Файлы");
        text3.setVisible(false);
        text3.setLayoutX((double) Config.WIDTH - ((double) Config.WIDTH /3));
        text3.setLayoutY((double) Config.HIGTH/10-5);



        group.getChildren().add(button);
        group.getChildren().add(cb1);
        group.getChildren().add(cb2);
        group.getChildren().add(slider);
        group.getChildren().add(text);
        group.getChildren().add(text1);
        group.getChildren().add(text2);
        group.getChildren().add(text3);

        ChoiceBox<String> pointGraphChoiceBoxFile = new ChoiceBox<>(FXCollections.observableArrayList(fileList));
        pointGraphChoiceBoxFile.setVisible(false);
        pointGraphChoiceBoxFile.setLayoutX((double) Config.WIDTH - ((double) Config.WIDTH /3));
        pointGraphChoiceBoxFile.setLayoutY((double) Config.HIGTH /10);
        pointGraphChoiceBoxFile.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldV, newV) -> {
            System.out.println("old ))" + oldV);
            System.out.println("new++ " + newV);
            //PointGraphAbstract pointGraph = presenter.getPointGraph((Integer) newV);
            try {
                this.typeOfPointGraph = presenter.getPointGrahpFile(fileList.get((Integer) newV));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
//            this.xMin = pointGraph.getxMin();
//            this.xMax = pointGraph.getxMax();
//            this.yMin = pointGraph.getyMin();
//            this.yMax = pointGraph.getyMax();
//            this.zMin = pointGraph.getzMin();
//            this.zMax = pointGraph.getzMax();
        });

        ChoiceBox<String> pointGraphChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(list));
        pointGraphChoiceBox.setLayoutX((double) Config.WIDTH /20);
        pointGraphChoiceBox.setLayoutY((double) Config.HIGTH /10);
        pointGraphChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldV, newV) -> {
            System.out.println("old " + oldV);
            System.out.println("new " + newV);
            if(newV.equals(0)){
                pointGraphChoiceBoxFile.setVisible(true);
                text3.setVisible(true);
            }else {
                pointGraphChoiceBoxFile.setVisible(false);
                text3.setVisible(false);
                this.typeOfPointGraph = presenter.getGraphs().get((Integer) newV-1);

                this.xMin = typeOfPointGraph.getxMin();
                this.xMax = typeOfPointGraph.getxMax();
                this.yMin = typeOfPointGraph.getyMin();
                this.yMax = typeOfPointGraph.getyMax();
                this.zMin = typeOfPointGraph.getzMin();
                this.zMax = typeOfPointGraph.getzMax();
                slider.setMin(yMin);
                slider.setMax(yMax);
                slider.setVisible(true);
            }


            //PointGraphAbstract pointGraph = presenter.getPointGraph((Integer) newV);


            //this.typeOfServiceGraph = (int) newV;

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
        System.out.println(setka+" "+aproksimation+" "+yAxisCor+" "+typeOfPointGraph);
        HelloApplication application = new HelloApplication(typeOfPointGraph, setka, aproksimation);
        application.start(stage);

    }

    public void errorCreatePointGraph() throws IOException {

    }
}
