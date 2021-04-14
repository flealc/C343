import java.util.*;
import java.util.function.Consumer;

abstract class GraphTraversal {
    protected final Hashtable<Node, ArrayList<Edge>> neighbors;
    protected NodeCollection nodesToTraverse;

    GraphTraversal(Hashtable<Node, ArrayList<Edge>> neighbors) {
        this.neighbors = neighbors;
    }

    void traverse(Consumer<Node> consumer) {
        while (!nodesToTraverse.isEmpty())
            visit(nodesToTraverse.extract(), consumer);
    }

    void visit(Node u, Consumer<Node> consumer) {
        if (u.isNotVisited()) {
         //  System.out.println("extracted & visiting " + u.getName());
            u.setVisited();
            consumer.accept(u);
            neighbors.get(u).forEach(this::relax);
        }
    }

    abstract void relax(Edge e);
}

// ----------------------------------------------------------------------

class BFS extends GraphTraversal {
    BFS(Hashtable<Node, ArrayList<Edge>> neighbors) {
        super(neighbors);
        nodesToTraverse = new QUEUE_COLL();
    }

    void relax(Edge e) {
        nodesToTraverse.insert(e.getDestination());
    }

    List<Node> startFrom(Node s) {
        List<Node> result = new ArrayList<>();
        nodesToTraverse.insert(s);
        traverse(result::add);
        return result;
    }
}

// ----------------------------------------------------------------------

class DFS extends GraphTraversal {
    DFS(Hashtable<Node, ArrayList<Edge>> neighbors) {
        super(neighbors);
        nodesToTraverse = new STACK_COLL();
    }

    void relax(Edge e) {
        nodesToTraverse.insert(e.getDestination());
    }

    List<Node> startFrom(Node s) {
        List<Node> result = new ArrayList<>();
        nodesToTraverse.insert(s);
        traverse(result::add);
        return result;
    }
}

// ----------------------------------------------------------------------

class TopologicalSort extends GraphTraversal {
    TopologicalSort(Hashtable<Node, ArrayList<Edge>> neighbors) {
        super(neighbors);
        Set<Node> nodes = neighbors.keySet();
        for (Node n : nodes) n.setValue(0);
        for (Node n : nodes) {
            for (Edge edge : neighbors.get(n)) {
                edge.getDestination().updateValue(i -> i + 1);
            }
        }
        nodesToTraverse = new HEAP_COLL(nodes);

    }

    void relax(Edge e) {
        e.getDestination().updateValue(i -> i - 1);
        e.getDestination().moveItUp();

    }

    Queue<Node> sort() {
        Queue<Node> result = new LinkedList<>();
        traverse(node -> {
            if (node.getValue() != 0) throw new Error("Cycle detected");
            result.offer(node);
        });
        return result;
    }
}

// ----------------------------------------------------------------------

class ShortestPaths extends GraphTraversal {
    ShortestPaths(Hashtable<Node, ArrayList<Edge>> neighbors) {
        super(neighbors);
        Set<Node> nodes = neighbors.keySet();
        for (Node n : nodes) n.setValue(Integer.MAX_VALUE);
        nodesToTraverse = new HEAP_COLL(nodes);

    }

    void relax(Edge e) {
        if (e.getDestination().getValue() == (Integer.MAX_VALUE)) e.getDestination().setValue(e.getSource().getValue() + e.getWeight());
        else if (e.getDestination().getValue() > (e.getSource().getValue() + e.getWeight())) e.getDestination().setValue((e.getSource().getValue() + e.getWeight()));
        //System.out.println(e.getDestination().getName() + " updated to " + e.getDestination().getValue());
        e.getDestination().moveItUp();
       // if (e.getDestination().getHeapIndex() != 0) e.getDestination().moveItUp();;

    }


    void fromSource(Node source) {

        source.updateValue(i -> 0);
        source.moveItUp();
        //if (source.getHeapIndex() != 0) source.moveItUp();
        traverse(node -> {
            if (node.getValue() == Integer.MAX_VALUE) throw new Error("Error");
        });
    }
}

