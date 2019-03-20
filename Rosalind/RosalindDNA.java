import edu.princeton.cs.algs4.StdOut;

public class RosalindDNA {

    public static void main(String[] args) {
        int[] counts = new int[4];
        In in = new In("data.txt");
        String dna = in.readLine();
        for (char nucleotide : dna.toCharArray()) {
            counts["ACGT".indexOf(nucleotide)]++;
        }
        for (int i = 0; i < counts.length; i++) {
            StdOut.println(counts[i] + " ");
        }
        StdOut.println();
    }

}
