import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectTest {

    /**
     * This is a very minimal version of the method you need to write.
     * Your test cases should attempt to cover all patterns of
     * intersecting rectangles.
     *
     * We will run your testing method against several implementations
     * of "intersect" that might be incorrect in subtle ways. We expect
     * your test cases to pass when the implementation of "intersect"
     * is correct and to fail when the implementation of "intersect"
     * has errors. 
     */
    @Test
    void intersect() {
        Rect r1 = new Rect(0, 0, 10, 10);
        Rect r2 = new Rect(20, 20, 30, 30);
        Rect r3 = new Rect(5, 5, 15, 15);
        Rect r4 = new Rect(10, 10, 15, 15);
        Rect r5 = new Rect(0,0,0,0);
        Rect r6 = new Rect(1,1,1,1);
        Rect r7 = new Rect(-1,-1,1,1);

        assertFalse(r1.intersect(r2));
        assertTrue(r1.intersect(r3));
        assertTrue(r1.intersect(r4));
        assertTrue(r1.intersect(r5));
        assertFalse(r6.intersect(r5));
        assertTrue(r5.intersect(r7));
        assertTrue(r7.intersect(r5));
    }
}