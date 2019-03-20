import edu.princeton.cs.algs4.Bipartite;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdOut;

public class RosalindBIP {
    public static void main(String[] args) {
        //Use an in object to read in the data
        In in = new In("rosalind_bipartiteness.txt");
        //First read in the number of graphs to check
        int graph_num = in.readInt();
        //For loop that calls the createGraph method once for every graph
        for (int i = 1; i <= graph_num ; i++) {
            createGraph(in);
        }
    }
    private static void createGraph(In in) {
        //Read number of vertices and edges
        int vert = in.readInt();
        int edge = in.readInt();
        //Using the graph.java class, create a new graph with the number of vertices + 1 (since the graph class starts with vertex 0)
        Graph graph = new Graph(vert + 1);
        //For loop that adds the edges of the graph to the graph object
        for (int i = 0; i < edge; i++) {
            graph.addEdge(in.readInt(), in.readInt());
        }
        //Uses the bipartite class to check if the graph is bipartite
        if (new Bipartite(graph).isBipartite()) {
            //If the graph is bipartite, prints 1
            StdOut.print("1 ");
        }
        else {
            //Prints -1 if the graph is not bipartite
            StdOut.print("-1 ");
        }
    }
}
