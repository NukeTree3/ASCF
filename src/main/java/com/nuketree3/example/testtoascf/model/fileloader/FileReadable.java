package com.nuketree3.example.testtoascf.model.fileloader;

import com.nuketree3.example.testtoascf.model.graph.PointGraphAbstract;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface FileReadable {

    ArrayList<String> getFileList();

    PointGraphAbstract createGraphFile(String[] strings);

    PointGraphAbstract getGraphFile(String fileName) throws FileNotFoundException;
}
