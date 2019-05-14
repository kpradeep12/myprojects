package net.thetechstack.java.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {
    public void search(Graph graph, Node root){
        Stack<Node> stack = new Stack<>();
        List<Node> visited = new ArrayList<>();
        stack.add(root);
        visited.add(root);

        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.println("process "+node.data);
            graph.getAdjList(node).forEach(ele -> {
                if(!visited.contains(ele)){
                    stack.push(ele);
                    visited.add(ele);
                }
            });
        }
    }
    public static void main(String[] args) {
        Graph graph = new Graph(8, false);
        graph.addEdge(4,2);graph.addEdge(4,1);
        graph.addEdge(2,5);graph.addEdge(2,6);graph.addEdge(2,3);graph.addEdge(2,1);
        graph.addEdge(1,3);
        graph.addEdge(3,6);graph.addEdge(5,7);graph.addEdge(6,7);
        graph.print();
        DepthFirstSearch bfs = new DepthFirstSearch();
        bfs.search(graph, new Node(4));
    }
}
