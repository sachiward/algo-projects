import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Subsets {


    private static List<Integer> ithSubset(int i) {
        List<Integer> result = new ArrayList<>();
        //this for loop runs log i times
        for (int mask = 1, shift = 0; mask <= i; mask <<= 1, shift++) {
            //bit wise mask checks binary where 1011 represents the set {0, 1, 3}
            //positions represented in binary - 3210
            if ((mask & i) != 0) {
                result.add(shift);
            }
        }

        return result;
    }

    private static void printAllSubsets(int n) {
        for (int i = 0; i < 1 << n; i++) {
            StdOut.println(ithSubset(i));
        }
    }

    public static void main(String[] args) {
        printAllSubsets(4);
    }

}
