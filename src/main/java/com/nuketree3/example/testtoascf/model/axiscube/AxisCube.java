package com.nuketree3.example.testtoascf.model.axiscube;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class AxisCube {

    public Group returnCubeWithAxis(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax) {
        Group group = new Group();
        group.getChildren().add(returnCube(xMin, xMax, yMin, yMax, zMin, zMax));
        group.getChildren().add(textToAllAxis(xMin, xMax, yMin, yMax, zMin, zMax));
        return group;
    }


    //метод создает сетку для пары осей координат
    public Group createSetka(double minAxisOne, double maxAxisOne, double minAxisTwo, double maxAxisTwo) {
        double lineWidth = 1.0;
        Color gridColor = Color.WHITE;
        Group group1 = new Group();

        for (double y =  minAxisOne; y <= maxAxisOne; y += (Math.abs(maxAxisOne - minAxisOne)) / 10) {
            Line line = new Line(minAxisTwo, y, maxAxisTwo, y);
            line.setStroke(gridColor);
            line.setFill(gridColor);
            line.setStrokeWidth(lineWidth);

            group1.getChildren().add(line);
        }

        for (double x = minAxisTwo; x <= maxAxisTwo; x += (Math.abs(minAxisTwo - maxAxisTwo)) / 10) {
            Line line = new Line(x, minAxisOne, x, maxAxisOne);
            line.setStroke(gridColor);
            line.setFill(gridColor);
            line.setStrokeWidth(lineWidth);

            group1.getChildren().add(line);

        }
        return group1;
    }

    public Group createText(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax){
        Group group = new Group();

        //yAxis
        double tempABS = ((Math.abs(yMax - yMin)) / 10);
        for (double y = yMin; y <= yMax; y += tempABS) {

            Text text = new Text(""+(int)y);
            text.setFont(new Font("Arial", tempABS/2));
            text.setFill(Color.BLACK);
            text.setRotationAxis(Rotate.X_AXIS);
            text.setRotate(180);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(y);
            textGroup.setTranslateX(xMin - (int) tempABS);
            textGroup.setTranslateZ(zMax);
            group.getChildren().add(textGroup);
        }

        //xAxis
        tempABS = (Math.abs(xMax - xMin)) / 10;
        for (double x = xMin; x <= xMax; x += tempABS) {

            Text text = new Text(""+(int)x);
            text.setFont(new Font("Arial", tempABS/3));
            text.setFill(Color.BLACK);
            text.setRotationAxis(Rotate.X_AXIS);
            text.setRotate(180);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(yMin+tempABS/6);
            textGroup.setTranslateX(x);
            textGroup.setTranslateZ(zMax);
            group.getChildren().add(textGroup);
        }

        //zAxis
        tempABS = (Math.abs(zMin - zMax)) / 10;
        for (double z = zMin; z <= zMax; z += tempABS ) {

            Text text = new Text(""+(int)z);
            text.setFont(new Font("Arial", tempABS/3));
            text.setFill(Color.BLACK);
            text.setRotationAxis(Rotate.Z_AXIS);
            text.setRotate(180);

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

    public Group textToAllAxis(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax){
        Group group = new Group();
        group.getChildren().add(createText(xMin, xMax, yMin, yMax, zMin, zMax));
        return group;
    }

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
