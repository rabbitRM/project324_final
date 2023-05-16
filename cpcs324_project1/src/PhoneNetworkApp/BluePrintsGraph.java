/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhoneNetworkApp;

import GraphFramework.Graph;
import GraphFramework.Vertex;

/**
 *
 * @author cvs
 */
public class BluePrintsGraph extends Graph{
     
     public static Line createEdge(Vertex source1, Vertex target1 , int weight1 ){
        return new Line (source1 , target1 , weight1);
        
    }
     
      public static Office createVertex(char lable ){
        return new Office(lable);
    }
     
}
