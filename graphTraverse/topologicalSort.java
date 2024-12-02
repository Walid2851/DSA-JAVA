package graphTraverse;
import java.util.*;

class Graph {
    private final int v;
    private final List<List<Integer>> adj;

    // Constructor
    Graph(int v) {
        this.v = v;
        adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Add a directed edge from x to y
    public void addEdge(int x, int y) {
        adj.get(x).add(y);
    }

    // Recursive DFS helper for topological sort
    private void dfs(int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, stack);
            }
        }
        stack.push(node); // Add node to stack after processing all neighbors
    }

    // Function to perform topological sort
    public List<Integer> topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[v];

        // Perform DFS for each unvisited node
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }

        // Extract the nodes from the stack to form the topological order
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}

public class TopologicalSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the number of nodes and edges
        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph graph = new Graph(n);

        // Input the edges
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph.addEdge(x, y);
        }

        // Perform topological sort
        List<Integer> topoSort = graph.topologicalSort();

        // Print the result
        System.out.println("Topological Order:");
        for (int node : topoSort) {
            System.out.print(node + " ");
        }
    }
}
