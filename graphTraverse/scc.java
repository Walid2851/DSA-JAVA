package graphTraverse;

import java.util.*;

public class scc {

    static final int N = 1000010;

    private ArrayList<Integer>[] g = new ArrayList[N];    // Original graph
    private ArrayList<Integer>[] gt = new ArrayList[N];   // Transposed graph (reverse edges)
    private int[] visit = new int[N];   // Visited array for both DFS passes

    static int v, e;
    ArrayList<Integer> postOrder = new ArrayList<>();  // Post-order list for first DFS
    ArrayList<ArrayList<Integer>> components = new ArrayList<>(); // List of all components

    // Constructor
    scc(int v, int e) {
        this.v = v;
        this.e = e;

        // Initialize adjacency lists for original and transposed graphs
        for (int i = 0; i <= v; i++) {
            g[i] = new ArrayList<>();
            gt[i] = new ArrayList<>();
        }
    }

    // Input the graph
    void inputGraph() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < e; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            g[x].add(y);  // Directed edge from x to y
        }
    }

    // DFS for original graph to record post-order in `postOrder`
    void dfs1(int node) {
        visit[node] = 1;
        for (int child : g[node]) {
            if (visit[child] == 0) {
                dfs1(child);
            }
        }
        postOrder.add(node);  // Add node in post-order
    }

    // Reverse the graph (transpose)
    void reverseGraph() {
        for (int i = 0; i < v; i++) {
            for (int child : g[i]) {
                gt[child].add(i);  // Reverse edge direction
            }
        }
    }

    // DFS for transposed graph to find SCCs
    void dfs2(int node, ArrayList<Integer> comp) {
        visit[node] = 1;
        comp.add(node);  // Add node to the current component
        for (int child : gt[node]) {
            if (visit[child] == 0) {
                dfs2(child, comp);
            }
        }
    }

    // Run DFS for the first pass to get the post-order
    void runFirstPass() {
        for (int i = 0; i < v; i++) {
            if (visit[i] == 0) {
                dfs1(i);
            }
        }
    }

    // Run DFS on the reversed graph to discover SCCs
    void runSecondPass() {
        Collections.reverse(postOrder);  // Reverse the post-order for second DFS pass
        Arrays.fill(visit, 0);  // Reset visited array for the second pass

        for (int node : postOrder) {
            if (visit[node] == 0) {
                ArrayList<Integer> comp = new ArrayList<>();
                dfs2(node, comp);
                components.add(comp);  // Store the discovered component
            }
        }
    }

    // Output the strongly connected components
    void showComponents() {
        System.out.println("Strongly Connected Components:");
        for (ArrayList<Integer> component : components) {
            System.out.println(component);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();  // Number of vertices
        int e = sc.nextInt();  // Number of edges

        scc ts = new scc (v, e);
        ts.inputGraph();       // Input the graph
        ts.runFirstPass();     // First DFS pass to get post-order
        ts.reverseGraph();     // Reverse the graph
        ts.runSecondPass();    // Second DFS pass to find SCCs
        ts.showComponents();   // Output SCCs
    }
}
