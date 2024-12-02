package practice;

import java.util.ArrayList;
import java.util.List;

public class BridgeFinder {
    private int V; // Number of vertices
    private List<List<Integer>> adj; // Adjacency list
    private boolean[] visited; // Visited array
    private int[] disc; // Discovery times of visited vertices
    private int[] low; // Earliest visited vertex (the discovery time) reachable from subtree rooted with the vertex
    private int time; // Timer for discovery times

    // Constructor
    public BridgeFinder(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[V];
        disc = new int[V];
        low = new int[V];
    }

    // Add edge to the graph
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // Function to find and print bridges using DFS traversal
    public void findBridges() {
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, -1); // Start DFS with no parent (-1)
            }
        }
    }

    // DFS function
    private void dfs(int u, int parent) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) {
            if (!visited[v]) { // If v is not visited, explore it
                dfs(v, u);

                // Update low value of u based on subtree rooted at v
                low[u] = Math.min(low[u], low[v]);

                // If low value of v is greater than discovery time of u, (u, v) is a bridge
                if (low[v] > disc[u]) {
                    System.out.println("Bridge found: " + u + " - " + v);
                }
            } else if (v != parent) { // Update low value for back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        int V = 11; // Number of vertices
        BridgeFinder graph = new BridgeFinder(V);

        graph.addEdge(1, 3);
        graph.addEdge(1, 5);
        graph.addEdge(3, 9);
        graph.addEdge(9, 8);
        graph.addEdge(9, 4);
        graph.addEdge(9, 2);
        graph.addEdge(2, 6);
        graph.addEdge(2, 7);
        graph.addEdge(4, 7);
        graph.addEdge(2, 5);
        graph.addEdge(2, 4);

        graph.findBridges();
    }
}
