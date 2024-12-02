package practice;

import java.util.ArrayList;
import java.util.List;

public class ArticulationPoint {
    private int V; // Number of vertices
    private List<List<Integer>> adj; // Adjacency list
    private boolean[] visited; // Visited array
    private int[] disc; // Discovery times of visited vertices
    private int[] low; // Earliest visited vertex (the discovery time) reachable from subtree rooted with the vertex
    private int[] parent; // Parent vertices in DFS tree
    private boolean[] articulationPoint; // To store articulation points

    // Constructor
    public ArticulationPoint(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[V];
        disc = new int[V];
        low = new int[V];
        parent = new int[V];
        articulationPoint = new boolean[V];
        for (int i = 0; i < V; i++) {
            parent[i] = -1; // Initialize parent of all vertices as -1
        }
    }

    // Add edge to the graph
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }
 
    // Function to find and print articulation points using DFS traversal
    public void findArticulationPoints() {
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        // Printing articulation points
        System.out.println("Articulation points:");
        for (int i = 0; i < V; i++) {
            if (articulationPoint[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        for (int i = 0; i < V; i++) {
            System.out.println( disc[i] + " " +low[i]);
        }

    }

    // DFS function
    private void dfs(int u) {
        // Initialize discovery time and low value
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v : adj.get(u)) { // Go through all vertices adjacent to this
            if (!visited[v]) { // v is not visited, so make it a child of u in DFS tree
                children++;
                parent[v] = u;
                dfs(v);

                // Check if the subtree rooted at v has a connection back to an ancestor of u
                low[u] = Math.min(low[u], low[v]);

                // u is an articulation point in following cases:

                // (1) u is root of DFS tree and has two or more children
                if (parent[u] == -1 && children > 1) {
                    articulationPoint[u] = true;
                }

                // (2) u is not root and low value of one of its child is more than discovery value of u
                if (parent[u] != -1 && low[v] >= disc[u]) {
                    articulationPoint[u] = true;
                }
            } else if (v != parent[u]) { // Update low value of u for back edge
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        int V = 11; // Number of vertices
        ArticulationPoint graph = new ArticulationPoint(V);

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


        graph.findArticulationPoints();
    }

    private int time = 0; // Timer for discovery times
}

