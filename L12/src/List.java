import java.util.Optional;

class EmptyListE extends RuntimeException {}

//-------------------------------------------------------------
abstract class List<E> {
    abstract int length();
    abstract boolean inList(E elem);
    abstract void setRest (List<E> rest) throws EmptyListE;
    abstract List<E> getRest() throws EmptyListE;
    abstract void setFirst (E first) throws EmptyListE;
    abstract E getFirst() throws EmptyListE;
    static <E> Optional<E> cycleStart (List<E> ls) {

        List<E> slowa = ls;
        List<E> fasta = ls;

try {
    do {
        slowa = slowa.getRest();
        fasta = fasta.getRest().getRest();

    } while (!slowa.getFirst().equals(fasta.getFirst()));
    slowa = ls;

} catch (EmptyListE e) {return Optional.empty();}


        while (!slowa.getFirst().equals(fasta.getFirst())) {
            slowa = slowa.getRest();
            fasta = fasta.getRest();
        }

        return Optional.of(fasta.getFirst());


    }
}

//-------------------------------------------------------------

class EmptyL<E> extends List<E> {

    int length () { return 0; }

    boolean inList(E elem) { return false; }

    @Override
    void setRest(List<E> rest) throws EmptyListE {
        throw new EmptyListE();
    }

    @Override
    List<E> getRest() throws EmptyListE {
        throw new EmptyListE();
    }

    @Override
    void setFirst(E first) throws EmptyListE {
        throw new EmptyListE();
    }

    @Override
    E getFirst() throws EmptyListE {
        throw new EmptyListE();
    }

    public boolean equals (Object o) {
        return o instanceof EmptyL;
    }
}

//-------------------------------------------------------------

class NodeL<E> extends List<E> {
    private E first;
    private List<E> rest;

    NodeL (E first, List<E> rest) {
        this.first = first;
        this.rest = rest;
    }

    public E getFirst() {
        return first;
    }

    public void setFirst(E first) {
        this.first = first;
    }

    public List<E> getRest() {
        return rest;
    }

    public void setRest(List<E> rest) {
        this.rest = rest;
    }

    int length () { return 1 + rest.length(); }

    boolean inList(E elem) {
        return first.equals(elem) || rest.inList(elem);
    }

    public boolean equals (Object o) {
        if (o instanceof NodeL) {
            NodeL<E> other = (NodeL<E>) o;
            return first.equals(other.first) && rest.equals(other.rest);
        }
        else return false;
    }
}

//-------------------------------------------------------------
//-------------------------------------------------------------