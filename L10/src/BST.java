import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class EmptyBSTE extends Exception {}

abstract class BST implements TreePrinter.PrintableNode {
    abstract boolean isEmpty ();
    abstract int getValue () throws EmptyBSTE;
    abstract BST getLeftTree () throws EmptyBSTE;
    abstract BST getRightTree () throws EmptyBSTE;
    abstract BST insert (int v);
    abstract BST delete (int v);
    abstract Pair<Integer, BST> deleteLeftMostChild() throws EmptyBSTE;

    // Look at BFSlevel.
    // Do not use BFSlevel to print out array; instead, modify the method itself to print out the tree.
    // You have to do more work than just printing out the levels to the tree - try
    // to format the output so that we can tell what values are in the subtrees; it doesn't need to be
    // as pretty as TreePrinter.
    static void BFSPrint (BST t) {
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        ArrayList<Integer> currentLevel = new ArrayList();
        Queue<BST> treeQ = new LinkedList<>();
        Queue<BST> subtreeQ = new LinkedList<>();
        treeQ.add(t);

        //int i = t.height();
        while (!treeQ.isEmpty()){
            System.out.println("Hi");
            while (!treeQ.isEmpty()) {
                try {
                    BST ct = treeQ.remove();
                    //currentLevel.add(ct.getValue());
                    System.out.println()
                    subtreeQ.add(ct.getLeftTree());
                    subtreeQ.add(ct.getRightTree());
                } catch (EmptyBSTE ignored) {currentLevel.add(-42);}
            }
            result.add(currentLevel);
            System.out.println(currentLevel.toString());
            System.out.println("");
            currentLevel = new ArrayList<>();
            treeQ.addAll(subtreeQ);
            subtreeQ= new LinkedList<>();

        }

        // TODO extra credit
    }
}

class Empty extends BST {

    boolean isEmpty () { return true; }

    int getValue() throws EmptyBSTE { throw new EmptyBSTE(); }

    BST getLeftTree () throws EmptyBSTE { throw new EmptyBSTE(); }

    BST getRightTree () throws EmptyBSTE { throw new EmptyBSTE(); }

    BST insert (int v) {
        return new BSTNode(v, this, this);
    }

    // deletes int v from tree; if v not in tree, returns tree as is
    BST delete (int v) {
        return this;
    }

    // deletes leftmost child from empty tree... since leaf doesn't exist,
    // what should we do here ?
    Pair<Integer, BST> deleteLeftMostChild() throws EmptyBSTE {




        throw new EmptyBSTE();
    }

    public TreePrinter.PrintableNode getLeft() { return null; }
    public TreePrinter.PrintableNode getRight() { return null; }
    public String getText() { return null; }
}

class BSTNode extends BST {
    private final int value;
    private final BST leftTree;
    private final BST rightTree;

    BSTNode(int value, BST leftTree, BST rightTree) {
        this.value = value;
        this.leftTree = leftTree;
        this.rightTree = rightTree;
    }

    boolean isEmpty () { return false; }

    int getValue() { return value; }

    BST getLeftTree () {
        return leftTree;
    }

    BST getRightTree () { return rightTree; }

    BST insert (int v) {
        if (v < this.value) {
            return new BSTNode(this.value, leftTree.insert(v), rightTree);
        } else {
            return new BSTNode(this.value, leftTree, rightTree.insert(v));
        }
    }

    // deletes int v from tree; if v not in tree, returns tree as is
    // (hint: we know that int v is not in tree after we have traversed what was necessary)
    // if int v is the root, we want to delete it and replace with leftmost leaf in the right subtree
    BST delete (int v) {
        BST newBST = new Empty();
        Pair temp;


            if (v == value) {
                try {
                temp = rightTree.deleteLeftMostChild();
                int newValue = (int) temp.getFirst();
                BST newRightTree = (BST) temp.getSecond();

                return new BSTNode(newValue, leftTree, newRightTree);
                } catch (EmptyBSTE e) {return leftTree;}
            } else if (v < value) {
                return new BSTNode(value, leftTree.delete(v), rightTree);
            } else if (v > value) {
                return new BSTNode(value, leftTree, rightTree.delete(v));
            }
                return newBST;

    }

    // returns a pair with the value of the leftmost leaf and the right subtree of this leftmost leaf
    // a very helpful visualization is posted on the canvas Lab 10 page.

    Pair<Integer, BST> deleteLeftMostChild() {

       


        if (leftTree.isEmpty()) {
            return new Pair(getValue(), rightTree);
        } else {
            int valx = getValue();
            BST toRecurr = leftTree;
            while (!toRecurr.isEmpty()) {
                try {
                    valx = toRecurr.getValue();
                    toRecurr = toRecurr.getLeftTree();
                } catch (EmptyBSTE t) { }
            }
            return new Pair(valx, new BSTNode(value, leftTree.delete(valx), rightTree));

        }


    }


    public TreePrinter.PrintableNode getLeft() {
        if (leftTree.isEmpty()) return null;
        else return leftTree;
    }
    public TreePrinter.PrintableNode getRight() {
        if (rightTree.isEmpty()) return null;
        else return rightTree;
    }

    public String getText() { return String.valueOf(value); }
}