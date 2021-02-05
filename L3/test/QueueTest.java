import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void queues () throws EmptyQueueE {
        Queue<Integer> q1 = new SlowQueue<>();
        for (int i=0; i<10; i++) {
            q1.enqueue(i);
        }
        for (int i=0; i<10; i++) {
            assertEquals(i, q1.getFront());
            q1.dequeue();
        }

        Queue<Integer> q2 = new AmortizedQueue<>();
        for (int i=0; i<10; i++) {
            q2.enqueue(i);
        }
        for (int i=0; i<10; i++) {
            assertEquals(i, q2.getFront());
            q2.dequeue();
        }
    }

    Duration timeQueue (Random r, int size, int bound, Queue<Integer> q) throws EmptyQueueE {

        Instant start = Instant.now();

        for (int i=0; i<100; i++) {
            q.enqueue(r.nextInt(bound));
        }

        for (int i=0; i<size; i++) {
            if (r.nextBoolean()) {
                int e = r.nextInt(bound);
                q.enqueue(e);
            }
            else {
                q.dequeue();
            }
        }

        Instant end = Instant.now();
        return Duration.between(start, end);
    }

    @Test
    void timeQueues () throws EmptyQueueE {
        Random r = new Random(100);
        int size = 10000;
        int bound = 1000;

        Duration d1 = timeQueue(r, size, bound, new SlowQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on slow queue; time in ms = %d%n",
                size, d1.toMillis());

        Duration d2 = timeQueue(r, size, bound, new AmortizedQueue<>());
        System.out.printf("Running %d enqueue/dequeue operations on amortized queue; time in ms = %d%n",
                size, d2.toMillis());
    }

}