package com.nuketree3.example.testtoascf.model;

import com.nuketree3.example.testtoascf.model.axiscube.AxisCube;
import com.nuketree3.example.testtoascf.model.emuns.PointGraphType;
import com.nuketree3.example.testtoascf.model.factory.PointGraphFactory;
import com.nuketree3.example.testtoascf.model.fileloader.FileRead;
import com.nuketree3.example.testtoascf.model.graph.*;
import com.nuketree3.example.testtoascf.model.plane.Plane;
import javafx.scene.Group;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Service {

    private PointGraphAbstract graph;
    private final FileRead fileRead;
    private ArrayList<PointGraphType> graphs;
    private PointGraphFactory graphFactory;

    public Service(){
        this.graph = new PointGraph();
        fileRead = new FileRead();
        graphFactory = new PointGraphFactory();
        graphs = new ArrayList<>(Arrays.asList(PointGraphType.values()));
    }

    public ArrayList<PointGraphType> getGraphs(){
        return graphs;
    }

    public ArrayList<String> getFileList(){
        return fileRead.getFileList();
    }

    public PointGraphAbstract getGraph(PointGraphType type){
        return graphFactory.createPointGraph(type);
    }

    public PointGraphAbstract getPointGrahpFile(String fileName) throws FileNotFoundException {
        return fileRead.getGraphFile(fileName);
    }

    public void getPointGraphDefolt(){
        this.graph.setDefoult();
    }

    public Group getAxisCube(double xMin, double xMax, double yMin, double yMax, double zMin, double zMax){
        AxisCube axisCube = new AxisCube();
        return axisCube.returnCubeWithAxis(xMin, xMax, yMin, yMax, zMin, zMax);
    }

    public Group get3dGraph(int smoothMedianParametr, PointGraphAbstract graph, double xParametr, double yParametr, double zParametr){
        Plane plane = new Plane(graph);
        return plane.generatePlane(smoothMedianParametr, xParametr, yParametr, zParametr);
    }
}
