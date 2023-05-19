package PhoneNetworkApp;

import GraphFramework.KruskalAlg;
import GraphFramework.MHprimAlg;
import GraphFramework.MSTAlgorithm;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class PhoneNWDesignApp {

    

    public static void main(String[] args) throws FileNotFoundException {


      
      Scanner input = new Scanner (System.in);
      BluePrintsGraph PhLNetwork = new BluePrintsGraph();
     
       MHprimAlg prim = new MHprimAlg(MSTAlgorithm.graph);
       KruskalAlg kruskal = new KruskalAlg(MSTAlgorithm.graph);
       
        // long variables to store the strating time and ending time 
        //of processing Kruskal and Prim algorithms
        long startTime, endTime;

        // double variable to store differenece of time 
        //in nanoseconed between startTime and endTime variables
        double result;
        System.out.println("\t -------Test and compare Different Minimum Spanning Tree Algorithms-------\n");
        System.out.println("\t1- Kruskal's Algorithm& Prim's Algorithm (based on Priority Queue)\n"
                + "\t2- Prim's Algorithm (based on Min Heap)& Prim's Algorithm(based on Priority Queue)");
        System.out.print(">> Enter your choice (1 or 2): ");
        int choice = input.nextInt();
        if(choice == 1)
              PhLNetwork.readGraphFromFile("input.txt");
        else {
            System.out.print("Enter the number of Vertices");
            int vertexNO= input.nextInt();
             System.out.print("Enter the number of Edges");
            int edgeNO= input.nextInt();
             System.out.print("Enter 0 if the graph is undirected or 1 if directed ");
            int isdiagraph= input.nextInt();
            PhLNetwork.makeGraph(vertexNO, edgeNO , isdiagraph);  
        }
          
            
        
 startTime =  System.currentTimeMillis() ;
 prim.primMST();
 endTime = System.currentTimeMillis() ;
 result = (double) (endTime - startTime) ;
 System.out.println("It took me " + result + " ms\n");
 
 startTime =  System.currentTimeMillis() ;
 kruskal.kruskal();
 endTime = System.currentTimeMillis() ; 
 result = (double) (endTime - startTime) ;
 System.out.println("It took me " + result + " ms\n");      


    

    }

}
