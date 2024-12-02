package com.nuketree3.example.testtoascf.model.graph;


import java.io.Serializable;
public abstract class PointGraphAbstract implements Serializable {

    private static int xMin;
    private static int xMax;
    private static int yMin;
    private static int yMax;
    private static int zMin;
    private static int zMax;

    static double[][] coordinates;

    public static double[][] getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }

    public static int getxMin() {
        return xMin;
    }

    public static void setxMin(int xMin) {
        PointGraphAbstract.xMin = xMin;
    }

    public static int getxMax() {
        return xMax;
    }

    public static void setxMax(int xMax) {
        PointGraphAbstract.xMax = xMax;
    }

    public static int getyMin() {
        return yMin;
    }

    public static void setyMin(int yMin) {
        PointGraphAbstract.yMin = yMin;
    }

    public static int getyMax() {
        return yMax;
    }

    public static void setyMax(int yMax) {
        PointGraphAbstract.yMax = yMax;
    }

    public static int getzMin() {
        return zMin;
    }

    public static void setzMin(int zMin) {
        PointGraphAbstract.zMin = zMin;
    }

    public static int getzMax() {
        return zMax;
    }

    public static void setzMax(int zMax) {
        PointGraphAbstract.zMax = zMax;
    }


    public abstract void setDefoult();
}
