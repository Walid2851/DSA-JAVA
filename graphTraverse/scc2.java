package graphTraverse;

import java.util.*;

class Graph{
        private final int v;
        private final int e;
        private final List<List<Integer>> g;
 
        Graph(int v, int e){
            this.v = v;
            this.e = e;
            g = new ArrayList<>(v + 1);
            for (int i = 0; i <= v; i++) {
                g.add(new ArrayList<>());
            }
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < e; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                g.get(x).add(y);
            }
        }


    public void dfs1(int s, int[] visit, Stack<Integer> st) {
        visit[s] = 1; 
        for (int child : g.get(s)) {
            if (visit[child] == 0) {
                dfs1(child, visit, st);
            }
        }
        st.push(s);
    }

    // Method to find all SCCs using Kosaraju's Algorithm
    public List<List<Integer>> scc() {
        Stack<Integer> st = new Stack<>();
        int visit[] = new int[v + 1];

        // Perform DFS and fill the stack according to finishing times
        for (int i = 1; i <= v; i++) {
            if (visit[i] == 0) {
                dfs1(i, visit, st);
            }
        }

        // Transpose the graph
        Graph gt = new Graph(v, e);
        for (int i = 1; i <= v; i++) {
            for (int child : g.get(i)) {
                gt.g.get(child).add(i); // Reverse the direction of edges
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        Arrays.fill(visit, 0);

        // Process vertices in the order defined by the stack
        while (!st.isEmpty()) {
            int x = st.pop();
            if (visit[x] == 0) {
                List<Integer> comp = new ArrayList<>();
                gt.dfs2(x, visit, gt.g, comp);
                ans.add(comp);
            }
        }
        return ans;
    }

    // DFS method for the transposed graph to find SCCs
    public void dfs2(int s, int[] visit, List<List<Integer>> gt, List<Integer> ans) {
        visit[s] = 1;
        ans.add(s); // Add current vertex to the SCC
        for (int child : gt.get(s)) {
            if (visit[child] == 0) {
                dfs2(child, visit, gt, ans);
            }
        }
    }
}

public class scc2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph graph = new Graph(n, m);

        List<List<Integer>> sccList = graph.scc();

        
    }
}
