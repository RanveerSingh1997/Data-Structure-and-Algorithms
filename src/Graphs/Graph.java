package Graphs;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private final HashMap<String, ArrayList<String>> adjList = new HashMap<>();

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        System.out.println(graph.addEdge("A", "B"));
        System.out.println(graph.addEdge("B", "C"));
        System.out.println(graph.addEdge("C", "A"));
        graph.printGraph();
        System.out.println(graph.removeEdge("A", "C"));
        System.out.println(graph.removeEdge("B", "B"));
        graph.printGraph();
        System.out.println(graph.removeVertex("C"));
        graph.printGraph();
    }

    public void printGraph() {
        System.out.println(adjList);
    }

    public void addVertex(String vertex) {
        adjList.computeIfAbsent(vertex, k -> new ArrayList<>());
    }

    public boolean addEdge(String vertex1, String vertex2) {
        if (adjList.get(vertex1) != null && adjList.get(vertex2) != null) {
            adjList.get(vertex1).add(vertex2);
            adjList.get(vertex2).add(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeEdge(String vertex1, String vertex2) {
        if (adjList.get(vertex1) != null && adjList.get(vertex2) != null) {
            adjList.get(vertex1).remove(vertex2);
            adjList.get(vertex2).remove(vertex1);
            return true;
        }
        return false;
    }

    public boolean removeVertex(String vertex) {
        if (adjList.get(vertex) == null) return false;
        for (String otherVertex : adjList.get(vertex)) {
            adjList.get(otherVertex).remove(vertex);
        }
        adjList.remove(vertex);
        return true;
    }
}
