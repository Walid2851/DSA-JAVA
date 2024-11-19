package practice;

import java.io.File;
import java.io.IOException;
import java.util.*;

class GRAPH {

    private int v; // Number of vertices
    private int e; // Number of edges
    private List<List<Integer>> g; // Use List<List<Integer>> for adjacency list

    // Variables for DFS
    private String[] color;
    private int[] prev, d, f;
    private int time;

    // Constructor to initialize the graph from a file
    GRAPH(String s) {
        try {
            File f = new File(s);
            Scanner sc = new Scanner(f);
            v = sc.nextInt(); 
            e = sc.nextInt(); 

            g = new ArrayList<>(v + 1); // Initialize list for 1-based indexing
            for (int i = 0; i <= v; i++) {
                g.add(new ArrayList<>()); // Initialize each list
            }

            for (int i = 0; i < e; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                add_edge(x, y);
            }
  
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addVertex(int n) {
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
            v++;
        }
    }

    public void add_edge(int u, int v) {
        g.get(u).add(v);
        g.get(v).add(u);
    }

    public int vertex_num() {
        return v;
    }

    public void display_vertex(int v) {
        System.out.println(g.get(v));
    }

    public void displayGraph() {
        for (int i = 0; i < v; i++) {
            System.out.print(i + " -> ");
            for (Integer child : g.get(i)) {
                System.out.print(child + " ");
            }
            System.out.println();
        }
    }

    // BFS function
    public int[] BFS(int start) {
        // Arrays to store color, previous vertex, and distance
        String[] color = new String[v + 1];
        int[] prev = new int[v + 1];
        int[] distance = new int[v + 1];

        // Initialize all vertices
        for (int i = 0; i <= v; i++) {
            color[i] = "WHITE"; // Unvisited vertex
            prev[i] = -1; // No predecessor
            distance[i] = Integer.MAX_VALUE; // Infinite distance
        }

        // Set the starting vertex
        color[start] = "GRAY";
        distance[start] = 0;
        prev[start] = -1;

        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        // BFS loop
        while (!queue.isEmpty()) {
            int u = queue.poll();
            // Explore all adjacent vertices of u
            for (int v : g.get(u)) {
                if (color[v].equals("WHITE")) {
                    color[v] = "GRAY";
                    distance[v] = distance[u] + 1;
                    prev[v] = u;
                    queue.add(v);
                }
            }
            color[u] = "BLACK"; // Mark vertex u as fully explored
        }

        return prev; // Return the predecessors to reconstruct paths later
    }

    // Print-Path function
    public void PrintPath(int s, int v, int[] prev) {
        if (v == s) {
            System.out.print(s + " ");
        } else if (prev[v] == -1) {
            System.out.println("No path");
        } else {
            PrintPath(s, prev[v], prev);
            System.out.print(v + " ");
        }
    }

    // DFS function
    public void DFS() {
        color = new String[v + 1];
        prev = new int[v + 1];
        d = new int[v + 1];
        f = new int[v + 1];
        time = 0;

        // Initialize all vertices
        for (int i = 0; i <= v; i++) {
            color[i] = "WHITE";
            prev[i] = -1;
            d[i] = Integer.MAX_VALUE;
            f[i] = Integer.MAX_VALUE;
        }

        // Start DFS from each vertex
        for (int u = 0; u < v; u++) {
            if (color[u].equals("WHITE")) {
                DFS_Visit(u);
            }
        }
    }

    // DFS_Visit function
    private void DFS_Visit(int u) {
        color[u] = "GRAY";
        time++;
        d[u] = time;

        // Explore all adjacent vertices of u
        for (int v : g.get(u)) {
            if (color[v].equals("WHITE")) {
                prev[v] = u;
                DFS_Visit(v);
            }
        }

        color[u] = "BLACK";
        time++;
        f[u] = time;
    }

    // Function to print discovery and finishing times
    public void printDFSTimes() {
        System.out.println("Vertex\tDiscovery\tFinishing");
        for (int i = 0; i < v; i++) {
            System.out.println(i + "\t" + d[i] + "\t\t" + f[i]);
        }
    }

}

public class slide {
    public static void main(String args[]) throws IOException {

        GRAPH graph = new GRAPH("input.txt");

        // Display the adjacency list (Lab 1)
        graph.displayGraph();

        // Perform BFS and print path (Lab 2)
        int startVertex = 1; // Start vertex for BFS
        int endVertex = 5; // End vertex to find the path to

        int[] prev = graph.BFS(startVertex);

        // Print the path from startVertex to endVertex
        System.out.println("\nPath from vertex " + startVertex + " to vertex " + endVertex + ":");
        graph.PrintPath(startVertex, endVertex, prev);
        System.out.println();

        // Perform DFS
        System.out.println("Performing DFS:");
        graph.DFS();
        graph.printDFSTimes();
    }
}
