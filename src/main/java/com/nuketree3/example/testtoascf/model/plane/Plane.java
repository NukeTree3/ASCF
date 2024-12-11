package com.nuketree3.example.testtoascf.model.plane;

import com.nuketree3.example.testtoascf.model.graph.PointGraphAbstract;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Plane {
    private PointGraphAbstract graph;

    private static final double SATURATION = 1.0;
    private static final double BRIGHTNESS = 1.0;


    public Plane(PointGraphAbstract graph) {
        this.graph = graph;
    }

    /**
     * generatePlane - метод, который создает Group множество точек графа
     *
     * @param smoothMedianParametr - параметр аппроксимации
     * @param xParametr - параметр растяжения для оси x
     * @param yParametr - параметр растяжения для оси y
     * @param zParametr - параметр растяжения для оси z
     * @return - Group, содержащий все точки графа
     */
    public Group generatePlane(int smoothMedianParametr, double xParametr, double yParametr, double zParametr) {

        Group root = new Group();

        if (yParametr <= 0) {
            yParametr = 1;
        }

        if (xParametr <= 0) {
            xParametr = 1;
        }

        if (zParametr <= 0) {
            zParametr = 1;
        }

        int xIndex = 0;
        for (int x = graph.getXMin(); x < graph.getXMax(); x++) {
            int zIndex = 0;
            for (int z = graph.getZMin(); z < graph.getZMax(); z++) {

                Box box = new Box();
                box.translateXProperty().set(x * xParametr);
                box.translateZProperty().set(z * zParametr);

                List<Double> values = new ArrayList<>();
                for (int k = Math.max(0, xIndex - smoothMedianParametr / 2); k <= Math.min(Math.abs(graph.getXMax()) + Math.abs(graph.getXMin()) - 1, xIndex + smoothMedianParametr / 2); k++) {
                    for (int l = Math.max(0, zIndex - smoothMedianParametr / 2); l <= Math.min(Math.abs(graph.getZMax()) + Math.abs(graph.getZMin()) - 1, zIndex + smoothMedianParametr / 2); l++) {
                        values.add(graph.getCoordinates()[k][l] * yParametr);
                    }
                }
                Collections.sort(values);

                // вычисление оттенка в зависимости от высоты
                double hue;
                double y = values.get(values.size() / 2);
                if (y < 0) {
                    // если значение y < 0, то в зависимости от значения y присваивает значение hue от 150 до 240
                    hue = (90.0 * (1.0 - Math.exp(0.03 * y))) + 150;
                } else if (y == 0) {
                    hue = 150;
                } else {
                    // если значение y > 0, то в зависимости от значения y присваивает значение hue от 0 до 150
                    hue = (150 * Math.exp(-0.04 * y));
                }

                box.translateYProperty().set(y);
                Color color = Color.hsb(hue, SATURATION, BRIGHTNESS);
                box.setMaterial(new PhongMaterial(color));
                root.getChildren().add(box);
                zIndex++;
            }
            xIndex++;
        }
        return root;
    }
}
