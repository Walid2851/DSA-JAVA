import java.util.Scanner;
import java.util.Vector;

class bfsTraverse{
   Vector<Integer> v = new Vector<>(100000);

   Vector[] g = new Vector[100000];



   public void show(){
   Scanner sc = new Scanner(System.in);
   int vertex = sc.nextInt();
  int edge = sc.nextInt();
   System.out.println(vertex +  " " + edge);
  }
   

}

public class bfs{
    public static void main(String args[]){
       bfsTraverse bt = new bfsTraverse();
       bt.show();
    }
}