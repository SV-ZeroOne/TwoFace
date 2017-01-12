package za.co.entelect.bootcamp.twoface.roshambo;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;

/**
 * Created by sean.vienings on 2017/01/12.
 */
public class console {

    String xmlFileName = "";




}

public void readXml(String xmlLocation)
{
    LinkedList<Item> items = new LinkedList<Item>();
    try
    {
        File xmlFile = new File(xmlLocation);
        DocumentBuilderFactory dFactory = new DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList itemList = doc.getElementsByTagName("name");

        for (int nameLoop = 0; nameLoop < itemList.getLength(); nameLoop++)
        {


            /**
             * Populate each item name here
             */
            items.add(new Item(itemList.item(nameLoop).toString()));


         }
        for (int itemLoop = 0; itemLoop < itemList.getLength(); itemLoop++)
        {
            /**
             * Populate how each item beats others here
             */
            /**
             * Search for each element over here and then add each element
             */
            items.add(new Item(itemList.item(itemLoop).toString()));
        }


    }
}