
package PhoneNetworkApp;
import GraphFramework.Vertex;


public class Office extends Vertex{
    
    
    public Office (int lable ){
       super( lable );
    }
    
    //********
   
    
   
   public String setLabelO (int num ){
       return "O"+num ;
   }
    
    public String displayInfo(boolean isReq1){
        if(isReq1)
        return "Office No."+ (char)(super.getLabel()+'A');
        else 
        return "Office No." +setLabelO(getLabel());
    }

    
}
