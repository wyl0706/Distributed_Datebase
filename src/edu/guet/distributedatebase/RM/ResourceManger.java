package edu.guet.distributedatebase.RM;

import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

/**
 * Created by Caesar on 2015/5/30.
 */
public class ResourceManger {
    //
    HashMap<String, Cars> carsTable = new HashMap<String, Cars>(50, 0.75f);
    HashMap<String, Customers> customersTable = new HashMap<String, Customers>(50, 0.75f);
    HashMap<String, Flights> flightsTable = new HashMap<String, Flights>(50, 0.75f);
    HashMap<String, Hotels> hotelsTable = new HashMap<String, Hotels>(50, 0.75f);
    HashMap<Integer, Reservations> reservationsTable = new HashMap<Integer, Reservations>(50, 0.75f);
    HashSet<Integer> xids = new HashSet<Integer>();
    int xid = 0;

    //creatReservationsTable() is creat a reseations table, the returned value is HashMap<Integer, Reservations>
    public HashMap<Integer, Reservations> creatReservationsTable() {
        try {
            SAXReader reader = new SAXReader();
            Document doc_car = null;
            doc_car = reader.read(new File("database/Reservations.xml"));
            Element root = doc_car.getRootElement();
            List nodes = root.elements("elements");
            for (Iterator it = nodes.iterator(); it.hasNext(); ) {
                Element elm = (Element) it.next();
                Reservations rese = new Reservations();
                rese.setCustName(elm.element("custName").getText());
                rese.setResvKey(elm.element("resvKey").getText());
                rese.setResvType(Integer.parseInt(elm.element("resvType").getText()));
                rese.setBookid(Integer.parseInt(elm.element("bookid").getText()));
                reservationsTable.put(Integer.parseInt(elm.element("bookid").getText()), rese);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return reservationsTable;
    }

    //creatHotelsTable() is creat a hotels table, the returned value is HashMap<String, Hotels>
    public HashMap<String, Hotels> creatHotelsTable() {
        try {
            SAXReader reader = new SAXReader();
            Document doc_car = null;
            doc_car = reader.read(new File("database/Hotels.xml"));
            Element root = doc_car.getRootElement();
            List nodes = root.elements("elements");
            for (Iterator it = nodes.iterator(); it.hasNext(); ) {
                Element elm = (Element) it.next();
                Hotels hotel = new Hotels();
                hotel.setLocation(elm.element("location").getText());
                hotel.setPrice(Integer.parseInt(elm.element("price").getText()));
                hotel.setNumRooms(Integer.parseInt(elm.element("numRooms").getText()));
                hotel.setNumAvail(Integer.parseInt(elm.element("numAvail").getText()));
                hotelsTable.put(elm.element("location").getText(), hotel);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return hotelsTable;
    }

    //creatHotelsTable() is creat a flights table,  the returned value is HashMap<String, Flights>
    public HashMap<String, Flights> creatFlightsTable() {
        try {
            SAXReader reader = new SAXReader();
            Document doc_car = null;
            doc_car = reader.read(new File("database/Flights.xml"));
            Element root = doc_car.getRootElement();
            List nodes = root.elements("elements");
            for (Iterator it = nodes.iterator(); it.hasNext(); ) {
                Element elm = (Element) it.next();
                Flights flights = new Flights();
                flights.setFlightNum(elm.element("flightNum").getText());
                flights.setPrice(Integer.parseInt(elm.element("price").getText()));
                flights.setNumSeats(Integer.parseInt(elm.element("numSeats").getText()));
                flights.setNumAvail(Integer.parseInt(elm.element("numAvail").getText()));
                flightsTable.put(elm.element("flightNum").getText(), flights);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return flightsTable;
    }

    //creatCustomersTable() is creat a customers table,  the returned value is HashMap<String, Customers>
    public HashMap<String, Customers> creatCustomersTable() {
        try {
            SAXReader reader = new SAXReader();
            Document doc_cust = null;
            doc_cust = reader.read(new File("database/Customers.xml"));
            Element root = doc_cust.getRootElement();
            List nodes = root.elements("elements");
            for (Iterator it = nodes.iterator(); it.hasNext(); ) {
                Element elm = (Element) it.next();
                Customers cust = new Customers();
                cust.setCustName(elm.element("custName").getText());
                customersTable.put(elm.element("custName").getText(), cust);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return customersTable;
    }

    //creatCarsTable() is creat a cars table, the returned value is HashMap<String, Cars>
    public HashMap<String, Cars> creatCarsTable() {
        try {
            SAXReader reader = new SAXReader();
            Document doc_car = null;
            doc_car = reader.read(new File("database/Cars.xml"));
            Element root = doc_car.getRootElement();
            List nodes = root.elements("elements");
            for (Iterator it = nodes.iterator(); it.hasNext(); ) {
                Element elm = (Element) it.next();
                Cars car = new Cars();
                car.setLocation(elm.element("location").getText());
                car.setPrice(Integer.parseInt(elm.element("price").getText()));
                car.setNumCars(Integer.parseInt(elm.element("numCars").getText()));
                car.setNumAvail(Integer.parseInt(elm.element("numAvail").getText()));
                carsTable.put(elm.element("location").getText(), car);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return carsTable;
    }

    public void addCar(Transaction tran, Cars car) {
        carsTable.put(car.getLocation(), car);
        String context = "add Cars " + car.getLocation() + " " + car.getPrice() + " "
                + car.getNumCars() + " " + car.getNumAvail() + '\n';
        tran.setIsChangeCars(true);
        writelog(tran.getXid(), context);
    }

    public void addCustomers(Transaction tran, Customers cust) {
        customersTable.put(cust.getCustName(), cust);
        String context = "add Customers " + cust.getCustName() + '\n';
        tran.setIsChangeCustomers(true);
        writelog(tran.getXid(), context);
    }

    public void addFilghts(Transaction tran, Flights flight) {
        flightsTable.put(flight.getFlightNum(), flight);
        String context = "add Flights " + flight.getFlightNum() + " " + flight.getPrice() + " "
                + flight.getNumSeats() + " " + flight.getNumAvail() + '\n';
        tran.setIsChangeFilghts(true);
        writelog(tran.getXid(), context);
    }

    public void addHotels(Transaction tran, Hotels hotel) {
        hotelsTable.put(hotel.getLocation(), hotel);
        String context = "add Hotels " + hotel.getLocation() + " " + hotel.getPrice() + " "
                + hotel.getNumRooms() + " " + hotel.getNumAvail() + "\n";
        tran.setIsChangeHotels(true);
        writelog(tran.getXid(), context);
    }

    public void addReservations(Transaction tran, Reservations reservation) {
        reservationsTable.put(reservation.getBookid(), reservation);
        String context = "add Reservations " + reservation.getBookid() + " " + reservation.getCustName() + " "
                + reservation.getResvType() + " " + reservation.getResvKey() + '\n';
        tran.setIsChangeReservations(true);
        writelog(tran.getXid(), context);
    }

    public void deleteCar(int xid, String carloc) {
        Cars car = carsTable.remove(carloc);
        String context = "delete Cars " + car.getLocation() + " " + car.getPrice() + " "
                + car.getNumCars() + " " + car.getNumAvail() + '\n';
        writelog(xid, context);
    }

    public void deleteCustomer(int xid, String custName) {
        Customers cust = customersTable.remove(custName);
        String context = "delete Customers " + cust.getCustName() + '\n';
        writelog(xid, context);
    }

    public void deleteFilght(int xid, String flightNum) {
        Flights filght = flightsTable.remove(flightNum);
        String context = "delete Flights " + filght.getFlightNum() + " " + filght.getPrice() + " "
                + filght.getNumSeats() + " " + filght.getNumAvail() + '\n';
        writelog(xid, context);
    }

    public void deleteHotel(int xid, String location) {
        Hotels hotel = hotelsTable.remove(location);
        String context = "delete Hotels " + hotel.getLocation() + " " + hotel.getPrice() + " "
                + hotel.getNumRooms() + " " + hotel.getNumAvail() + '\n';
        writelog(xid, context);
    }

    public void deleteReservation(int xid, String bookid) {
        Reservations rese = reservationsTable.remove(Integer.getInteger(bookid));
        String context = "delete Reservations " + rese.getBookid() + " " + rese.getCustName() + " "
                + rese.getResvType() + " " + rese.getResvKey() + '\n';
        writelog(xid, context);
    }

    public void writelog(int xid, String context) {
        FileWriter writer = null;
        try {
            writer = new FileWriter("logs/" + xid + ".txt", true);
            writer.write(context);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void abort(int xid) {
        xids.remove(xid);

    }

    public void commit(int xid) {
        xids.remove(xid);
        String context = "commit\n";
        writelog(xid, context);
    }

    public Transaction start() {
        Transaction tran = new Transaction(++xid);
        File f = new File("logs/" + tran.getXid() + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        xids.add(tran.getXid());
        return tran;
    }

    public static void main(String[] args) {

        ResourceManger rm = new ResourceManger();
        RMTools rmt = new RMTools();
        //Cars car = new Cars("Guilin", 8, 500, 200);
        rm.creatCarsTable();

        rmt.writeCars(rm.carsTable);
    }
}
