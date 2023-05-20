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
        System.out.println(bst.next(bst.find(3)).getKey());
        System.out.println(bst.rangeSearch(3,5));
    }

}
