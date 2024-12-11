package com.nuketree3.example.testtoascf.view;

import com.nuketree3.example.testtoascf.model.emuns.PointGraphType;
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

    private Presenter presenter;

    private boolean setka;

    private PointGraphAbstract typeOfPointGraph;

    private int smoothMedianParametr;
    private double xParametr = 1;
    private double yParametr = 1;
    private double zParametr = 1;

    private String errorMessage;

    private double yMin;
    private double yMax;
    private double xMin;
    private double xMax;
    private double zMin;
    private double zMax;

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        presenter = new Presenter();


        ArrayList<String> fileList = presenter.getFileList();
        fileList.add("...");

        ArrayList<String> list = new ArrayList<>();
        list.addFirst("Загрузка из файла");

        for(PointGraphType graph : presenter.getGraphs()) {
            list.add(String.valueOf(graph));
        }

        TextField xAxisTextField = axisTextField((double) Config.WIDTH/20,(double) Config.HIGH /2);
        TextField yAxisTextField = axisTextField((double) Config.WIDTH/20+200,(double) Config.HIGH /2);
        TextField zAxisTextField = axisTextField((double) Config.WIDTH/20+400,(double) Config.HIGH /2);

        Text xAxisText = axisText((double) Config.WIDTH/20,(double) Config.HIGH /2-10,0.0);
        Text yAxisText = axisText((double) Config.WIDTH/20+200,(double) Config.HIGH /2-10,0.0);
        Text zAxisText = axisText((double) Config.WIDTH/20+400,(double) Config.HIGH /2-10,0.0);

        xAxisTextField.textProperty().addListener((observable) -> {
            try {
                xParametr = Double.parseDouble(xAxisTextField.getText());
            } catch (NumberFormatException e) {
                xParametr = 1;
            }
            System.out.println(xMax + " " + xMin);
            xAxisText.setText((Math.abs(xMax)+Math.abs(xMin))*xParametr+"");
        });
        yAxisTextField.textProperty().addListener((observable) -> {
            try {
                yParametr = Double.parseDouble(yAxisTextField.getText());
            } catch (NumberFormatException e) {
                yParametr = 1;
            }
            yAxisText.setText((Math.abs(yMax)+Math.abs(yMin))*yParametr+"");
        });
        zAxisTextField.textProperty().addListener((observable) -> {
            try {
                zParametr = Double.parseDouble(zAxisTextField.getText());
            } catch (NumberFormatException e) {
                zParametr = 1;
            }
            zAxisText.setText((Math.abs(zMax)+Math.abs(zMin))*zParametr+"");
        });




        Text modelText = new Text();
        modelText.setText("Модели");
        modelText.setLayoutX((double) Config.WIDTH /20);
        modelText.setLayoutY((double) Config.HIGH /10-5);


        Text axisSetkaText = new Text();
        axisSetkaText.setText("Отображение сетки");
        axisSetkaText.setLayoutX((double) Config.WIDTH /20+20);
        axisSetkaText.setLayoutY((double) Config.HIGH /4.3);

        Text medianParametrText = new Text();
        medianParametrText.setLayoutX((double) Config.WIDTH /20+170);
        medianParametrText.setLayoutY((double) Config.HIGH /3.1);

        Slider medianParametrSlider = new Slider();
        medianParametrSlider.setVisible(false);
        medianParametrSlider.setMin(1);
        medianParametrSlider.setMax(50);
        medianParametrSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            smoothMedianParametr = (int) Math.ceil((Double) newValue);
            medianParametrText.setText(String.valueOf(smoothMedianParametr));
        });
        medianParametrSlider.setLayoutX((double) Config.WIDTH/20+200);
        medianParametrSlider.setLayoutY((double) Config.HIGH /3.5);


        Text aproksimatonFText = new Text();
        aproksimatonFText.setText("Функция апроксимации");
        aproksimatonFText.setLayoutX((double) Config.WIDTH /20+20);
        aproksimatonFText.setLayoutY((double) Config.HIGH /3.1);

        Text axisParametrChanges = new Text();
        axisParametrChanges.setText("Изменение параметров оссей");
        axisParametrChanges.setLayoutX((double) Config.WIDTH /20+20);
        axisParametrChanges.setLayoutY((double) Config.HIGH /2.45);



        CheckBox axisSetkaCheckBox = new CheckBox();
        axisSetkaCheckBox.selectedProperty().addListener(observable -> {setka = axisSetkaCheckBox.isSelected();});
        axisSetkaCheckBox.setLayoutX((double) Config.WIDTH/20);
        axisSetkaCheckBox.setLayoutY((double) Config.HIGH /5);

        CheckBox aproksimationFCheckBox = new CheckBox();
        aproksimationFCheckBox.selectedProperty().addListener(observable -> {
            medianParametrSlider.setVisible(aproksimationFCheckBox.isSelected());
            medianParametrText.setText("");
            if(!aproksimationFCheckBox.isSelected()) {
                smoothMedianParametr = 1;
            }

        });
        aproksimationFCheckBox.setLayoutX((double) Config.WIDTH/20);
        aproksimationFCheckBox.setLayoutY((double) Config.HIGH /3.5);


        CheckBox axisParametrChangesCheckBox = new CheckBox();
        axisParametrChangesCheckBox.selectedProperty().addListener(observable -> {
            xAxisText.setVisible(axisParametrChangesCheckBox.isSelected());
            yAxisText.setVisible(axisParametrChangesCheckBox.isSelected());
            zAxisText.setVisible(axisParametrChangesCheckBox.isSelected());

            xAxisTextField.setVisible(axisParametrChangesCheckBox.isSelected());
            yAxisTextField.setVisible(axisParametrChangesCheckBox.isSelected());
            zAxisTextField.setVisible(axisParametrChangesCheckBox.isSelected());
        });
        axisParametrChangesCheckBox.setLayoutX((double) Config.WIDTH/20);
        axisParametrChangesCheckBox.setLayoutY((double) Config.HIGH /2.7);






        Text fileText = new Text();
        fileText.setText("Файлы");
        fileText.setVisible(false);
        fileText.setLayoutX((double) Config.WIDTH - ((double) Config.WIDTH /3));
        fileText.setLayoutY((double) Config.HIGH /10-5);


        Text errorText = new Text();
        errorText.setLayoutX((double) Config.WIDTH - ((double) Config.WIDTH /4));
        errorText.setLayoutY((double) Config.HIGH /1.4);
        errorText.setFill(Color.RED);
        errorText.setVisible(false);
        errorText.setText("Ошибка компиляции модели");

        Button button = new Button("Готово");
        button.setOnAction(e -> {
            try {
                if(errorMessage==null){
                    System.out.println(xAxisTextField.getText()+ " "+ yAxisTextField.getText() + " " + zAxisTextField.getText());
                }
                change(stage);
            } catch (Exception ex) {
                try {
                    errorCreatePointGraph(errorText);
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
            }
        });
        button.setLayoutX((double) Config.WIDTH - ((double) Config.WIDTH /6));
        button.setLayoutY((double) Config.HIGH /1.3);





        group.getChildren().add(button);
        group.getChildren().add(axisSetkaCheckBox);
        group.getChildren().add(aproksimationFCheckBox);
        group.getChildren().add(axisParametrChangesCheckBox);


        group.getChildren().add(xAxisTextField);
        group.getChildren().add(yAxisTextField);
        group.getChildren().add(zAxisTextField);

        group.getChildren().add(xAxisText);
        group.getChildren().add(yAxisText);
        group.getChildren().add(zAxisText);


        group.getChildren().add(medianParametrSlider);

        group.getChildren().add(modelText);
        group.getChildren().add(axisSetkaText);
        group.getChildren().add(aproksimatonFText);
        group.getChildren().add(fileText);
        group.getChildren().add(axisParametrChanges);
        group.getChildren().add(medianParametrText);
        group.getChildren().add(errorText);

        ChoiceBox<String> pointGraphChoiceBoxFile = new ChoiceBox<>(FXCollections.observableArrayList(fileList));
        pointGraphChoiceBoxFile.setVisible(false);
        pointGraphChoiceBoxFile.setLayoutX((double) Config.WIDTH - ((double) Config.WIDTH /3));
        pointGraphChoiceBoxFile.setLayoutY((double) Config.HIGH /10);

        pointGraphChoiceBoxFile.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldV, newV) -> {


            if(newV.equals(fileList.size()-1)){
                xAxisText.setText("0.0");
                yAxisText.setText("0.0");
                zAxisText.setText("0.0");
                typeOfPointGraph = null;
            }else {
                try {
                    this.typeOfPointGraph = presenter.getPointGrahpFile(fileList.get((Integer) newV));

                    this.xMin = typeOfPointGraph.getXMin();
                    this.xMax = typeOfPointGraph.getXMax();
                    this.yMin = typeOfPointGraph.getYMin();
                    this.yMax = typeOfPointGraph.getYMax();
                    this.zMin = typeOfPointGraph.getZMin();
                    this.zMax = typeOfPointGraph.getZMax();

                    xAxisText.setText(Math.abs(xMax)+Math.abs(xMin)+"");
                    yAxisText.setText(Math.abs(yMax)+Math.abs(yMin)+"");
                    zAxisText.setText(Math.abs(zMax)+Math.abs(zMin)+"");


                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        ChoiceBox<String> pointGraphChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(list));
        pointGraphChoiceBox.setLayoutX((double) Config.WIDTH /20);
        pointGraphChoiceBox.setLayoutY((double) Config.HIGH /10);
        pointGraphChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observableValue, oldV, newV) -> {
            if(newV.equals(0)){

                pointGraphChoiceBoxFile.setVisible(true);
                pointGraphChoiceBoxFile.setValue(fileList.getLast());
                fileText.setVisible(true);
                typeOfPointGraph = null;
                xAxisText.setText("0.0");
                yAxisText.setText("0.0");
                zAxisText.setText("0.0");

            }else {

                pointGraphChoiceBoxFile.setVisible(false);
                fileText.setVisible(false);
                this.typeOfPointGraph = presenter.getGraph(presenter.getGraphs().get((Integer) newV-1));

                this.xMin = typeOfPointGraph.getXMin();
                this.xMax = typeOfPointGraph.getXMax();
                this.yMin = typeOfPointGraph.getYMin();
                this.yMax = typeOfPointGraph.getYMax();
                this.zMin = typeOfPointGraph.getZMin();
                this.zMax = typeOfPointGraph.getZMax();

                xAxisText.setText(Math.abs(xMax)+Math.abs(xMin)+"");
                yAxisText.setText(Math.abs(yMax)+Math.abs(yMin)+"");
                zAxisText.setText(Math.abs(zMax)+Math.abs(zMin)+"");

            }
        });

        group.getChildren().addAll(pointGraphChoiceBox);
        group.getChildren().addAll(pointGraphChoiceBoxFile);


        Scene scene = new Scene(group, Config.WIDTH, Config.HIGH, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.SILVER);


        stage.setTitle("Настройки");
        stage.setScene(scene);
        stage.show();
    }

    public void change(Stage stage) throws IOException {
        WorkWindow application = new WorkWindow(typeOfPointGraph, setka,smoothMedianParametr,xParametr,yParametr,zParametr);
        System.out.println(xMin+" "+xMax+" "+xParametr+" "+yMin+" "+yMax+" "+yParametr+" "+zMin+" "+zMax+" "+yParametr);
        application.start(stage);

    }

    public void errorCreatePointGraph(Text errorText) throws IOException {
        errorText.setVisible(true);

    }

    private TextField axisTextField(double xPositon, double yPositon) {
        TextField textField = new TextField();
        textField.setLayoutX(xPositon);
        textField.setLayoutY(yPositon);
        textField.setVisible(false);
        return textField;
    }

    private Text axisText(double xPositon, double yPositon, double value) {
        Text axisText = new Text();
        axisText.setLayoutX(xPositon);
        axisText.setLayoutY(yPositon);
        axisText.setText(value+"");
        axisText.setVisible(false);
        return axisText;
    }

    private Text simpleText(String text, double xPosition, double yPosition) {
        Text modelText = new Text();
        modelText.setText(text);
        modelText.setLayoutX(xPosition);
        modelText.setLayoutY(yPosition);
        return modelText;
    }
}