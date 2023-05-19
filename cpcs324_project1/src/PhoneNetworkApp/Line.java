
package PhoneNetworkApp;

import GraphFramework.Edge;

import GraphFramework.Vertex;
public class Line extends Edge{
    
    private int lLength ;
    
    
    public Line( Vertex source, Vertex target , int weight) {
       super ( source , target , weight);
       lLength = weight *5;
    }
    
    
    
    public String displayInfo(){
        return "line length: "+ lLength ;
    }

    @Override
    public int getWeight() {
       return lLength;
    }

}
