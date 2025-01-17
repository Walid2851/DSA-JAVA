import java.io.*;
import java.util.*;


class MergeSort{

    public static void mergeSort(int arr[],int st,int en){

    }
}

class FloyedWarshall{

}
    
    
    
    public class LabExperiment {
        public static void main(String[] args)  throws IOException{
        
      // Merge Sort
            int[] array = {12, 11, 13, 5, 6, 7};
            System.out.println("Input Array: " + Arrays.toString(array));
            MergeSort.mergeSort(array, 0, array.length - 1);
        System.out.println("Sorted Array: " + Arrays.toString(array));
//      // Floyd-Warshall
//         int[][] graph = {
//             {0, 3, Integer.MAX_VALUE, 5},
//             {2, 0, Integer.MAX_VALUE, 4},
//             {Integer.MAX_VALUE, 1, 0, Integer.MAX_VALUE},
//             {Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 0}
//         };
//         System.out.println("Shortest distances:");
//         FloydWarshall.floydWarshall(graph);
       
//  // Articulation Points and Bridges
//         Graph graphObj = new Graph("input.txt");
//         System.out.println("Articulation Points:");
//         graphObj.findArticulationPoints();
//         System.out.println("Bridges:");
//         graphObj.findBridges();

     
    }
}

