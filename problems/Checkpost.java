package problems;

import java.io.*;
import java.util.*;
 
public class Checkpost {
    static class Graph {
        private int v;
        private List<Integer>[] g;
        private List<Integer>[] gTranspose;
 
        Graph(int v) {
            this.v = v;
            g = new ArrayList[v + 1];
            gTranspose = new ArrayList[v + 1];
            for (int i = 0; i <= v; i++) {
                g[i] = new ArrayList<>();
                gTranspose[i] = new ArrayList<>();
            }
        }
 
        public void addEdge(int u, int v) {
            g[u].add(v);
            gTranspose[v].add(u);
        }
 
        public void dfs1(int s, boolean[] visited, ArrayDeque<Integer> stack) {
            visited[s] = true;
            for (int child : g[s]) {
                if (!visited[child]) {
                    dfs1(child, visited, stack);
                }
            }
            stack.push(s);
        }
 
        public void dfs2(int s, boolean[] visited, List<Integer> component) {
            visited[s] = true;
            component.add(s);
            for (int child : gTranspose[s]) {
                if (!visited[child]) {
                    dfs2(child, visited, component);
                }
            }
        }
 
        public List<List<Integer>> findSCCs() {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            boolean[] visited = new boolean[v + 1];
 
            // Step 1: Perform DFS and fill the stack according to finishing times
            for (int i = 1; i <= v; i++) {
                if (!visited[i]) {
                    dfs1(i, visited, stack);
                }
            }
 
            // Step 2: Process the transposed graph in stack order
            Arrays.fill(visited, false);
            List<List<Integer>> sccList = new ArrayList<>();
            while (!stack.isEmpty()) {
                int node = stack.pop();
                if (!visited[node]) {
                    List<Integer> component = new ArrayList<>();
                    dfs2(node, visited, component);
                    sccList.add(component);
                }
            }
 
            return sccList;
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        // Input number of vertices (n)
        int n = Integer.parseInt(br.readLine());
 
        // Input the cost of each vertex (junction)
        int[] cost = new int[n + 1];
        String[] costInput = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(costInput[i - 1]);
        }
 
        // Input number of edges (m)
        int m = Integer.parseInt(br.readLine());
 
        // Create graph and read edges
        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            String[] edgeInput = br.readLine().split(" ");
            int u = Integer.parseInt(edgeInput[0]);
            int v = Integer.parseInt(edgeInput[1]);
            graph.addEdge(u, v);
        }
 
        // Find SCCs
        List<List<Integer>> sccList = graph.findSCCs();
 
        long sum = 0;
        long numWays = 1;
        final int MOD = 1_000_000_007;
 
        // Process each SCC
        for (List<Integer> component : sccList) {
            int minCost = Integer.MAX_VALUE;
            int count = 0;
 
            // Calculate minimum cost and count directly in the SCC
            for (int node : component) {
                if (cost[node] < minCost) {
                    minCost = cost[node];
                    count = 1;
                } else if (cost[node] == minCost) {
                    count++;
                }
            }
 
            sum += minCost;
            numWays = (numWays * count) % MOD;
        }
 
        // Output results
        bw.write(sum + " " + numWays + "\n");
        bw.flush();
    }
}
