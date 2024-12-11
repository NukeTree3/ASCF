package com.nuketree3.example.testtoascf.presenter;

import com.nuketree3.example.testtoascf.model.Service;
import com.nuketree3.example.testtoascf.model.emuns.PointGraphType;
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

    public ArrayList<PointGraphType> getGraphs(){
        return service.getGraphs();
    }

    public PointGraphAbstract getGraph(PointGraphType type){
        return service.getGraph(type);
    }

    public PointGraphAbstract getPointGrahpFile(String fileName) throws FileNotFoundException {
        return service.getPointGrahpFile(fileName);
    }

    public Group getAxisCube(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax){
        return service.getAxisCube(xMin, xMax, yMin, yMax, zMin, zMax);
    }

    public Group get3dGraph(int smoothMedianParametr, PointGraphAbstract pointGraph, double xParametr, double yParametr, double zParametr){
        return service.get3dGraph(smoothMedianParametr, pointGraph, xParametr, yParametr, zParametr);
    }
}
