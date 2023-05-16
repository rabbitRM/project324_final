
package PhoneNetworkApp;
import GraphFramework.Vertex;


public class Office extends Vertex{
    
    
    public Office (int lable ){
       super( lable );
    }
    
    //********
    public static Office createVertex(int lable ){
        return new Office(lable);
    }
    
    
    //*********
//    public String setLabel (int num ){
//        return "O"+num ;
//    }
//    
    public int displayInfo(){
        return label ;
    }
    
    
}
