package graphTraverse;

import java.util.*;

public class GraphDfs {

    final static int N = 1000010;

     private ArrayList<Integer>[] g = new ArrayList[N];
     private int visit[] = new int[N];
     private int dist[] = new int[N];
     int v;
     int e;

     GraphDfs(int v,int e){
        this.v = v;
        this.e = e;
        for(int i=0;i<=v;i++){
            g[i] = new ArrayList<>();
        }
     }

     Scanner sc = new Scanner(System.in);

     void inputGraph(){
    System.out.println("Enter the edges (pair of vertices):");
        for(int i=0;i<e;i++){
        int x = sc.nextInt();
        int y = sc.nextInt();
        g[x].add(y);
        g[y].add(x);
        }
     }

    //  void show(){
    //     for(int child:g[1]){
    //         System.out.println(child);
    //     }
    //  }

    void dfs(int source){
        System.out.println("Visiting: " + source);
        visit[source]=1;
        for(int child:g[source]){
            if(visit[child]==0){
                dfs(child);
            }
        }
    }


    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices and edges:");
        int v = sc.nextInt();
        int e = sc.nextInt();

        GraphDfs gd = new GraphDfs(v, e);

        gd.inputGraph();
        System.out.println("Starting DFS from vertex 1:");
        gd.dfs(1);
    }
}
