package com.nuketree3.example.testtoascf.model.axiscube;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class AxisCube {

    private static final String TEXT_STYLE = "Arial";
    private static final int TEXT_ROTATION_ANGLE = 180;
    private static final double LINE_WIDTH = 1.0;
    private static final int GRID_DIVISION = 10;

    /**
     * returnCubeWithAxis - основной метод, создающий объемную сетку с числовым обозначением
     * @param xMin - минимальное значение для оси x
     * @param xMax - максимальное значение для оси x
     * @param yMin - минимальное значение для оси y
     * @param yMax - максимальное значение для оси y
     * @param zMin - минимальное значение для оси z
     * @param zMax - максимальное значение для оси z
     * @return - Group, представляющий собой объемную сетку с числовыми обозначениями
     */
    public Group returnCubeWithAxis(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
        Group group = new Group();
        group.getChildren().add(returnCube(xMin, xMax, yMin, yMax, zMin, zMax));
        group.getChildren().add(createText(xMin, xMax, yMin, yMax, zMin, zMax));
        return group;
    }

    /**
     * createSetka - метод, который создает измерительную сетку для ...
     * @param minAxisOne - минимальное значение первой оси
     * @param maxAxisOne - максимальное значение первой оси
     * @param minAxisTwo - минимально значение второй оси
     * @param maxAxisTwo - максимальное значение второй оси
     * @return - Group, представляющий собой сетку по двум осям
     */
    public Group createSetka(double minAxisOne, double maxAxisOne, double minAxisTwo, double maxAxisTwo) {
        Color gridColor = Color.WHITE;
        Group group = new Group();

        for (double y =  minAxisOne; y <= maxAxisOne; y += (Math.abs(maxAxisOne - minAxisOne)) / GRID_DIVISION) {
            Line line = new Line(minAxisTwo, y, maxAxisTwo, y);
            line.setStroke(gridColor);
            line.setFill(gridColor);
            line.setStrokeWidth(LINE_WIDTH);

            group.getChildren().add(line);
        }

        for (double x = minAxisTwo; x <= maxAxisTwo; x += (Math.abs(minAxisTwo - maxAxisTwo)) / GRID_DIVISION) {
            Line line = new Line(x, minAxisOne, x, maxAxisOne);
            line.setStroke(gridColor);
            line.setFill(gridColor);
            line.setStrokeWidth(LINE_WIDTH);

            group.getChildren().add(line);

        }
        return group;
    }

    /**
     * createText - метод, который создает цифровое обозначение для сетки
     * @param xMin - минимальное значение для оси x
     * @param xMax - максимальное значение для оси x
     * @param yMin - минимальное значение для оси y
     * @param yMax - максимальное значение для оси y
     * @param zMin - минимальное значение для оси z
     * @param zMax - максимальное значение для оси z
     * @return - group, представляющее собой цифровое обозначение всех трех осей
     */
    public Group createText(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax){
        Group group = new Group();

        //yAxis
        double tempABS = ((Math.abs(yMax - yMin)) / GRID_DIVISION);
        for (double y = yMin; y <= yMax; y += tempABS) {

            Text text = new Text(""+(int)y);
            text.setFont(new Font(TEXT_STYLE, tempABS/2));
            text.setFill(Color.BLACK);
            text.setRotationAxis(Rotate.X_AXIS);
            text.setRotate(TEXT_ROTATION_ANGLE);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(y);
            textGroup.setTranslateX(xMin - (int) tempABS);
            textGroup.setTranslateZ(zMax);
            group.getChildren().add(textGroup);
        }

        //xAxis
        tempABS = (Math.abs(xMax - xMin)) / GRID_DIVISION;
        for (double x = xMin; x <= xMax; x += tempABS) {

            Text text = new Text(""+(int)x);
            text.setFont(new Font(TEXT_STYLE, tempABS/3));
            text.setFill(Color.BLACK);
            text.setRotationAxis(Rotate.X_AXIS);
            text.setRotate(TEXT_ROTATION_ANGLE);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(yMin+tempABS/6);
            textGroup.setTranslateX(x);
            textGroup.setTranslateZ(zMax);
            group.getChildren().add(textGroup);
        }

        //zAxis
        tempABS = (Math.abs(zMin - zMax)) / GRID_DIVISION;
        for (double z = zMin; z <= zMax; z += tempABS ) {

            Text text = new Text(""+(int)z);
            text.setFont(new Font(TEXT_STYLE, tempABS/3));
            text.setFill(Color.BLACK);
            text.setRotationAxis(Rotate.Z_AXIS);
            text.setRotate(TEXT_ROTATION_ANGLE);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(yMin+tempABS/6);
            textGroup.setTranslateX(zMax);
            textGroup.setTranslateZ(z);
            textGroup.setRotationAxis(Rotate.Y_AXIS);
            textGroup.setRotate(-90);
            group.getChildren().add(textGroup);
        }
        return group;
    }

//    public Group createTextForAxis(double firstAxisMin, double firstAxisMax, double secondAxisMin, double secondAxisMax){
//        double tempABS = ((Math.abs(secondAxisMax - secondAxisMin)) / GRID_DIVISION);
//        for (double y = secondAxisMin; y <= secondAxisMax; y += tempABS) {
//
//            Text text = new Text(""+(int)y);
//            text.setFont(new Font(TEXT_STYLE, tempABS/2));
//            text.setFill(Color.BLACK);
//            text.setRotationAxis(Rotate.X_AXIS);
//            text.setRotate(TEXT_ROTATION_ANGLE);
//
//            Group textGroup = new Group(text);
//            textGroup.setTranslateY(y);
//            textGroup.setTranslateX(firstAxisMin - (int) tempABS);
//            textGroup.setTranslateZ(zMax);
//            group.getChildren().add(textGroup);
//        }
//    }

//    public Group textToAllAxis(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax){
//        Group group = new Group();
//        group.getChildren().add(createText(xMin, xMax, yMin, yMax, zMin, zMax));
//        return group;
//    }

    /**
     * returnCube - метод, который создает полноценную объемную сетку
     * @param xMin - минимальное значение для оси x
     * @param xMax - максимальное значение для оси x
     * @param yMin - минимальное значение для оси y
     * @param yMax - максимальное значение для оси y
     * @param zMin - минимальное значение для оси z
     * @param zMax - максимальное значение для оси z
     * @return - Group, представляющий собой объемную трехмерную измерительную сетку.
     */
    public Group returnCube(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax){
        Group group = new Group();

        Group groupYZ = createSetka(yMin, yMax, zMin, zMax);
        groupYZ.setRotationAxis(Rotate.Y_AXIS);
        groupYZ.setRotate(90);
        groupYZ.setTranslateX(-(zMax - zMin)/2);
        groupYZ.setTranslateZ(Math.abs(zMax + zMin)/2);
        group.getChildren().add(groupYZ);

        Group groupXZ = createSetka(zMin, zMax, xMin, xMax);
        groupXZ.setRotationAxis(Rotate.X_AXIS);
        groupXZ.setRotate(90);
        groupXZ.setTranslateZ(Math.abs(zMax + zMin)/2);
        groupXZ.setTranslateY(yMin-Math.abs(zMax + zMin)/2);
        group.getChildren().add(groupXZ);

        Group groupXY = createSetka(yMin, yMax, xMin, xMax);
        groupXY.setTranslateZ(zMin);
        group.getChildren().add(groupXY);


        return group;
    }
}
