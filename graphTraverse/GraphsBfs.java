package graphTraverse;
import java.util.*;

public class GraphsBfs{


    static final int N = 1000010;

    static ArrayList<Integer>[] g = new ArrayList[N];

    static int visit[] = new int[N];
    static int dist[] = new int[N];

   public static void bfs(int source){
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        visit[source]=1;
        dist[source]=0;

        while(!q.isEmpty()){
            int curr_v = q.poll();
            for(int child:g[curr_v]){
                if(visit[child]==0){
                    q.add(child);
                    visit[child]=1;
                    dist[child]=dist[curr_v]+1;
                }
            }
        }
    }

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();

        for(int i=0;i<=v;i++){
            g[i] = new ArrayList<>();
        }

        for(int i=0;i<e;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            g[x].add(y);
            g[y].add(x);
        }
        bfs(1);

        for(int i=1;i<=v;i++){
            System.out.println(visit[i] + " " + dist[i]);
        }
    }
}