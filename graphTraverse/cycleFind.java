package graphTraverse;

import java.util.ArrayList;
import java.util.Scanner;

public class cycleFind {

    static final int N = 1000010;

    private ArrayList<Integer> g[] = new ArrayList[N];
    private int visit[] = new int[N];
    private int dist[] = new int[N];
    int v;
    int e;

    cycleFind(int v,int e){
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
            g[y].add(x);
        }
    }

    boolean findCycle(int source,int parent){

        visit[source]=1;

        boolean isCycle = false;

        for(int child:g[source]){
            if(visit[child]==1 && child == parent) continue;
            if(visit[child]==1) return true;
            isCycle |= findCycle(child, source);
        }
        return isCycle;
    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc .nextInt();

        cycleFind cf = new cycleFind(v, e);
        cf.inputGraph();
        boolean ans = cf.findCycle(1,-1);
        System.out.println(ans);
    }
}
