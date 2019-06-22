package com.javawithprince.POJO;

import com.javawithprince.ADT.Graph;
import com.javawithprince.ADT.Util.GraphUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class RepoContext {

    private static RepoContext repo;
    private static Graph dependenciesGraph;
    private static HashSet<String> installedPkgs;
    private static HashSet<String> installedPKgsExplicit;

    public RepoContext() {
        dependenciesGraph = new Graph();
        installedPkgs = new HashSet<>();
        installedPKgsExplicit = new HashSet<>();
    }

    //Used for testing
    protected void deleteGraph() {
        dependenciesGraph = new Graph();
    }

    public ArrayList<String> getDependencies(String pkg) {
        return dependenciesGraph.getDependencies(pkg);
    }

    public ArrayList<String> getReverseDependencies(String pkg) {
        return dependenciesGraph.getReverseDependencies(pkg);
    }

    public HashMap<String, HashSet<String>> getReverseDependenciesMap(String pkg) {
        return dependenciesGraph.getReverseDependenciesMap();
    }

    public HashSet<String> getInstalledPkg() {
        return installedPkgs;
    }

    public HashSet<String> getInstalledPKgsExplicit() {
        return installedPKgsExplicit;
    }

    public  boolean addDependencies(String src, String dst) {
        ArrayList<String> list = new ArrayList<>();
        return dependenciesGraph.addEdge(src, dst);
    }

    public  boolean isCyclic() {
        return GraphUtil.isCyclic(dependenciesGraph);
    }

    public boolean install(String pkg) {
        return installedPkgs.add(pkg);
    }

    public boolean installExplicit(String pkg) {
        return installedPKgsExplicit.add(pkg);
    }

    public void removeReverseDependencies(String pkg) {
        dependenciesGraph.removeReverseDependencies(pkg);
    }
}
