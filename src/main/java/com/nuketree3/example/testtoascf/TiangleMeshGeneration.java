package com.nuketree3.example.testtoascf;

import javafx.scene.shape.TriangleMesh;

public class TiangleMeshGeneration {
    private TriangleMesh mesh;

    public TiangleMeshGeneration() {
        mesh = new TriangleMesh();
    }

    public void setArray(int[][] array){
        for(int i = 0; i<array.length; i++){
            for(int j = 0; j<array[i].length; j++){
                //System.out.println(array[i][j]);
                mesh.getPoints().addAll(i,j,array[i][j]);
            }
        }
        getFaces(array);
    }

    public void getFaces(int[][] array){
        for(int i = 0; i<array.length; i++){
            for(int j = 0; j<array[i].length; j++){
                int p0 = i * array.length;
                int p1 = p0 + 1;
                int p2 = p0 + array[i].length;
                int p3 = p2 + 1;
                mesh.getFaces().addAll(p0, p0, p1, p1, p2, p2, p2, p2, p1, p1, p3, p3);
            }
        }
    }

    public TriangleMesh getMesh(){
        return mesh;
    }
}
