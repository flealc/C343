import org.junit.jupiter.api.Test;


public class CycleDetectionTest {
        @Test
        public void cycle () {
                // TODO write your tests here. below is just one example of a linked list with a cycle
                // you should come up with your own examples
                List<Integer> lasteights = new NodeL<>(8, new EmptyL<>());
                List<Integer> lastthrees = new NodeL<>(3,
                        new NodeL<>(4,
                                new NodeL<>(5,
                                        new NodeL<>(6,
                                                new NodeL<>(7,
                                                        lasteights)))));
                lasteights.setRest(lastthrees);
                // below is the full list with the cycle
                List<Integer> ls = new NodeL<>(1,
                        new NodeL<>(2,
                                lastthrees));
        }
}
