package com.nuketree3.example.testtoascf.model.factory;

import com.nuketree3.example.testtoascf.model.emuns.PointGraphType;
import com.nuketree3.example.testtoascf.model.graph.*;

public class PointGraphFactory {

    public PointGraphAbstract createPointGraph(PointGraphType type) {
        PointGraphAbstract graph = null;

        switch (type) {
            case POINT_GRAPH_ONE_WAVES -> graph = new PointGraphOneWaves();
            case POINT_GRAPH_RANDOM -> graph = new PointGraphRandom();
            case POINT_GRAPH_SADDLE -> graph = new PointGraphSaddle();
            case POINT_GRAPH_SAMBRERO -> graph = new PointGraphSambrero();
            case POINT_GRAPH_TABLE -> graph = new PointGraphTable();
            case POINT_GRAPH_UNKNOWN -> graph = new PointGraphUnknown();
            case POINT_GRAPH_WAVES -> graph = new PointGraphWaves();
        }
        return graph;
    }
}
