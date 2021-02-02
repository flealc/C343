import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    @Test
    void lists() throws EmptyListE {
        List<Integer> ints =
                new NodeL<>(5,
                        new NodeL<>(4,
                                new NodeL<>(3,
                                        new NodeL<>(2,
                                                new NodeL<>(1,
                                                        new NodeL<>(0,
                                                                new EmptyL<>()))))));
        assertEquals(ints, List.countdown(5));

        List<Integer> ints100 =
                new NodeL<>(5,
                        new NodeL<>(4,
                                new NodeL<>(3,
                                        new NodeL<>(100,
                                                new NodeL<>(2,
                                                        new NodeL<>(1,
                                                                new NodeL<>(0,
                                                                        new EmptyL<>())))))));

        assertEquals(ints100, ints.insertAfter(3, 100));

        assertEquals(ints, ints100.removeFirst(100));

        assertEquals(3, ints.indexOf(2));

        List<Integer> intsEvens =
                new NodeL<>(4,
                        new NodeL<>(2,
                                new NodeL<>(0,
                                        new EmptyL<>())));

        List<Integer> intsOdds =
                new NodeL<>(5,
                        new NodeL<>(3,
                                new NodeL<>(1,
                                        new EmptyL<>())));

        assertEquals(intsEvens, ints.filter(n -> n % 2 == 0));
        assertEquals(intsOdds, ints.filter(n -> n % 2 == 1));

        List<Integer> intsSquared =
                new NodeL<>(25,
                        new NodeL<>(16,
                                new NodeL<>(9,
                                        new NodeL<>(4,
                                                new NodeL<>(1,
                                                        new NodeL<>(0,
                                                                new EmptyL<>()))))));

        assertEquals(intsSquared, ints.map(n -> n * n));

        assertEquals(15, ints.reduce(0, Integer::sum));

        List<Integer> intsEvensOdds =
                new NodeL<>(5,
                        new NodeL<>(3,
                                new NodeL<>(1,
                                        new NodeL<>(4,
                                                new NodeL<>(2,
                                                        new NodeL<>(0,
                                                                new EmptyL<>()))))));

        assertEquals(intsEvensOdds, intsOdds.append(intsEvens));

        List<Integer> intsRev =
                new NodeL<>(0,
                        new NodeL<>(1,
                                new NodeL<>(2,
                                        new NodeL<>(3,
                                                new NodeL<>(4,
                                                        new NodeL<>(5,
                                                                new EmptyL<>()))))));

        assertEquals(intsRev, ints.reverse());

        List<List<Integer>> evensPS = intsEvens.powerSet();

        assertEquals(8, evensPS.length());
        assertTrue(evensPS.inList(new EmptyL<>()));
        assertTrue(evensPS.inList(new NodeL<>(0, new EmptyL<>())));
        assertTrue(evensPS.inList(new NodeL<>(2, new EmptyL<>())));
        assertTrue(evensPS.inList(new NodeL<>(4, new EmptyL<>())));
        assertTrue(evensPS.inList(new NodeL<>(4, new NodeL<>(2, new EmptyL<>()))));
        assertTrue(evensPS.inList(new NodeL<>(4, new NodeL<>(0, new EmptyL<>()))));
        assertTrue(evensPS.inList(new NodeL<>(2, new NodeL<>(0, new EmptyL<>()))));
        assertTrue(evensPS.inList(new NodeL<>(4, new NodeL<>(2, new NodeL<>(0, new EmptyL<>())))));
    }

    /**
     * My Test Cases
     */

    @Test
    void length() {
        List<Integer> emptyList = new EmptyL<>();
        List<Integer> listOf1 = List.countdown(0);
        List<Integer> listOf10 = List.countdown(9);

        assertEquals(1, listOf1.length());
        assertEquals(10, listOf10.length());
        assertEquals(0, emptyList.length());

    }

    @Test
    void inList() {
        List<Integer> listOf10 = List.countdown(9);
        List<Integer> emptyList = new EmptyL<>();

        assertTrue(listOf10.inList(3));
        assertTrue(listOf10.inList(9));
        assertTrue(listOf10.inList(0));
        assertFalse(listOf10.inList(10));
        assertFalse(emptyList.inList(3));
    }

    @Test
    void insertAfter() {
        List<Integer> emptyList = new EmptyL<>();

        List<Integer> missing3 =
                new NodeL<>(5,
                        new NodeL<>(4,
                                new NodeL<>(2,
                                        new NodeL<>(1,
                                                new NodeL<>(0,
                                                        new EmptyL<>())))));
        List<Integer> double4 =
                new NodeL<>(5,
                        new NodeL<>(4,
                                new NodeL<>(4,
                                        new NodeL<>(1,
                                                new NodeL<>(0,
                                                        new EmptyL<>())))));

        List<Integer> double43 = double4.insertAfter(4, 3);

        assertEquals(emptyList.insertAfter(4, 5), emptyList);

        List<Integer> complete = missing3.insertAfter(4, 3);
        assertTrue(complete.inList(5));
        assertTrue(complete.inList(4));
        assertTrue(complete.inList(3));
        assertTrue(complete.inList(2));
        assertTrue(complete.inList(1));
        assertTrue(complete.inList(0));
        assertTrue(double43.inList(3));
        assertEquals(5, missing3.length());
        assertEquals(6, complete.length());
        assertEquals(7, double43.length());

    }

    @Test
    void removeFirst() {
        List<Integer> listOf5 = List.countdown(5);
        List<Integer> double4 = listOf5.insertAfter(4, 4);
        List<Integer> removeDup4 = double4.removeFirst(4);
        List<Integer> missing5 = listOf5.removeFirst(5);
        List<Integer> missing3 = listOf5.removeFirst(3);
        List<Integer> missing0 = listOf5.removeFirst(0);
        List emptyList = new EmptyL<>();

        assertEquals(emptyList.removeFirst(3),new EmptyL<>());
        assertFalse(missing3.inList(3));
        assertEquals(5, missing3.length());
        assertEquals(listOf5, removeDup4);
        assertFalse(missing0.inList(0));
        assertTrue(missing0.inList(1));
        assertTrue(missing0.inList(2));
        assertTrue(missing0.inList(3));
        assertTrue(missing0.inList(4));
        assertTrue(missing0.inList(5));
        assertTrue(missing5.inList(4));
        assertEquals(5, missing5.length());

    }

    @Test
    void indexOf() throws EmptyListE {
        List<Integer> listOf5 = List.countdown(5);
        List emptyList = new EmptyL<>();
        assertEquals(0, listOf5.indexOf(5));
        assertEquals(1, listOf5.indexOf(4));
        assertEquals(2, listOf5.indexOf(3));
        assertEquals(4, listOf5.indexOf(1));
        assertEquals(5, listOf5.indexOf(0));
        assertThrows(EmptyListE.class, () -> {emptyList.indexOf(3); });
    }

    @Test
    void filter() {
        List<Integer> listOf5 = List.countdown(5);
        List<Integer> lessThan4 = listOf5.filter(n -> n <= 3);
        List<Integer> notMultipleOf5 = List.countdown(50).filter(n -> n % 5 != 0);
        List emptyList = new EmptyL<>();

        assertEquals(emptyList.filter(n -> n.equals(listOf5)), new EmptyL<>());
        assertFalse(lessThan4.inList(4));
        assertFalse(lessThan4.inList(5));
        assertTrue(lessThan4.inList(3));
        assertTrue(notMultipleOf5.inList(3));
        assertTrue(notMultipleOf5.inList(27));
        assertTrue(notMultipleOf5.inList(43));
        assertFalse(notMultipleOf5.inList(50));
        assertFalse(notMultipleOf5.inList(25));
        assertFalse(notMultipleOf5.inList(0));
    }

    @Test
    void map() {
        List<Integer> listOf5 =
                new NodeL<>(5,
                        new NodeL<>(4,
                                new NodeL<>(3,
                                        new NodeL<>(2,
                                                new NodeL<>(1,
                                                        new NodeL<>(0,
                                                                new EmptyL<>()))))));
        List<Integer> list5To10 =
                new NodeL<>(10,
                        new NodeL<>(9,
                                new NodeL<>(8,
                                        new NodeL<>(7,
                                                new NodeL<>(6,
                                                        new NodeL<>(5,
                                                                new EmptyL<>()))))));

        List emptyList = new EmptyL<>();

        assertEquals(emptyList.map(n -> new NodeL<>(n, new EmptyL<>())), new EmptyL<>());
        assertEquals(listOf5, list5To10.map(n -> n - 5));
        assertEquals(list5To10, listOf5.map(n -> n + 5));
    }

    @Test
    void reduce() {
        List<Integer> list5To0 =
                new NodeL<>(5,
                        new NodeL<>(4,
                                new NodeL<>(3,
                                        new NodeL<>(2,
                                                new NodeL<>(1,
                                                        new NodeL<>(0,
                                                                new EmptyL<>()))))));
        List<Integer> list0To5 =
                new NodeL<>(0,
                        new NodeL<>(1,
                                new NodeL<>(2,
                                        new NodeL<>(3,
                                                new NodeL<>(4,
                                                        new NodeL<>(5,
                                                                new EmptyL<>()))))));
        List<Integer> emptyList = new EmptyL<>();

        List<Integer> listOf6 = List.countdown(6);

        assertEquals(list5To0.reduce(0, Integer::sum), list0To5.reduce(0, Integer::sum));
        assertNotEquals(list5To0.reduce(0, Integer::sum), listOf6.reduce(0, Integer::sum));
        assertEquals(0, emptyList.reduce(0, Integer::sum));

    }

    @Test
    void append() {
        List<String> abc =
                new NodeL<>("a",
                        new NodeL<>("b",
                                new NodeL<>("c",
                                        new EmptyL<>())));
        List<String> def =
                new NodeL<>("d",
                        new NodeL<>("e",
                                new NodeL<>("f",
                                        new EmptyL<>())));

        List<String> abcdef =
                new NodeL<>("a",
                        new NodeL<>("b",
                                new NodeL<>("c",
                                        new NodeL<>("d",
                                                new NodeL<>("e",
                                                        new NodeL<>("f",
                                                                new EmptyL<>()))))));

        List emptyList = new EmptyL<>();

        assertEquals(abcdef, abc.append(def));
        assertEquals(abc, emptyList.append(abc));
    }

    @Test
    void reverseHelper() throws EmptyListE {
        List<String> abc =
                new NodeL<>("a",
                        new NodeL<>("b",
                                new NodeL<>("c",
                                        new EmptyL<>())));
        List<String> cba =
                new NodeL<>("c",
                        new NodeL<>("b",
                                new NodeL<>("a",
                                        new EmptyL<>())));
        List<String> reverseCba = cba.reverse();
        List emptyList = new EmptyL<>();

        assertEquals(abc, cba.reverse());
        assertEquals(0, reverseCba.indexOf("a"));
        assertEquals(emptyList, emptyList.reverse());
    }

    @Test
    void powerSet() {

        List<String> abcdef =
                new NodeL<>("a",
                        new NodeL<>("b",
                                new NodeL<>("c",
                                        new NodeL<>("d",
                                                new NodeL<>("e",
                                                        new NodeL<>("f",
                                                                new EmptyL<>()))))));
        List<String> abc =
                new NodeL<>("a",
                        new NodeL<>("b",
                                new NodeL<>("c",
                                        new EmptyL<>())));
        List emptyList = new EmptyL<>();

        List<List<String>> abcdefPS = abcdef.powerSet();
        assertTrue(abcdefPS.inList(abc));
        assertTrue(abcdefPS.inList(new EmptyL<>()));
        assertTrue(emptyList.powerSet().inList(new EmptyL<>()));
    }

}




