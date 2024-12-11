package com.nuketree3.example.testtoascf.model.graph;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class PointGraphAbstract implements Serializable {

    protected String nameGraph;

    protected int xMin;
    protected int xMax;
    protected double yMin;
    protected double yMax;
    protected int zMin;
    protected int zMax;

    double[][] coordinates;

    public abstract void setDefoult();
}
