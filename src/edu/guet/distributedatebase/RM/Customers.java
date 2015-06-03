package edu.guet.distributedatebase.RM;

/**
 * Created by Caesar on 2015/5/30.
 */
public class Customers {

    String custName;

    public Customers() {
    }

    public Customers(String custName) {
        this.custName = custName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}
