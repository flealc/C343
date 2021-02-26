import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortTest {

    @Test
    void inc () {
        List<Integer> expected = Arrays.asList(1, 5, 19, 41, 109, 209, 505, 929, 2161, 3905, 8929,
                16001, 36289, 64769, 146305, 260609, 587521, 1045505, 2354689, 4188161);
        for (int i=0; i<20; i++) assertEquals(expected.get(i), Sort.increment(i));
    }

    @Test
    void digit () {
        assertEquals(7, Sort.getDigit(1347,0));
        assertEquals(4, Sort.getDigit(1347,1));
        assertEquals(3, Sort.getDigit(1347,2));
        assertEquals(1, Sort.getDigit(1347,3));

    }

    @Test
    void sort () {
        List<Integer> ns = Arrays.asList(70,100,203,784,412,135,101);
        List<Integer> sorted = Arrays.asList(70, 100, 101, 135, 203, 412, 784);

        assertEquals(sorted, Sort.streamSort(ns));
        assertEquals(sorted, Sort.insertionSort(ns));
       assertEquals(sorted, Sort.mergeSort(ns));
        assertEquals(sorted, Sort.shellSort(ns));
        assertEquals(sorted, Sort.radixSort(ns,3));
    }

    Duration timeM (Function<List<Integer>,List<Integer>> f, List<Integer> ns) {
        Instant start = Instant.now();
        f.apply(ns);
        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    @Test
    void timeSort () {
        Random r = new Random(500);
        int size = 10000;
        int bound = 1000;

        List<Integer> ns =
                r.ints(size, 0,bound).
                        boxed().
                        collect(Collectors.toList());

        long d;

        d = timeM(Sort::streamSort, ns).toMillis();
        System.out.printf("Java sort takes %d ms%n", d);

        d = timeM(Sort::insertionSort, ns).toMillis();
        System.out.printf("Insertion sort takes %d ms%n", d);

        d = timeM(Sort::mergeSort, ns).toMillis();
        System.out.printf("Merge sort takes %d ms%n", d);

        d = timeM(Sort::shellSort, ns).toMillis();
        System.out.printf("Shell sort takes %d ms%n", d);

        d = timeM(nums -> Sort.radixSort(nums, 3), ns).toMillis();
        System.out.printf("Radix sort takes %d ms%n", d);
    }

<<<<<<< HEAD

    /** My Test Cases */

    @Test
    void radixSortTest () {


        List<Integer> list = Arrays.asList(56,97,118,41,109,929,576,505);
        List<Integer> expected = Arrays.asList(41,56,97,109,118,505,576,929);
        assertEquals(expected, Sort.radixSort(list,3));


        List<Integer> list1 = Arrays.asList(89,102,18,41,32,876,6,505);
        List<Integer> expected1 = Arrays.asList(6,18,32,41,89,102,505,876);
        assertEquals(expected1, Sort.radixSort(list1,3));


    }

    @Test
    void mergeSort () {
        List<Integer> list = Arrays.asList(56,97,118,41,109,929,576,505);
        //List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        ///List<Integer> list = Arrays.asList(10, 9, 5, 7, 8, 3, 1, 2, 4, 6);
        List<Integer> expected = Arrays.asList(41,56,97, 109, 118, 505, 576, 929);
        //3, 1, 2, 4, 6, 10, 9, 5, 7, 8
        assertEquals(expected, Sort.shellSort(list));
    }
}
=======
}
>>>>>>> 84ab3fa1bf14a6fb9f53caf93905c6d9d9e15549
