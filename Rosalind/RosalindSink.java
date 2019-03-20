import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

public class RosalindSink {
    public static void main(String[] args) {
        In in = new In("rosalind_gs.txt");
        int count = in.readInt();

        for (int i = 0; i < count; i++) {
            int vert = in.readInt();
            int edge = in.readInt();
            Digraph graph = new Digraph(vert);
            boolean there = true;
            for (int j = 0; j < edge; j++) {
                graph.addEdge(in.readInt()- 1, in.readInt() - 1);
            }
            for (int j = 0; j < vert; j++) {
                there = true;
                DepthFirstDirectedPaths path = new DepthFirstDirectedPaths(graph, j);
                for (int k = 0; k < vert; k++) {
                    if(!path.hasPathTo(k)){
                        there = false;
                        break;
                    }
                }
                if (there) {
                    StdOut.print((j + 1) + " ");
                    break;
                }
            }
            if(!there) StdOut.print(-1 + " ");
        }
    }
}
