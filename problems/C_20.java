package problems;

import java.io.*;
import java.util.*;
 
public class C_20 {
    static class Pair<U, V> {
        private final U first;
        private final V second;
 
        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
 
        public U getFirst() {
            return first;
        }
 
        public V getSecond() {
            return second;
        }
    }
 
    static class Graph {
        private final int v;
        private final int e;
        private final List<List<Pair<Integer, Integer>>> g;
 
        Graph(int v, int e, BufferedReader br) throws IOException {
            this.v = v;
            this.e = e;
            g = new ArrayList<>(v + 1);
            for (int i = 0; i <= v; i++) {
                g.add(new ArrayList<>());
            }
            for (int i = 0; i < e; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int wt = Integer.parseInt(st.nextToken());
                g.get(x).add(new Pair<>(y, wt));
                g.get(y).add(new Pair<>(x, wt));
            }
        }
 
        public void runDijkstra(int source, int destination) {
            int[] dist = new int[v + 1];
            int[] parent = new int[v + 1];
            boolean[] visited = new boolean[v + 1];
 
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(parent, -1);
 
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getFirst));
            dist[source] = 0;
            pq.add(new Pair<>(0, source));
 
            while (!pq.isEmpty()) {
                Pair<Integer, Integer> node = pq.poll();
                int u = node.getSecond();
 
                if (visited[u]) continue;
                visited[u] = true;
 
                if (u == destination) break;
 
                for (Pair<Integer, Integer> edge : g.get(u)) {
                    int v = edge.getFirst();
                    int wt = edge.getSecond();
 
                    if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                        parent[v] = u;
                        pq.add(new Pair<>(dist[v], v));
                    }
                }
            }
 
            if (dist[destination] == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
 
            StringBuilder result = new StringBuilder();
            List<Integer> path = new ArrayList<>();
            for (int at = destination; at != -1; at = parent[at]) {
                path.add(at);
            }
            Collections.reverse(path);
            for (int node : path) {
                result.append(node).append(" ");
            }
            System.out.println(result.toString().trim());
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(n, m, br);
        graph.runDijkstra(1, n);
    }
}