import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphTraversal {

    static void traverseDepthFirst(List<Integer>[] neighbors, int start) {
        Set<Integer> visited = new HashSet<Integer>();
        traverseDepthFirst(neighbors, start, visited);
    }

    static void traverseDepthFirst(List<Integer>[] neighbors, int start, Set<Integer> visited) {
        StdOut.println(start);
        for (Integer n : neighbors[start]) {
            if (!visited.contains(n)) {
                traverseDepthFirst(neighbors, n, visited);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer>[] neighbors = new List[5];
        for (int i = 0; i < neighbors.length; i++) {
            neighbors[i] = new ArrayList<Integer>();
        }
        neighbors[0].add(2);
        neighbors[0].add(4);
        neighbors[1].add(2);
        neighbors[1].add(4);
        neighbors[2].add(0);
        neighbors[2].add(1);
        neighbors[3].add(4);
        neighbors[4].add(0);
        neighbors[4].add(1);
        neighbors[4].add(3);
    }

}
