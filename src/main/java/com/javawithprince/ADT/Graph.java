package com.javawithprince.ADT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * Graph class is responsible for tracking which package dependents on which packets
 * And also which pkg is required
 *
 */
public class Graph {

    private final HashMap<String, HashSet<String>> dependenciesMap;
    private final HashMap<String, HashSet<String>> reverseDependenciesMap;

    public Graph() {
        dependenciesMap = new HashMap<>();
        reverseDependenciesMap = new HashMap<>();
    }

    //Constant time operation as we are using map
    public boolean addEdge(String src, String dest) {

        //Added reverse relationship
        if(!reverseDependenciesMap.containsKey(dest))
            reverseDependenciesMap.put(dest, new HashSet<String>());
        reverseDependenciesMap.get(dest).add(src);

        if(!dependenciesMap.containsKey(src))
            dependenciesMap.put(src, new HashSet<String>());
        return dependenciesMap.get(src).add(dest);
    }

    //Depends on
    public ArrayList<String> getDependencies(String pkg) {
        ArrayList<String> list = new ArrayList<>();
        if(dependenciesMap.containsKey(pkg))
            list.addAll(dependenciesMap.get(pkg));
        return list;
    }

    //used by
    public ArrayList<String> getReverseDependencies(String pkg) {
        ArrayList<String> list = new ArrayList<>();
        if(reverseDependenciesMap.containsKey(pkg))
            list.addAll(reverseDependenciesMap.get(pkg));
        return list;
    }

    //Linear time operation
    public void removeReverseDependencies(String pkg) {
        if(dependenciesMap.containsKey(pkg)) {
            for(String p : dependenciesMap.get(pkg)) {
                reverseDependenciesMap.get(p).remove(pkg);
            }
        }
    }

    public HashMap<String, HashSet<String>> getDependenciesMap() {
        return dependenciesMap;
    }

    public HashMap<String, HashSet<String>> getReverseDependenciesMap() {
        return reverseDependenciesMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Graph)) return false;
        Graph graph = (Graph) o;
        return Objects.equals(dependenciesMap, graph.dependenciesMap) &&
                Objects.equals(reverseDependenciesMap, graph.reverseDependenciesMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dependenciesMap, reverseDependenciesMap);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Graph{");
        sb.append("dependenciesMap=").append(dependenciesMap);
        sb.append(", reverseDependenciesMap=").append(reverseDependenciesMap);
        sb.append('}');
        return sb.toString();
    }
}
