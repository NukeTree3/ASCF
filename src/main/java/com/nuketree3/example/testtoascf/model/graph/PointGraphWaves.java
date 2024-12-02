package com.nuketree3.example.testtoascf.model.graph;

public class PointGraphWaves extends PointGraphAbstract {

    private static int xMin = -10;
    private static int xMax = 10;
    private static int yMin = -10;
    private static int yMax = -10;
    private static int zMin = -100;
    private static int zMax = 100;

    @Override
    public void setDefoult() {
        for (int x = xMin; x < xMax; x++) {
            for (int y = yMin; y < yMax; y++) {
                double z = (Math.sin(x)*Math.cos(y));
                if(z<zMin) zMin= (int) z;
                if(z>zMax) zMax= (int) z;
                coordinates[x][y] = z;
            }
        }
    }

}
