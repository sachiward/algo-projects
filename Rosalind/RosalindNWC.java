import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;

public class RosalindNWC {
    public static void main(String[] args) {
        In in = new In("rosalind_negativecyc.txt");
        int graph_num = in.readInt();
        for (int i = 0; i < graph_num; i++) {
            createGraph(in);
        }
    }

    private static void createGraph(In in) {
        int vert = in.readInt() + 1;
        //StdOut.println("number of vertices:" + vert);
        int edge = in.readInt();
        //StdOut.println("number of edges:" + edge);
        int a, b = 0;
        double weight = 0;
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(vert);
        for (int i = 0; i < edge; i++) {
            a = in.readInt();
            b = in.readInt();
            weight = in.readDouble();
            DirectedEdge e = new DirectedEdge(a, b, weight);
            graph.addEdge(e);

        }
        //StdOut.print(graph.toString());
        if (hasNegativeWeightedCycle(graph)){
            StdOut.print("1 ");
        }
        else {
            StdOut.print("-1 ");
        }
    }

    private static boolean hasNegativeWeightedCycle(EdgeWeightedDigraph graph) {
        int s = graph.V();
        for (int i = 0; i < s; i++) {
            BellmanFordSP g = new BellmanFordSP(graph, i);
            if (g.hasNegativeCycle()) {
                return true;
            }
        }
        return false;

    }

}
