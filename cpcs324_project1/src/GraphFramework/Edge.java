package GraphFramework;

public abstract class Edge implements Comparable<Edge>{
    private int weight ;
    private Vertex source ;
    private Vertex target ;
    private Vertex parent ;
    
//    public Vertex parent ;
    
    public Edge( Vertex source, Vertex target , int weight) {
        this.weight = weight;
        this.source = source;
        this.target = target;
        
    }
    public Edge( Vertex source,  int weight) {
        this.weight = weight;
        this.source = source;
      
        
    }
    public Edge(){
        
    }
     public Vertex getSource() {
        return source;
    }
    
    public Vertex getTarget() {
        return target;
    }
    
   public abstract int getWeight() ;


      public abstract String displayInfo();

      
       public int compareTo(Edge other) {
        return Integer.compare(weight, other.weight);
    }
       
}
