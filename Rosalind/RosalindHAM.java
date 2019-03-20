import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;

public class RosalindHAM {

    public static void main(String[] args) {
        In in = new In("rosalind_ham.txt");
        int count = in.readInt();


        for (int i = 0; i < count; i++) {
            int vertices = in.readInt();
            int edges = in.readInt();
            int[] inDegree = new int[vertices];
            boolean[] visited = new boolean[vertices];
            Digraph graph = new Digraph(vertices);
            for (int j = 0; j < edges; j++) {
                graph.addEdge(in.readInt() - 1, in.readInt() - 1);
            }
            int found = 1;
            int end = -1;
            int beginning = -1;
            for (int j = 0; j < vertices; j++) {
                inDegree[j] = graph.indegree(j);
                if (inDegree[j] == 0 && beginning == -1) {
                    beginning = j;
                } else if (inDegree[j] == 0 && beginning != -1) {
                    found = -1;
                } if (graph.outdegree(j) == 0 && end == -1) {
                    end = j;
                } else if (graph.outdegree(j) == 0 && end != -1) {
                    found = -1;
                }

            }

            StdOut.print(found + " ");

            if (found == 1) {

                do {
                    StdOut.print((beginning + 1) + " ");
                    for (int neighbor : graph.adj(beginning)) {
                        inDegree[neighbor]--;
                    }
                    visited[beginning] = true;
                    int next = end;
                    for (int neighbor : graph.adj(beginning)) {
                        if (inDegree[neighbor] == 0 && !visited[neighbor]) {
                            next = neighbor;
                        }
                    }
                    beginning = next;
                } while (beginning != end);
                StdOut.print((end + 1) + " ");
            }


            StdOut.println();
        }
    }
}
