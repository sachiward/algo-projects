
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TarjanSCC;

public class Rosalind2SAT {

    public static void main(String[] args) {
        In in = new In("rosalind_2sat.txt");
        int count = in.readInt();

        //variable storage is weird
        //negatives stored as absolute value
        //positives stored as absval + the number of vars
        //0 does not exist
        for (int i = 0; i < count; i++) {
            boolean satisfiable = true;
            int variables = in.readInt();
            int vertices = 2 * variables + 1;
            int edges = 2 * in.readInt();
            Digraph graph = new Digraph(vertices);
            int[] assignments = new int[vertices];//1 is true and 2 is false, but need a default unsorted value
            int[] solution = new int[variables+1];
            for (int j = 0; j < edges / 2; j++) {
                int x = in.readInt();
                if(x<0){
                    x = Math.abs(x);
                } else{
                    x = x+ variables ;
                }
                int y = in.readInt();
                if(y<0){
                    y = Math.abs(y);
                } else{
                    y = y+ variables ;
                }
                int notx = 0;
                int noty = 0;
                if(x<=variables){
                    notx = x+variables;
                } else{
                    notx = x-variables;
                }
                if (y<=variables){
                    noty=y+variables;
                } else{
                    noty=y-variables;
                }

                graph.addEdge(notx, y);
                graph.addEdge(noty, x);
            }
            TarjanSCC connected = new TarjanSCC(graph);
            for (int j = 1; j < variables+1; j++) { //first just check if it can be satisfied
                if (connected.stronglyConnected(j, j + variables)) {
                    satisfiable = false;
                }
            }
            if (satisfiable) {
                StdOut.print(1 + " ");
                for (int j = 1; j < vertices; j++) {
                    if (assignments[j] == 0) {
                        DepthFirstDirectedPaths paths = new DepthFirstDirectedPaths(graph, j);
                        if (!paths.hasPathTo((j + variables) % vertices)) {
                            for (int k = 0; k < vertices; k++) {
                                if (paths.hasPathTo(k)) {
                                    if (assignments[k] == 2) {
                                        assignments[j] = 2;
                                        break;
                                    }
                                }
                            }
                        } else {
                            assignments[j] = 2;
                        }
                    }
                    if (assignments[j] == 0) {
                        assignments = assign(assignments, j, graph);
                    }
                }

                for (int j = 1; j <= variables; j++) {//figure out what the assignments are
                    if (assignments[j] == 1) {
                        solution[j] = -j;
                    } else {
                        solution[j]=j;
                    }
                }


                for (int j = 1; j <= variables; j++) {//print out the assignments
                    StdOut.print(solution[j] + " ");
                }
            } else {
                StdOut.print(0);
            }
            StdOut.println();
        }
    }

    public static int[] assign(int[] assignments, int vertex, Digraph graph) {//TODO assign the negation false
        assignments[vertex] = 1;
        if(vertex<assignments.length/2){
            assignments[vertex+assignments.length/2] = 2;
        } else {
            assignments[vertex-assignments.length/2] = 2;
        }
        for (int neighbor : graph.adj(vertex)) {
            if (assignments[neighbor] != 1) {
                assignments = assign(assignments, neighbor, graph);
            }
        }
        return assignments;
    }
}

