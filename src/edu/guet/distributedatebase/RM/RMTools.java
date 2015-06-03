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
