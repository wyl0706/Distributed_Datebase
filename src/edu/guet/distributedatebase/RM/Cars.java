package edu.guet.distributedatebase.RM;

/**
 * Created by Caesar on 2015/5/30.
 */
public class Cars {

    private String location;
    private int price;
    private int numCars;
    private int numAvail;


    public boolean flag;

    public Cars() {

    }

    public Cars(String location, int price, int numCars, int numAvail) {
        this.location = location;
        this.price = price;
        this.numCars = numCars;
        this.numAvail = numAvail;

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumCars() {
        return numCars;
    }

    public void setNumCars(int numCars) {
        this.numCars = numCars;
    }

    public int getNumAvail() {
        return numAvail;
    }

    public void setNumAvail(int numAvail) {
        this.numAvail = numAvail;
    }
}
