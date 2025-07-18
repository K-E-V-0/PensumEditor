package com.pensumeditor.datastructures.nonlinear;

import com.pensumeditor.datastructures.linear.CircularArrayList;
import com.pensumeditor.datastructures.linear.List;

import java.util.NoSuchElementException;

public class AVLTree implements Tree {
    public class Node {
        private int key, height;
        private Node left, right, parent;
        public Node(int key) {
            this.key = key;
            this.left = this.right = this.parent = null;
            height = 1;
        }
        public int getKey() {
            return key;
        }
    }
    private Node root;
    public AVLTree(int key) {
        root = new Node(key);
    }
    public AVLTree() {
        root = null;
    }
    public void makeEmpty() {
        root = null;
    }
    public Node getRoot() {
        return root;
    }
    public boolean isEmpty() {
        return root == null;
    }

    public int height(){
        return height(root);
    }
    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    public int size(){
        return size(root);
    }
    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    private int getBalanceFactor(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public void insert(int key) {
        root = insertNode(root, key);
    }

    private Node insertNode(Node node, int item) {

        // Find the position and insert the node
        if (node == null)
            return (new Node(item));
        if (item < node.key)
            node.left = insertNode(node.left, item);
        else if (item > node.key)
            node.right = insertNode(node.right, item);
        else
            return node;

        // Update the balance factor of each node
        // And, balance the tree
        node.height = 1 + max(height(node.left), height(node.right));
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (item < node.left.key) {
                return rightRotate(node);
            } else if (item > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (item > node.right.key) {
                return leftRotate(node);
            } else if (item < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }
    public int search(int key) {
        return find(key).key;
    }
    public Node find(int value) {
        return find(value, root);
    }

    private Node find(int value, Node node) {
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
    public int findMin(){
        return findMin(root).key;
    }

    private Node findMin(Node node) {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty tree");
        }
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }
    public int findMax() {
        return findMax(root).key;
    }
    private Node findMax(Node node) {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty tree");
        }
        Node current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }

    public Node next(Node node) {
        if (node.right != null) {
            return LeftDescendant(node.right);
        } else {
            return RightAncestor(node);
        }
    }

    private Node LeftDescendant(Node node) {
        if (node.left != null) {
            return LeftDescendant(node.left);
        } else {
            return node;
        }
    }

    private Node RightAncestor(Node node) {
        if (node != null) {
            if (node.parent != null && node.key < node.parent.key) {
                return node.parent;
            } else {
                return RightAncestor(node.parent);
            }
        }
        return null;
    }

    public List<Integer> rangeSearch(int x, int y) {
        return rangeSearch(x,y,root);
    }

    private List<Integer> rangeSearch(int x, int y, Node R) { // ARREGLAR
        List<Integer> L = new CircularArrayList<>();
        Node N = find(x, R);
        while (N != null && N.key <= y) {
            if (N.key >= x) {
                L.add(N.key);
            }
            N = next(N);
        }
        return L;
    }

    public void delete(int item){
        if (find(item) != null) {
            root = deleteNode(root, item);
        }
        else {
            System.out.println("Element doesn't exist.");
        }
    }

    private Node deleteNode(Node root, int item) {
        if (root == null)
            return root;
        if (item < root.key)
            root.left = deleteNode(root.left, item);
        else if (item > root.key)
            root.right = deleteNode(root.right, item);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = findMin(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }
        if (root == null)
            return root;
        root.height = max(height(root.left), height(root.right)) + 1;
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (getBalanceFactor(root.left) >= 0) {
                return rightRotate(root);
            } else {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(root.right) <= 0) {
                return leftRotate(root);
            } else {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }
        return root;
    }

    public void preOrderTraversal(){
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

    public void printTree(){
        printTree(root,"",true);
    }
    private void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.key);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }
}