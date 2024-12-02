package com.nuketree3.example.testtoascf.model.graph;


public class PointGraphTable extends PointGraphAbstract {

    private static int xMin = -100;
    private static int xMax = 100;
    private static int yMin = -10;
    private static int yMax = -100;
    private static int zMin = -100;
    private static int zMax = 100;

    @Override
    public void setDefoult() {
        for (int x = xMin; x < xMax; x++) {
            for (int y = yMin; y < yMax; y++) {
                int z = (x * x * y * y + 2);
                if(z<zMin) zMin=z;
                if(z>zMax) zMax=z;
                coordinates[x][y] = z;
            }
        }
    }
}
