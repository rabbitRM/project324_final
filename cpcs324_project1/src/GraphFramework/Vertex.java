package GraphFramework;
 

import java.util.LinkedList;

public class Vertex {
    
    public int label ;
    public boolean isVisited ;    
    public  LinkedList<Edge> adjLists = new LinkedList<>();
   public Vertex parent;

    public void setParent(Vertex parent) {
        this.parent = parent;
    }
    
    public Vertex(int label ){
        this.label = label ;
        isVisited = false ;
        
    }
public Edge getMinEdge(){
        int cost = Integer.MAX_VALUE ;
        Edge min =null;
        for (int i = 0; i < adjLists.size(); i++) {
            if(adjLists.get(i).weight < cost){
                cost = adjLists.get(i).weight;
                min = adjLists.get(i);
            }
        }
        return min ;
    }
    public void setLabel(int label) {
        this.label = label;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public void setAdjLists(LinkedList<Edge> adjLists) {
        this.adjLists = adjLists;
    }
    public Vertex(){
        
    }
    
    public LinkedList<Edge> getEdges() {
        return adjLists;
    }
    
    public int displayInfo (){
        return label ;
    }
    
  
}
