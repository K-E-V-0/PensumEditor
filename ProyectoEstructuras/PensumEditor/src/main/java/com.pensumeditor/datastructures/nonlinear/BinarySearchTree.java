package com.pensumeditor.datastructures.nonlinear;

public class BinarySearchTree <T> {
    class Node {
        private T data;
        private Node left;
        private Node right;

        public Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    public BinarySearchTree(T data) {
        root = new Node(data);
    }

}

