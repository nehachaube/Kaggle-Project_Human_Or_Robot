import java.util.ArrayList;
//Class used to construct tree

public class Node {

    public Node parent;
    public Node[] children;
    public ArrayList<Bidder_Record> bidder_record;
    public double entropy;
    public boolean alreadyUsed; //whether the attribute is already used or not
    public Attribute attribute;
    
    public Node()
    {
        this.bidder_record = new ArrayList<Bidder_Record>();
        setEntropy(0.0);
        setParent(null);
        setChildren(null);
        setAlreadyUsed(false);
        setAttribute(new Attribute("", ""));
    }
    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node[] getChildren() {
        return children;
    }
    public ArrayList<Bidder_Record> classify(ArrayList<Bidder_Record> bidders){
        for(Bidder_Record bidder:bidders)
        {
            if(bidder.getN_bids()<=136)
            {
                bidder.setOutcome(true);
            }
            else
            {
                FileReaderFeatures f = new FileReaderFeatures();
                f.generate(bidder);
            }
        }
        return bidders;
    }
    public void setChildren(Node[] children) {
        this.children = children;
    }
    public ArrayList<Bidder_Record> getBidder_record() {
        return bidder_record;
    }
    public void setBidder_record(ArrayList<Bidder_Record> bidder_record) {
        this.bidder_record = bidder_record;
    }
    public double getEntropy() {
        return entropy;
    }
    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }
    public boolean isAlreadyUsed() {
        return alreadyUsed;
    }
    public void setAlreadyUsed(boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }
    public Attribute getAttribute() {
        return attribute;
    }
    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
    
    
}
