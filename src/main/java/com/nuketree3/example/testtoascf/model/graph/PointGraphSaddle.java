package com.nuketree3.example.testtoascf.model.graph;

public class PointGraphSaddle extends PointGraphAbstract {

    public PointGraphSaddle() {

        nameGraph = "PointGraphSaddle";

        xMin = -100;
        xMax = 100;
        yMin = -50;
        yMax = 50;
        zMin = -100;
        zMax = 100;
        coordinates = new double[Math.abs(xMax)+Math.abs(xMin)][Math.abs(zMax)+Math.abs(zMin)];
        setDefoult();
    }

    @Override
    public void setDefoult() {
        int xIndex = 0;
        for (int x = xMin; x < xMax; x++) {
            int zIndex = 0;
            for (int z = zMin; z < zMax; z++) {
                int y = (x*z)/100;
                if(y<yMin) yMin=y;
                if(y>yMax) yMax=y;
                coordinates[xIndex][zIndex] = y;
                zIndex++;
            }
            xIndex++;
        }
    }
}
