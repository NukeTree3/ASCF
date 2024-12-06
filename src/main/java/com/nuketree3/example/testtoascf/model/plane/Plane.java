package com.nuketree3.example.testtoascf.model.plane;

import com.nuketree3.example.testtoascf.model.graph.PointGraphAbstract;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;


public class Plane {
    private PointGraphAbstract graph;

    public Plane(PointGraphAbstract graph) {
        this.graph = graph;
    }

    public Group generatePlane() {
        System.out.println("Generating plane...");
        
        Group root = new Group();
        int xIndex = 0;
        for (int x = graph.getxMin(); x < graph.getxMax(); x++) {
            int zIndex = 0;
            for (int z = graph.getzMin(); z < graph.getzMax(); z++) {
                Box box = new Box();
                box.translateXProperty().set(x);
                box.translateZProperty().set(z);
                double hue;
                //int z = rand.nextInt(-100, 100); +
                //double z = (double) (i * i * j * j + 2) /1000000; +
                //double z = (double) (i*j)/100; +
                //double z = (double) (Math.sin((double) i /10)*Math.cos((double) j /10))*10; +
                //double z = (double) (Math.sin((double) i/10)*10); +
                //double z = Math.exp(-(i/10.0 * i/10.0 + j/10.0 * j/10.0) /8)*(Math.sin(i/10.0*i/10.0)+Math.cos(j/10.0*j/10.0))*50; +
                //double z = (double) Math.sin(((double) i /10)*((double) i /10)+((double) j /10)*((double) j /10))/(((double) i /10)*((double) i /10)+((double) j /10)*((double) j /10))*25;
                double y = graph.getCoordinates()[xIndex][zIndex];
                if(y<0){
                    hue = (90.0 * (1.0 - Math.exp(0.03 * y)))+150;
                }else if(y==0){
                    hue = 150;
                }
                else {
                    hue = (150 * Math.exp(-0.04 * y));
                }
                //box.translateZProperty().set(rand.nextInt(100));
                box.translateYProperty().set(-y);
                Color color = Color.hsb(hue, 1.0, 1.0);
                box.setMaterial(new PhongMaterial(color));
                root.getChildren().add(box);
                //System.out.println(x+" "+y+" "+z);
                zIndex++;
            }
            xIndex++;

        }
        System.out.println("Plane generated.");
        return root;
    }

}
