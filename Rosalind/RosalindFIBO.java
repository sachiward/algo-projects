import edu.princeton.cs.algs4.StdOut;

public class RosalindFIBO {

    static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static void main(String[] args) {
        StdOut.println(fib(24));
    }

}
