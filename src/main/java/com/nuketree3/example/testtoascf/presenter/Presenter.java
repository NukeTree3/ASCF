package com.nuketree3.example.testtoascf.presenter;

import com.nuketree3.example.testtoascf.model.Service;
import com.nuketree3.example.testtoascf.model.graph.*;
import javafx.scene.Group;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Presenter {

//    boolean setka;
//    boolean aproksimaton;
//    double yAxisCor;
//    int typeOfPointGraph;
//    int typeOfServiceGraph;


    private final Service service;

    public Presenter() {
        this.service = new Service();
    }

//    public void setTypeOfPointGraph(int typeOfPointGraph) {
//        this.typeOfPointGraph = typeOfPointGraph;
//    }

    public ArrayList<String> getFileList(){
        return service.getFileList();
    }

    public ArrayList<PointGraphAbstract> getGraphs(){
        return service.getGraphs();
    }

    public PointGraphAbstract getPointGraph(int num) {
        return service.getPointGraph(num);
    }

    public PointGraphAbstract getPointGrahpFile(String fileName) throws FileNotFoundException {
        return service.getPointGrahpFile(fileName);
    }

    public void getPointGraphDefolt() {
        service.getPointGraphDefolt();
    }

    public Group getAxisCube(int xMin, int xMax, double yMin, double yMax, int zMin, int zMax){
        return service.getAxisCube(xMin, xMax, yMin, yMax, zMin, zMax);
    }

    public Group get3dGraph(int num){
        return service.get3dGraph(getPointGraph(num));
    }
}
