package com.nuketree3.example.testtoascf.model.graph;

import java.util.Random;

public class PointGraphRandom extends PointGraphAbstract {


    public PointGraphRandom() {

        nameGraph = "PointGraphRandom";

        xMin = 0;
        xMax = 100;
        yMin = -50;
        yMax = 50;
        zMin = 0;
        zMax = 100;
        coordinates = new double[Math.abs(xMax)+Math.abs(xMin)][Math.abs(zMax)+Math.abs(zMin)];
        setDefoult();
    }
    @Override
    public void setDefoult() {
        Random rand = new Random();
        int xIndex = 0;
        for (int x = xMin; x < xMax; x++) {
            int zIndex = 0;
            for (int z = zMin; z < zMax; z++) {
                int y = rand.nextInt(-100,100);
                if(y<yMin) yMin=y;
                if(y>yMax) yMax=y;
                coordinates[xIndex][zIndex] = y;
                zIndex++;
            }
            xIndex++;
        }
    }
}
