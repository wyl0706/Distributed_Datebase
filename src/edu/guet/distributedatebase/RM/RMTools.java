package edu.guet.distributedatebase.RM;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.soap.Node;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Caesar on 2015/6/3.
 */
public class RMTools {
    //add a new node for Cars table, like INSERT in SQL
    public void writeCars(HashMap<String, Cars> carsTable) {
        try {
            Element root = DocumentHelper.createElement("table");
            Document document = DocumentHelper.createDocument(root);
            Iterator it = carsTable.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                Cars car = carsTable.get(key);
                Element newEle = root.addElement("elements");
                Element newLoc = newEle.addElement("location");
                Element newPri = newEle.addElement("price");
                Element newNCar = newEle.addElement("numCars");
                Element newNAva = newEle.addElement("numAvail");
                newLoc.setText(car.getLocation());
                newPri.setText(Integer.toString(car.getPrice()));
                newNCar.setText(Integer.toString(car.getNumCars()));
                newNAva.setText(Integer.toString(car.getNumAvail()));
            }
            writer(document, "database/Cars.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeCustomers(HashMap<String, Customers> customersTable) {
        try {
            Element root = DocumentHelper.createElement("table");
            Document document = DocumentHelper.createDocument(root);
            Iterator it = customersTable.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                Customers cust = customersTable.get(key);
                Element newEle = root.addElement("elements");
                Element newLoc = newEle.addElement("custName");
                newLoc.setText(cust.getCustName());
            }
            writer(document, "database/Customers.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFlights(HashMap<String, Flights> flightsTable) {
        try {
            Element root = DocumentHelper.createElement("table");
            Document document = DocumentHelper.createDocument(root);
            Iterator it = flightsTable.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                Flights flight = flightsTable.get(key);
                Element newEle = root.addElement("elements");
                Element newFNum = newEle.addElement("flightNum");
                Element newPri = newEle.addElement("price");
                Element newNSeat = newEle.addElement("numSeats");
                Element newNAva = newEle.addElement("numAvail");
                newFNum.setText(flight.getFlightNum());
                newPri.setText(Integer.toString(flight.getPrice()));
                newNSeat.setText(Integer.toString(flight.getNumSeats()));
                newNAva.setText(Integer.toString(flight.getNumAvail()));
            }
            writer(document, "database/Flights.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeHotels(HashMap<String, Hotels> hotelsTable) {
        try {
            Element root = DocumentHelper.createElement("table");
            Document document = DocumentHelper.createDocument(root);
            Iterator it = hotelsTable.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                Hotels hotel = hotelsTable.get(key);
                Element newEle = root.addElement("elements");
                Element newLoc = newEle.addElement("location");
                Element newPri = newEle.addElement("price");
                Element newNroom = newEle.addElement("numRooms");
                Element newNAva = newEle.addElement("numAvail");
                newLoc.setText(hotel.getLocation());
                newPri.setText(Integer.toString(hotel.getPrice()));
                newNroom.setText(Integer.toString(hotel.getNumRooms()));
                newNAva.setText(Integer.toString(hotel.getNumAvail()));
            }
            writer(document, "database/Hotels.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeReservations(HashMap<Integer, Reservations> reservationsTable) {
        try {
            Element root = DocumentHelper.createElement("table");
            Document document = DocumentHelper.createDocument(root);
            Iterator it = reservationsTable.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                Reservations rese = reservationsTable.get(key);
                Element newEle = root.addElement("elements");
                Element newid = newEle.addElement("bookid");
                Element newCName = newEle.addElement("custName");
                Element newRType = newEle.addElement("resvType");
                Element newRKey = newEle.addElement("resvKey");
                newid.setText(Integer.toString(rese.getBookid()));
                newCName.setText(rese.getCustName());
                newRType.setText(Integer.toString(rese.getResvType()));
                newRKey.setText(rese.getResvKey());
            }
            writer(document, "database/Reservations.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writer(Document document, String str) throws Exception {
        // OutputFormat format = OutputFormat.createCompactFormat();
        OutputFormat format = OutputFormat.createPrettyPrint();
        // set coding
        format.setEncoding("UTF-8");
        // create XMLWriter object,set the URL and coding
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                new FileOutputStream(new File(str)), "UTF-8"), format);
        // write and flush
        writer.write(document);
        writer.flush();
        writer.close();
    }
}
