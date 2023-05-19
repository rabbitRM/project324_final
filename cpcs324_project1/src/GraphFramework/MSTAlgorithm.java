package GraphFramework;

import java.util.ArrayList;



public abstract class MSTAlgorithm {
    
   
  
  
    protected  ArrayList<GraphFramework.Edge> MSTResultList =  new ArrayList<GraphFramework.Edge>();
    
    
    protected static Graph graph  ;
   
    
    public abstract void displayResultingMST( ArrayList<GraphFramework.Edge> MSTResultList , boolean isReq1);
        
    
}
