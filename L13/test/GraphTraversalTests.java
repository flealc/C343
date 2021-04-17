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

        StronglyConnected scc = new StronglyConnected(neighbors);
        var ans = scc.findSCCs(start);

        for (List<Node> lev : ans) {
            System.out.println(lev);
        }


    }
    @Test
    void myTest() {
        Node n1, n2, n3, n4, n5;
        n1 = new Node("1");
        n2 = new Node("2");
        n3 = new Node("3");
        n4 = new Node("4");
        n5 = new Node("5");

        start = n1;
        neighbors = new Hashtable<>();
        neighbors.put(n1, new ArrayList<>(Arrays.asList(new Edge(n1,n3), new Edge(n1,n4))));
        neighbors.put(n2, new ArrayList<>(Collections.singletonList(new Edge(n2, n1))));
        neighbors.put(n3, new ArrayList<>(Collections.singletonList(new Edge(n3, n2))));
        neighbors.put(n4, new ArrayList<>(Arrays.asList(new Edge(n4,n5))));
        neighbors.put(n5, new ArrayList<>(Collections.emptyList()));

        StronglyConnected scc = new StronglyConnected(neighbors);
        var ans = scc.findSCCs(start);
        for (List<Node> lev : ans) {
            System.out.println(lev);
        }


    }
}
