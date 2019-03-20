import java.util.Arrays;

public class RosalindMED {

    public static void main(String[] args) {

        In in = new In("rosalind_med.txt");
        int amount = in.readInt();
        int position =0;
        int smallest = 0;
        int stored[] = new int[amount];
        int sorted[] = new int[amount];

        for(int i =0; i<amount; i++) {
            stored[i] = in.readInt();

            if(i==amount-1) {
                position = in.readInt();
            }
        }

        Arrays.sort(stored);

        StdOut.println(stored[position-1]);

    }
}

