package edu.guet.distributedatebase.RM;

/**
 * Created by Caesar on 2015/6/4.
 * 改类是用来记录事务对数据库的操作情况
 */
public class Transaction {

    private int xid;
    private boolean isChangeCars;
    private boolean isChangeCustomers;
    private boolean isChangeFlights;
    private boolean isChangeHotels;
    private boolean isChangeReservations;

    public Transaction(int xid) {
        this.xid = xid;
        isChangeCars = false;
        isChangeCustomers = false;
        isChangeFlights = false;
        isChangeHotels = false;
        isChangeReservations = false;
    }

    public int getXid() {
        return xid;
    }

    public void setXid(int xid) {
        this.xid = xid;
    }

    public boolean isChangeCars() {
        return isChangeCars;
    }

    public void setIsChangeCars(boolean isChangeCars) {
        this.isChangeCars = isChangeCars;
    }

    public boolean isChangeCustomers() {
        return isChangeCustomers;
    }

    public void setIsChangeCustomers(boolean isChangeCustomers) {
        this.isChangeCustomers = isChangeCustomers;
    }

    public boolean isChangeFlights() {
        return isChangeFlights;
    }

    public void setIsChangeFlights(boolean isChangeFlights) {
        this.isChangeFlights = isChangeFlights;
    }

    public boolean isChangeHotels() {
        return isChangeHotels;
    }

    public void setIsChangeHotels(boolean isChangeHotels) {
        this.isChangeHotels = isChangeHotels;
    }

    public boolean isChangeReservations() {
        return isChangeReservations;
    }

    public void setIsChangeReservations(boolean isChangeReservations) {
        this.isChangeReservations = isChangeReservations;
    }
}
