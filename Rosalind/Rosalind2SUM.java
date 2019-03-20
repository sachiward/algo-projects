import java.io.BufferedReader;
import java.io.FileReader;

public class Rosalind2SUM {

    public static void main( String[] args ) {
        int[][] arrs = null;

        In in = new In("rosalind_2sum.txt");
        arrs = new int[in.readInt()][];
        int array_length = in.readInt();
        for(int i = 0; i < arrs.length; ++i) {
            arrs[i] = new int[array_length];
            for(int j = 0; j < array_length; ++j) {
                arrs[i][j] = in.readInt();
            }
        }

        String[] out = twoSum(arrs);
        for(int i = 0; i < out.length; ++i) {
            System.out.println(out[i]);
        }
    }

    public static String[] twoSum( int[][] arrs ) {
        String[] out = new String[arrs.length];
        for(int a = 0; a < arrs.length; ++a) {
            boolean found = false;
            for(int i = 0; i < arrs[a].length - 1; ++i) {
                for(int j = i + 1; j < arrs[a].length; ++j) {
                    if(arrs[a][i] == (-1 * arrs[a][j])) {
                        out[a] = "" + (i + 1) + " " + (j + 1);
                        found = true;
                        break;
                    }
                }
                if(found) {
                    break;
                }
            }
            if(!found) {
                out[a] = "-1";
            }
        }
        return out;
    }
}
