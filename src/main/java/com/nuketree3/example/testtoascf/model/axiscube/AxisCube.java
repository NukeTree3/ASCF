package com.nuketree3.example.testtoascf.model.axiscube;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class AxisCube {

    public Group returnCubeWithAxis(int xMin, int xMax, double yMin, double yMax, int zMin, int zMax) {
        Group group = new Group();
        group.getChildren().add(returnCube(xMin, xMax, yMin, yMax, zMin, zMax));
        group.getChildren().add(textToAllAxis(xMin, xMax, yMin, yMax, zMin, zMax));
        return group;
    }

    public Group createSetka(double minAxisOne, double maxAxisOne, double minAxisTwo, double maxAxisTwo) {
        double zTranslate = 0;
        double lineWidth = 1.0;
        Color gridColor = Color.WHITE;
        Group group1 = new Group();

        for (double y =  minAxisOne; y <= maxAxisOne; y += (Math.abs(minAxisOne) + Math.abs(maxAxisOne)) / 10) {
            Line line = new Line(minAxisTwo, 0, maxAxisTwo, 0);

            line.setStroke(gridColor);
            line.setFill(gridColor);
            line.setTranslateY(y);
            line.setTranslateZ(zTranslate);
            line.setStrokeWidth(lineWidth);

            group1.getChildren().add(line);
        }

        for (double x = minAxisTwo; x <= maxAxisTwo; x += (Math.abs(minAxisTwo) + Math.abs(maxAxisTwo)) / 10) {
            Line line = new Line(0, minAxisOne, 0, maxAxisOne);
            line.setStroke(gridColor);
            line.setFill(gridColor);
            line.setTranslateX(x);
            line.setTranslateZ(zTranslate);
            line.setStrokeWidth(lineWidth);

            group1.getChildren().add(line);

        }
        return group1;
    }

    public Group createText(int xMin, int xMax, double yMin, double yMax, int zMin, int zMax){
        Group group = new Group();

        double tempABS = ((Math.abs(yMax) + Math.abs(yMin)) / 10);
        for (double y = yMin; y <= yMax; y += tempABS) {

            Text text = new Text(""+(int)y);
            text.setFont(new Font("Arial", tempABS/2));
            text.setFill(Color.BLACK);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(y);
            textGroup.setTranslateX(-xMax - (int) tempABS);
            textGroup.setTranslateZ(zMax);
            group.getChildren().add(textGroup);
        }

        tempABS = (double) (Math.abs(xMax) + Math.abs(xMin)) / 10;
        for (double x = xMin; x <= xMax; x += tempABS) {

            Text text = new Text(""+(int)x);
            text.setFont(new Font("Arial", tempABS/3));
            text.setFill(Color.BLACK);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(yMax+tempABS/6);
            textGroup.setTranslateX(x);
            textGroup.setTranslateZ(-zMax - tempABS /10);
            group.getChildren().add(textGroup);
        }

        tempABS = (double) (Math.abs(zMin) + Math.abs(zMax)) / 10;
        for (double z = zMin; z <= zMax; z += tempABS ) {

            Text text = new Text(""+(int)z);
            text.setFont(new Font("Arial", tempABS/3));
            text.setFill(Color.BLACK);

            Group textGroup = new Group(text);
            textGroup.setTranslateY(yMax+tempABS/6);
            textGroup.setTranslateX(-xMax - tempABS / 10);
            textGroup.setTranslateZ(z);
            textGroup.setRotationAxis(Rotate.Y_AXIS);
            textGroup.setRotate(90);
            group.getChildren().add(textGroup);
        }
        return group;
    }

    public Group textToAllAxis(int xMin, int xMax, double yMin, double yMax, int zMin, int zMax){
        Group group = new Group();
        group.getChildren().add(createText(xMin, xMax, yMin, yMax, zMin, zMax));
        return group;
    }

    public Group returnCube(int xMin, int xMax, double yMin, double yMax, int zMin, int zMax){
        Group group = new Group();

        Group groupXY = createSetka(yMin, yMax, zMin, zMax);
        groupXY.setRotationAxis(Rotate.Y_AXIS);
        groupXY.setRotate(90);
        groupXY.setTranslateX((Math.abs(xMin)+Math.abs(xMax))/2);
        group.getChildren().add(groupXY);

        Group groupXZ = createSetka(zMin, zMax, xMin, xMax);
        groupXZ.setRotationAxis(Rotate.X_AXIS);
        groupXZ.setTranslateY((Math.abs(yMin)+Math.abs(yMax))/2);
        groupXZ.setRotate(90);
        group.getChildren().add(groupXZ);

        Group groupZY = createSetka(xMin, xMax, yMin, yMax);
        groupZY.setRotationAxis(Rotate.Z_AXIS);
        groupZY.setTranslateZ((Math.abs(zMin)+Math.abs(zMin))/2);
        groupZY.setRotate(90);
        group.getChildren().add(groupZY);

        return group;
    }
}
