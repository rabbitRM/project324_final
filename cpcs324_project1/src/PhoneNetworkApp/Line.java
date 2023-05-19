

package PhoneNetworkApp;

import GraphFramework.Edge;

import GraphFramework.Vertex;
public class Line extends Edge{
    
    private static int lLength ;
    
    
    public Line( Vertex source1, Vertex target1 , int weight1) {
       super ( source1 , target1 , weight1);
       lLength = weight1 *5 ;
    }
    
    
    public int diplayInfo(){
        return lLength;
    }
    
}
