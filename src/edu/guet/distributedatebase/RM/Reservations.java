package edu.guet.distributedatebase.RM;

/**
 * Created by Caesar on 2015/5/30.
 */
public class Reservations {


    int bookid;
    String custName;
    int resvType;
    String resvKey;

    public Reservations() {

    }

    public Reservations(String custName, int resvType, String resvKey, int bookid) {
        this.bookid = bookid;
        this.custName = custName;
        this.resvType = resvType;
        this.resvKey = resvKey;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getResvType() {
        return resvType;
    }

    public void setResvType(int resvType) {
        this.resvType = resvType;
    }

    public String getResvKey() {
        return resvKey;
    }

    public void setResvKey(String resvKey) {
        this.resvKey = resvKey;
    }
}
