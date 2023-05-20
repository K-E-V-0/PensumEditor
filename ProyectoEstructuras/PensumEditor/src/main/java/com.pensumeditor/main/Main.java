package com.pensumeditor.main;

import com.pensumeditor.datastructures.nonlinear.BinarySearchTree;
import com.pensumeditor.datastructures.nonlinear.BinarySearchTree.*;
import com.pensumeditor.datastructures.nonlinear.Heap;

public class Main {

    public static void main(String[] args) {

        /*UserInterface userInterface = new UserInterface();

        userInterface.startMenu();*/

        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(4);
        bst.insert(3);
        bst.insert(6);
        bst.insert(7);
        bst.inOrderTraversal();
        bst.delete(2);
        bst.inOrderTraversal();
    }

}
