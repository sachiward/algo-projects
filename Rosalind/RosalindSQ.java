import edu.princeton.cs.algs4.StdOut;

public class RosalindSQ {

    public static void main(String[] args) {

        //make an adjacency matrix for the graph
        In in = new In("rosalind_square.txt");
        int numGraphs = in.readInt();
        for (int i = 0; i < numGraphs; i++) {
            int vertices = in.readInt();
            int edges = in.readInt();

            int graph[][] = new int[vertices][vertices];
            for (int j = 0; j < edges; j++) {
                int vertex1 = in.readInt();
                int vertex2 = in.readInt();
                //1 for connection between two edges
                graph[vertex1-1][vertex2-1] = 1;
                graph[vertex2-1][vertex1-1] = 1;
            }
            //multiply matrix by itself to get the number of ways to get to each vertex in 2 steps
            int[][] product = multiplyMatrices(graph, graph, vertices);
            StdOut.print(hasSquare(product, vertices) + " ");
        }
    }

    public static int hasSquare(int[][] matrix,int vertices){
        //if any of the entries in the product matrix is 2, there are two ways of length 2 to get a vertex, thus there must be a square
        for(int k=0; k<vertices; k++){
            for(int l=0; l<vertices; l++){
                if(matrix[k][l]==2 && k!=l){
                    return 1;
                }
            }

        }
        return -1;
    }

    /**method that multiplies two 2D matrices**/
    public static int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix, int vertices) {
        int[][] product = new int[vertices][vertices];
        for(int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                for (int k = 0; k < vertices; k++) {
                    product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }

        return product;
    }
}