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
            u.setVisited();
            neighbors.get(u).forEach(this::relax);
            consumer.accept(u);
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
        nodesToTraverse = new HEAP_COLL(nodes);
        for (Node n : nodes)
            for (Edge edge : neighbors.get(n))
                edge.getDestination().updateValue(i -> i + 1);
    }

    void relax(Edge e) {
        e.getDestination().updateValue(i -> i - 1);
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
        return;
    }

    void fromSource(Node source) {
        source.updateValue(i -> 0);
        traverse(node -> {
        });
    }
}

class Kosarajus extends GraphTraversal {
    Kosarajus(Hashtable<Node, ArrayList<Edge>> neighbors) {
        super(neighbors);
        nodesToTraverse = new STACK_COLL();
    }

    void relax(Edge e) {
        if (e.getDestination().isNotVisited()) {
            nodesToTraverse.insert(e.getDestination());
        }
    }

    ArrayList<ArrayList<Node>> SCC() {
        Set<Node> nodes = neighbors.keySet();
        for (Node n : nodes) {
            nodesToTraverse.insert(n);
        }

        ArrayList<Queue<Node>> queues = new ArrayList<>();
        traverse(queue::offer);

        Hashtable<Node, ArrayList<Edge>> newNeighbors = transpose();
        for (Node n : newNeighbors.keySet()) {
            neighbors.put(n, newNeighbors.get(n));
        }

        nodesToTraverse.insert(queue.poll());
        neighbors.keySet().forEach(Node::setNotVisited);

        ArrayList<ArrayList<Node>> sccs = new ArrayList<>();
        ArrayList<Node> currentScc = new ArrayList<>();
        traverse(n -> {
            currentScc.add(n);

            if (nodesToTraverse.isEmpty()) {
                sccs.add((ArrayList<Node>) currentScc.clone());
                currentScc.clear();
                while (!queue.isEmpty() && !queue.peek().isNotVisited()) {
                    queue.poll();
                }
                if (!queue.isEmpty()) {
                    nodesToTraverse.insert(queue.poll());
                }
            }
        });

        return sccs;
    }

    Hashtable<Node, ArrayList<Edge>> transpose() {
        Hashtable<Node, ArrayList<Edge>> newNeighbors = new Hashtable<>();
        for (Node n : neighbors.keySet()) {
            newNeighbors.put(n, new ArrayList<>());
        }

        for (ArrayList<Edge> es : neighbors.values()) {
            es.forEach(e -> {
                Edge newEdge = e.flip();
                newNeighbors.get(newEdge.getSource()).add(newEdge);
            });
        }

        return newNeighbors;
    }
}