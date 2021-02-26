import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CTest {

    @Test
    void CMTest() {
        int[][] cookies = {
                { 9, 4, 1, 2, 5},
                { 7, 2, 6, 4, 2},
                { 3, 8, 2, 2, 1},
                { 5, 2, 9, 3, 5},
                { 4, 9, 5, 9, 7},
        };

        int m = new CookieMonster(cookies,5).findBest();

        System.out.printf("m=%d%n", m);

        // TODO more correctness tests
    }
@Test
    void moreCMTest() {
        int[][] cookies1 = {
                { 9, 4, 1, 2, 5},
                { 7, 2, 6, 4, 2},
                { 3, 8, 2, 2, 1},
                { 5, 2, 10, 3, 5},
                { 4, 9, 5, 9, 7},
        };

        int m = new CookieMonster(cookies1,5).findBest();

        System.out.printf("m=%d%n", m);

        // TODO more correctness tests
    }
}