package problems;

import java.util.*;
import java.io.*;

public class Hierarchy {
    static class Edge implements Comparable<Edge> {
        int from, to, cost;
        
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
    
    static class DisjointSet {
        int[] parent, rank;
        
        DisjointSet(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        boolean unite(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) return false;
            
            if (rank[px] < rank[py]) {
                int temp = px;
                px = py;
                py = temp;
            }
            parent[py] = px;
            if (rank[px] == rank[py]) {
                rank[px]++;
            }
            return true;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // Read number of employees
        int n = Integer.parseInt(br.readLine());
        
        // Read qualifications
        int[] qualifications = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            qualifications[i] = Integer.parseInt(st.nextToken());
        }
        
        // Read number of applications
        int m = Integer.parseInt(br.readLine());
        
        // Store applications (edges)
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }
        
        // Sort edges by cost
        Collections.sort(edges);
        
        // Track supervisors and total cost
        boolean[] hasSupervisor = new boolean[n + 1];
        long totalCost = 0;
        DisjointSet dsu = new DisjointSet(n);
        
        // Process edges
        for (Edge edge : edges) {
            // Check qualification constraint
            if (qualifications[edge.from] <= qualifications[edge.to]) 
                continue;
            
            // Check supervisor constraint
            if (hasSupervisor[edge.to]) 
                continue;
            
            // Check for cycle
            if (dsu.find(edge.from) == dsu.find(edge.to)) 
                continue;
            
            // Accept this connection
            totalCost += edge.cost;
            dsu.unite(edge.from, edge.to);
            hasSupervisor[edge.to] = true;
        }
        
        // Count roots
        int rootCount = 0;
        for (int i = 1; i <= n; i++) {
            if (!hasSupervisor[i]) 
                rootCount++;
        }
        
        // Validate hierarchy
        if (rootCount != 1) {
            System.out.println(-1);
            return;
        }
        
        System.out.println(totalCost);
    }
}