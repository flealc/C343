import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Function;

class EmptyE extends Exception {}

@FunctionalInterface
interface TriFunction<A,B,C,R> {
    R apply (A a, B b, C c);
}

abstract class Tree implements TreePrinter.PrintableNode {
    abstract boolean isEmpty ();
    abstract int getValue () throws EmptyE;
    abstract Tree getLeftTree () throws EmptyE;
    abstract Tree getRightTree () throws EmptyE;

    /**
     * Returns the height of the tree as calculated by the constructor
     */
    abstract int height ();

    /**
     * Flip the left and right subtrees at every level
     */
    abstract Tree mirror();

    /**
     * Always insert 'v' in the left substree but flip the subtrees
     * so that next insertion goes in the other subtree
     */
    abstract Tree insert (int v);

    /**
     * Each node has several paths that reach empty nodes and these paths
     * may have different lengths. This method computes the number of
     * paths of maximum length that start at the given node.
     */
    abstract int numberMaxPaths ();

    /**
     * Each node has paths that reach empty nodes. This method returns
     * the maximum sum of values along any path that start at the given
     * node
     */
    abstract int maxSum ();

    /**
     * To go from node to another, we can move up from one node to a
     * common ancestor and then move down. The largest such distance
     * is called the diameter of the tree.
     */
    abstract int diameter ();

    /**
     * Returns a new tree in which the given function was applied at
     * every node of the current tree
     */
    abstract Tree map (Function<Integer,Integer> f);

    /**
     * At each node, recursively call reduce on the left subtree and the
     * right subtree and then apply the given function to the value at
     * the node and the results of the recursive calls
     */
    abstract int reduce (int base, TriFunction<Integer,Integer,Integer,Integer> f);

    static ArrayList<Integer> DFSRec (Tree t) {
        try {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(t.getValue());
            result.addAll(DFSRec(t.getRightTree()));
            result.addAll(DFSRec(t.getLeftTree()));
            return result;
        }
        catch (EmptyE e) {
            return new ArrayList<>();
        }
    }

    static ArrayList<Integer> DFSIter (Tree t) {
        ArrayList<Integer> result = new ArrayList<>();

        Stack<Tree> stack = new Stack<>();
        stack.push(t);

        while (!stack.isEmpty()) {
            try {
                Tree ct = stack.pop();
                result.add(ct.getValue());
                stack.add(ct.getLeftTree());
                stack.add(ct.getRightTree());
            }
            catch (EmptyE ignored) {}
        }

        return result;
    }

    /**
     * Traverses a tree returning the nodes in breadth first order.
     * The first element in the list is the root, then its two children
     * from left to right, then their four children from left to right, etc.
     */
    static ArrayList<Integer> BFS (Tree t) {
        return null; // TODO
    }

    /**
     * Same as BFS except that the values at each level are collected
     * in their own list
     */
    static ArrayList<ArrayList<Integer>> BFSLevel (Tree t) {
        return null; // TODO
    }

    static Tree fromArray (int[] vs) {
        Tree t = new Empty();
        for (int v : vs) t = t.insert(v);
        return t;
    }
}

class Empty extends Tree {

    boolean isEmpty () { return true; }

    int getValue() throws EmptyE { throw new EmptyE(); }

    Tree getLeftTree () throws EmptyE { throw new EmptyE(); }

    Tree getRightTree () throws EmptyE { throw new EmptyE(); }

    int height () {
        return 0; // TODO
    }

    Tree mirror() {
        return null; // TODO
    }

    Tree insert (int v) {
        return null; // TODO
    }

    int numberMaxPaths() {
        return 0; // TODO
    }

    int maxSum () {
        return 0; // TODO
    }

    int diameter () {
        return 0; // TODO
    }

    Tree map (Function<Integer,Integer> f) {
        return null; // TODO
    }

    int reduce (int base, TriFunction<Integer,Integer,Integer,Integer> f) {
        return 0; // TODO
    }

    public TreePrinter.PrintableNode getLeft() { return null; }
    public TreePrinter.PrintableNode getRight() { return null; }
    public String getText() { return null; }
}

class Node extends Tree {
    private final int value;
    private final Tree leftTree;
    private final Tree rightTree;
    private final int height;

    Node (int value, Tree leftTree, Tree rightTree) {
        this.value = value;
        this.leftTree = leftTree;
        this.rightTree = rightTree;
        this.height = 1 + Math.max(leftTree.height(), rightTree.height());
    }

    boolean isEmpty () { return false; }

    int getValue() { return value; }

    Tree getLeftTree () {
        return leftTree;
    }

    Tree getRightTree () { return rightTree; }

    int height () {
        return 0; // TODO
    }

    Tree mirror() {
        return null; // TODO
    }

    Tree insert (int v) {
        return null; // TODO
    }

    int numberMaxPaths() {
        return 0; // TODO
    }

    int maxSum () {
        return 0; // TODO
    }

    int diameter () {
        return 0; // TODO
    }

    Tree map (Function<Integer,Integer> f) {
        return null; // TODO
    }

    int reduce (int base, TriFunction<Integer,Integer,Integer,Integer> f) {
        return 0; // TODO
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