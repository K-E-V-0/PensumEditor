package com.pensumeditor.datastructures.nonlinear;

import com.pensumeditor.datastructures.linear.CircularArrayList;
import com.pensumeditor.datastructures.linear.LinkedList;
import com.pensumeditor.datastructures.linear.List;

public class BinarySearchTree {
    public class Node {
        private int key;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int key) {
            this.key = key;
            left = right = parent = null;
        }

        public int getKey() {
            return key;
        }

        @Override
        public String toString() {
            return Integer.toString(key);
        }
    }

    private Node root;

    public BinarySearchTree(int key) {
        root = new Node(key);
    }

    public BinarySearchTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node insert(int key) {
        root = insert(key, root);
        return root;
    }

    public Node insert(int key, Node node) {
        if (node == null) {
            return new Node(key);
        }
        if (key > node.key) {
            node.right = insert(key, node.right);
            node.right.parent = node;
        } else if (key < node.key) {
            node.left = insert(key, node.left);
            node.left.parent = node;
        }
        return node;
    }

    public Node find(int value) {
        return find(value, root);
    }

    public Node find(int value, Node node) {
        if (node != null) {
            if (node.key == value) {
                return node;
            } else if (node.key > value) {
                if (node.left != null) {
                    return find(value, node.left);
                }
                return node;
            } else {
                if (node.right != null) {
                    return find(value, node.right);
                }
            }
            return node;
        }
        return null;
    }

    public Node next(Node node) {
        if (node.right != null) {
            return LeftDescendant(node.right);
        } else {
            return RightAncestor(node);
        }
    }

    public Node LeftDescendant(Node node) {
        if (node.left != null) {
            return LeftDescendant(node.left);
        } else {
            return node;
        }
    }

    public Node RightAncestor(Node node) {
        if (node != null) {
            if (node.parent != null && node.key < node.parent.key) {
                return node.parent;
            } else {
                return RightAncestor(node.parent);
            }
        }
        return null;
    }

    public List<Node> rangeSearch(int x, int y) {
        return rangeSearch(x,y,root);
    }

    public List<Node> rangeSearch(int x, int y, Node R) {
        List<Node> L = new CircularArrayList<>();
        Node N = find(x, R);
        while (N != null && N.key <= y) {
            if (N.key >= x) {
                L.add(N);
            }
            N = next(N);
        }
        return L;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public Node delete(Node root, int key) {
        if (root == null) {
            return root;
        }
        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.key = minValue(root.right);
            root.right = delete(root.right, root.key);
        }
        return root;
    }

    private int minValue(Node node) {
        int minValue = node.key;
        while (node.left != null) {
            minValue = node.left.key;
            node = node.left;
        }
        return minValue;
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
        System.out.println();
    }

    private void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
        System.out.println();
    }

    private void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.key + " ");
        }
    }
}
