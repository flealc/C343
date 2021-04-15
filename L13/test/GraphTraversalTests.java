import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

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
        // TODO more tests
        StronglyConnected scc = new StronglyConnected(neighbors);
        var ans = scc.findSCCs(start);
        for (List<Node> lev : ans) {
            System.out.println(lev);
        }

        I am putting this blurb here so your IDE will underline the file red. Please do tests.
    }
}