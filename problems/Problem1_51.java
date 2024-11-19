package problems;

import java.util.*;
import java.io.File;
import java.io.IOException;

class GRAPH {

    public int v; 
    public int e; 
    public int k;
    public int l;
    public List<List<Integer>> g;

    GRAPH(String s) {
        try {
            File f = new File(s);
            Scanner sc = new Scanner(f);
            v = sc.nextInt(); 
            e = sc.nextInt(); 
            k = sc.nextInt();
            l = sc.nextInt();

            g = new ArrayList<>(v + 1); 
            for (int i = 0; i <= v; i++) {
                g.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                add_edge(x, y);
            }
  
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addVertex(int n) {
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
            v++;
        }
    }

    public void add_edge(int u, int v) {
        g.get(u).add(v);
        g.get(v).add(u);
    }

    public int vertex_num() {
        return v;
    }

    public void display_vertex(int v) {
        System.out.println(g.get(v));
    }

    public void displayGraph() {
        for (int i =0; i < v; i++) {
            System.out.print(i + "->" );
            for(Integer child:g.get(i)){
                System.out.print(child+" ");
            }
            System.out.println();
        }
    }
    public int bfs(int s, int f) {
        boolean[] visit = new boolean[v + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{s, f});
        visit[s] = true;
        int city_visit = 1;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int curr_v = node[0];
            int r_fuel = node[1];

            for (int child : g.get(curr_v)) {
                if (!visit[child] && r_fuel > 0) {
                    visit[child] = true;
                    queue.offer(new int[]{child, r_fuel - 1});
                    city_visit++;
                }
            }
        }
        return city_visit;
    }
}

public class Problem1_51{
    public static void main(String args[])throws IOException{
        GRAPH graph = new GRAPH("input1.txt");
        //graph.displayGraph();
        int ans = graph.bfs(graph.l, graph.k);
        System.out.println(ans);

    }
}