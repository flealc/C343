import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    @Test
    public void basic () {
        Tree t1, t2, t3, t4, t5, t6;
        t1 = new Node(5, new Empty(), new Empty());
        t2 = new Node(30, new Empty(), new Empty());
        t3 = new Node(2, t1, t2);
        t4 = new Node(38, t3.map(k -> k+1), t3.mirror().insert(100));
        t5 = Tree.fromArray(new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        t6 = new Node(0, new Node(-1, new Empty(), new Empty()), t5);

       // TreePrinter.print(t1);
       // TreePrinter.print(t2);
        //TreePrinter.print(t3);
        TreePrinter.print(t4);
        TreePrinter.print(t5);
        TreePrinter.print(t6);


        assertEquals(4, t4.height());
        assertEquals(2, t4.numberMaxPaths());
        assertEquals(16, t5.numberMaxPaths());
        assertEquals(7, t4.diameter());
        assertEquals(8, t5.diameter());
        assertEquals(8,t6.diameter());
        assertEquals(215, t4.reduce(0, (a,b,c) -> a+b+c));
        assertEquals(170, t4.maxSum());


    }

    @Test
    public void mirrorTest() throws EmptyE{
        Tree t1, t2, t3, t4, t5;
        t1 = new Node(5, new Empty(), new Empty());
        t2 = new Node(30, new Empty(), t1);
        t3 = new Node(2, t1, t2);
        t4 = new Node(5, t3, t2);
        t5 = t4.mirror();

        assertEquals(t4.getLeftTree().getValue(),t5.getRightTree().getValue());

    }
    @Test
    public void insertTest() throws EmptyE{
        Tree t1, t2, t3, t4, t5, t6, t7, t8, t9;
        t1 = new Node(1, new Empty(), new Empty());
        t2 = t1.insert(2);
        t3 = t2.insert(3);
        t4 = t3.insert(4);
        t5 = t4.insert(5);
        t6 = t5.insert(6);
        t7 = t6.insert(7);
        t8 = t7.insert(8);
        t9 = new Node(1, new Node (2, new Empty(), new Empty()),new Node(3, new Node (4, new Empty(), new Empty()), new Node(5, new Empty(), new Empty())));
        TreePrinter.print(t9);
      // System.out.println(t9.reduce(1, (a,b,c) -> a+b+c));
        System.out.println(Tree.BFSLevel(t9));
    }
}
