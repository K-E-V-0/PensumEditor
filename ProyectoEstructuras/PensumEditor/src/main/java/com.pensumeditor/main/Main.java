package com.pensumeditor.main;

import com.pensumeditor.datastructures.nonlinear.BinarySearchTree;
import com.pensumeditor.datastructures.nonlinear.BinarySearchTree.*;
import com.pensumeditor.datastructures.nonlinear.Heap;

public class Main {

    public static void main(String[] args) {

        /*UserInterface userInterface = new UserInterface();

        userInterface.startMenu();*/

        Heap heaps = new Heap(20);
        heaps.insert(5);
        heaps.insert(8);
        heaps.insert(1);
        heaps.insert(4);
        heaps.insert(2);
        heaps.insert(6);
        heaps.insert(9);
        heaps.insert(8);
        heaps.insert(3);
        heaps.insert(6);
        heaps.insert(9);
        heaps.insert(25);
        heaps.insert(34);
        heaps.insert(12);
        heaps.insert(84);
        heaps.insert(63);
        heaps.insert(71);
        System.out.println(heaps);
        System.out.println(heaps.extractMax());
        System.out.println(heaps);
    }

}
