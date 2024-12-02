package problems;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class DeletingRails {
 
    static class Edge implements Comparable<Edge> {
        int node;
        // int src;
        long weight;
        boolean isRail; 
        
        public Edge( int node, long weight, boolean isRail) {
            // this.src = src;
            this.node = node;
            this.weight = weight;
            this.isRail = isRail;
        }
 
        @Override
        public int compareTo(Edge that) {
            if(that.weight > this.weight) return -1;
            if(that.weight == this.weight){
                if (!this.isRail) {
                    return -1;
                }
                if (!that.isRail) {
                    return 1;
                }        
                return 0;        
            }
            return 1; 
        }
    }
 
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
 
 
    public static void main(String[] args) {
        FastReader sc = new FastReader();
        int n, m, k;
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        List<List<Edge>> adj = new ArrayList<>(n+1);
        List<List<Integer>> rails = new ArrayList<>(n+1);
        for (int i = 0; i <= n; i++) {adj.add(new ArrayList<>());rails.add(new ArrayList<>());}
 
        for (int i = 0; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            adj.get(a).add(new Edge(b, c, false));
            adj.get(b).add(new Edge(a, c, false));
        }
        for (int i = 0; i < k; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            rails.get(a).add(b);
            adj.get(1).add(new Edge(a,b, true));
            adj.get(a).add(new Edge(1, b, true));
        }
 
        // dijkstra implementation
        PriorityQueue<Edge> Q = new PriorityQueue<>();
        boolean vis[] = new boolean[n+1];
        boolean ViaRail[] = new boolean[n+1];
        long weight[] = new long[n+1];
        Arrays.fill(weight, Long.MAX_VALUE);
        weight[1] = 0;
        
        Q.add(new Edge(1, 0, false));
 
        while (!Q.isEmpty()) {
            Edge e = Q.poll();
            if(vis[e.node]) continue;
            vis[e.node] = true;
 
            for (Edge p : adj.get(e.node)) {
                if(!vis[p.node]){
                    if(weight[p.node] > (p.weight + weight[e.node])){
                        weight[p.node] = p.weight + weight[e.node];
                        ViaRail[p.node] = p.isRail;
                        Q.add(new Edge(p.node, weight[p.node], false)); // amar banano kono node rail na, rail shudhu 1 theke
                    } else if(weight[p.node] == (p.weight + weight[e.node]) && !p.isRail){
                        ViaRail[p.node] = p.isRail;
                    }
                }
            }
        }
 
        int count = 0;
        for (int i = 1; i < rails.size(); i++) {
            boolean flag = false;
            for(Integer r : rails.get(i)){
                if(r >= weight[i] && (!ViaRail[i] || flag)) count++;
                flag = true;
            }
        }
        System.out.println(count);
    }
}