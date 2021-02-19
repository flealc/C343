import java.util.ArrayList;
import java.util.List;

public class Sort {

    // This method should sort the input list using the algorithm for insertion sort.
    // That is, first create a new ArrayList with all of the elements from input ns.
    // Then, iterate through this new ArrayList - comparing a current element to its
    // predecessor. While current is less, it is swapped w predecessor.

    // For those who prefer wordier instructions, check out Lab 5 post on canvas :)
    // Otherwise, best of luck on the lab! Tests/debugging will help a lot with IndexOutOfBoundsExceptions
    static List<Integer> insertionSort(List<Integer> ns) {

        ArrayList<Integer> toSort = new ArrayList<Integer>(ns);


        for (int i = 1; i < ns.size(); i++) {
            int current = ns.get(i);
            int predIndex = i - 1;

            while (predIndex >= 0 && toSort.get(predIndex) > current) {
                toSort.set(predIndex + 1, toSort.get(predIndex));
                predIndex = predIndex - 1;
            }

            toSort.set(predIndex + 1, current);
        }

        return toSort;


    }
}

