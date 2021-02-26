import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All sorting algorithms must return a NEW sorted list. Do not
 * modify the incoming list
 */
public class Sort {
    static List<Integer> streamSort(List<Integer> ns) {
        return ns.stream().sorted().collect(Collectors.toList());
    }

    static List<Integer> insertionSort(List<Integer> ns) {
        ArrayList<Integer> toSort = new ArrayList<>(ns);
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

    static List<Integer> mergeSort(List<Integer> ns) {
        // real work done in PList
        return PList.toList(PList.fromList(ns).mergeSort());
    }

    static int increment(int n) {
        // From https://oeis.org/search?q=shell+sort
        // a(n) = 9*2^n - 9*2^(n/2) + 1 if n is even;
        // a(n) = 8*2^n - 6*2^((n+1)/2) + 1 if n is odd.
        if (n % 2 == 0)
            return (int) (9 * Math.pow(2, n) - 9 * Math.pow(2, n / 2) + 1);
        else
            return (int) (8 * Math.pow(2, n) - 6 * Math.pow(2, (n + 1) / 2) + 1);
    }

    /**
     * Steps:
     * 1. create an array incSequence that calls increment above until the
     * gap is more than half of the size of the array
     * 2. Start from the largest gap and iterate down the list of gaps
     * 3. For each gap, do an insertion sort for the elements separated
     * by the given gap
     */
    static List<Integer> shellSort(List<Integer> ns) {   // TODO

        ArrayList<Integer> incSequence = new ArrayList();
        for (int i = 0; increment(i) <= (ns.size() / 2); i++) {

            incSequence.add(i, increment(i));
        }


        ArrayList<Integer> toSort = new ArrayList(ns);


        for (int i = incSequence.size() - 1; i >= 0; i--) {
            int gap = incSequence.get(i);

            for (int j = gap; j < ns.size(); j++) {
                int temp = toSort.get(j);
                int k;
                for (k = j; k >= gap && toSort.get(k - gap) > temp; k = k - gap) {
                    toSort.set(k, toSort.get(k - gap));
                }
                toSort.set(k, temp);
            }
        }
            return toSort;
    }

    static int getDigit(int n, int d) {
        if (d == 0) return n % 10;
        else return getDigit(n / 10, d - 1);
    }

    /**
     * Steps:
     * 1. Create 10 buckets represented as ArrayLists, one for each digit
     * 2. For each digit d (d=0 to len-1) with 0 the least significant digit,
     * do the following
     * 3. Take a number from the input list, look at digit 'd' in that number,
     * and add it to the bucket 'd'
     * 4. When you finish processing the list, copy the contents of the
     * buckets into a temporary list
     * 5. Clear the buckets and repeat for the next 'd'
     */
    static List<Integer> radixSort(List<Integer> ns, int len) {

        ArrayList<ArrayList<Integer>> buckets = new ArrayList();
        for (int i = 0; i < 10; i ++) {
            buckets.add(new ArrayList<Integer>());
        }
        List<Integer> temp = new ArrayList(ns);



        for (int i = 0; i < len; i++) {

            for (int j = 0; j < temp.size(); j++) {
                buckets.get(getDigit(temp.get(j), i)).add(temp.get(j));
            }
            temp.clear();

            for (int k = 0; k < 10; k ++) {
                temp.addAll(buckets.get(k));
                buckets.get(k).clear();
            }

        }
        return temp;
    }
}
