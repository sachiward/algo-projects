import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.StdOut;

public class RosalindSCC {

    public static void main(String[] args) {
        In in = new In("rosalind_stronglyconnected.txt");
        int v = in.readInt() + 1;
        int e = in.readInt();
        Digraph g = new Digraph(v);

        for (int i = 1; i <= e; i++) {
            int m = in.readInt();
            int n = in.readInt();
            g.addEdge(m, n);
        }

        KosarajuSharirSCC scc = new KosarajuSharirSCC(g);
        StdOut.print(scc.count() - 1);

    }

}
