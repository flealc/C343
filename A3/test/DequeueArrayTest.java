import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DequeueArrayTest {

    @Test
    public void dequeDoubleNoResize() throws NoSuchElementE {
        DequeueArray<Integer> d = new DequeueArrayDouble<>(5);
        assertEquals(0, d.size());
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        assertEquals(3, d.removeFirst());
        assertEquals(1, d.removeLast());
        assertEquals(2, d.removeLast());
        assertEquals(0, d.size());
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        assertEquals(5, d.removeFirst());
        assertEquals(3, d.removeFirst());
        assertEquals(1, d.removeFirst());
        assertEquals(2, d.removeFirst());
        assertEquals(4, d.removeFirst());
    }

    @Test
    public void dequeDoubleResize() throws NoSuchElementE {
        DequeueArray<Integer> d = new DequeueArrayDouble<>(5);
        d.addLast(1);
        d.addLast(2);
        d.addFirst(3);
        d.addLast(4);
        d.addFirst(5);
        d.addFirst(6);
        assertEquals(10, d.getCapacity());
        assertEquals(6, d.removeFirst());
        assertEquals(5, d.removeFirst());
        assertEquals(3, d.removeFirst());
        assertEquals(1, d.removeFirst());
        assertEquals(2, d.removeFirst());
        assertEquals(4, d.removeFirst());
    }

    Duration timeDequeue (Random r, int size, int bound, DequeueArray<Integer> dq) throws NoSuchElementE {
        Instant start = Instant.now();

        for (int i=0; i<1000; i++) {
            dq.addFirst(r.nextInt(bound));
        }

        for (int i=0; i<size; i++) {
            int e = r.nextInt(bound);
            switch (r.nextInt(4)) {
                case 0 -> dq.addFirst(e);
                case 1 -> dq.addLast(e);
                case 2 -> dq.removeFirst();
                case 3 -> dq.removeLast();
                default -> throw new Error("Impossible");
            }
        }

        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    @Test
    void timeDeQueues () throws NoSuchElementE {
        Random r = new Random(500);
        int size = 10000;
        int bound = 1000;

        Duration d1 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d1.toMillis());

        Duration d2 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d2.toMillis());

        Duration d3 = timeDequeue(r, size, bound, new DequeueArrayPlusOne<>(100));
        System.out.printf("Running %d operations (size +1); time in ms = %d%n",
                size, d3.toMillis());
    }

    /** My test cases */
    @Test
    public void dequeueOneAndHalfResize() throws NoSuchElementE {
        DequeueArray<Integer> d15 = new DequeueArrayOneAndHalf<>(5);
        d15.addLast(1);
        d15.addLast(2);
        d15.addFirst(3);
        d15.addLast(4);
        d15.addFirst(5);
        d15.addFirst(6);
        d15.addFirst(7);
        d15.addFirst(8);
        assertEquals(8, d15.getCapacity());
        assertEquals(8, d15.removeFirst());
        assertEquals(7, d15.removeFirst());
        assertEquals(6, d15.removeFirst());
        assertEquals(5, d15.removeFirst());
        assertEquals(3, d15.removeFirst());
        assertEquals(1, d15.removeFirst());
        assertEquals(2, d15.removeFirst());
        assertEquals(4, d15.removeFirst());
        d15.addFirst(10);
        assertEquals(10, d15.removeLast());



    }
    @Test
    public void dequeuePlusOneResize() throws NoSuchElementE {
        DequeueArray<Integer> d1 = new DequeueArrayPlusOne<>(5);
        d1.addLast(1);
        d1.addLast(2);
        d1.addFirst(3);
        d1.addLast(4);
        d1.addFirst(5);
        d1.addFirst(6);
        d1.addFirst(7);
        d1.addFirst(8);
        assertEquals(8, d1.getCapacity());
        assertEquals(8, d1.removeFirst());
        assertEquals(7, d1.removeFirst());
        assertEquals(6, d1.removeFirst());
        assertEquals(5, d1.removeFirst());
        assertEquals(3, d1.removeFirst());
        assertEquals(1, d1.removeFirst());
        assertEquals(2, d1.removeFirst());
        assertEquals(4, d1.removeFirst());
        d1.addFirst(10);
        assertEquals(10, d1.removeLast());




    }


    @Test
    void myTimeDeQueues () throws NoSuchElementE {
        Random r = new Random(500);
        int size = 1000;
        int bound = 1000;

        System.out.println();

        Duration d1 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d1.toMillis());

        Duration d2 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d2.toMillis());

        Duration d3 = timeDequeue(r, size, bound, new DequeueArrayPlusOne<>(100));
        System.out.printf("Running %d operations (size +1); time in ms = %d%n",
                size, d3.toMillis());


        size = 50000;
        System.out.println();

        Duration d4 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d4.toMillis());

        Duration d5 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d5.toMillis());

        Duration d6 = timeDequeue(r, size, bound, new DequeueArrayPlusOne<>(100));
        System.out.printf("Running %d operations (size +1); time in ms = %d%n",
                size, d6.toMillis());


        size = 100000;
        System.out.println();

        Duration d7 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d7.toMillis());

        Duration d8 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d8.toMillis());

        Duration d9 = timeDequeue(r, size, bound, new DequeueArrayPlusOne<>(100));
        System.out.printf("Running %d operations (size +1); time in ms = %d%n",
                size, d9.toMillis());

        size = 700000;
        System.out.println();
        System.out.println("From here on the PlusOne test just crashes");
        System.out.println();

        Duration d10 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d10.toMillis());

        Duration d11 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d11.toMillis());
    }

    @Test
    void myBigTimeDeQueues () throws NoSuchElementE {
        Random r = new Random(500);
        int size = 1000000;
        int bound = 1000;

        System.out.println();


        Duration d12 = timeDequeue(r, size, bound, new DequeueArrayDouble<>(100));
        System.out.printf("Running %d operations (size *2); time in ms = %d%n",
                size, d12.toMillis());

        Duration d13 = timeDequeue(r, size, bound, new DequeueArrayOneAndHalf<>(100));
        System.out.printf("Running %d operations (size *1.5); time in ms = %d%n",
                size, d13.toMillis());
    }


}

