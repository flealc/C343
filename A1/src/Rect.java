public class Rect {
    private final int xmin;
    private final int ymin;
    private final int xmax;
    private final int ymax;

    Rect(int xmin, int ymin, int xmax, int ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    /**
     * Returns true if the point (x,y) is inside the
     * current rectangle. Touching the sides of the rectangle
     * counts as being inside it.
     */
    boolean contains(int x, int y) {
        return x >= xmin && x <= xmax &&
                y >= ymin && y <= ymax;
    }
    

    /**
     * Returns true if the given rectangle r intersects the
     * current one. If the two rectangles touch in even
     * one point, that counts as an intersection.
     */
    boolean intersect(Rect r) {
        return contains(r.xmin, r.ymax) || contains(r.xmax, r.ymin) ||
                contains(r.xmax, r.ymax) || contains(r.xmin, r.ymin) || r.contains(this.xmin, this.ymax) ||
                r.contains(this.xmax, this.ymin) || r.contains(this.xmax, this.ymax)
                || r.contains(this.xmin, this.ymin); // TODO
    }

    public String toString() {
        return String.format("R[(%d,%d)--(%d,%d)]", xmin, ymin, xmax, ymax);
    }
}
