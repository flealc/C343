import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SeamCarving {
    private int[] pixels;
    private int type, height, width;
    /**

       pic 3x5
 [ (0,0), (0,1), (0,2), (0,3), (0,4), (1,0), (1,1), (1,2), (1,3), (1,4), (2,0), (2,1), (2,2), (2,3), (2,4) ]
height * width = 15
array[0] -- array[4] first row
array[5] -- array[9] second row
array[10] -- array[14] third row

seam is (0,1), (1,2), (2,2) 

after cut 

 [ (0,0), (0,2), (0,3), (0,4), (1,0), (1,1), (1,3), (1,4), (2,0), (2,1), (2,3), (2,4) ]

size 12, 3x4
     */

    // Field getters

    int[] getPixels() {
        return pixels;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    // Read and write images

    void readImage(String filename) throws IOException {
        BufferedImage image = ImageIO.read(new File(filename));
        type = image.getType();
        height = image.getHeight();
        width = image.getWidth();
        pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }

    void writeImage(String filename) throws IOException {
        BufferedImage image = new BufferedImage(width, height, type);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        ImageIO.write(image, "jpg", new File(filename));
    }

    // Accessing pixels and their neighbors

    Color getColor(int h, int w) {
        int pixel = pixels[w + h * width];
        return new Color(pixel, true);
    }

    // for a general position, returns the four neighbors above,
    // right, below, and left

    ArrayList<Position> getHVneighbors(int h, int w) {
        ArrayList<Position> neighbors = new ArrayList<>();

        if (w == 0) neighbors.add(new Position(h, w + 1));
        else if (w + 1 == width) neighbors.add(new Position(h, w - 1));
        else {
            neighbors.add(new Position(h, w - 1));
            neighbors.add(new Position(h, w + 1));
        }

        if (h == 0) neighbors.add(new Position(h + 1, w));
        else if (h + 1 == height) neighbors.add(new Position(h - 1, w));
        else {
            neighbors.add(new Position(h + 1, w));
            neighbors.add(new Position(h - 1, w));
        }
        
        return neighbors;
    }

    // For a general position, returns the three neighbors
    // under it: below left, below, and below right

    ArrayList<Position> getBelowNeighbors(int h, int w) {
        ArrayList<Position> neighbors = new ArrayList<>();
        if (h + 1 == height) return neighbors;
        neighbors.add(new Position(h + 1, w));
        if (w == 0) {
            neighbors.add(new Position(h + 1, w + 1));
            return neighbors;
        } else if (w + 1 == width) {
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        } else {
            neighbors.add(new Position(h + 1, w + 1));
            neighbors.add(new Position(h + 1, w - 1));
            return neighbors;
        }
    }

    // Computing energy at given pixel
    // Get the 4 surrounding neighbors and sum
    // the squares of the differences of RGB values

    int computeEnergy(int h, int w) {
        Color c = getColor(h, w);
        Function<Integer, Integer> sq = n -> n * n;
        int energy = 0;
        for (Position p : getHVneighbors(h, w)) {
            Color nc = getColor(p.getFirst(), p.getSecond());
            energy += sq.apply(nc.getRed() - c.getRed());
            energy += sq.apply(nc.getGreen() - c.getGreen());
            energy += sq.apply(nc.getBlue() - c.getBlue());
        }
        return energy;
    }

    // Find seam of minimum total energy starting from (h,w) going down
    // returns the list of positions in the seam and its cost
    //
    // use a hashtable to memoize the work
    //
    // The steps to follow are:
    // 1. Compute the energy at the current position
    // 2. Find the neighbors below the current position
    // 3. If there are no neighbors (we are at the bottom row), return the
    //    appropriate result
    // 4. Otherwise, recursively findSeam starting from each neighbor's
    //    position
    // 5. Return the minimum answer after adding the current node and current
    //    energy

    final Map<Position, Pair<List<Position>, Integer>> hash = new HashMap<>();

    Pair<List<Position>, Integer> findSeam(int h, int w) {
        return null; // TODO
	/*
	  has three positions under it: bottom-left, bottom, bottom-right
	 */
    }

    // Call findSeam for all position in the first row (h=0)
    // andd returns the best (the one with the lowest
    // total energy)
    //
    // CLEAR the hashtable before starting; each calculation
    // of bestSeam needs to start with a fresh hashtable
    // but all the calls the findSeam will share the same
    // hashtable

    Pair<List<Position>, Integer> bestSeam() {
	// init hashtable (clear it)
	// findSeam(0,0)
	// findSeam(0,1)
	// findSeam(0,2)
	// findSeam(0,3)
	// ...
	//	return min
        return null; // TODO
    }

    // Putting it all together; find best seam and copy pixels
    // without that seam
    //
    // the logic is to create a small array of pixels, copy all
    // the pixels from the old array to the new array except
    // the ones in the seam

    void cutSeam() {
      // TODO
    }
}


