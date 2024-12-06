package com.nuketree3.example.testtoascf;

import com.nuketree3.example.testtoascf.model.fileloader.FileRead;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileRead fr = new FileRead();
        fr.getGraphFile("graph1.txt");
    }
}
