import java.util.ArrayList;
import java.util.List;

public class Sort {

    // helper function for sort
    // inorder traversal visits left subtree, node, and right subtree respectively
    static List<Integer> inOrder (Tree t) {
        List<Integer> sortedList = new ArrayList<>();

        try {
            sortedList.addAll(inOrder(t.getLeftTree()));
            sortedList.add(t.getValue());
            sortedList.addAll(inOrder(t.getRightTree()));
        } catch (EmptyE e) { return new ArrayList<>();}
       return sortedList;
    }

    // This method should sort the input list by building a BinaryTree
    // Traverse the resulting BinaryTree using inorder traversal to get a sorted ls
    static List<Integer> sort (List<Integer> ls) {
        Tree theTree = new Empty();
        for (int i = 0; i < ls.size(); i++ ) {
            theTree = theTree.insert(ls.get(i));
        }
        List<Integer> sorted = inOrder(theTree);
        return sorted;
    }
}