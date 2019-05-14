package net.thetechstack.java.graph;

public class App {
    public static void main(String args[]){
        Graph g = new Graph(3, true);
        g.addEdge(0,2);
        g.addEdge(0,1);
        g.print();
    }
}
