package net.thetechstack.java.graph;

import java.lang.reflect.Array;
import java.util.*;

class Node{
    int data;
    Node(int data){
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return data == node.data;
    }

    @Override
    public int hashCode() {
        return data;
    }

    @Override
    public String toString(){
        return Integer.toString(data);
    }
}

class Graph {
    private boolean isDirected;
    private Map<Node, ArrayList<Node>> adjNodes = new HashMap<>();

    Graph(int size, boolean isDirected){
        this.isDirected = isDirected;
        for(int i=1; i<=size; i++)
            adjNodes.put(new Node(i), new ArrayList<>());
    }

    void addEdge(int source, int destination) {
        Node sour = new Node(source);
        Node dest = new Node(destination);
        adjNodes.get(sour).add(dest);
        if(isDirected)
            adjNodes.get(dest).add(sour);
    }
    void print(){
        adjNodes.forEach((k,v) -> {
            System.out.printf("%d -> ", k.data);
            v.forEach(node -> System.out.printf("%d -> ",node.data));
            System.out.println();
        });
    }

    ArrayList<Node> getAdjList(Node node){
        return adjNodes.get(node);
    }
    boolean isDirected() {return isDirected; }
int edgeCount = 0;
    public void bfs(Node root, Traversal traversal){

        Queue<Node> queue = new LinkedList<>();
        List<Node> visited = new ArrayList<>();
        List<Node> processed = new ArrayList<>();
        Map<Node, Node> relation = new HashMap<>();
        queue.add(root);
        visited.add(root);
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            processed.add(node);
            traversal.processNodeEarly(node);
            this.getAdjList(node).forEach(element -> {
                if((!processed.contains(element)) || isDirected()){
                    traversal.processEdge(node, element);
                    System.out.println(++edgeCount);
                }
                if(!visited.contains(element)) {
                    visited.add(element);
                    queue.add(element);
                    System.out.println(queue);
                    relation.put(node,element);
                }
            });
            traversal.processNodeLate(node);
        }
    }
    public void dfs(Node root){
        Stack<Node> stack = new Stack<>();
        List<Node> visited = new ArrayList<>();
        stack.add(root);
        visited.add(root);

        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.println("process "+node.data);
            this.getAdjList(node).forEach(ele -> {
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
        graph.addEdge(7,2);graph.addEdge(7,4);
        graph.print();
        graph.bfs(new Node(4), new Traversal() {
            @Override
            public void processNodeEarly(Node node) {
                System.out.printf("process early %s%n", node);
            }

            @Override
            public void processNodeLate(Node node) {
                System.out.printf("process late %s%n", node);
            }

            @Override
            public void processEdge(Node a, Node b) {
                System.out.printf("process edge %s - %s %n", a, b);
            }
        });
    }
}

