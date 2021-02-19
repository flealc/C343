import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class SortTest {

    @Test
    void sort () {
        // TODO more correctness tests
        List<Integer> ns = Arrays.asList(70,100,203,784,412,135,101);

        System.out.printf("Original list = %s%n", ns);
        System.out.printf("insertionSort = %s%n%n", Sort.insertionSort(ns));

        /** My Cases */

        List<Integer> ns1 = Arrays.asList(1,89,3,55,8,34,21,13,5,2);
        assertTrue(Sort.insertionSort(ns1).get(3).equals(5));
        assertTrue(Sort.insertionSort(ns1).get(2).equals(3));
        assertFalse(Sort.insertionSort(ns1).get(1).equals(89));
        assertFalse(Sort.insertionSort(ns1).get(4).equals(55));


        List<Integer> ns2 = Arrays.asList(2,5,2,8,0,7,23,45,2,5);
        System.out.printf("Original list = %s%n", ns2);
        System.out.printf("insertionSort = %s%n%n", Sort.insertionSort(ns2));
    }

    @Test
    void timeSort () {
        List<Integer> worstCase = new ArrayList<Integer>();
        for (int i = 10000; i >= 0 ; i--){
            worstCase.add(i);
        }

        Instant start = Instant.now();
        // TODO efficiency tests
        // In particular, include a test that demonstrates why insertion sort's worst-case
        // performance is O(n^2)
        Sort.insertionSort(worstCase);

        Instant end = Instant.now();
        Duration time = Duration.between(start, end);
        System.out.println(time.toMillis());
    }

    @Test
    void averageCaseSort () {
        List<Integer> averageCase = new ArrayList<Integer>();
        Random r = new Random(500);
        for (int i = 0; i < 10000; i++){
            averageCase.add(r.nextInt());
        }

        Instant start = Instant.now();

        Sort.insertionSort(averageCase);

        Instant end = Instant.now();
        Duration time = Duration.between(start, end);
        System.out.println(time.toMillis());
    }

    @Test
    void bestCaseSort () {
        List<Integer> bestCase = new ArrayList<Integer>();

        for (int i = 0; i < 10000; i++){
            bestCase.add(i);
        }

        Instant start = Instant.now();

        Sort.insertionSort(bestCase);

        Instant end = Instant.now();
        Duration time = Duration.between(start, end);
        System.out.println(time.toMillis());
    }
}
