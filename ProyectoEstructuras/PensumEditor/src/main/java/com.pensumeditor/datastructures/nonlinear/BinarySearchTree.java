package com.pensumeditor.datastructures.nonlinear;

public class BinarySearchTree {
    class Node {
        private int key;
        private Node left;
        private Node right;

        public Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }

    private Node root;

    public BinarySearchTree(int key) {
        root = new Node(key);
    }

    public Node getRoot() {
        return root;
    }

    public Node LeftDescendant(Node node) {
        if (node.left != null) {
            return LeftDescendant(node.left);
        } else {
            return node;
        }
    }

    public Node RightAncestor(Node node) { // Revisar si sirve
        if (root != null) {
            Node actual = root;
            while (actual.left.key > node.key) {
                actual = actual.left;
            }
            return actual;
        }
        return null;
    }

    public Node find(int value, Node node) {
        if (node.key == value) {
            return node;
        } else if (node.key > value) {
            if (node.left != null) {
                return find(value, node.left);
            }
            return node;
        } else {
            return find(value, node.right);
        }
    }

    public Node next(Node node) {
        if (node.right != null) {
            return LeftDescendant(node.right);
        } else {
            return RightAncestor(node);
        }

    }



}

