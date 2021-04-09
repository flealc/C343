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
        System.out.println(List.cycleStart(ls));
        }

        @Test
        public void myCycleTest () {

                List<Integer> cycleLast = new NodeL<>(15, new EmptyL<>());
                List<Integer> cycle = new NodeL<>(6,
                        new NodeL<>(146,
                                new NodeL<>(27,
                                        new NodeL<>(52,
                                                new NodeL<>(65,
                                                        new NodeL<>(37,
                                                                new NodeL<>(12,
                                                        cycleLast)))))));
                cycleLast.setRest(cycle);

                List<Integer> ls = new NodeL<>(1,
                        new NodeL<>(34,
                                cycle));
                System.out.println(List.cycleStart(ls));
        }

        @Test
        public void noCycleTest () {


                List<Integer> noCycle = new NodeL<>(6,
                        new NodeL<>(146,
                                new NodeL<>(27,
                                        new NodeL<>(52,
                                                new NodeL<>(65,
                                                        new NodeL<>(37,
                                                                new NodeL<>(12,
                                                                        new EmptyL<>())))))));



                System.out.println(List.cycleStart(noCycle));
        }
}
