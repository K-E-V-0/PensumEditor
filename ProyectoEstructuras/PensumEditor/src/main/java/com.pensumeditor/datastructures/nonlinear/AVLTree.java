package com.pensumeditor.datastructures.nonlinear;

import com.pensumeditor.datastructures.linear.ArrayList;
import com.pensumeditor.datastructures.linear.List;

public class AVLTree {
    static class Node{
        private int key;
        private AVLTree.Node left;
        private AVLTree.Node right;
        private AVLTree.Node parent;
        int height;

        public Node(int key){
            this.key = key;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.height = 1;
        }
    }

}
