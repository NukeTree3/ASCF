package com.nuketree3.example.testtoascf.model;

import com.nuketree3.example.testtoascf.model.axiscube.AxisCube;
import com.nuketree3.example.testtoascf.model.fileloader.FileRead;
import com.nuketree3.example.testtoascf.model.graph.*;
import com.nuketree3.example.testtoascf.model.plane.Plane;
import javafx.scene.Group;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Service {

    private PointGraphAbstract graph;
    private final FileRead fileRead;
    private ArrayList<PointGraphAbstract> graphs;

    public Service(){
        this.graph = new PointGraph();
        fileRead = new FileRead();

        graphs = new ArrayList<>();

        graphs.add(new PointGraphOneWaves());
        graphs.add(new PointGraphRandom());
        graphs.add(new PointGraphSaddle());
        graphs.add(new PointGraphSambrero());
        graphs.add(new PointGraphTable());
        graphs.add(new PointGraphUnknow());
        graphs.add(new PointGraphWaves());


    }

    public ArrayList<PointGraphAbstract> getGraphs(){
        return graphs;
    }

    public ArrayList<String> getFileList(){
        return fileRead.getFileList();
    }

    public PointGraphAbstract getPointGraph(int num) {
//        return switch (num) {
//            case 1 -> new PointGraphOneWaves();
//            case 2 -> new PointGraphRandom();
//            case 3 -> new PointGraphSaddle();
//            case 4 -> new PointGraphSambrero();
//            case 5 -> new PointGraphTable();
//            case 6 -> new PointGraphUnknow();
//            case 7 -> new PointGraphWaves();
//            default -> new PointGraph();
//        };
        return graphs.get(num);
    }

    public PointGraphAbstract getPointGrahpFile(String fileName) throws FileNotFoundException {
        return fileRead.getGraphFile(fileName);
    }

    public void getPointGraphDefolt(){
        this.graph.setDefoult();
    }

    public Group getAxisCube(int xMin, int xMax, double yMin, double yMax, int zMin, int zMax){
        AxisCube axisCube = new AxisCube();
        return axisCube.returnCubeWithAxis(xMin, xMax, yMin, yMax, zMin, zMax);
    }

    public Group get3dGraph(PointGraphAbstract graph){
        Plane plane = new Plane(graph);
        return plane.generatePlane();
    }
}
