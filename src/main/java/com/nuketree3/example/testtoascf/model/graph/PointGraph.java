package com.nuketree3.example.testtoascf.model.graph;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointGraph extends PointGraphAbstract{

    @Override
    public void setDefoult() {
        for (int x = 0; x < Math.abs(xMax)+Math.abs(xMin); x++) {
            for (int z = 0; z < Math.abs(zMax)+Math.abs(zMin); z++) {
                this.coordinates[x][z] = 0;
            }
        }
    }

    public void checkGraph() {
        for (int x = 0; x < Math.abs(xMax)+Math.abs(xMin); x++) {
            for (int z = 0; z < Math.abs(zMax)+Math.abs(zMin); z++) {
                double y = coordinates[x][z];
                if(y<yMin) yMin= y;
                if(y>yMax) yMax= y;
            }
        }
    }

}
