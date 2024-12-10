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

    public Plane(PointGraphAbstract graph) {
        this.graph = graph;
    }

    public Group generatePlane(double xParametr,double yParametr,double zParametr) {
        System.out.println("Generating plane...");

        if(yParametr <= 0){
            yParametr = 1;
        }

        if(xParametr <= 0){
            xParametr = 1;
        }

        if(zParametr <= 0){
            zParametr = 1;
        }
        
        Group root = new Group();
        int xIndex = 0;
        for (int x = graph.getxMin(); x < graph.getxMax(); x++) {
            int zIndex = 0;
            for (int z = graph.getzMin(); z < graph.getzMax(); z++) {
                Box box = new Box();
                box.translateXProperty().set(x*xParametr);
                box.translateZProperty().set(z*zParametr);
                double hue;
                double y = graph.getCoordinates()[xIndex][zIndex]*yParametr;
                if(y<0){
                    hue = (90.0 * (1.0 - Math.exp(0.03 * y)))+150;
                }else if(y==0){
                    hue = 150;
                }
                else {
                    hue = (150 * Math.exp(-0.04 * y));
                }
                box.translateYProperty().set(y);
                Color color = Color.hsb(hue, 1.0, 1.0);
                box.setMaterial(new PhongMaterial(color));
                root.getChildren().add(box);
                zIndex++;
            }
            xIndex++;

        }
        System.out.println("Plane generated.");
        return root;
    }


    public Group getPlaneWithSmoothMedian(int smoothMedianParametr, double xParametr,double yParametr,double zParametr) {

        Group root = new Group();

        if(yParametr <= 0){
            yParametr = 1;
        }

        if(xParametr <= 0){
            xParametr = 1;
        }

        if(zParametr <= 0){
            zParametr = 1;
        }

        int xIndex = 0;
        for (int x = graph.getxMin(); x < graph.getxMax(); x++) {
            int zIndex = 0;
            for (int z = graph.getzMin(); z < graph.getzMax(); z++) {

                Box box = new Box();
                box.translateXProperty().set(x*xParametr);
                box.translateZProperty().set(z*zParametr);

                List<Double> values = new ArrayList<>();
                for (int k = Math.max(0, xIndex - smoothMedianParametr / 2); k <= Math.min(Math.abs(graph.getxMax())+Math.abs(graph.getxMin()) - 1, xIndex + smoothMedianParametr / 2); k++) {
                    for (int l = Math.max(0, zIndex - smoothMedianParametr / 2); l <= Math.min(Math.abs(graph.getzMax())+Math.abs(graph.getzMin()) - 1, zIndex + smoothMedianParametr / 2); l++) {
                        values.add(graph.getCoordinates()[k][l]*yParametr);
                    }
                }
                Collections.sort(values);

                double hue;
                double y =  values.get(values.size() / 2);
                if(y<0){
                    hue = (90.0 * (1.0 - Math.exp(0.03 * y)))+150;
                }else if(y==0){
                    hue = 150;
                }
                else {
                    hue = (150 * Math.exp(-0.04 * y));
                }
                box.translateYProperty().set(y);
                Color color = Color.hsb(hue, 1.0, 1.0);
                box.setMaterial(new PhongMaterial(color));
                root.getChildren().add(box);
                zIndex++;
            }
            xIndex++;
        }
        return root;
    }
}
