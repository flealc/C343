import java.util.ArrayList;
import java.util.List;

class PEmptyE extends Exception {}

abstract class PList {
    abstract int getElem () throws PEmptyE;
    abstract PList getRest () throws PEmptyE;
    abstract int length ();

    /**
     * Splits the current list in two at the given
     * index
     */
    abstract Pair<PList,PList> splitAt (int index);

    /**
     * Keep dividing the list in two until you reach
     * a base case; then merge the sorted lists
     * resulting from the recursive calls
     */
    abstract PList mergeSort ();

    /**
     * The given list 'ns' is sorted; the current
     * list (this) is also sorted. Return a new
     * sorted list from these two lists.
     */
    abstract PList merge (PList ns);

    static List<Integer> toList (PList ns) {
        List<Integer> result = new ArrayList<>();
        while (true) {
            try {
                result.add(ns.getElem());
                ns = ns.getRest();
            } catch (PEmptyE e) {
                return result;
            }
        }
    }

    static PList fromList (List<Integer> ns) {
        PList result = new PEmpty();
        for (int i=0; i<ns.size(); i++) {
            result = new PNode(ns.get(i), result);
        }
        return result;
    }
}

class PEmpty extends PList {
    int getElem() throws PEmptyE {
        throw new PEmptyE();
    }

    PList getRest() throws PEmptyE {
        throw new PEmptyE();
    }

    int length() {
        return 0;
    }

    Pair<PList, PList> splitAt(int index) {
        return null; // TODO
    }

    PList mergeSort() {
        return null; // TODO
    }

    PList merge(PList ns) {
        return null; // TODO
    }
}


class PNode extends PList {
    private final int elem;
    private final PList rest;
    private final int len;

    PNode(int elem, PList rest) {
        this.elem = elem;
        this.rest = rest;
        this.len = rest.length() + 1;
    }

    int getElem() {
        return elem;
    }

    PList getRest() {
        return rest;
    }

    int length() {
        return len;
    }

    Pair<PList, PList> splitAt(int index) {
        return null; // TODO
    }

    PList mergeSort() {
        return null; // TODO
    }

    PList merge(PList ns) {
        return null; // TODO
    }
}

