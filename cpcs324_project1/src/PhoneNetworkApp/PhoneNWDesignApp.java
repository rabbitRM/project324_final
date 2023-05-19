package PhoneNetworkApp;

import GraphFramework.KruskalAlg;
import GraphFramework.MHprimAlg;
import GraphFramework.MSTAlgorithm;
import java.io.FileNotFoundException;



public class PhoneNWDesignApp {

    

    public static void main(String[] args) throws FileNotFoundException {


      
      
        BluePrintsGraph PhLNetwork = new BluePrintsGraph();
        MSTAlgorithm.graph = PhLNetwork;

         MHprimAlg f2 = new MHprimAlg(MSTAlgorithm.graph);
       KruskalAlg k = new KruskalAlg(MSTAlgorithm.graph);
        // long variables to store the strating time and ending time 
        //of processing Kruskal and Prim algorithms
        long startTime, endTime;

        // double variable to store differenece of time 
        //in nanoseconed between startTime and endTime variables
        double result;

        
        
        
//      MSTAlgorithm.graph.readGraphFromFile("input.txt");
//    f2.primMST();
// 
//     k.kruskal();

MSTAlgorithm.graph.makeGraph(100, 1000 , 0);  
//
 f2.primMST();
 
 k.kruskal();
       


    
     
        // computing the starting time for the algorithm
//       startTime =  System.currentTimeMillis() ;
       
//        // calling prim Algorithm        
//      
////        // computing the ending time for the algorithm
//        endTime = System.currentTimeMillis() ;
////
////        // computing the time it took the algorithm to process
//       result = (double) (endTime - startTime) ;
//////
//////        //print a message
////       System.out.println("It took me " + result + " ms\n");
//
//        
//        // computing the starting time for the algorithm
//        startTime = System.currentTimeMillis() ;
//        
        // calling Kruskal Algorithm        
//        k.kruskal(MSTAlgorithm.graph);
        
//        // computing the ending time for the algorithm
//        endTime = System.currentTimeMillis() ;
//
//        // computing the time it took the algorithm to process
//        result = (double) (endTime - startTime) ;
//
//        //print a message
//        System.out.println("It took me " + result + " ms\n");
    }

}
