import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//Decision Tree Generation
public class Tree {
    ArrayList<Attribute> attributes = new ArrayList<Attribute>();
    //Listing attributes which are used
    static String[] attributeNames= {"outcome","address_frequent_address","payment_account_infrequent_account","ips_per_bidder_per_auction_median"
            ,"on_ip_that_has_a_bot","sleep","bids_per_auction_median","n_bids","n_bids_url"};
    //Listing attribute types- discrete,continuous or attribute being used for classification as classify
    static String[] attributeValues={"classify","discrete","discrete","continuous","discrete","discrete","continuous","continuous","continuous"};
   static ArrayList<Double> attributeValue = new ArrayList<Double>(); //storing continous attribute elements in arraylist so as to compute threshold
    public void addingAttributes()
    {
        for(int i=0;i<10;i++)
        {
        Attribute attribute = new Attribute(attributeNames[i],attributeValues[i]);
        attributes.add(attribute);
        }
        
    }
    //Building Decision Tree
    public Node buildDecisionTree(ArrayList<Bidder_Record> records, Node root,Attribute attribute)
    {
        int bestAttribute=-1;
        double maxGain=0.0;double Gain=0.0;
        int count0=0,count1=0;
        double infoGain=0.0,infoGainA=0.0;double subentropy=0.0;
        double threshold=0.0;
        //For root node we compute entropy, if entropy is zero, it means no further expansion needed
        root.setEntropy(Entropy.calcEntropy(records, attribute));
        System.out.println("Main Entropy"+root.getEntropy());
        if(root.getEntropy()==0)
        {
            return root;
        }
        
        //else Calculating entropy gain for all the attributes which are not alreadyUsed
        for(int i=1;i<Attribute.NUM_ATTRIBUTES;i++)
        {
            infoGainA=0.0;
          // if(!isAttributeUsed().equalsIgnoreCase(attribute.name))
           //{
               attribute.name=attributeNames[i];
               attribute.value= attributeValues[i];
               ArrayList<Double> entropies = new ArrayList<Double>();
               ArrayList<Bidder_Record> subset1 = new ArrayList<Bidder_Record>();
               ArrayList<Bidder_Record> subset2 = new ArrayList<Bidder_Record>();
                   for(int k=0;k<records.size();k++)
                   {
                       Bidder_Record record = records.get(k);
                       if(attribute.value.equals("discrete"))
                       {
                           switch(attribute.getName()){
                           case "address_frequent_address": if(record.isAddress_frequent_address()==1)
                               subset1.add(record);
                           else subset2.add(record);
                           break;
                           case "payment_account_infrequent_account": if(record.isPayment_account_infrequent_account()==1)
                               subset1.add(record);
                           else subset2.add(record);
                           break;
                           case "on_ip_that_has_a_bot": if(record.isOn_ip_that_has_a_bot()==1)
                               subset1.add(record);
                           else subset2.add(record);
                           break;
                           case "sleep": if(record.getSleep()=="True")
                               subset1.add(record);
                           else subset2.add(record);
                           break;
                           }
                       }
                       else if(attribute.value.equals("continuous"))
                       {
                           switch(attribute.getName()){
                           case "ips_per_bidder_per_auction_median": 
                               threshold=calculatingThreshold(attributeValue);
                               attribute.setThresholdValue(threshold);
                               if(record.getIps_per_bidder_per_auction_median()<=attribute.getThresholdValue())
                               subset1.add(record);
                           else subset2.add(record);
                               break;
                           case "bids_per_auction_median": 
                               threshold=calculatingThreshold(attributeValue);
                               attribute.setThresholdValue(threshold);
                               if(record.getBids_per_auction_median()<=attribute.getThresholdValue())
                               subset1.add(record);
                           else
                               subset2.add(record);
                               break;
                           case "n_bids" : 
                               threshold=calculatingThreshold(attributeValue);
                               attribute.setThresholdValue(threshold);
                               if(record.getN_bids()<=attribute.getThresholdValue())
                               subset1.add(record);
                           else
                               subset2.add(record);
                               break;
                           case "n_bids_url" :
                               threshold=calculatingThreshold(attributeValue);
                               attribute.setThresholdValue(threshold);
                               if(record.getN_bids_url()<=attribute.getThresholdValue())
                               subset1.add(record);
                           else subset2.add(record);
                               break;
                           }
                       }
               }
                   //calculating information gain for individual attribute
                   if(subset1.size()!=0)
                   {
                        subentropy=Entropy.calcEntropy(subset1, attribute);
                        //System.out.println("Subentropy for attribute"+attribute.name+subentropy);
                        double temp=((double)subset1.size())/((double)subset1.size()+(double)subset2.size());
                       infoGainA+=temp*subentropy;
                       //System.out.println("InfoGainA1"+"attribute.name+"+infoGainA);
                       
                   }
                   if(subset2.size()!=0)
                   {
                       subentropy=Entropy.calcEntropy(subset2,attribute);
                       double temp=((double)subset2.size())/((double)subset1.size()+(double)subset2.size());
                       infoGainA+=temp*subentropy;
                      // System.out.println("InfoGainA2"+"attribute.name+"+infoGainA);
                   }
                   //calculating gain
                   Gain=infoGain-infoGainA;
                   //System.out.println("Gain for Attribute"+" "+attribute.name+" "+Gain);
                   if(Gain>maxGain)
                   {
                       bestAttribute=i;
                       maxGain=Gain;
                   }
           }
        //}
        if(bestAttribute!=-1)
        {
            root.children = new Node[2]; //each parent has two children-for discrete(0 or 1) and for continuous(<=threshold and >threshold)
            root.alreadyUsed=true; // marking attribute to be used
            Node t = new Node();
            t.classify(records);
        }
       return root; 
    }
    
    //calculating threshold for the continuous attributes
    
    public double calculatingThreshold(ArrayList<Double> attributeVlaues)
    {
        double threshold=0.0;
        Collections.sort(attributeVlaues);
        //double threshold=calculateMedian(attributeVlaues);
        return threshold;
      
    }
    //setting median as the threshold for continuous attribute 
    public static double calculateMedian(ArrayList<Double> arr)
    {
        double median = 0;
        
        // If our array's length is even, then we need to find the average of the two centered values
        if (arr.size() % 2 == 0)
        {
            int indexA = (arr.size() - 1) / 2;
            int indexB = arr.size() / 2;
            
            median = ((double) (arr.get(indexA) + arr.get(indexB))) / 2;
        }
        // Else if our array's length is odd, then we simply find the value at the center index
        else  
        {
            int index = (arr.size() - 1) / 2;
            median = arr.get(index);
        }
        
        // Print the values of the sorted array
        for (Double v : arr) 
        {
                System.out.println(v);
        }
                        
        return median;  
    }
    
    public static void main(String[]args) throws IOException
    {
        ArrayList<Bidder_Record> records= new ArrayList<Bidder_Record>();
        Node root= new Node();
        FileReaderFeatures f = new FileReaderFeatures();
        //reading training data present in features
        f.readFirstLinetoSelectAttributes("src\\features.csv");//-containing training data only
        records=f.readFile("src\\features.csv");
        Tree t = new Tree();
        //for(int i=0;i<9;i++)
       //{
            Attribute attribute = new Attribute(attributeNames[0], attributeValues[0]);
            //Creating Decision Tree
            System.out.println("Creating Decision tree model from training data");
          t.buildDecisionTree(records, root,attribute);  
        //}
          // Using decision tree model to classify test data
          ArrayList<Bidder_Record> sample_submission = new ArrayList<Bidder_Record>();
          ArrayList<Bidder_Record> classify = new ArrayList<Bidder_Record>();
          FileReaderFeatures f1 = new FileReaderFeatures();
          f.readFirstLinetoSelectAttributes("src\\sample_submission.csv");//contains test data only
         sample_submission= f1.readFile("src\\sample_submission.csv");
         WriteClassification_CSV classify1 = new WriteClassification_CSV();
         classify=root.classify(sample_submission);
         System.out.println("Classify test data into Human or Robot and storing in CSV file");
         classify1.csvWriter(classify, "sample_submission_output.csv");
         System.out.println("sample_submission_output.csv file created");
    }
}
