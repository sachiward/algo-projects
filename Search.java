import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Search {
    static int unorderedLinearSearch(double[] arr, double key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    static int orderedLinearSearch(double[] arr, double key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
            if (arr[i] > key) {
                return -1;
            }
        }
        return -1;
    }

    static int binarySearch(double[] arr, double key) {
        int low = 0;
        int high = arr.length;
        while (low <= high) {
            int mid = low + (low - high) / 2;
            if (arr[mid] < key) {
                low = mid + 1;
            }
            else if (arr[mid] > key) {
                high = mid - 1;
            }
            else {
                return mid;
            }
        }
        return arr.length;
    }

    public static void main(String[] args) {
        double[] arr = new double[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = StdRandom.uniform();
        }
        arr[666] = 0.123456789;
        StdOut.println(orderedLinearSearch(arr, 0.123456789));
        StdOut.println(binarySearch(arr, 0.123456789));
    }
}
