
//-----------------------------------------------------------------------
// Empty AVL exception
//-----------------------------------------------------------------------

import com.sun.source.tree.Tree;

class EmptyAVLE extends Exception {}

//-----------------------------------------------------------------------
// Abstract AVL class
//-----------------------------------------------------------------------

abstract class AVL<E extends Comparable<E>> implements TreePrinter.PrintableNode {

    //--------------------------
    // Static fields and methods
    //--------------------------
    protected static int Rrotations, Lrotations;

    static void resetRotations () {
        Rrotations = 0;
        Lrotations = 0;
    }
    static int getRRotations() { return Rrotations; }
    static int getLRotations() { return Lrotations; }
    static int getRotations () { return Rrotations + Lrotations; }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    abstract E data() throws EmptyAVLE;
    abstract AVL<E> left() throws EmptyAVLE;
    abstract AVL<E> right() throws EmptyAVLE;
    abstract int height();
    abstract boolean isEmpty ();

    //--------------------------
    // Main methods
    //--------------------------

    abstract boolean find(E key);
    abstract AVL<E> insert(E data);
    abstract AVL<E> delete(E data);
    abstract Pair<E, AVL<E>> extractLeftMost() throws EmptyAVLE;
}

//-----------------------------------------------------------------------

class Empty<E extends Comparable<E>> extends AVL<E> {

    //--------------------------
    // Getters and simple methods
    //--------------------------

    E data() throws EmptyAVLE {
        throw new EmptyAVLE();
    }

    AVL<E> left() throws EmptyAVLE {
        throw new EmptyAVLE();
    }

    AVL<E> right() throws EmptyAVLE {
        throw new EmptyAVLE();
    }

    int height() { return 0; }

    boolean isEmpty () { return true; }

    //--------------------------
    // Main methods
    //--------------------------

    boolean find(E data) {
        return false;
    }

    AVL<E> insert(E data) {
        return new Node<>(data, new Empty<>(), new Empty<>());
    }

    AVL<E> delete(E key) {
        // ignore delete action if key not found
        return this;
    }

    Pair<E, AVL<E>> extractLeftMost() throws EmptyAVLE {
        throw new EmptyAVLE();
    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return null;
    }
    public TreePrinter.PrintableNode getRight() {
        return null;
    }
    public String getText() {
        return "";
    }
}

//-----------------------------------------------------------------------

class Node<E extends Comparable<E>> extends AVL<E> {
    private final E data;
    private final AVL<E> left;
    private final AVL<E> right;
    private final int height;

    Node(E data, AVL<E> left, AVL<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(left.height(),right.height());
    }

    /**
     * This method is essentially a constructor that guarantees
     * the result is balanced. In more detail:
     *   - if the heights of the left and right subtrees are
     *     no more than 1 apart then a new Node is constructed
     *     and returned; this node is balanced
     *   - if the height of the left subtree is larger than the
     *     height of the right subtree by 2 or more or vice versa
     *     then the constructed node will not be balanced. To
     *     balance it, a possible rotation is performed on either
     *     the left or right subtree, and then the constructed
     *     node is rotated in the appropriate direction.
     */
    Node<E> balance (E data, AVL<E> left, AVL<E> right) {

        if (right.height() + 1 < left.height()) {
            Node<E> newLeft = (Node<E>)left;
            newLeft = newLeft.possibleRotateLeft();
            Node<E> toReturn = new Node(data, newLeft, right);
            toReturn = toReturn.rotateRight();
            return toReturn;

        } else if (left.height() + 1 < right.height()) {
            Node<E> newRight = (Node<E>) right;
            newRight = newRight.possibleRotateRight();
            Node<E> toReturn1 = new Node(data, left, newRight);
            toReturn1 = toReturn1.rotateLeft();
            return toReturn1;
        }
            return new Node(data, left, right);

    }

    /**
     * If the current tree is unbalanced with the height of the left subtree
     * smaller than the height of the right subtree, then rotate left;
     * otherwise do nothing
     */
    Node<E> possibleRotateLeft () {

        if (right.height() > left.height()) {

                return new Node<E>(data, left, right).rotateLeft();

        }
        return new Node(data, left, right);

    }

    /**
     * If the current tree is unbalanced with the height of the right subtree
     * smaller than the height of the left subtree, then rotate right;
     * otherwise do nothing
     */
    Node<E> possibleRotateRight () {

        if (right.height() < left.height()) {
            return new Node<E>(data, left, right).rotateRight();
        }
        return new Node(data, left, right);

    }

    /**
     * This method should never be called if the left subtree is empty.
     * The method returns a new tree that is right-rotated as follows:
     *              x                          y
     *            /   \                      /   \
     *           y     C     ===>           A     x
     *         /   \                             /  \
     *        A    B                            B    C
     *
     * For testing purposes, we globally count the number of rotations
     * by incrementing Rrotations.
     */
    Node<E> rotateRight() {
        Rrotations++;
        assert !left.isEmpty();
        Node<E> toReturn = new Node(0, new Empty(), new Empty());
        try {
           toReturn = new Node(left.data(), left.left(), new Node(data, left.right(), right));

        } catch (EmptyAVLE e) { }
        return toReturn;
    }

    /**
     * This is the symmetric case of rotateRight
     */
    Node<E> rotateLeft() {
        Lrotations++;
        assert !right.isEmpty();
        Node<E> toReturn = new Node(0, new Empty(), new Empty());
        try {
            toReturn =  new Node(right.data(), new Node(data, left, right.left()),right.right());

        } catch (EmptyAVLE e) {}
        return toReturn;
    }

    //--------------------------
    // Getters and simple methods
    //--------------------------

    E data() { return data; }
    AVL<E> left() { return left; }
    AVL<E> right() { return right; }
    int height() { return height; }
    boolean isEmpty () { return false; }

    //--------------------------
    // Main methods
    //--------------------------

    /**
     * If the given key is in the tree, it will either be
     * equal to the current 'data' or found in the left
     * subtree if it is smaller than the current data, or
     * in the right subtree if it is greater than the
     * current data.
     */
    boolean find(E key) {

        if (key.compareTo(data) == 0) return true;
        else if (key.compareTo(data) > 0) return right.find(key);
        else if (key.compareTo(data) < 0) return left.find(key);
        else return false;// TODO
    }

    /**
     * If the key is smaller than or equal to the current data
     * insert it to the left; otherwise insert it to the right
     * Of course the insertion might cause the tree to become
     * unbalanced and a corrective action must be taken to
     * re-balance it.
     */
    AVL<E> insert(E key) {

        if (key.compareTo(data) == 0 || (key.compareTo(data) == -1)) {
            return balance(data, left.insert(key), right);
        }
            return balance(data, left, right.insert(key));


    }

    /**
     * The key is first compared to the current data; if it
     * smaller then we recursively delete from the left
     * subtree and re-balance. If it is bigger we recursively
     * delete from the right subtree and re-balance.
     *
     * If however it is equal to the current data, it means
     * we need to replace the root of this tree by other
     * data. To maintain order, we will find the smallest
     * value in the right subtree and store in the current
     * node.
     */
    AVL<E> delete(E key) {
        AVL newAVL = new Empty();


        if (data.compareTo(key) > 0) {
            return balance(data, left.delete(key), right);
        } else if (data.compareTo(key) < 0) {
            return balance(data, left, right.delete(key));
        } else if (data.compareTo(key) == 0)

            try {
                newAVL= balance(right.extractLeftMost().getFirst(), left, right.extractLeftMost().getSecond());
            } catch (EmptyAVLE e) {newAVL = left;}

        return newAVL;

    }

    /**
     * This is what this method does pictorially:
     *
     *         a                 pair.first = c
     *       /   \               pair.second =
     *      b     C                    a
     *    /   \         ==>           /  \
     *   c     B                     b    C
     *    \                        /  \
     *     A                      A    B
     *
     * The leftmost node 'c' was returned as the first
     * component of the result. The second component
     * was the remaining tree without 'c'. Note however
     * that if removing 'c' were to make the tree
     * unbalanced, it would be balanced before returning it.
     */
    Pair<E, AVL<E>> extractLeftMost() {

try {

    return new Pair(left.extractLeftMost().getFirst(), balance(data, left.extractLeftMost().getSecond(), right));
} catch (EmptyAVLE e) {
    return new Pair(data, right);}



        /*
        if (left.isEmpty()) {
            return new Pair(data, right);
        } else {
            E valx = (E)data;
            AVL toRecurr = left;
            while (!toRecurr.isEmpty()) {
                try {
                    valx = (E)toRecurr.data();
                    toRecurr = toRecurr.left();
                } catch (EmptyAVLE t) { }
            }
            AVL toReturn = balance(data, left.delete(valx), right);
            return new Pair(valx, toReturn);
                    //new Node(data, left.delete(valx), right));

        }

 */

    }

    //--------------------------
    // Printable interface
    //--------------------------

    public TreePrinter.PrintableNode getLeft() {
        return left.isEmpty() ? null : left;
    }
    public TreePrinter.PrintableNode getRight() {
        return right.isEmpty() ? null : right;
    }
    public String getText() {
        return String.valueOf(data);
    }
}

//-----------------------------------------------------------------------
//-----------------------------------------------------------------------
