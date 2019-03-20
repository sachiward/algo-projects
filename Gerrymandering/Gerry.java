
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


public class Gerry implements Gerrymanderer {

    @Override
    public int[][] gerrymander(Electorate e, boolean party) {

        int d = e.getNumberOfDistricts();
        int[][] result = new int[d][d];
        int superCount = 0;

        while(true) {
            //StdOut.println("SUPERCOUNT" + superCount);
            superCount++;

            result = new int[d][d];

            int minToWin = ((d - 1) / 2) + 1, resultCount = 0, numValidDistricts = 1, count;
            boolean[] voters = e.getVoters();
            Set<Integer> visited = new HashSet<>();

            Graph g = e.getGraph();

            for (int i = 0; i < d * d; i++) {

                count = 0;

                if (voters[i] == party && !visited.contains(i)) {

                    int[] tmp = new int[d];

                    for (int q = 0; q < tmp.length; q++) {
                        tmp[q] = -1;
                    }

                    tmp[count++] = i;
                    int[] arr = findPath(g, d, i, minToWin, party, voters);
                    int numAdj = 0;

                    for (int k = 0; k < arr.length; k++) {
                        if (arr[k] != 0) {
                            numAdj++;
                        }
                    }

                    //StdOut.println(" I: " + i + " ");

                    for (int q = 0; q < minToWin - 1; q++) tmp[count++] = findMin(arr, d);

                    int[] nextTo = possibleValues(voters, tmp, g, d);

                    //StdOut.print(" NEXTTO: ");
                    //for (int k : nextTo) StdOut.print(" " + k);

                    //StdOut.println(" NUMADJ: " + numAdj);

                    for (int n = 0; n < (minToWin - 1) * 100; n++) {

                        if (numAdj < minToWin - 1 || nextTo.length <= minToWin) continue;

                        tmp = findRandom(minToWin, nextTo, tmp, count);

                        if (isValidDistrict(tmp, d, g)) {

                            for (int j : tmp) visited.add(j);

                            Graph tmpGraph;

                            tmpGraph = newGraph(visited, d);
                            //StdOut.print(tmpGraph);

                            if (isValidDistrictInMap(tmpGraph, d, numValidDistricts, false)) {

                                numValidDistricts++;
                                g = tmpGraph;
                                //StdOut.println("TRUE IN MAP");

                                for (int q = 0; q < tmp.length; q++) {
                                    //StdOut.println(tmp[q]);
                                    result[resultCount][q] = tmp[q];
                                }

                                resultCount++;
                                break;
                            } else {
                                for (int j : tmp) visited.remove(j);
                            }
                        }//end of isValidDistrict
                        count = d - minToWin + 1;
                    } //end of n loop
                } // end of party if

                //StdOut.print(g);
            } // end of i for loop
            //StdOut.println("--MADE GRAPHS FOR PARTY--");

            boolean end = false;
            boolean flag = false;
            int x = 0;

            for (int i = 0; i < d * d; i++) {
                x++;
                if (x > d * d) {
                    flag = true;
                }
                if(x > d * d * 2) break;
                //StdOut.println("X: " + x);

                int counter = 0;

                if (!visited.contains(i)) {

                    while (true) {
                        counter++;
                        if (counter > d * 100) break;

                        Set<Integer> tmp = new HashSet<>();
                        tmp.add(i);

                        Set<Integer> random = new HashSet<>();

                        int r = 0, rand = 0;

                        for (int n = 0; n < d - 1; n++) {

                            if (n == 0) {
                                for (int j : g.adj(i)) {
                                    random.add(j);
                                }
                            } else {
                                for (int j : g.adj(rand)) {
                                    if (!random.contains(j)) random.add(j);
                                }
                            }

                            Integer[] arr = random.toArray(new Integer[random.size()]);
                            // StdOut.println("SIZE: " + random.size());

                            while (true) {
                                r = new Random().nextInt(random.size());
                                rand = arr[r];

                                if (!tmp.contains(rand)) {
                                    tmp.add(rand);
                                    break;
                                }
                            }

                        }

                        Integer[] newTMP = tmp.toArray(new Integer[tmp.size()]);
                        //StdOut.println("RAND");
                        int[] test = new int[tmp.size()];
                        for (int q = 0; q < newTMP.length; q++) {
                            //StdOut.println(newTMP[q]);
                            test[q] = newTMP[q];
                        }

                        if (isValidDistrict(test, d, g)) {

                            for (int j : test) visited.add(j);

                            Graph tmpGraph;

                            tmpGraph = newGraph(visited, d);
                            //StdOut.print(tmpGraph);
                            if (resultCount == d - 1) {
                                for (int q = 0; q < test.length; q++) {
                                    //StdOut.println("TRUE IN MAP");
                                    //StdOut.println(test[q]);
                                    result[resultCount][q] = test[q];
                                }
                                end = true;
                                break;
                            }

                            if (isValidDistrictInMap(tmpGraph, d, numValidDistricts, flag)) {

                                numValidDistricts++;
                                g = tmpGraph;
                                //StdOut.println("TRUE IN MAP");

                                for (int q = 0; q < test.length; q++) {
                                    //StdOut.println(test[q]);
                                    result[resultCount][q] = test[q];
                                }

                                resultCount++;
                                break;
                            } else {
                                for (int j : test) visited.remove(j);
                            }
                        }//end of isValidDistrict

                    } //end of while(true)

                } //end of visited contains

                if (end) break;
                if (i == d * d - 1) i = 0;
            } //end of i

            if(end) break;
        }

        return result;
    }


    public static int[] findRandom(int minToWin, int[] nextTo, int[] tmp, int count){
        for (int k = 0; k < minToWin - 1; k++) {

            int r = new Random().nextInt(nextTo.length);
            boolean flag = false;
            for(int l: tmp){
                if (l == nextTo[r]){
                    flag = true;
                }
            }
            if (!flag) {
                tmp[count++] = nextTo[r];
            }
            else k--;
            //StdOut.println(k);
        }

        return tmp;
    }


    public static int[] findPath(Graph g, int d, int i, int minToWin, boolean party, boolean[] voters){
        int[] arr = new int[d*d];
        BreadthFirstPaths sp = new BreadthFirstPaths(g, i);
        for (int j = 0; j < d * d; j++) {
            if (j != i) {
                if (minToWin >= sp.distTo(j) && party == voters[j]) {
                    arr[j] = sp.distTo(j);
                    //StdOut.println(" I: " + i + " J: " + j + " ARR: " + arr[j]);
                }
            }
        } // end of j for loop
        return arr;
    }

    public static int[] possibleValues(boolean[] voters, int[] tmp, Graph g, int d){
        int [] nextTo = new int[d * d];
        for (int q = 0; q < nextTo.length; q++){
            nextTo[q] = -1;
        }
        int nextToCounter = 0;

        for (int q = 0; q < tmp.length; q++)
        {
            if(tmp[q] == -1) break;
            for (int adj : g.adj(tmp[q])) {

                boolean notInTmp = true, inNextTo = true;

                for(int l: tmp) if(adj == l)notInTmp = false;

                for(int l: nextTo) if(adj == l)inNextTo = false;

                if (inNextTo && notInTmp) {
                    nextTo[nextToCounter++] = adj;
                    //if(voters[j] != party) nextTo[nextToCounter++] = adj;
                }
            }
        }

        int counter = 0;
        for (int j:nextTo) {
            if(j != -1)counter++;
        }
        int [] arr = new int[counter];
        counter = 0;
        for (int j:nextTo) {
            if(j != -1)arr[counter++] = j;
        }
        return arr;
    }

    public static boolean isValidDistrict(int[] tmpDistrict, int d, Graph g){
        int [] tmp = tmpDistrict.clone();
        Graph graph = new Graph(d * d);
        for (int i = 0; i < tmp.length; i++){
            if(tmp[i] != -1) {
                for (int j : g.adj(tmp[i])) {
                    //StdOut.println(" I: " +  tmp[i] + " J: " + j);
                    for (int k = 0; k < tmp.length; k++) {
                        if (j == tmp[k] && k != i) {
                            graph.addEdge(tmp[i], tmp[k]);
                            //tmp[i] = -1;
                        }
                    }
                }
            } // end of if != -1
        } //end of i
        //StdOut.println(graph);
        CC cc = new CC(graph);
        //StdOut.print(" CCCOUNT: " + cc.count());
        if (cc.count() != (d * d) - d +1){
            return false;
        }
        return true;
    }

    public static boolean isValidDistrictInMap(Graph graph, int d, int num, boolean test){
        CC cc = new CC(graph);
        int count = cc.count();
        int bicount = 0;
        Biconnected bic = new Biconnected(graph);
        for (int v = 0; v < graph.V(); v++)
            if (bic.isArticulation(v)) {
                //StdOut.println(" BIC: " + v);
                bicount++;
            }
        //StdOut.println(" CC: " + count);
        if(test) bicount = 1;
        if((count == (d * num) + 1) && bicount < d - 1) return true;
//        if((count > (d * num) + 1) && (count <= (d * num) + d)) {
//            return true;
//        }
        return false;
    }


    public static Graph newGraph(Set<Integer> visited, int d){
        Graph graph = new Graph(d * d);
        for (int i = 0; i < d * d; i++) {
            int x = i / d;
            int y = i % d;

            if(!visited.contains(i)){
                if (x < d - 1 && !visited.contains((x + 1) * d + y)) {
                    // Add neighbor to east
                    graph.addEdge(i, (x + 1) * d + y);
                }
                if (y < d - 1 && !visited.contains(x * d + y + 1)) {
                    // Add neighbor to north
                    graph.addEdge(i, x * d + y + 1);
                }
            } // end of if check
        } // end of i loop
        return graph;
    }//end of newGraph

    public static int findMin(int[] arr, int d){
        int min = d;
        int min_idx = 0;
        for(int k = 0; k < arr.length; k++){
            if (arr[k] < min && arr[k] != 0){
                min = arr[k];
                min_idx = k;
            }
        }
        arr[min_idx] = 0;
        //StdOut.println("min_idx: " + min_idx + " min: " + min);
        return min_idx;
    }
}
