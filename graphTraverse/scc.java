package graphTraverse;

import java.util.*;

class Graph {
    private final int v;
    private final List<List<Integer>> g;

    Graph(int v) {
        this.v = v;
        g = new ArrayList<>(v + 1);
        for (int i = 0; i <= v; i++) {
            g.add(new ArrayList<>());
        }
    }

    public void addEdge(int x, int y) {
        g.get(x).add(y);
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

    public void dfs2(int s, int[] visit, List<List<Integer>> gt, List<Integer> comp) {
        visit[s] = 1;
        comp.add(s);
        for (int child : gt.get(s)) {
            if (visit[child] == 0) {
                dfs2(child, visit, gt, comp);
            }
        }
    }

    public List<List<Integer>> scc() {
        Stack<Integer> st = new Stack<>();
        int[] visit = new int[v + 1];

        // First DFS pass
        for (int i = 1; i <= v; i++) {
            if (visit[i] == 0) {
                dfs1(i, visit, st);
            }
        }

        // Transpose graph
        List<List<Integer>> gt = new ArrayList<>(v + 1);
        for (int i = 0; i <= v; i++) {
            gt.add(new ArrayList<>());
        }
        for (int i = 1; i <= v; i++) {
            for (int child : g.get(i)) {
                gt.get(child).add(i); 
            }
        }

        // Second DFS pass
        List<List<Integer>> sccList = new ArrayList<>();
        Arrays.fill(visit, 0);
        while (!st.isEmpty()) {
            int x = st.pop();
            if (visit[x] == 0) {
                List<Integer> comp = new ArrayList<>();
                dfs2(x, visit, gt, comp);
                sccList.add(comp);
            }
        }

        return sccList;
    }
}

public class scc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        Graph graph = new Graph(n);

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph.addEdge(x, y);
        }

        List<List<Integer>> sccList = graph.scc();

        System.out.println("Strongly Connected Components:");
        for (List<Integer> component : sccList) {
            System.out.println(component);
        }
    }
}
