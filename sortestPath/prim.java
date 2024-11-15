package sortestPath;

import java.io.*;
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

class Graph {
    private int v; // Number of vertices
    private int e; // Number of edges
    private List<List<Pair<Integer, Integer>>> g; // Adjacency list

    // Constructor to initialize graph from file
    Graph(String s) {
        try {
            File f = new File(s);
            Scanner sc = new Scanner(f);
            v = sc.nextInt();
            e = sc.nextInt();
            g = new ArrayList<>(v + 1);

            // Initialize adjacency list
            for (int i = 0; i <v; i++) {
                g.add(new ArrayList<>());
            }

            // Read edges
            for (int i = 0; i < e; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int wt = sc.nextInt();
                g.get(x).add(new Pair<>(y, wt));
               // g.get(y).add(new Pair<>(x, wt)); // Since the graph is undirected
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e);
        }
    }

    // Display the graph
    public void displayGraph() {
        for (int i = 0; i < g.size(); i++) {
            System.out.print(i + " --> ");
            for (Pair<Integer, Integer> child : g.get(i)) {
                System.out.print("(" + child.getFirst() + ", " + child.getSecond() + ") ");
            }
            System.out.println();
        }
    }

    // Prim's Algorithm for MST
    public void runPrim(int s) {
        int[] key = new int[v + 1]; // Min weight to connect to MST
        int[] parent = new int[v + 1]; // Parent array to store MST
        boolean[] inMst = new boolean[v + 1]; // Track nodes included in MST

        // Initialize arrays
        Arrays.fill(key, Integer.MAX_VALUE);
        key[s] = 0;
        parent[s] = -1;

        // Priority queue for selecting minimum weight edge
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.getFirst(), b.getFirst()));
        pq.add(new Pair<>(0, s));

        while (!pq.isEmpty()) {
            // Extract minimum weight node
            var node = pq.poll();
            int u = node.getSecond();

            // If already in MST, skip
            if (inMst[u]) continue;

            // Mark as included in MST
            inMst[u] = true;

            // Traverse adjacent vertices
            for (var child : g.get(u)) {
                int v = child.getFirst();
                int wt = child.getSecond();

                // Update if not in MST and edge weight is smaller
                if (!inMst[v] && wt < key[v]) {
                    key[v] = wt;
                    pq.add(new Pair<>(key[v], v));
                    parent[v] = u;
                }
            }
        }

        // Print the MST
        System.out.println("Edge \tWeight");
        for (int i = 0; i <v; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i + "\t" + key[i]);
            }
        }
    }
}

public class prim {
    public static void main(String[] args) {
        Graph graph = new Graph("sortestPath/input.txt");
        graph.displayGraph();
        graph.runPrim(0); // Start Prim's Algorithm from node 0
    }
}
