import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphTraversalTest {
    private Node start;
    private Hashtable<Node, ArrayList<Edge>> neighbors;

    @BeforeEach
    void simpleGraph() {
        Node a, b, c, d, e;
        a = new Node("a");
        b = new Node("b");
        c = new Node("c");
        d = new Node("d");
        e = new Node("e");

        start = a;
        neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Arrays.asList(new Edge(a,c), new Edge(a,b))));
        neighbors.put(b, new ArrayList<>(Arrays.asList(new Edge(b,c), new Edge(b,e), new Edge(b,d))));
        neighbors.put(c, new ArrayList<>(Collections.emptyList()));
        neighbors.put(d, new ArrayList<>(Collections.singletonList(new Edge(d, e))));
        neighbors.put(e, new ArrayList<>(Collections.singletonList(new Edge(e, b))));

    }

    @Test
    void scc() {
        // the following is for the graph on the wiki page for Tarjan's algo
        System.out.println("The following is from wiki:");
        Node a, b, c, d, e, f, g, h;
        a = new Node("a");
        b = new Node("b");
        c = new Node("c");
        d = new Node("d");
        e = new Node("e");
        f = new Node("f");
        g = new Node("g");
        h = new Node("h");

        neighbors = new Hashtable<>();
        neighbors.put(a, new ArrayList<>(Collections.singletonList(new Edge(a, b))));
        neighbors.put(b, new ArrayList<>(Collections.singletonList(new Edge(b, c))));
        neighbors.put(c, new ArrayList<>(Collections.singletonList(new Edge(c, a))));
        neighbors.put(d, new ArrayList<>(Arrays.asList(new Edge(d, c), new Edge(d, b), new Edge(d, e))));
        neighbors.put(e, new ArrayList<>(Arrays.asList(new Edge(e, d), new Edge(e, f))));
        neighbors.put(f, new ArrayList<>(Arrays.asList(new Edge(f,c), new Edge(f, g))));
        neighbors.put(g, new ArrayList<>(Collections.singletonList(new Edge(g, f))));
        neighbors.put(h, new ArrayList<>(Arrays.asList(new Edge(h, g), new Edge(h, h), new Edge(h, e))));

        Kosarajus scc = new Kosarajus(neighbors);
        ArrayList<ArrayList<Node>> ans = scc.SCC();

        for (List<Node> lev : ans) {
            System.out.println(lev);
        }
    }
}
