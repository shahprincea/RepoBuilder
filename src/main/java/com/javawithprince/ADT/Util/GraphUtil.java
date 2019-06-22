package com.javawithprince.ADT.Util;

import com.javawithprince.ADT.Graph;

import java.util.HashMap;

public class GraphUtil {

    private static final int UNVISITED = 0;

    private static boolean DFS(Graph graph, String vertex, HashMap<String, Integer> colorMap) {

        //Visiting
        int VISITING = 1;
        colorMap.put(vertex, VISITING);

        //Iterate through all the neighbors of the visiting vertex
        for(String neighbor : graph.getDependencies(vertex)) {

            //Found backedge
            if(colorMap.get(neighbor) != null && colorMap.get(neighbor) == VISITING)
                return true;

            if(colorMap.get(neighbor) != null && colorMap.get(neighbor) == UNVISITED && DFS(graph, neighbor, colorMap) == true)
                return true;
        }

        int VISITED = 2;
        colorMap.put(vertex, VISITED);
        return false;
    }

    /**
     *
     * This runs in O(V + E) time
     *
     * @param graph
     * @return true if given graph is cyclic
     */
    public static boolean isCyclic(Graph graph) {

        if(graph == null)
            return false;

        //Initialize all the vertex in the graph with unvisited
        HashMap<String, Integer> colorMap = new HashMap<>();
        for(String vertex : graph.getDependenciesMap().keySet()) {
            colorMap.put(vertex, UNVISITED);
        }

        for(String vertex : colorMap.keySet()) {

            if(colorMap.get(vertex) == UNVISITED) {
                if(DFS(graph, vertex, colorMap))
                    return true;
            }
        }

        return false;
    }
}
