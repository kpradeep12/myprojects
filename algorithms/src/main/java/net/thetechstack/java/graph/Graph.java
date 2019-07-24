package net.thetechstack.java.graph;

import java.util.*;
import java.util.stream.*;

class Node{
    int data;
    Node(int data) {this.data = data;}
    public String toString(){ return Integer.toString(data); }
    public int hashCode() { return this.data; }
    public boolean equals(Object obj){
        if(obj == null) return false;
        if(obj != null && obj.getClass() != this.getClass()) return false;
        return this.data == ((Node) obj).data;
    }
}

public class Graph{
    Map<Integer, LinkedList<Node>> map = new HashMap<>();
    Graph(int size){
        IntStream.range(1, size+1).forEach(i -> map.put(i, new LinkedList<Node>()));
    }
    Graph addEdge(int src, int dest){
        map.get(src).add(new Node(dest));
        map.get(dest).add(new Node(src));
        return this;
    }
    public void print(){
        map.forEach((key, val) -> {
            System.out.printf("%d -> ", key);
            val.forEach(node -> {
                System.out.printf("%s - ", node);
            });
            System.out.println();
        });
    }
    public Map<Integer, LinkedList<Node>> getMap(){ return this.map; }
    public static void main(String args[]){
        Graph graph = new Graph(6);
        graph.addEdge(1, 2).addEdge(2, 3).addEdge(2, 4).addEdge(3, 5).addEdge(4,6).print();
        System.out.println("---");
        Traversal.bfs(graph, new Node(1));
        System.out.println("---");
        Traversal.dfs(graph, new Node(1));
    }
}