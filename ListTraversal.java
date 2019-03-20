public class ListTraversal {
    static class Node {
        int key;
        Node next;
        Node(int key, Node next) {
            this.key = key;
            this.next = next;
        }
    }

    static void traverseForward(Node list) {
        for(Node n = list; n != null; n = n.next) {
            System.out.println(n.key);
        }
//        Node n = list;
//        while (n != null){
//            System.out.println(n.key);
//            n = n.next;
//        }
    }

    public static void main(String[] args) {
        Node list = new Node(4, new Node(8, new Node(15, new Node(16, new Node(23, new Node(42, null))))));
        traverseForward(list);
    }
}
