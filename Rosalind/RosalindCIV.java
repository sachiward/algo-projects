import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileReader;

public class RosalindCIV {
    public static void main(String[] args) {
        try {
            In in = new In("counting_inversions.txt");
            in.readLine();
            String[] p = in.readLine().split(" ");
            int[] arr = new int[p.length];
            for (int i = 0; i < arr.length; ++i) {
                arr[i] = Integer.parseInt(p[i]);
            }
            inversions(arr);
        } catch (Exception e) {
        }
    }

    public static void inversions(int[] a) {
        int c = 0;
        for (int i = 0; i < a.length - 1; ++i) {
            for (int j = i + 1; j < a.length; ++j) {
                if (a[i] > a[j]) {
                    ++c;
                }
            }
        }
        StdOut.print(c + " ");
    }
}
