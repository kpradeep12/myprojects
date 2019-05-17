package net.thetechstack.java.graph;

public interface Traversal {
    void processNodeEarly(Node node);
    void processNodeLate(Node node);
}
