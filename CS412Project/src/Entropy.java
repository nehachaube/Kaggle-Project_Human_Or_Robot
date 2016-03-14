import java.util.ArrayList;

//Class to calculate Entropy

public class Entropy {

    public static double calcEntropy(ArrayList<Bidder_Record> bidder_record,Attribute attribute)
    {
        double entropy=0.0;
        
            int count0=0;
            int count1=0;
            for(int j=0;j<bidder_record.size();j++)
            {
                Bidder_Record record = bidder_record.get(j);
                //if(attribute.value.equals("classify"))
               // {
                  //  switch(attribute.getName()){
                   // case "outcome": 
                if(record.getOutcome())
                        count0++;
                    else count1++;
                   // break;
                    //}
               // }
                
            }
            double probability0 = count0 / (double)bidder_record.size();
            double probability1 = count1 / (double)bidder_record.size();
            
            if(count0>0 || count1>0)
            {
                entropy = (-probability0 * (Math.log(probability0) / Math.log(2))) - (probability1 * (Math.log(probability1) / Math.log(2))) ;
            }
        
        return entropy;
    }
    
}
