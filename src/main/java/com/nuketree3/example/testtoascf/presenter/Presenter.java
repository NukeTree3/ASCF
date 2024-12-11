package com.nuketree3.example.testtoascf.presenter;

import com.nuketree3.example.testtoascf.model.Service;
import com.nuketree3.example.testtoascf.model.graph.*;
import javafx.scene.Group;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Presenter {


    private final Service service;

    public Presenter() {
        this.service = new Service();
    }

    public ArrayList<String> getFileList(){
        return service.getFileList();
    }

    public ArrayList<PointGraphAbstract> getGraphs(){
        return service.getGraphs();
    }

    public PointGraphAbstract getPointGrahpFile(String fileName) throws FileNotFoundException {
        return service.getPointGrahpFile(fileName);
    }

    public void getPointGraphDefolt() {
        service.getPointGraphDefolt();
    }

    public Group getAxisCube(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax){
        return service.getAxisCube(xMin, xMax, yMin, yMax, zMin, zMax);
    }

    public Group get3dGraph(PointGraphAbstract pointGraph, double xParametr, double yParametr, double zParametr){
        return service.get3dGraph(pointGraph, xParametr, yParametr, zParametr);
    }
    public Group get3dGraphWithSmoothMedian(PointGraphAbstract pointGraph, int smoothMedianParametr, double xParametr, double yParametr, double zParametr){
        return service.get3dGraphWithSmoothMedian(smoothMedianParametr,pointGraph,xParametr,yParametr,zParametr);
    }
}
