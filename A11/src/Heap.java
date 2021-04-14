import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

class Heap {
    private final ArrayList<Node> nodes;
    private int size;

    Heap (Set<Node> nodes) {
        this.nodes = new ArrayList<>(nodes);
        this.size = nodes.size();

       //added lines
        for (int i = 0; i < this.nodes.size(); i++) {
            this.nodes.get(i).setHeap(this);
            this.nodes.get(i).setHeapIndex(i);
        }
        // end added lines
        if (!isEmpty()) heapify(); //added !isEmpty check
    }

    void heapify () {

        for (int i= size / 2; i>=0; i--) moveDown(nodes.get(i));
        //System.out.println(this.nodes.toString());
    }

    boolean isEmpty () {
        return size == 0;
    }

    Optional<Node> getParent(Node n) {
         //if (n.getHeapIndex() == 0) return Optional.of(n);
        int parentIndex = (n.getHeapIndex() - 1) / 2;

        if (parentIndex < 0) return Optional.empty();
        return Optional.of(nodes.get(parentIndex));
    }

    Optional<Node> getLeftChild(Node n) {

        int childIndex = 2 * n.getHeapIndex() + 1;
        if (childIndex >= size) return Optional.empty();
        return Optional.of(nodes.get(childIndex));
    }

    Optional<Node> getRightChild(Node n) {

        int childIndex = 2 * n.getHeapIndex() + 2;
        if (childIndex >= size) return Optional.empty();
        return Optional.of(nodes.get(childIndex));
    }

    Optional<Node> getMinChild(Node n) {

        Optional<Node> leftChild = getLeftChild(n);
        Optional<Node> rightChild = getRightChild(n);
        if (rightChild.isEmpty()) return leftChild;
        else {
            assert leftChild.isPresent();

            return Optional.of(Node.min(leftChild.get(), rightChild.get()));
        }
    }

    void swap(Node n1, Node n2) {

       //grouped both assignments here to avoid infinite loop
        int p1 = n1.getHeapIndex();
        int p2 = n2.getHeapIndex();

        nodes.set(p1, n2);
        n2.setHeapIndex(p1);

        nodes.set(p2, n1);
        n1.setHeapIndex(p2);
    }

    void moveDown(Node n) {

        Optional<Node> minChild = getMinChild(n);
        minChild.ifPresent(c -> {
            if (c.compareTo(n) < 0) {
                swap(n, c);
                moveDown(n);
            }
        });
    }

    void moveUp(Node n) {

            Optional<Node> parent = getParent(n);
            parent.ifPresent(p -> {
                if (n.compareTo(p) < 0) {
                    swap(n, p);
                    moveUp(n);
                }
            });

    //System.out.println(nodes.toString());
    }

    void insert (Node n) {
        n.setHeap(this);
        n.setHeapIndex(nodes.size()); //changed from n.setHeapIndex(size);
        //removed: nodes.set(size,n);
        nodes.add(n);
        moveUp(n);
        System.out.println(nodes.toString());
    }

    Node extractMin() {
        Node n = nodes.get(0);
        //removed: Node last = nodes.get(size);
        Node last = nodes.get(size - 1);
        nodes.set(0, last);
        last.setHeapIndex(0);
        nodes.remove(size - 1);
        size --;
        moveDown(last);

       // System.out.println("after extraction" + nodes.toString());
        return n;
    }
}
