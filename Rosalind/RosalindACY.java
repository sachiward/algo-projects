import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

public class RosalindACY {

    public static void main(String[] args) {
        In in = new In("rosalind_acyclic.txt");
        int graph_num = in.readInt();
        for (int i = 0; i < graph_num; i++) {
            createGraph(in);
        }

    }

    private static void createGraph(In in) {
        int vert = in.readInt();
        //StdOut.println("number of vertices:" + vert);
        int edge = in.readInt();
        //StdOut.println("number of edges:" + edge);
        Digraph graph = new Digraph(vert + 1);
        for (int i = 0; i < edge; i++) {
            graph.addEdge(in.readInt(), in.readInt());
        }
        //StdOut.print(graph.toString());
        isAcyclic(graph);

    }

    private static void isAcyclic(Digraph graph) {
        if (new DirectedCycle(graph).hasCycle()) {
            StdOut.print("-1 ");
        }
        else {
            StdOut.print("1 ");
        }
    }


}
