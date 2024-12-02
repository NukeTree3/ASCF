package com.nuketree3.example.testtoascf;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.Random;

public class Plane {
    private int[][] plane;
    Plane(int width, int height) {
        plane = new int[2*width][2*height];
    }

    public Group generatePlane() {
        Random rand = new Random();
        Group root = new Group();
        int l = 0;
        for (int i = -plane.length/2+1; i < plane.length/2; i++) {
            for (int j = -plane[l].length/2+1; j < plane[l].length/2; j++) {
                Box box = new Box();
                box.translateXProperty().set(i);
                box.translateZProperty().set(j);
                double hue = 0;
                //int z = rand.nextInt(-100, 100);
                //double z = (double) (i * i * j * j + 2) /1000000;
                double z = (double) (i*j)/100;
                //double z = (double) (Math.sin((double) i /10)*Math.cos((double) j /10))*10;
                //double z = (double) (Math.sin((double) i /10)*10);
                //double z = Math.exp((double) -(i/10.0 * i/10.0 + j/10.0 * j/10.0) /8)*(Math.sin(i/10.0*i/10.0)+Math.cos(j/10.0*j/10.0))*50;
                //double z = (double) Math.sin(((double) i /10)*((double) i /10)+((double) j /10)*((double) j /10))/(((double) i /10)*((double) i /10)+((double) j /10)*((double) j /10))*25;
                if(z<0){
                    hue = (90.0 * (1.0 - Math.exp(0.03 * (double) z)))+150;
                }else if(z==0){
                    hue = 150;
                }
                else {
                    hue = (150 * Math.exp(-0.04 * (double) z));
                }
                //box.translateZProperty().set(rand.nextInt(100));
                box.translateYProperty().set(-z);
                Color color = Color.hsb(hue, 1.0, 1.0);
                box.setMaterial(new PhongMaterial(color));
                root.getChildren().add(box);
            }
            l++;
            //System.out.println(l+" "+i);
        }
        return root;
    }

    public int[][] getPlane() {
        return plane;
    }

    public void setPlane(int[][] plane) {
        this.plane = plane;
    }
}
