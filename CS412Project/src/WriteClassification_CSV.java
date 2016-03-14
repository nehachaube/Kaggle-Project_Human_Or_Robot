import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//class which generates the sample_submission_output file
public class WriteClassification_CSV {
    
  //Delimiter used in CSV file
  private static final String COMMA_DELIMITER = ",";
  private static final String NEW_LINE_SEPARATOR = "\n";
  //CSV file header
  private static final String FILE_HEADER = "bidder_id,prediction";
  public double threshold1 = 0.0;

    
  
    public void csvWriter(ArrayList<Bidder_Record> bidders,String fileName)
    {
        FileWriter fileWriter = null;
        try {
               fileWriter = new FileWriter(fileName);
               //Write the CSV file header
               fileWriter.append(FILE_HEADER.toString());
       //Add a new line separator after the header
               fileWriter.append(NEW_LINE_SEPARATOR);
               //Write a new bidder object list to the CSV file
                 for(Bidder_Record bidder:bidders)
                 {
                     fileWriter.append(bidder.getBidder_id());
                     fileWriter.append(COMMA_DELIMITER);
                     if(!bidder.getOutcome())
                      {
                     fileWriter.append("0");
                      }
                     else if(bidder.getOutcome())
                      {
                         fileWriter.append("1");
                      }
                     fileWriter.append(NEW_LINE_SEPARATOR);
                        }
                  } catch (Exception e) {
                      System.out.println("Error in CsvFileWriter !!!");
                       e.printStackTrace();
                         } finally {
                  try {
                fileWriter.flush();
                fileWriter.close();
                   } catch (IOException e) {
               System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
                        }
                   
            
                    }
            
                }
  
    public static void main(String[]args) throws IOException
    {
        //testing writing of result(sample submission) in CSV file
        /*ArrayList<Bidder_Record> sample_submission = new ArrayList<Bidder_Record>();
        ArrayList<Bidder_Record> classify1 = new ArrayList<Bidder_Record>();
        FileReaderFeatures f = new FileReaderFeatures();
        f.readFirstLinetoSelectAttributes("files\\sample_submission.csv");
       sample_submission= f.readFile("files\\sample_submission.csv");
       WriteClassification_CSV t = new WriteClassification_CSV();
       classify1=t.classify(sample_submission);
       
       t.csvWriter(classify1, "files\\sample_submission_output.csv");*/
    }

}
