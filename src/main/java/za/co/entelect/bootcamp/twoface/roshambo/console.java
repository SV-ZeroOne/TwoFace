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


   public static void main(String[] s){
        String xmlFileName = "config.xml";
        readXml(xmlFileName);
   }




public static void readXml(String xmlLocation)
{
    LinkedList<Item> items = new LinkedList<Item>();

    try
    {
        File xmlFile = new File(xmlLocation);
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        NodeList itemList = doc.getElementsByTagName("name");

        //NodeList itemList = doc.getElementsByTagName("name");

        for (int nameLoop = 0; nameLoop < itemList.getLength(); nameLoop++)
        {
            /**
             * Populate each item name here
             */
            Item temp = new Item(itemList.item(nameLoop).getNodeValue());
            items.add(temp);
            System.out.println(itemList.item(nameLoop).getTextContent());

         }
/**
        NodeList itemList2 = doc.getElementsByTagName("name");
        for (int nameLoop = 0; nameLoop < itemList2.getLength(); nameLoop++)
        {
            /**
             * Populate how each item beats others here
*/
           // Item item;
            //Item temp = new Item(itemList.item(nameLoop).getTextContent());
            NodeList childList = doc.getElementsByTagName("beats");
            for (int j = 0; j < childList.getLength(); j++)
            {

                for (item i: itemList2) {
                   // i.get(0).addBeats("wkajdshakjhd");
                    ///String usr = document.getElementsByTagName("user").item(0).getTextContent();
                };
            }

            items.add(new Item(itemList2.item(nameLoop).toString()));
        }
*/

    }catch(Exception e)
    {
        System.out.println("Errors reading");
    }
}}
