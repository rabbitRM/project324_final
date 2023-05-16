package PhoneNetworkApp;

import GraphFramework.Graph;
import GraphFramework.KruskalAlg;
import java.io.FileNotFoundException;
import GraphFramework.MHprimAlg;
import GraphFramework.MSTAlgorithm;


public class PhoneNWDesignApp {

    

    public static void main(String[] args) throws FileNotFoundException {


      
        KruskalAlg k = new KruskalAlg();
        BluePrintsGraph PhLNetwork = new BluePrintsGraph();
        MSTAlgorithm.graph = PhLNetwork;

         MHprimAlg f2 = new MHprimAlg();
        // long variables to store the strating time and ending time 
        //of processing Kruskal and Prim algorithms
        long startTime, endTime;

        // double variable to store differenece of time 
        //in nanoseconed between startTime and endTime variables
        double result;

  MSTAlgorithm.graph.readGraphFromFile("input.txt");
     
  f2.primMinHeap(MSTAlgorithm.graph.vertices);       
  k.kruskal(MSTAlgorithm.graph.vertices);
//        
     MSTAlgorithm.graph.makeGraph(10, 100);
     
//        // computing the starting time for the algorithm
       startTime =  System.currentTimeMillis() ;
//  
//        // calling prim Algorithm        
      f2.primMinHeap(MSTAlgorithm.graph.vertices);
//        // computing the ending time for the algorithm
        endTime = System.currentTimeMillis() ;
//
//        // computing the time it took the algorithm to process
       result = (double) (endTime - startTime) ;
//
//        //print a message
       System.out.println("It took me " + result + " ms\n");

        
        // computing the starting time for the algorithm
        startTime = System.currentTimeMillis() ;
        
        // calling Kruskal Algorithm        
        k.kruskal(MSTAlgorithm.graph.vertices);
        
        // computing the ending time for the algorithm
        endTime = System.currentTimeMillis() ;

        // computing the time it took the algorithm to process
        result = (double) (endTime - startTime) ;

        //print a message
        System.out.println("It took me " + result + " ms\n");
    }

}
