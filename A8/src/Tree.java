import java.util.*;
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
    static ArrayList<Integer> BFS (Tree t) { // TODO

        ArrayList<Integer> result = new ArrayList<>();

        Queue<Tree> queue = new LinkedList<>();
        queue.add(t);

        while (!queue.isEmpty()) {
            try {
                Tree ct = queue.remove();
                result.add(ct.getValue());
                queue.add(ct.getLeftTree());
                queue.add(ct.getRightTree());
            }
            catch (EmptyE ignored) {}
        }

        return result;


    }

    /**
     * Same as BFS except that the values at each level are collected
     * in their own list
     */
    static ArrayList<ArrayList<Integer>> BFSLevel (Tree t) { // TODO
        ArrayList<ArrayList<Integer>> result = new ArrayList();
       // try{
       //     result.add(new ArrayList<Integer>(t.getValue()));
        //} catch (EmptyE e) {return new ArrayList<ArrayList<Integer>>();}
        ArrayList<Integer> currentLevel = new ArrayList();
        Queue<Tree> treeQ = new LinkedList<>();
        Queue<Tree> subtreeQ = new LinkedList<>();

        treeQ.add(t);

            while (!treeQ.isEmpty()) {
                try {
                    currentLevel.clear();
                    Tree ct = treeQ.remove();
                    currentLevel.add(ct.getValue());
                    subtreeQ.add(ct.getLeftTree());
                    subtreeQ.add(ct.getRightTree());
                    result.add(currentLevel);
                    while (!subtreeQ.isEmpty()) {
                        currentLevel.clear();
                        Tree tt = subtreeQ.remove();
                        currentLevel.add(tt.getValue());
                        treeQ.add(tt.getLeftTree());
                        treeQ.add(tt.getRightTree());
                        result.add(currentLevel);
                    }
                } catch (EmptyE ignored) { }
            }

            result.add(currentLevel);
        return result;
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
        return 0;
    }

    Tree mirror() {
        return this;
    }

    Tree insert (int v) {
        return new Node(v, this, this);
    }

    int numberMaxPaths() {
        return 1;
    }

    int maxSum () { return 0;
    }

    int diameter () {
        return 0;
    }

    Tree map (Function<Integer,Integer> f) {
        return this;
    }

    int reduce (int base, TriFunction<Integer,Integer,Integer,Integer> f) {
        return base;
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
        return height;
    }

    Tree mirror() {
        Tree mirroredTree = new Node(getValue(), getRightTree().mirror(), getLeftTree().mirror());
        return mirroredTree;
    }

    Tree insert (int v) {

        if (leftTree.isEmpty()) {
            return new Node(value, rightTree, new Node(v, new Empty(), new Empty()));
        } else return new Node(value, rightTree, leftTree.insert(v));
        }


    int numberMaxPaths() {
       if (rightTree.height() == leftTree.height()) {
           return leftTree.numberMaxPaths() + rightTree.numberMaxPaths();
       }
        if (leftTree.height() < rightTree.height()) {
            return rightTree.numberMaxPaths();
        } else return leftTree.numberMaxPaths();
    }
    int maxSum () {

        if (height == 1) {
            return value;
        } else return value + Math.max(leftTree.maxSum(), rightTree.maxSum());
         }

    int diameter () {

        if (leftTree.height() == rightTree.height()) {
            return leftTree.height() + rightTree.height() + 2;
        } else return Math.max(Math.max(leftTree.diameter(), rightTree.diameter()), (leftTree.height() + rightTree.height() + 2));

    }

    Tree map (Function<Integer,Integer> f) {
        return new Node(f.apply(value), leftTree.map(f), rightTree.map(f));
    }

    int reduce (int base, TriFunction<Integer,Integer,Integer,Integer> f) {

        return f.apply(value, leftTree.reduce(base,f), rightTree.reduce(base,f));
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