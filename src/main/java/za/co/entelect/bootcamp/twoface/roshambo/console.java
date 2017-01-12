package za.co.entelect.bootcamp.twoface.roshambo;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by sean.vienings on 2017/01/12.
 */
public class console {

    String xmlFileName = "";




}

public void readXml(String xmlLocation)
{
    try
    {
        File xmlFile = new File(xmlLocation);
        DocumentBuilderFactory dFactory = new DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList itemList = doc.getElementsByTagName("name");

        for (int itemLoop = 0; itemLoop < itemList.getLength(); itemLoop++)
        {
            /**
             * Populate each item here
             */
        }

    }
}