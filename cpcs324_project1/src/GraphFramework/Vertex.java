package GraphFramework;
 

import java.util.LinkedList;

public abstract class Vertex {
    
    private int label ;
    private boolean isVisited ;    
    private LinkedList<Edge> adjLists = new LinkedList<>();
   

   
    public Vertex(int label ){
        this.label = label ;
        isVisited = false ;
        
    }
public Edge getMinEdge(){
        int cost = Integer.MAX_VALUE ;
        Edge min =null;
        for (int i = 0; i < adjLists.size(); i++) {
            if(adjLists.get(i).getWeight() < cost){
                cost = adjLists.get(i).getWeight();
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

    public int getLabel() {
        return label;
    }

    public boolean isIsVisited() {
        return isVisited;
    }
    
    public LinkedList<Edge> getAdjLists() {
        return adjLists;
    }
    
    public abstract String displayInfo (boolean isReq1);

    
  
}
