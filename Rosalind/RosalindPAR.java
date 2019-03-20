import java.util.Arrays;

public class RosalindPAR {
    public static void main(String[] args) {

        In in = new In("rosalind_ps.txt");
        int amount = in.readInt();
        int howMany =0;
        int stored[] = new int[amount];

        for(int i =0; i<amount; i++) {
            stored[i] = in.readInt();

            if(i==amount-1) {
                howMany = in.readInt();
            }
        }

        Arrays.sort(stored);

        for(int i =0; i<howMany; i++) {
            StdOut.print(stored[i] + " ");
        }
    }
}
