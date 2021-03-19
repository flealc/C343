import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    // TODO does it work ?

    @Test
    public void insert() throws EmptyE {
    Tree elm = new Node(5, new Empty(), new Empty());
    Tree newTree = elm.insert(8);
    System.out.println(newTree.getRightTree().getValue());
    }

    @Test
    public void sort () {
    ArrayList<Integer> aList = new ArrayList<>();
    aList.add(5);
    aList.add(6);
    aList.add(3);
    aList.add(5);
    aList.add(10);
    aList.add(42);

    System.out.println(Sort.sort(aList));
    }
}
