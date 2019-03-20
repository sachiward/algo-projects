import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

public class RosalindDDEG {

    public static void main( String[] args ) {
        In in = new In("DoubleDegreeArray.txt");
        int vert = in.readInt();
        int edge = in.readInt();
        Graph graph = new Graph(vert + 1);
        int []data = new int[edge * 2];
        int neighbor_degs = 0;

        while (in.hasNextLine()) {
            data = in.readAllInts();
        }

        for (int k = 0; k < data.length; k += 2) {
            graph.addEdge(data[k], data[k + 1]);
        }

        for (int v = 1; v < vert + 1; v++) {
            for (int j:  graph.adj(v)) {
                neighbor_degs += graph.degree(j);
            }
            StdOut.print(neighbor_degs + " ");
            neighbor_degs = 0;
        }

    }
}
