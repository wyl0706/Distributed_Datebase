package edu.guet.distributedatebase.RM;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import edu.guet.distributedatebase.RM.Flights;
import com.sun.java.util.jar.pack.*;
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

    //增加操作
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

    public void addFlights(Transaction tran, Flights flight) {
        flightsTable.put(flight.getFlightNum(), flight);
        String context = "add Flights " + flight.getFlightNum() + " " + flight.getPrice() + " "
                + flight.getNumSeats() + " " + flight.getNumAvail() + '\n';
        tran.setIsChangeFlights(true);
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

    //删除操作
    public void deleteCars(Transaction tran, String carloc) {
        Cars car = carsTable.remove(carloc);
        String context = "delete Cars " + car.getLocation() + " " + car.getPrice() + " "
                + car.getNumCars() + " " + car.getNumAvail() + '\n';
        tran.setIsChangeCars(true);
        writelog(tran.getXid(), context);
    }

    public void deleteCustomers(Transaction tran, String custName) {
        Customers cust = customersTable.remove(custName);
        String context = "delete Customers " + cust.getCustName() + '\n';
        tran.setIsChangeCustomers(true);
        writelog(tran.getXid(), context);
    }

    public void deleteFlights(Transaction tran, String flightNum) {
        Flights flight = flightsTable.remove(flightNum);
        String context = "delete Flights " + flight.getFlightNum() + " " + flight.getPrice() + " "
                + flight.getNumSeats() + " " + flight.getNumAvail() + '\n';
        tran.setIsChangeFlights(true);
        writelog(tran.getXid(), context);
    }

    public void deleteHotels(Transaction tran, String location) {
        Hotels hotel = hotelsTable.remove(location);
        String context = "delete Hotels " + hotel.getLocation() + " " + hotel.getPrice() + " "
                + hotel.getNumRooms() + " " + hotel.getNumAvail() + '\n';
        tran.setIsChangeHotels(true);
        writelog(tran.getXid(), context);
    }

    public void deleteReservations(Transaction tran, String bookid) {
        Reservations rese = reservationsTable.remove(Integer.getInteger(bookid));
        String context = "delete Reservations " + rese.getBookid() + " " + rese.getCustName() + " "
                + rese.getResvType() + " " + rese.getResvKey() + '\n';
        tran.setIsChangeReservations(true);
        writelog(tran.getXid(), context);
    }

    //更新操作
    public void updataCars(Transaction tran, Cars car) {
        Cars old = carsTable.get(car.getLocation());
        if (old == null) {
            this.addCar(tran, car);
        } else {
            carsTable.put(car.getLocation(), car);
            String contxt = "updata Cars old " + old.getLocation() + " " + old.getPrice() + " " + old.getNumCars() + " " + old.getNumAvail()
                    + " new " + car.getLocation() + " " + car.getPrice() + " " + car.getNumCars() + " " + car.getNumAvail();
            tran.setIsChangeCars(true);
            writelog(tran.getXid(), contxt);
        }
    }

    public void updataCustomers(Transaction tran, Customers cust) {
        Customers old = customersTable.get(cust.getCustName());
        if (old == null) {
            this.addCustomers(tran, cust);
        } else {
            customersTable.put(cust.getCustName(), cust);
            String contxt = "updata Customers old " + old.getCustName() + " new " + cust.getCustName();
            tran.setIsChangeCustomers(true);
            writelog(tran.getXid(), contxt);
        }
    }

    public void updataFlights(Transaction tran, Flights flight) {
        Flights old = flightsTable.get(flight.getFlightNum());
        if (old == null) {
            this.addFlights(tran, flight);
        } else {
            flightsTable.put(flight.getFlightNum(), flight);
            String contxt = "updata Cars old " + old.getFlightNum() + " " + old.getPrice() + " " + old.getNumSeats() + " " + old.getNumAvail()
                    + " new " + flight.getFlightNum() + " " + flight.getPrice() + " " + flight.getNumSeats() + " " + flight.getNumAvail();
            tran.setIsChangeFlights(true);
            writelog(tran.getXid(), contxt);
        }
    }

    public void updataHotels(Transaction tran, Hotels hotel) {
        Hotels old = hotelsTable.get(hotel.getLocation());
        if (old == null) {
            this.addHotels(tran, hotel);
        } else {
            hotelsTable.put(hotel.getLocation(), hotel);
            String contxt = "updata Flights old " + old.getLocation() + " " + old.getPrice() + " " + old.getNumRooms() + " " + old.getNumAvail()
                    + " new " + hotel.getLocation() + " " + hotel.getPrice() + " " + hotel.getNumRooms() + " " + hotel.getNumAvail();
            tran.setIsChangeHotels(true);
            writelog(tran.getXid(), contxt);
        }
    }

    public void updataReservations(Transaction tran, Reservations rese) {
        Reservations old = reservationsTable.get(rese.getBookid());
        if (old == null) {
            this.addReservations(tran, rese);
        } else {
            reservationsTable.put(rese.getBookid(), rese);
            String contxt = "updata Reservations old " + rese.getBookid() + " " + old.getCustName() + " " + old.getResvType() + " " + old.getResvKey()
                    + " new " + rese.getBookid() + " " + rese.getCustName() + " " + rese.getResvType() + " " + rese.getResvKey();
            tran.setIsChangeReservations(true);
            writelog(tran.getXid(), contxt);
        }
    }

    //写日志
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

    /*public void abort(Transaction tran) {
        xids.remove(xid);
        Stack<String> logs = new Stack<String>();
        File file = new File("logs/" + xid + ".txt");
        BufferedReader reader = null;
        String tempString = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                logs.push(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!logs.empty()) {
            tempString = logs.pop();
            if (tempString.equals("commit")) {
                return;
            }
            String[] strs = tempString.split(" ");
            //此处用到反射Reflect来实现调用操作不同的表的函数
            switch (strs[0]) {
                //add操作对应的delete操作不需要对应的对象，只需要字符串
                case "add":
                    try {
                        Method method = this.getClass().getMethod("delete" + strs[1], Transaction.class, String.class);
                        method.invoke(this, tran, strs[2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "delete":
                    try {
                        Method method = this.getClass().getMethod("add" + strs[1], Transaction.class, Class.forName(strs[1]));
                        Class<?> obj = Class.forName(strs[1]);
                        Constructor<?> cons[] = obj.getConstructors();
                        switch (strs[1]) {
                            case "Cars":
                                cons[1].newInstance(strs[2], Integer.parseInt(strs[3]),
                                        Integer.parseInt(strs[4]), Integer.parseInt(strs[5]));
                                method.invoke(this, tran, obj);
                                break;
                            case "Customers":
                                cons[1].newInstance(strs[2]);

                                break;
                            case "Flights":
                                cons[1].newInstance(strs[2], Integer.parseInt(strs[3]),
                                        Integer.parseInt(strs[4]), Integer.parseInt(strs[5]));
                                method.invoke(this, tran, obj);
                                break;
                            case "Hotels":
                                cons[1].newInstance(strs[2], Integer.parseInt(strs[3]),
                                        Integer.parseInt(strs[4]), Integer.parseInt(strs[5]));
                                method.invoke(this, tran, obj);
                                break;
                            case "Reservations":
                                cons[1].newInstance(Integer.parseInt(strs[2]), strs[3],
                                        Integer.parseInt(strs[4]), strs[5]);
                                method.invoke(this, tran, obj);
                                break;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "updata":
                    try {
                        Method method = this.getClass().getMethod("updata" + strs[1], Transaction.class, Cars.class);
                        Cars car = new Cars(strs[2], Integer.parseInt(strs[3]),
                                Integer.parseInt(strs[4]), Integer.parseInt(strs[5]));
                        method.invoke(this, tran, car);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }


        }

    }*/


    public void commit(Transaction tran) {
        xids.remove(tran.getXid());
        String context = "commit\n";
        writelog(xid, context);
    }

    public Transaction start() {
        Transaction tran = new Transaction(++xid);
//        File f = new File("logs/" + tran.getXid() + ".txt");
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(f);
//            fw.write("");
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        xids.add(tran.getXid());
        return tran;
    }

    public static void main(String[] args) {

        ResourceManger rm = new ResourceManger();
        RMTools rmt = new RMTools();
        Flights car = new Flights("B2", 10, 800, 200);
        rm.creatFlightsTable();
        Transaction tran = rm.start();
//        rm.abort(tran);
//        rm.deleteFlights(tran, car.getFlightNum());
        rmt.writeFlights(rm.flightsTable);
    }
}
