package com.nuketree3.example.testtoascf.model.fileloader;

import com.nuketree3.example.testtoascf.model.graph.PointGraph;
import com.nuketree3.example.testtoascf.model.graph.PointGraphAbstract;

import java.io.*;
import java.util.ArrayList;

public class FileRead implements FileReadable {

    @Override
    public ArrayList<String> getFileList(){
        ArrayList<String> list = new ArrayList<>();
        File folder = new File("src/main/resources/testtxtfiles");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for(File file : listOfFiles){
            if(file.isFile()){
                list.add(file.getName());
            }
        }
        return list;
    }



    @Override
    public PointGraphAbstract getGraphFile(String fileName) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/testtxtfiles/" + fileName))) {
            String[] strings = new String[7];
            StringBuilder input = new StringBuilder();
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                if(i <= 5){
                    //System.out.println(i + " " +line);
                    strings[i] = line;
                }else{
                    input.append(line+" ");

                }
                i++;
            }
            //System.out.print("_____"+input.toString());
            strings[6] = input.toString();
            return createGraphFile(strings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PointGraphAbstract createGraphFile(String[] strings) {
        PointGraph graph = new PointGraph();
        graph.setxMin(Integer.parseInt(strings[0]));
        //System.out.println();
        graph.setxMax(Integer.parseInt(strings[1]));
        graph.setyMin(Integer.parseInt(strings[2]));
        graph.setyMax(Integer.parseInt(strings[3]));
        graph.setzMin(Integer.parseInt(strings[4]));
        graph.setzMax(Integer.parseInt(strings[5]));
        String[] yValues = strings[6].split(" ");
        int n = 0;
        double[][] arr = new double[Math.abs(Integer.parseInt(strings[0])) + Math.abs(Integer.parseInt(strings[1]))][Math.abs(Integer.parseInt(strings[4])) + Math.abs(Integer.parseInt(strings[5]))];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = Double.parseDouble(yValues[n]);
                n++;
            }
        }
        graph.setCoordinates(arr);
        graph.checkGraph();
        return graph;
    }
}
