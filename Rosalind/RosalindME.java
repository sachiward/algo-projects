import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
public class RosalindME {

    static void counter(int arrsize, int[] counts) {
        for (int j = 0; j < arrsize; j++) {
            if (counts[j] > arrsize / 2) {
                //prints majority element when it exists
                StdOut.println(j);
                return;
            }
        }
        //if majority element does not exist print -1
        StdOut.println(-1);
    }

    public static void main(String[] args) {
        //collect data from file
        In in = new In("rosalind_maj.txt");
        //initialize
        int arrnum = in.readInt();
        int arrsize = in.readInt();
        int[] counts = new int[10001];
        int[][] dataset = new int[arrnum][arrsize];

        //populate 2d array
        for (int i = 0; i < arrnum; i++) {
            Arrays.fill(counts, 0); // reset outer array value
            // populate array and add value to counts index
            for (int k = 0; k < arrsize; k++) {
                int input = in.readInt();
                counts[input] += 1;
                dataset[i][k] = input;
            }
            //calls counter method
            counter(arrsize, counts);
        }

    }
}
