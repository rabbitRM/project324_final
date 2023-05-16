package GraphFramework;

public class Edge implements Comparable<Edge>{
    public int weight ;
    public Vertex source ;
    public Vertex target ;
    public Vertex parent ;
    
//    public Vertex parent ;
    
    public Edge( Vertex source, Vertex target , int weight) {
        this.weight = weight;
        this.source = source;
        this.target = target;
        
    }
    public Edge(){
        
    }
     public Vertex getSource() {
        return source;
    }
    
    public Vertex getTarget() {
        return target;
    }
    
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }
    
      
      public int displayInfo(){
          return weight;
      }
      
       public int compareTo(Edge other) {
        return Integer.compare(weight, other.weight);
    }
}
