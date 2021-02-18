import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

class SortTest {

    @Test
    void sort () {
        // TODO more correctness tests
        List<Integer> ns = Arrays.asList(70,100,203,784,412,135,101);

        System.out.printf("Original list = %s%n", ns);
        System.out.printf("insertionSort = %s%n%n", Sort.insertionSort(ns));
    }

    @Test
    void timeSort () {
        Instant start = Instant.now();
        // TODO efficiency tests
        // In particular, include a test that demonstrates why insertion sort's worst-case
        // performance is O(n^2)
        Instant end = Instant.now();
        Duration time = Duration.between(start, end);
        System.out.println(time.toMillis());
    }

}
