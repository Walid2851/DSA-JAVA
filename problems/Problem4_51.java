package problems;

import java.io.File;
import java.io.IOException;
import java.util.*;

class GRAPH {

    public int v; 
    public int e; 
    public List<List<Integer>> g;

    GRAPH(String s) {
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
       // g.get(v).add(u);
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
    public void dfs1(int s, int[] visit, Stack<Integer> st) {
        visit[s] = 1;
        for (int child : g.get(s)) {
            if (visit[child] == 0) {
                dfs1(child, visit, st);
            }
        }
        st.push(s); 
    }
    public void dfs2(int s, int[] visit, List<List<Integer>> gt, List<Integer> ans) {
        visit[s] = 1;
        ans.add(s);
        for (int child : gt.get(s)) {
            if (visit[child] == 0) {
                dfs2(child, visit, gt, ans);
            }
        }
    }
    public List<List<Integer>>  SCC (){
       
        Stack<Integer> st = new Stack<>();
        int visit[] = new int[v + 1];

        for (int i = 1; i <= v; i++) {
            if (visit[i] == 0) {
                dfs1(i, visit, st);
            }
        }

        List<List<Integer>> gt = new ArrayList<>(v+1);
        for (int i = 0; i <= v; i++) {
            gt.add(new ArrayList<>());
        }
        for(int i=0;i<=v;i++){
            for(int child:g.get(i)){
                gt.get(child).add(i);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        Arrays.fill(visit, 0);

        while (!st.isEmpty()) {
            int x = st.pop();
            if (visit[x] == 0) {
                List<Integer> comp = new ArrayList<>();
                dfs2(x, visit, gt, comp);
                ans.add(comp);
            }
        }

        return ans;

    }

}

public class Problem4_51{
    public static void main(String args[])throws IOException{

        GRAPH graph = new GRAPH("input2.txt");

        // graph.displayGraph();

         List<List<Integer>> sccList = graph.SCC();

        int x = 1;
        int[] res = new int[graph.v+1];

      for (List<Integer> component : sccList) {
          for(int v :component){
            res[v] = x;
          }
          x++;
      }

      System.out.println(x-1);
      for(int i=1;i<=graph.v;i++){
        System.out.print(res[i]+" ");
      }
      System.out.println();

    }
}
