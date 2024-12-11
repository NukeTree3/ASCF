package com.nuketree3.example.testtoascf.model.fileloader;

import com.nuketree3.example.testtoascf.model.graph.PointGraph;
import com.nuketree3.example.testtoascf.model.graph.PointGraphAbstract;
import java.io.*;
import java.util.ArrayList;

public class FileRead implements FileReadable {

    /**
     * getFileList - метод, возвращающий список имен файлов в папке testtxtfiles
     * @return список имен файлов
     */
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

    /**
     * getGraphFile - создание точечного графа из файла
     * @param fileName - имя файла
     * @return - полученный из файла граф
     * @throws FileNotFoundException - если файл с таким именем не найден, отправляет сообщение об ошибке в консоль
     */
    @Override
    public PointGraphAbstract getGraphFile(String fileName) throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/testtxtfiles/" + fileName))) {
            String[] strings = new String[7];
            StringBuilder input = new StringBuilder();
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                if(i <= 5){
                    strings[i] = line;
                }else{
                    input.append(line+" ");

                }
                i++;
            }
            strings[6] = input.toString();
            return createGraphFile(strings);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * createGraphFile - метод, который создает граф из массива точек
     * @param strings - массив точек в пространстве, полученный из файла
     * @return - полученный граф
     */
    @Override
    public PointGraphAbstract createGraphFile(String[] strings) {

        PointGraph graph = new PointGraph();

        graph.setXMin(Integer.parseInt(strings[0]));
        graph.setXMax(Integer.parseInt(strings[1]));
        graph.setYMin(Integer.parseInt(strings[2]));
        graph.setYMax(Integer.parseInt(strings[3]));
        graph.setZMin(Integer.parseInt(strings[4]));
        graph.setZMax(Integer.parseInt(strings[5]));

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
