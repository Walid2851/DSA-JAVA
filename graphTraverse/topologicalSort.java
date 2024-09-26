package graphTraverse;

import java.util.*;

public class topologicalSort {


    static final int N = 1000010;

    private ArrayList<Integer> g[] = new ArrayList[N];
    private int visit[] = new int[N];
    static int v;
    static int e;

    topologicalSort(int v,int e){
        this.v = v;
        this.e = e;

        for(int i=0;i<=v;i++){
            g[i] = new ArrayList<>();
        }
    }
    Scanner sc = new Scanner(System.in);

    void inputGraph(){
        for(int i=0;i<e;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            g[x].add(y);
        }
    }

 ArrayList<Integer> ans = new ArrayList<>(v);

    void dfs(int source){
        visit[source]=1;

        for(int child:g[source]){
            if(visit[child]==0){
                visit[child]=1;
                dfs(child);
            }
        }
        ans.add(source);
    }

    void runDfs(){
        for(int i=1;i<=v;i++){
            if(visit[i]==0){
                dfs(i);
            }
        }
    }

    void showResult(){
        Collections.reverse(ans);
        for(int x:ans){
            System.out.println(x);
        }
    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc .nextInt();

        topologicalSort ts = new topologicalSort(v, e);
        ts.inputGraph();
        ts.runDfs();
        ts.showResult();

    }
}
