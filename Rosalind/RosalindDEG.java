import edu.princeton.cs.algs4.StdOut;
import java.io.*;
import java.io.FileNotFoundException;

public class RosalindDEG {

    public static void main(String[] args) throws FileNotFoundException {
        In in = new In("rosalind_deg.txt");
        int vert = in.readInt();
        int edge = in.readInt();
        int[] deg = new int[vert + 1];

        while(in.hasNextLine()) {
            int [] data = in.readAllInts();
            for (int i = 0; i < (edge * 2); i++) {
                deg[data[i]] += 1;
            }
        }

        for (int z = 1; z < (vert + 1); z++) {
            StdOut.print(deg[z] + " ");
       }
    }
}