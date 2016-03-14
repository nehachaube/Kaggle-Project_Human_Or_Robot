/**
 * 
 * @author iamneha
 * All bidder records stored in this class
 */
public class Bidder_Record {

    String bidder_id;
    int address_frequent_address; // 0 or 1
    int payment_account_infrequent_account;//0 or 1
    double ips_per_bidder_per_auction_median;//continuous valued
    int on_ip_that_has_a_bot; // 0 or 1
    String sleep; //True or False
    double bids_per_auction_median; //continuous valued
    double n_bids; //continuous valued
    double n_bids_url;//continuous valued
    boolean outcome; //YES or NO (human or bot)
    double bids_per_auction_mean;
    double ips_per_bidder_per_auction_mean;
    double max_bids_in_hour72;
   
    Bidder_Record(String id,int address,int payment,double ips_per_bidder_median,int on_ip_that_has_bot,String sleep,double bids_median,double n_bids,double n_url,boolean outcome,double bids_per_auction_mean,double ips_per_bidder_per_auction_mean,double max_bids_in_hour72)
    {
        super();
        this.bidder_id=id;
        this.address_frequent_address=address;
        this.payment_account_infrequent_account=payment;
        this.ips_per_bidder_per_auction_median=ips_per_bidder_median;
        this.on_ip_that_has_a_bot=on_ip_that_has_bot;
        this.sleep=sleep;
        this.bids_per_auction_median=bids_median;
        this.n_bids=n_bids;
        this.n_bids_url=n_url;
        this.outcome=outcome;
        this.bids_per_auction_mean=bids_per_auction_mean;
        this.ips_per_bidder_per_auction_mean=ips_per_bidder_per_auction_mean;
        this.max_bids_in_hour72=max_bids_in_hour72;
    }
    
    //getters and setters
    public String getBidder_id() {
        return bidder_id;
    }

    public void setBidder_id(String bidder_id) {
        this.bidder_id = bidder_id;
    }

    public int isAddress_frequent_address() {
        return address_frequent_address;
    }

    public void setAddress_frequent_address(int address_frequent_address) {
        this.address_frequent_address = address_frequent_address;
    }

    public int isPayment_account_infrequent_account() {
        return payment_account_infrequent_account;
    }

    public void setPayment_account_infrequent_account(
            int payment_account_infrequent_account) {
        this.payment_account_infrequent_account = payment_account_infrequent_account;
    }

    public double getIps_per_bidder_per_auction_median() {
        return ips_per_bidder_per_auction_median;
    }

    public void setIps_per_bidder_per_auction_median(
            double ips_per_bidder_per_auction_median) {
        this.ips_per_bidder_per_auction_median = ips_per_bidder_per_auction_median;
    }

    public int isOn_ip_that_has_a_bot() {
        return on_ip_that_has_a_bot;
    }

    public void setOn_ip_that_has_a_bot(int on_ip_that_has_a_bot) {
        this.on_ip_that_has_a_bot = on_ip_that_has_a_bot;
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep;
    }

    public double getBids_per_auction_median() {
        return bids_per_auction_median;
    }

    public void setBids_per_auction_median(double bids_per_auction_median) {
        this.bids_per_auction_median = bids_per_auction_median;
    }

    public double getN_bids() {
        return n_bids;
    }

    public void setN_bids(double n_bids) {
        this.n_bids = n_bids;
    }

    public double getN_bids_url() {
        return n_bids_url;
    }

    public void setN_bids_url(double n_bids_url) {
        this.n_bids_url = n_bids_url;
    }

    public boolean getOutcome() {
        return outcome;
    }

    public void setOutcome(boolean outcome) {
        this.outcome = outcome;
    }

    public double getBids_per_auction_mean() {
        return bids_per_auction_mean;
    }

    public void setBids_per_auction_mean(double bids_per_auction_mean) {
        this.bids_per_auction_mean = bids_per_auction_mean;
    }

    public double getIps_per_bidder_per_auction_mean() {
        return ips_per_bidder_per_auction_mean;
    }

    public void setIps_per_bidder_per_auction_mean(
            double ips_per_bidder_per_auction_mean) {
        this.ips_per_bidder_per_auction_mean = ips_per_bidder_per_auction_mean;
    }

    public double getMax_bids_in_hour72() {
        return max_bids_in_hour72;
    }

    public void setMax_bids_in_hour72(double max_bids_in_hour72) {
        this.max_bids_in_hour72 = max_bids_in_hour72;
    }

    @Override
    public String toString() {
        return "Bidder_Record [bidder_id=" + bidder_id
                + ", address_frequent_address=" + address_frequent_address
                + ", payment_account_infrequent_account="
                + payment_account_infrequent_account
                + ", ips_per_bidder_per_auction_median="
                + ips_per_bidder_per_auction_median + ", on_ip_that_has_a_bot="
                + on_ip_that_has_a_bot + ", sleep=" + sleep
                + ", bids_per_auction_median=" + bids_per_auction_median
                + ", n_bids=" + n_bids + ", n_bids_url=" + n_bids_url
                + ", outcome=" + outcome + "]";
    }
}
