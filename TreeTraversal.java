import edu.princeton.cs.algs4.ResizingArrayQueue;
import edu.princeton.cs.algs4.StdOut;

public class TreeTraversal {
    static class Node {
        int key;
        Node left;
        Node right;
        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
        Node(int key) {
            this.key = key;
        }
    }

    static void traverseBreadthFirst(Node root){
        ResizingArrayQueue<Node> q = new ResizingArrayQueue<>();
        q.enqueue(root);
        while (!q.isEmpty()) {
            Node n = q.dequeue();
            StdOut.println(n.key);
            if (n.left != null) {
                q.enqueue(n.left);
            }
            if (n.right != null){
                q.enqueue(n.right);
            }
        }
    }

    static void traversePostOrder(Node root){
        if (root != null) {
            traversePostOrder(root.left);
            traversePostOrder(root.right);
            StdOut.println(root.key);

        }
    }

    public static void main(String[] args) {
        Node root = new Node(3,
                //left subtree
                new Node(4, new Node(2), new Node(0)),
                //right subtree
                new Node(1, new Node(6), new Node(5))
                );
        traversePostOrder(root);
    }
}
