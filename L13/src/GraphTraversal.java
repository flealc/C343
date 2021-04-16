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

    // This method should reverse every edge in our graph.
    // If we had the following graph:
    //   a --(2)--> b
    // Taking the transpose would give us:
    //   a <--(2)-- b
    Hashtable<Node, ArrayList<Edge>> transpose() {
        Hashtable<Node, ArrayList<Edge>> transposed = new Hashtable<>();
        ArrayList<Edge> theEdges = new ArrayList<>();
        for (Node n : neighbors.keySet()) {
            transposed.put(n, new ArrayList<>());
            for (Edge e : neighbors.get(n)) {
                theEdges.add(e.flip());
            }

        }

        for (Edge e : theEdges) {
                transposed.get(e.getSource()).add(e);
            }


        return transposed;

    }

    void relax(Edge e) {
        nodesToTraverse.insert(e.getDestination());
    }

    // Depth-first search with a stack. Our stack is nodesToTraverse.
    List<Node> DFS(Node n) {
            List<Node> result = new ArrayList<>();
            nodesToTraverse.insert(n);
            traverse(result::add);
            return result;
    }

    // Kosaraju's Algorithm
    List<List<Node>> findSCCs(Node s) {
        LinkedList<List<Node>> stackofcomponents = new LinkedList<>();
        List<List<Node>> ans = new ArrayList<>();

        // Our first DFS
        for (Node n : neighbors.keySet()) {
            if (!n.isVisited()) {
                stackofcomponents.addFirst(DFS(n));

            }

        }

        this.neighbors = transpose();
        neighbors.keySet().forEach(Node::reset);

        // We have successfully finished our first DFS, along with getting the transpose of the graph...
        // There might be more SCCs inside our stack of components, we should DFS again.
        // What do we need to do here with our stackofcomponets? Pay attention to your types in the lines below,
        // they give a huge hint. Replace "???".
        // TODO

        List<Node> newList = new ArrayList<>();
        stackofcomponents.forEach(l -> newList.addAll(l));

            for (Node n : newList ) {
                if (!n.isVisited()) ans.add(DFS(n));
            }
        return ans;
    }
}
