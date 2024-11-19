package problems;

import java.io.*;
import java.util.*;


public class cities {

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

 static class Graph{
    private final int v;
    private final int e;
    private final int k;
    private final List<List<Pair<Integer,Integer>>> g;
    private final List<Pair<Integer,Integer>> train;

    Graph(int v,int e,int k) throws IOException{
        this.v = v;
        this.e = e;
        this.k = k;
        g = new ArrayList<>(v+1);
        train = new ArrayList<>(k);
        for(int i=0;i<=v;i++){
            g.add(new ArrayList<>());
        }
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<e;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int wt = sc.nextInt();
            g.get(x).add(new Pair<>(y, wt));
        }
        for(int i=0;i<k;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            train.add(new Pair<>(x, y));
        }
    }

        public void runDijkstra(int source) {
            int[] visit = new int[v + 1];
            int[] dist = new int[v + 1];
    
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(visit, 0);
            dist[source] = 0;
    
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.getFirst(), b.getFirst()));
    
            pq.add(new Pair<>(0, source));
    
            while (!pq.isEmpty()) {
                Pair<Integer, Integer> node = pq.poll();
                int u = node.getSecond();
                int dist_u = node.getFirst();
    
                if (visit[u] == 1) continue; 
                visit[u] = 1;
    
                for (Pair<Integer, Integer> child : g.get(u)) {
                    int v = child.getFirst();
                    int wt = child.getSecond();
                    if (dist_u + wt < dist[v]) {
                        dist[v] = dist_u + wt;
                        pq.add(new Pair<>(dist[v], v));
                    }
                }
            }
            int cnt =0;

            for(int i=0;i<k;i++){
                if(train.get(i).getSecond() >= dist[train.get(i).getFirst()])cnt++;
            }
            System.out.println(cnt);
        }

}

    public static void main(String args[]) throws IOException{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        Graph graph = new Graph(n, m, k);
        graph.runDijkstra(1);
    }
    
}
