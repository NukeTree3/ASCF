package com.nuketree3.example.testtoascf.model.graph;

import java.util.Random;

public class PointGraphRandom extends PointGraphAbstract {
    private static int xMin = 0;
    private static int xMax = 0;
    private static int yMin = 0;
    private static int yMax = 0;
    private static int zMin = 50;
    private static int zMax = -50;

    public void setDefoult() {
        Random rand = new Random();
        for (int x = xMin; x < xMax; x++) {
            for (int y = yMin; y < yMax; y++) {
                int z = rand.nextInt();
                if(z<zMin) zMin=z;
                if(z>zMax) zMax=z;
                coordinates[x][y] = z;
            }
        }
    }
}
