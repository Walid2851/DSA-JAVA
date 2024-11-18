package sortestPath;

import java.io.File;
import java.util.*;

class Pair<U, V> {
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

class Graph {
    private int v;
    private int e;
    private List<List<Pair<Integer, Integer>>> g;

    Graph(String s) {
        try {
            File f = new File(s);
            Scanner sc = new Scanner(f);
            v = sc.nextInt();
            e = sc.nextInt();
            g = new ArrayList<>(v + 1);
            for (int i = 0; i <= v; i++) {
                g.add(new ArrayList<>());
            }
            for (int i = 0; i < e; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int wt = sc.nextInt();
                g.get(x).add(new Pair<>(y, wt));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void displayGraph() {
        for (int i = 0; i < g.size(); i++) {
            System.out.print(i + " -> ");
            for (Pair<Integer, Integer> child : g.get(i)) {
                System.out.print("(" + child.getFirst() + ", " + child.getSecond() + ") ");
            }
            System.out.println();
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

        displayDistances(dist);
    }

    public void runBellman(int s){

        int[] dist = new int[v+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        dist[s] = 0;
        for(int i=0;i<v-1;i++){
            for(int j=0;j<v;j++){
                int dist_u = dist[j];
                for(Pair<Integer,Integer> child:g.get(j)){
                    int v = child.getFirst();
                    int wt = child.getSecond();
                    if(dist_u + wt < dist[v]){
                        dist[v] = dist_u + wt;
                    }
                }
            }
        }
    }

    public void displayDistances(int[] dist) {
        System.out.println("Shortest distances from source:");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Node " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "Infinity" : dist[i]));
        }
    }
}

public class dijkstra {
    public static void main(String[] args) {
        Graph graph = new Graph("sortestPath/input.txt");
        graph.displayGraph();
        graph.runDijkstra(0);
        //graph.runBellman(0);   
    }
}
