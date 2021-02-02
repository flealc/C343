import javax.swing.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class EmptyListE extends Exception {}

//-------------------------------------------------------------

/**
 * This is persistent implementation.
 * After a list is created, it is never updated
 *
 * See the test cases for examples
 */
abstract class List<E> {
    static List<Integer> countdown(int n) {
        if (n == 0)
            return new NodeL<>(0, new EmptyL<>());
        else
            return new NodeL<>(n, countdown(n - 1));
    }

    /**
     * Getter Methods
     */
    abstract E getFirst() throws EmptyListE;

    abstract List<E> getRest() throws EmptyListE;

    /**
     * Computes the length of the list
     */
    abstract int length();

    /**
     * Checks if the given elem occurs in the list
     * (Use .equals() to check for equality)
     */
    abstract boolean inList(E elem);

    /**
     * Inserts newElem after every occurrence of elem
     */
    abstract List<E> insertAfter(E elem, E newElem);

    /**
     * Removes the first occurrence of elem (if
     * there is one)
     */
    abstract List<E> removeFirst(E elem);

    /**
     * Returns the 0-based index of elem in the list
     * If the element is not in the list, throw
     * an exception
     */
    abstract int indexOf(E elem) throws EmptyListE;

    /**
     * Returns a new list that only contains the
     * elements satisfying the given predicate
     */
    abstract List<E> filter(Function<E, Boolean> pred);

    /**
     * Returns a new list in which every element
     * is transformed using the given function
     */
    abstract <F> List<F> map(Function<E, F> f);

    /**
     * Starting with base as the current result,
     * repeatedly applies the bifunction f to the
     * current result and one element from the list
     */
    abstract <F> F reduce(F base, BiFunction<E, F, F> f);

    /**
     * Appends the given list at the end of the
     * current list
     */
    abstract List<E> append(List<E> other);

    /**
     * Use to accumulate the reverse of the given list
     */
    abstract List<E> reverseHelper(List<E> result);

    /**
     * Returns a big list containing all the sublists of
     * the current list
     */
    abstract List<List<E>> powerSet();

    List<E> reverse() {
        return reverseHelper(new EmptyL<>());
    }

    abstract boolean isEmpty();

}


//-------------------------------------------------------------

class EmptyL<E> extends List<E> {

    E getFirst() throws EmptyListE {
      throw new EmptyListE();
    }

    List<E> getRest() throws EmptyListE {
      throw new EmptyListE();
    }

    int length() {
        return 0;                                           // DONE
    }

    boolean inList(E elem)  {

        return false;                                       // DONE
    }

    List<E> insertAfter(E elem, E newElem) {                // DONE
        return new EmptyL<>();
    }

    List<E> removeFirst(E elem) {                           // DONE
        return new EmptyL<>();
    }

    int indexOf(E elem) throws EmptyListE {                 // DONE
        throw new EmptyListE();
    }

    List<E> filter(Function<E, Boolean> pred) {             // DONE
        return new EmptyL<>();
    }

    <F> List<F> map(Function<E, F> f) {                     // DONE
        return new EmptyL<>();
    }

    <F> F reduce(F base, BiFunction<E, F, F> f) {           // DONE
        return base;
    }

    List<E> append(List<E> other) {                         // DONE
        return other;

    }

    List<E> reverseHelper(List<E> result) {                 // DONE

      return result;
    }

    List<List<E>> powerSet() {                              // DONE
        return new NodeL<List<E>>(new EmptyL<>(), new EmptyL<>());
    }

    public boolean equals (Object o) {
        return o instanceof EmptyL;
    }

    boolean isEmpty() {return true;}
}

//-------------------------------------------------------------

class NodeL<E> extends List<E> {
    private final E first;
    private final List<E> rest;

    NodeL (E first, List<E> rest) {
        this.first = first;
        this.rest = rest;
    }

    E getFirst() {
      return this.first;
    }

    List<E> getRest() {
      return this.rest;
    }

    int length() {                                          // DONE
        if (rest.isEmpty()) {
            return 1;
        } else {
            return (1 + rest.length());
        }
    }

    boolean inList(E elem) {                                // DONE
        if (first.equals(elem)) {
            return true;
        } else {
            return rest.inList(elem);
        }
    }

    List<E> insertAfter(E elem, E newElem) {                // DONE
       if (first.equals(elem)) {
           return (new NodeL<E>(first, new NodeL<>(newElem, rest.insertAfter(elem, newElem))));
       } else {
           return (new NodeL<E>(first, rest.insertAfter(elem, newElem)));
       }
    }

    List<E> removeFirst(E elem) {
        if (first.equals(elem)) {                           // DONE
            return rest;
        } else {
            return (new NodeL<E>(first, rest.removeFirst(elem)));
        }
    }

    int indexOf(E elem) throws EmptyListE {                 // DONE
        if (first.equals(elem)){
            return 0;
        } else {
            return (1 + rest.indexOf(elem));
        }
    }

    List<E> filter(Function<E, Boolean> pred) {             // DONE
        if (pred.apply(first)) {
            return new NodeL<E>(first, rest.filter(pred));
        } else {
            return rest.filter(pred);
        }
    }

    <F> List<F> map(Function<E, F> f) {                     // DONE
        return new NodeL<>(f.apply(first), rest.map(f));
    }


    <F> F reduce(F base, BiFunction<E, F, F> f) {           // DONE
        return f.apply(first, rest.reduce(base, f));
    }

    List<E> append(List<E> other) {                         // DONE
        return new NodeL(first, rest.append(other));
    }



    List<E> reverseHelper(List<E> result) {                 // DONE
        if (rest.isEmpty())
            return (new NodeL<>(first, result));
        else return rest.reverseHelper(new NodeL<>(first, result));
    }

    List<List<E>> powerSet() {                              // TODO
        // return rest.powerSet().map(n -> new NodeL<NodeL<E>>(first, n).append(new NodeL<NodeL<E>>(rest.powerSet(), new EmptyL<>())));
        // new NodeL<List<E>>()),rest.powerSet());

   /**List<List<E>> whole= */ return rest.powerSet().append(rest.powerSet().map(n -> new NodeL<>(first, n)));
    }


    public boolean equals (Object o) {
        if (o instanceof NodeL) {
            NodeL<E> other = (NodeL<E>) o;
            return first.equals(other.first) && rest.equals(other.rest);
        }
        else return false;
    }

    boolean isEmpty() {return false;}
}

//-------------------------------------------------------------
//-------------------------------------------------------------

