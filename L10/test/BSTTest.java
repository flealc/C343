import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BSTTest {

    // TODO does it work ?
    @Test
    public void delete () throws EmptyBSTE {
        BST tree = new BSTNode(8, new Empty(), new Empty());
        tree = tree.insert(2);
        tree = tree.insert(4);
        tree = tree.insert(12);
        tree = tree.insert(14);
        tree = tree.insert(1);
        tree = tree.insert(7);
        tree = tree.insert(13);
        tree = tree.insert(16);
        tree = tree.insert(11);
        tree = tree.insert(11);
        tree = tree.insert(9);
        tree = tree.insert(10);
        tree = tree.insert(8);
        tree = tree.insert(8);
        //TreePrinter.print(tree);
        //tree = tree.delete(9);
        BST.BFSPrint(tree);
    }
}
