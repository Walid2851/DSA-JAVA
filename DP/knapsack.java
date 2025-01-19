package DP;

public class knapsack {

    static int max(int a,int b){
        return (a>b) ? a : b ;
    }

    static void knapsack(){

        int P[] = {0,10,15,40};
        int wt[] = {0,1,2,3};

        int m=4;int n=3;

        int K[][] = new int[1000][1000];

        for(int i=0;i<=n;i++){
            for(int w=0;w<=m;w++){

                if(i==0 || w==0 )K[i][w]=0;

                else if(wt[i] <=w){
                    K[i][w] = max ((P[i]+K[i-1][w-wt[i]]),K[i-1][w]);
                }
                else{
                    K[i][w]=K[i-1][w];
                }
            }
        }

        for(int i=0;i<=n;i++){
            for(int w=0;w<=m;w++){
                System.out.print(K[i][w] + " ");
            }
            System.out.println();
        }

    }
    public static void main(String args[]){
            knapsack();
    }
}
