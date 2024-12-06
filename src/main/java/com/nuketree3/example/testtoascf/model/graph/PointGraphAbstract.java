package com.nuketree3.example.testtoascf.model.graph;


import java.io.Serializable;
public abstract class PointGraphAbstract implements Serializable {

    protected String nameGraph;

    protected int xMin;
    protected int xMax;
    protected double yMin;
    protected double yMax;
    protected int zMin;
    protected int zMax;

    double[][] coordinates;

    public String getNameGraph() {
        return nameGraph;
    }

    public double[][] getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }

    public int getxMin() {
        return xMin;
    }

    public void setxMin(int xMin) {
        this.xMin = xMin;
    }

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int xMax) {
        this.xMax = xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public void setyMin(int yMin) {
        this.yMin = yMin;
    }

    public double getyMax() {
        return yMax;
    }

    public void setyMax(int yMax) {
        this.yMax = yMax;
    }

    public int getzMin() {
        return zMin;
    }

    public void setzMin(int zMin) {
        this.zMin = zMin;
    }

    public int getzMax() {
        return zMax;
    }

    public void setzMax(int zMax) {
        this.zMax = zMax;
    }


    public abstract void setDefoult();
}
