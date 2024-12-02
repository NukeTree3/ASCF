package com.nuketree3.example.testtoascf.model.graph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointGraph extends PointGraphAbstract{

    private static int xMin = 0;
    private static int xMax = 0;
    private static int yMin = 0;
    private static int yMax = 0;
    private static int zMin = 0;
    private static int zMax = 0;


    @Override
    public void setDefoult() {
        for (int x = xMin; x < xMax; x++) {
            for (int y = yMin; y < yMax; y++) {
                this.coordinates[x][y] = 0;
            }
        }
    }

}
