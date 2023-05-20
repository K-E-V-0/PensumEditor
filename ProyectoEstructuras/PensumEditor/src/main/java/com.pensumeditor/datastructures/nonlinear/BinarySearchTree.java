package com.pensumeditor.datastructures.nonlinear;

public class BinarySearchTree {
    public class Node {
        private int key;
        private Node left;
        private Node right;

        public Node(int key) {
            this.key = key;
            left = right = null;
        }

        public int getKey() {
            return key;
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

    public Node search(int value, Node node) {
        Node nextNode = node.left;
        if (node.key < value) {
            nextNode = node.right;
        }
        if (nextNode != null) {
            return search(value, nextNode);
        }
        return node;
    }

    public Node search(int value) {
        return search(value, root);
    }

    public Node insert(int key) {
        Node newNode = new Node(key);
        if (root == null) {
            root = newNode;
            return root;
        }
        Node insertNode = search(key);
        if (insertNode.key == key) {
            inOrderTraversal();
            return insertNode;
        }
        if (insertNode.key > key) {
            insertNode.left = newNode;
        } else {
            insertNode.right = newNode;
        }
        return newNode;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public Node delete(Node root, int key) {
        // Return if the tree is empty
        /*if (root == null)
            return root;

        // Find the node to be deleted
        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            // If the node is with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // If the node has two children
            // Place the inorder successor in position of the node to be deleted
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = delete(root.right, root.key);
        }*/
        return root;
    }

    public Node next(Node node) {
        if (node.right != null) {
            return LeftDescendant(node.right);
        } else {
            return RightAncestor(node);
        }

    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }
    public void inOrderTraversal(Node node) {
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
    public void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
        System.out.println();
    }
    public void postOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
            System.out.print(node.key + " ");
        }
    }

}

