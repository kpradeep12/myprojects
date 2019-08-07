package net.thetechstack.java.graph;

public class BinaryTree{
    class Node{
        int data;
        Node left;
        Node right;
        Node(int data) { this.data = data;}
    }
    Node root;
    public void insert(int data){
        if(root == null) { 
            this.root = new Node(data);
        }else
            this.insert(data, root);
    }
    private void insert(int data, Node node){
        if(data < node.data && node.left != null)
            insert(data, node.left);
        else
            node.left = new Node(data);
        
        if(data > node.data && node.right != null)
            insert(data, node.right);
        else
            node.right = new Node(data);
    }
    public void preOrder(){
        this.preOrder(root);
    }
    private void preOrder(Node node){
        if(node != null){
            preOrder(node.left);
            System.out.printf("%d - ", node.data);
            preOrder(node.right);
        }
    }
    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        bt.insert(3);
        bt.insert(1);
        bt.insert(2);
        bt.insert(4);
        bt.preOrder();
    }
}