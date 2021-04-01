import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AVLTest {

    @Test
    public void AVLinsert() {
        AVL.resetRotations();
        List<Integer> nums = IntStream.range(0, 4096).boxed().collect(Collectors.toList());
        Collections.shuffle(nums, new Random(100));
        AVL<Integer> avl = new Empty<>();
        for (int i : nums) avl = avl.insert(i);
        assertEquals(14, avl.height());
        assertEquals(1430, AVL.getRRotations());
        assertEquals(1425, AVL.getLRotations());
    }

    @Test
    public void dup () {
        AVL<Integer> avl = new Empty<>();
        avl = avl.insert(1);
        avl = avl.insert(1);
        avl = avl.insert(1);
        avl = avl.insert(1);
        avl = avl.insert(1);
        avl = avl.insert(1);
        avl = avl.insert(1);
        avl = avl.insert(1);
        avl = avl.insert(1);

        avl = avl.delete(1);
        avl = avl.delete(1);
        avl = avl.delete(1);
        avl = avl.delete(1);
        avl = avl.delete(1);
        avl = avl.delete(1);
        avl = avl.delete(1);
        avl = avl.delete(1);
        avl = avl.delete(1);

        assertTrue(avl.isEmpty());
    }

    @Test
    public void AVLeasyRight() throws EmptyAVLE {
        AVL.resetRotations();
        AVL<Integer> avl = new Empty<>();
        avl = avl.insert(40);
        assertEquals(0, AVL.getRotations());

        avl = avl.insert(50);
        assertEquals(0, AVL.getRotations());

        avl = avl.insert(20);
        assertEquals(0, AVL.getRotations());

        avl = avl.insert(10);
        assertEquals(0, AVL.getRotations());

        avl = avl.insert(30);
        assertEquals(0, AVL.getRotations());

        avl = avl.insert(15);
        assertEquals(1, AVL.getRRotations());
        assertEquals(0, AVL.getLRotations());

        AVL<Integer> left = avl.left();
        AVL<Integer> right = avl.right();
        assertEquals(20, avl.data());
        assertEquals(10,left.data());
        assertEquals(15, left.right().data());
        assertEquals(40,right.data());
        assertEquals(30, right.left().data());
        assertEquals(50, right.right().data());
    }

    @Test
    public void AVLrotateRight() throws EmptyAVLE {
        AVL.resetRotations();
        AVL<Integer> avl = new Empty<>();
        avl = avl.insert(40);
        avl = avl.insert(50);
        avl = avl.insert(20);
        avl = avl.insert(10);
        avl = avl.insert(30);
        assertEquals(0, AVL.getRotations());

        avl = avl.insert(25);
        assertEquals(1, AVL.getRRotations());
        assertEquals(1, AVL.getLRotations());

        AVL<Integer> left = avl.left();
        AVL<Integer> right = avl.right();
        assertEquals(30, avl.data());
        assertEquals(20,left.data());
        assertEquals(10, left.left().data());
        assertEquals(25, left.right().data());
        assertEquals(40,right.data());
        assertEquals(50, right.right().data());
    }

    @Test
    public void AVLdelete() throws EmptyAVLE {
        AVL.resetRotations();
        AVL<Integer> avl = new Empty<>();
        avl = avl.insert(35);
        avl = avl.insert(20);
        avl = avl.insert(40);
        avl = avl.insert(7);
        avl = avl.insert(30);
        avl = avl.insert(50);
        avl = avl.insert(5);
        avl = avl.insert(10);
        assertEquals(0,AVL.getRotations());

        AVL<Integer> avl2 = avl.delete(35);
        assertEquals(1,AVL.getRRotations());
        assertEquals(0,AVL.getLRotations());

        AVL<Integer> left = avl2.left();
        AVL<Integer> right = avl2.right();
        assertEquals(20, avl2.data());
        assertEquals(7,left.data());
        assertEquals(5, left.left().data());
        assertEquals(10, left.right().data());
        assertEquals(40,right.data());
        assertEquals(30, right.left().data());
        assertEquals(50, right.right().data());
    }

    @Test
    public void AVLdelete2 () {
        AVL.resetRotations();
        AVL<Integer> t = new Empty<>();
        t = t.insert(44);
        t = t.insert(17);
        t = t.insert(78);
        t = t.insert(32);
        t = t.insert(50);
        t = t.insert(88);
        t = t.insert(48);
        t = t.insert(62);
        TreePrinter.print(t);
        assertEquals(0,AVL.getRotations());
        t.delete(32);
        TreePrinter.print(t);
        assertEquals(1,AVL.getRRotations());
        assertEquals(1,AVL.getLRotations());
    }

    @Test
    public void posRot () throws  EmptyAVLE {

        AVL t = new Node(50,
                new Node(45,
                        new Node(42, new Empty(), new Empty()),
                        new Node(47, new Empty(), new Empty())),
                new Node(60, new Empty(), new Empty()));

        Node u = new Node(40, new Empty(), t);


        AVL v = new Node(20,
                new Node(10, new Empty(), new Empty()),
                new Node(30,
                new Node(25, new Empty(), new Empty()),
                new Empty()));


        AVL y = new Node(20,
                new Node(10, new Empty(), new Empty()),
                new Node(30,
                        new Node(25, new Empty(), new Empty()),
                        new Node(35, new Empty(), new Empty())));

        Node w = new Node(40, v, new Node(50, new Empty(), new Empty()));

        Node x = new Node(40, y, new Node(50, new Empty(), new Empty()));
        //TreePrinter.print(new Node(40, new Empty(), t));
        //TreePrinter.print(w.balance(40, new Empty(), t));
        AVL.resetRotations();;
        AVL empty = new Empty();
        TreePrinter.print(empty);
        empty = empty.insert(40);
        empty = empty.insert(50);
        empty = empty.insert(43);
        assertEquals(1, AVL.getLRotations());

        TreePrinter.print(empty);

        TreePrinter.print(t);
        TreePrinter.print(t.delete(47));

        //TreePrinter.print(x.balance(40, y, new Node(50, new Empty(), new Empty())));


       //TreePrinter.print(t);
      //System.out.println(t.extractLeftMost().getFirst().toString());
       // System.out.println(t.extractLeftMost().getSecond().toString());



    }

}

