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

        return new Pair(new PEmpty(),new PEmpty()); // TODO
    }

    PList mergeSort() {
        return this; // TODO
    }

    PList merge(PList ns) {
        return ns; // TODO
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

    Pair<PList, PList> splitAt(int index) {  // TODO

        PList tempPListA = new PEmpty();
        PList tempPListB = this;

        for (int i = 0; i < index; i++) {
            try {
                tempPListA = new PNode(tempPListB.getElem(), tempPListA);
                tempPListB = tempPListB.getRest();
            } catch (PEmptyE e) {
            }
        }

        return new Pair(tempPListA, tempPListB);


    }

    PList mergeSort() {
        if (this.length() == 1) {return this;}
        PList firstHalf = this.splitAt(length() / 2).getFst();
        PList secondHalf = this.splitAt(length() / 2).getSnd();
        return firstHalf.mergeSort().merge(secondHalf.mergeSort());
    }
    PList merge(PList ns) {
        if (ns.length() == 0) {return this;}

            try {
                if (elem < ns.getElem()) {

                    return new PNode(elem, rest.merge(ns));
                } else return new PNode(ns.getElem(),this.merge(ns.getRest()));


            } catch (PEmptyE e) { return this;}

        }
    }


