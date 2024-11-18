package sortestPath;

import java.io.File;
import java.util.*;

class Pair<U, V> {
    private final U first;
    private final V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }
}

class DSU {
    private int n;
    int[] parent;
    int[] size;

    DSU(int n) {
        this.n = n;
        parent = new int[n + 1]; // Initializing parent array with size n+1
        size = new int[n + 1]; // Initializing size array with size n+1
    }

    public void make(int v) {
        parent[v] = v;
        size[v] = 1;
    }

    public int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]); // Path compression
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b); 
        if (a != b) {
            if (size[a] < size[b]) {
                int temp = a;
                a = b;
                b = temp;
            }
            parent[b] = a;
        
            size[a] += size[b];
        }
    }
}

class Graph {
    private int v;
    private int e;
    private List<Pair<Integer, Pair<Integer, Integer>>> g;

    Graph(String s) {
        try {
            File f = new File(s);
            Scanner sc = new Scanner(f);
            v = sc.nextInt();
            e = sc.nextInt();
            g = new ArrayList<>();
            for (int i = 0; i < e; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int wt = sc.nextInt();
                g.add(new Pair<>(x, new Pair<>(y, wt)));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void displayGraph() {
        g.sort(Comparator.comparing(edge -> edge.getSecond().getSecond())); // Sort edges by weight
        System.out.println("Graph edges (format: x -> (y, weight)):");
        for (Pair<Integer, Pair<Integer, Integer>> edge : g) {
            int x = edge.getFirst();
            int y = edge.getSecond().getFirst();
            int wt = edge.getSecond().getSecond();
            System.out.println(x + " -> (" + y + ", " + wt + ")");
        }
    }

    public void runKruskal() {
        // Sort edges by weight
        g.sort(Comparator.comparing(edge -> edge.getSecond().getSecond()));
        DSU dsu = new DSU(v);
        for (int i = 1; i <= v; i++) dsu.make(i);

        int total_cost = 0;

        System.out.println("Edges in the Minimum Spanning Tree (MST):");
        for (Pair<Integer, Pair<Integer, Integer>> edge : g) {
            int x = edge.getFirst();
            int y = edge.getSecond().getFirst();
            int wt = edge.getSecond().getSecond();
            if (dsu.find(x) != dsu.find(y)) { // Check if adding this edge will not form a cycle
                dsu.union(x, y);
                total_cost += wt;
                System.out.println(x + " - " + y + " with weight " + wt);
            }
        }
        System.out.println("Total cost of MST: " + total_cost);
    }
}

public class krushkal {
    public static void main(String[] args) {
        Graph graph = new Graph("sortestPath/input.txt");
        graph.displayGraph();
        graph.runKruskal();
    }
}
