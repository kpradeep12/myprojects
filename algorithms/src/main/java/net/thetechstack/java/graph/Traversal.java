package net.thetechstack.java.graph;

import java.util.*;

public class Traversal{
    public static void bfs(Graph graph, Node node){
        Queue<Node> queue = new LinkedList<>();
        List<Node> visited = new LinkedList<>();
        queue.offer(node);
        visited.add(node);
        while(!queue.isEmpty()){
            Node element = queue.poll();
            graph.getMap().get(element.data)
            .forEach(ele -> {
                if(!visited.contains(ele)){
                    visited.add(ele);
                    queue.add(ele);
                    System.out.println(ele);
                }
            });
        }
    } 
    public static void dfs(Graph graph, Node node){
        Deque<Node> stack = new LinkedList<>();
        List<Node> visited = new LinkedList<>();
        stack.add(node);
        visited.add(node);
        while(!stack.isEmpty()){
            Node element = stack.poll();
            graph.getMap().get(element.data).forEach(ele -> {
                if(!visited.contains(ele)){
                    stack.push(ele);
                    visited.add(ele);
                    System.out.println(ele);
                }
            });
        }
    }
    public static boolean isConnected(Graph graph, Node src, Node dest){
        List<Node> visited = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        visited.add(src);
        queue.offer(src);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            LinkedList<Node> nodes = graph.getMap().get(node.data);
            for(Node element: nodes){
                if(element.equals(dest)){
                    return true;
                }
                if(!visited.contains(element)){
                    visited.add(element);
                    queue.offer(element);
                }
            };
        }
        return false;
    }
}