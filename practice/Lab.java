package practice;

import java.io.File;
import java.io.IOException;
import java.util.*;

class GRAPH {

    private int v; 
    private int e; 
    private List<List<Integer>> g; // Use List<List<Integer>> for adjacency list

    GRAPH(String s) {
        try {
            File f = new File(s);
            Scanner sc = new Scanner(f);
            v = sc.nextInt(); 
            e = sc.nextInt(); 

            g = new ArrayList<>(v + 1); // Initialize list for 1-based indexing
            for (int i = 0; i <= v; i++) {
                g.add(new ArrayList<>()); // Initialize each list
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

    public int[] BFS(int source){
        int[] visit = new int[v+1];
        int[] dist = new int[v+1];

        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();

        q.add(source);
        visit[source]=1;
        dist[source]=0;

        while(!q.isEmpty()){
            int curr_v = q.poll();
            System.out.println("visit: "+curr_v);
            for(int child:g.get(curr_v)){
                if(visit[child]==0){
                    visit[child]=1;
                    dist[child] = 1+dist[curr_v];
                    q.add(child);
                }
            }
        }

        return dist;
    }

    public void DFS(int source){
        int[] visit = new int[v+1];
        rundfs(source,visit);
        System.out.println();

    }
    public void rundfs(int source,int[] visit){
        visit[source]=1;
        System.out.print(source+" ");
        for(int child:g.get(source)){
            if(visit[child]==0){
                rundfs(child, visit);
            }
        }
    }

    public List<Integer> topologicalSort(){
        ArrayList<Integer> ans = new ArrayList<>();
        int[] visit = new int[v+1];
           for(int i=0;i<v;i++){
            if(visit[i]==0){
                dfs(i,ans,visit);
            }
           }
           Collections.reverse(ans);
           return ans;
    }

    public void dfs(int s,List<Integer> ans,int[] visit){
        visit[s]=1;
        for(int child:g.get(s)){
            if(visit[child]==0){
                dfs(child, ans, visit);
            }
        }
        ans.add(s);
    }

    public List<List<Integer>>  SCC (){
       
        List<Integer> topoOrder = topologicalSort();

        List<List<Integer>> gt = new ArrayList<>(v+1);
        for (int i = 0; i <= v; i++) {
            gt.add(new ArrayList<>());
        }
        //transpose

        for(int i=0;i<v;i++){
            for(int child:g.get(i)){
                gt.get(child).add(i);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int[] visit = new int[v+1];
        for(int i:topoOrder){
            if(visit[i]==0){
                List<Integer> comp = new ArrayList<>();
                dfs3(i,visit,gt,comp);
                ans.add(comp);
            }
        }

        return ans;

    }

    public void dfs3(int s,int[]visit,List<List<Integer>> gt,List<Integer> ans){
        visit[s]=1;
        ans.add(s);
        for(int child:gt.get(s)){
            if(visit[child]==0){
                dfs3(child, visit, gt, ans);
            }
        }
    }

   
}

public class Lab{
    public static void main(String args[])throws IOException{

        GRAPH graph = new GRAPH("input.txt");

        //lab-1
        //display the adjacency list
         graph.displayGraph();
       //  graph.addVertex(3);
       //  System.out.println(graph.vertex_num());
       // graph.display_vertex(2);
       // int[] dist = graph.BFS(1);
        //for(int x:dist)System.out.println(x);
        
        //lab - 2
        // System.out.println("Performing DFS starting from vertex 5:");
        // graph.DFS(5);
       // System.out.println("\nPerforming Topological Sort:");
      //  List<Integer> topoOrder = graph.topologicalSort();
       // System.out.println("Topological Sort order: " + topoOrder);

      
       //SCC
      System.out.println("\nPerforming Strongly Connected Component:");
      List<List<Integer>> sccList = graph.SCC();

      System.out.println("Strongly Connected Components:");
      for (List<Integer> component : sccList) {
          System.out.println(component);
      }

    }
}

