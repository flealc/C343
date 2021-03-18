import java.util.ArrayList;
import java.util.List;

public class Sort {

    // helper function for sort
    // inorder traversal visits left subtree, node, and right subtree respectively
    static List<Integer> inOrder (Tree t) {
        // TODO
        try {
            List<Integer> left = inOrder(t.getLeftTree());
            List<Integer> right = inOrder(t.getRightTree());
            left.add(t.getValue());
            left.addAll(right);
            return left;
        } catch (EmptyE e) {
            return new ArrayList<>();
        }
    }

    // This method should sort the input list by building a BinaryTree
    // Traverse the resulting BinaryTree using inorder traversal to get a sorted ls
    static List<Integer> sort (List<Integer> ls) {
        // TODO
        Tree bintree = new Empty();
        for (Integer i : ls) {
            bintree = bintree.insert(i);
        }
        return inOrder(bintree);
    }
}