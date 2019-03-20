import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RosalindCC {


    static void traverseDepthFirst(List<Integer>[] neighbors, int start) {
        Set<Integer> visited = new HashSet<Integer>();
        traverseDepthFirst(neighbors, start, visited);
    }

    static void traverseDepthFirst(List<Integer>[] neighbors, int start, Set<Integer> visited) {
        visited.add(start);
        for (Integer n : neighbors[start]) {
            if (!visited.contains(n)) {
                traverseDepthFirst(neighbors, n, visited);
            }
        }
    }
    public static void main(String[] args) {
        In in = new In("rosalind_cc.txt");
        int node = in.readInt();
        int edge = in.readInt();
        List<Integer>[] graph = new List[node + 1];
        for (int i = 0; i < node + 1; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        int a, b;
        for (int j = 0; j < edge; j++) {
            a = in.readInt();
            b = in.readInt();
            graph[a].add(b);
            graph[b].add(a);
        }
        int count = 0;
        Set<Integer> visited = new HashSet<Integer>();
        for (int k = 1; k <= node; k++) {
            if (!visited.contains(k)) {
                traverseDepthFirst(graph, k, visited);
                count++;
            }
        }
        StdOut.println(count);

    }
}