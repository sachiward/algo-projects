import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;

public class RosalindSCY {
    public static void main(String[] args) {
        In in = new In("rosalind_shortestcycle.txt");
        int count = in.readInt();

        EdgeWeightedDigraph[] graph = new EdgeWeightedDigraph[count];

        for (int i = 0; i < count; i++) {
            int vertice = in.readInt();
            int edge = in.readInt();
            graph[i] = new EdgeWeightedDigraph(vertice);
            DirectedEdge find = new DirectedEdge(in.readInt()-1, in.readInt()-1, in.readInt());
            for (int j = 0; j < edge-1; j++) {
                DirectedEdge edge1 = new DirectedEdge(in.readInt()-1, in.readInt()-1, in.readInt());
                graph[i].addEdge(edge1);
            }
            DijkstraSP path = new DijkstraSP(graph[i], find.to());
            double length = path.distTo(find.from());
            length += find.weight();
            if(length<Double.POSITIVE_INFINITY){
                StdOut.print((int)length + " ");
            } else {
                StdOut.print("-1 ");
            }
        }

    }

}
