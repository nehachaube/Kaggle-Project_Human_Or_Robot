/**
 * 
 * @author iamneha
 * Class to read features.csv file and store records in Bidder_Record class
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class FileReaderFeatures {
    
   public static int bidder_id_index;
   public static int address_infrequent_address_index;
   public static int payment_account_infrequent_account_index;
   public static int ips_per_bidder_per_auction_median_index;
   public static int on_ip_that_has_a_bot_index;
   public static int sleep_index;
   public static int bids_per_auction_median_index;
   public static int n_bids_index;
   public static int n_bids_url_index;
   public static int outcome_index;
   public static int bids_per_auction_mean_index;
   public static int ips_per_bidder_per_auction_mean_index;
   public static int max_bids_in_hour72_index;
   static ArrayList<Attribute> attributes = new ArrayList<Attribute>();
   

    public void readFirstLinetoSelectAttributes(String file) throws IOException
    {
        BufferedReader fileReader = null;
        String line="";
        fileReader = new BufferedReader(new FileReader(file));
        //reading only the header of the file to extract the features we are interested in
        line=fileReader.readLine();
        System.out.println("Reading header "+line);
        String []read_header = line.split(",");
        for (int i=0;i<read_header.length;i++)
        {
            if(read_header[i].equalsIgnoreCase("bidder_id"))
            {
                bidder_id_index=i;
                System.out.println("Enter"+i);
                Attribute attribute = new Attribute("bidder_id","not required");
                attributes.add(attribute);
            }
            if(read_header[i].equalsIgnoreCase("address_infrequent_address"))
            {
                address_infrequent_address_index=i;
                Attribute attribute = new Attribute("address_infrequent_address","discrete");
                attributes.add(attribute);
            }
            if(read_header[i].equalsIgnoreCase("payment_account_infrequent_account"))
            {
                payment_account_infrequent_account_index=i;
                Attribute attribute = new Attribute("payment_account_infrequent_account","discrete");
                attributes.add(attribute);
            }
            if(read_header[i].equalsIgnoreCase("ips_per_bidder_per_auction_median"))
            {
                ips_per_bidder_per_auction_median_index=i;
                Attribute attribute = new Attribute("ips_per_bidder_per_auction_median","continuous");
                attributes.add(attribute); 
            }
            if(read_header[i].equalsIgnoreCase("on_ip_that_has_a_bot"))
            {
                on_ip_that_has_a_bot_index=i;
                Attribute attribute = new Attribute("on_ip_that_has_a_bot","discrete");
                attributes.add(attribute); 
            }
            if(read_header[i].equalsIgnoreCase("sleep"))
            {
                sleep_index=i;
                Attribute attribute = new Attribute("sleep","discrete");
                attributes.add(attribute);             }
            if(read_header[i].equalsIgnoreCase("bids_per_auction_median"))
            {
                bids_per_auction_median_index=i;
                Attribute attribute = new Attribute("bids_per_auction_median","continuous");
                attributes.add(attribute); 
            }
            if(read_header[i].equalsIgnoreCase("n_bids"))
            {
                n_bids_index=i;
                Attribute attribute = new Attribute("n_bids","continuous");
                attributes.add(attribute); 
            }
            if(read_header[i].equalsIgnoreCase("n_bids_url"))
            {
                n_bids_url_index=i;
                Attribute attribute = new Attribute("n_bids_url","continuous");
                attributes.add(attribute); 
            }
            if(read_header[i].equalsIgnoreCase("outcome"))
            {
                outcome_index=i;
                Attribute attribute = new Attribute("outcome","classify");
                attributes.add(attribute); 
            }
            if(read_header[i].equalsIgnoreCase("bids_per_auction_mean"))
            {
                bids_per_auction_mean_index=i;
                Attribute attribute = new Attribute("bids_per_auction_mean","continuous");
                attributes.add(attribute); 
            }
            if(read_header[i].equalsIgnoreCase("ips_per_bidder_per_auction_mean"))
            {
                ips_per_bidder_per_auction_mean_index=i;
                Attribute attribute = new Attribute("ips_per_bidder_per_auction_mean","continuous");
                attributes.add(attribute); 
            }
            if(read_header[i].equalsIgnoreCase("max_bids_in_hour72"))
            {
                max_bids_in_hour72_index=i;
                Attribute attribute = new Attribute("max_bids_in_hour72","continuous");
                attributes.add(attribute); 
            }
            
        }
        
    }
    
    public void generate(Bidder_Record bidder)
    {
        if(bidder.getN_bids()>136)
        {
            if(bidder.getSleep()=="True")
            {
                generate1(bidder);
        }
            else if(bidder.getSleep()=="False" )
            {
                if(bidder.getBids_per_auction_median()<=2.5)
                {
                    bidder.setOutcome(true);
                }
                else  if(bidder.getBids_per_auction_median()>2.5)
                {
                    if(bidder.getIps_per_bidder_per_auction_median()<=1.5)
                    {
                        bidder.setOutcome(false);
                    }
                    else if(bidder.getIps_per_bidder_per_auction_median()>1.5)
                    {
                        bidder.setOutcome(true);
                    }
                }
            }
            
  }
    }
    
    public ArrayList<Bidder_Record>  readFile(String file) throws IOException
    {
        BufferedReader fileReader = null;
        String line="";
        fileReader = new BufferedReader(new FileReader(file));
        fileReader.readLine();
        ArrayList<Bidder_Record> Records = new ArrayList<Bidder_Record>();
        WriteClassification_CSV d = null;
        while((line=fileReader.readLine())!=null)
        {
            String []tokens= line.split(",");
            if(tokens.length>0)
            {
                Bidder_Record record = new Bidder_Record(tokens[bidder_id_index],Integer.parseInt(tokens[address_infrequent_address_index]),
                        Integer.parseInt(tokens[payment_account_infrequent_account_index]),Double.parseDouble(tokens[ips_per_bidder_per_auction_median_index]),
                        Integer.parseInt(tokens[on_ip_that_has_a_bot_index]),tokens[sleep_index],Double.parseDouble(tokens[bids_per_auction_median_index]),
                        Double.parseDouble(tokens[n_bids_index]),Double.parseDouble(tokens[n_bids_url_index]),Boolean.parseBoolean(tokens[outcome_index]),Double.parseDouble(tokens[bids_per_auction_mean_index]),Double.parseDouble(tokens[ips_per_bidder_per_auction_mean_index]),Double.parseDouble(tokens[max_bids_in_hour72_index]));
                Records.add(record);
            }
        }
        
        //print records read
        for(Bidder_Record record : Records)
        {
            System.out.println(record.toString());
        }
        return Records;
    }
    public void generate1(Bidder_Record bidder)
    {
        if(bidder.isPayment_account_infrequent_account()==1)
        {
            bidder.setOutcome(true);
        }
        else
            if(bidder.isPayment_account_infrequent_account()==0)
            {
                if(bidder.getN_bids()<=1370)// threoshold
                {
                    if(bidder.getIps_per_bidder_per_auction_median()<=35)
                    {
                        bidder.setOutcome(false);
                        
                    }
                    else
                        if(bidder.getIps_per_bidder_per_auction_median()>35)
                        {
                            bidder.setOutcome(true);
                            
                        }
                        
                }
                else  if(bidder.getN_bids()>1370)
                {
                    bidder.setOutcome(true);
                }
                    
            }
            else if(bidder.isPayment_account_infrequent_account()==1)
            {
                bidder.setOutcome(true);
            }
    }
   
      
    
    public static void main(String[]args) throws IOException
    {
        FileReaderFeatures f = new FileReaderFeatures();
        f.readFirstLinetoSelectAttributes("files\\features.csv");
        f.readFile("files\\features.csv");
        
    }
}
