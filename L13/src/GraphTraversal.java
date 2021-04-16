import java.util.*;
import java.util.function.Consumer;

abstract class GraphTraversal {
    protected Hashtable<Node, ArrayList<Edge>> neighbors;
    protected NodeCollection nodesToTraverse;


    GraphTraversal(Hashtable<Node, ArrayList<Edge>> neighbors) {
        this.neighbors = neighbors;
    }

    void traverse(Consumer<Node> consumer) {
        while (!nodesToTraverse.isEmpty())
            visit(nodesToTraverse.extract(), consumer);
    }

    void visit(Node u, Consumer<Node> consumer) {
        if (!u.isVisited()) {
            u.setVisited();
            consumer.accept(u);
            neighbors.get(u).forEach(this::relax);
        }
    }

    abstract void relax(Edge e);
}

class StronglyConnected extends GraphTraversal {

    StronglyConnected(Hashtable<Node, ArrayList<Edge>> neighbors) {
        super(neighbors);
        nodesToTraverse = new STACK_COLL();
    }

    Hashtable<Node, ArrayList<Edge>> transpose() {
        // TODO
    }


    void relax(Edge e) {
        nodesToTraverse.insert(e.getDestination());
    }

    List<Node> DFS(Node n) {
        // TODO
    }

    Stack<Node> orderOfDFS() {
        Stack<Node> dfsStack = new Stack<>();
        Set<Node> nodes = neighbors.keySet();
        nodes.forEach(Node::reset);

        for (Node n : nodes) {
            if (!n.isVisited()) {
                orderOfDFSHelper(n, dfsStack);
            }
        }

        return dfsStack;
    }

    void orderOfDFSHelper(Node n, Stack<Node> dfsStack) {
        n.setVisited();

        for (Edge outgoingEdge : neighbors.get(n)) {
            Node destination = outgoingEdge.getDestination();
            if (!destination.isVisited()) {
                orderOfDFSHelper(destination, dfsStack);
            }
        }

        dfsStack.push(n);
    }

    List<List<Node>> findSCCs() {
        // TODO
    }
}
