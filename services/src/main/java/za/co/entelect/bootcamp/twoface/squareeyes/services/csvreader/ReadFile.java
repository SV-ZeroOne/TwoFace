package za.co.entelect.bootcamp.twoface.squareeyes.services.csvreader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by mpho.mahase on 2017/01/20.
 */
public class ReadFile {

    private final static Logger logger = LoggerFactory.getLogger(ReadFile.class);
    private String filePath;
    private String[] textLine;

    public ReadFile(String file) throws FileNotFoundException{
        logger.info("Beginning the logging for ReadFile");
        filePath = file;
        logger.debug("Assigning the file path to the constructor with value: {}", filePath.getClass().getSimpleName());
    }

    public String[] fileOpener() throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader textReader = new BufferedReader(fileReader);

        logger.info("FileReader holds the following: " + fileReader.getClass().getSimpleName());
        logger.info("BufferedReader holds the following: " + textReader.getClass().getSimpleName());
        String csvSplit = ";";

        textLine = textReader.readLine().split(csvSplit);

        return textLine;

    }

    public void outputFileContents(String[] stringArray){
        int counter = 0;
        for (String iterator : stringArray){
            System.out.println(++counter + " " + iterator);
        }
        System.out.println();
    }



    public Order assignRelevantDataToIssueObject() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        int issueID = Integer.parseInt(textLine[1]);
        logger.info("issueID holds the value: " + issueID);

        short quantity = Short.parseShort(textLine[2]);
        logger.info("quantity holds the value: " + quantity);

        BigDecimal total = new BigDecimal(textLine[3]);
        logger.info("total holds the value: " + total);

        SimpleDateFormat shipmentDate = new SimpleDateFormat("yyyy-MM-DD");

        int supplierID = Integer.parseInt(textLine[7]);
        logger.info("supplierID holds the value: " + supplierID);

        Order order = new Order(formatter.parse(textLine[0]), quantity, total, textLine[4], shipmentDate.parse(textLine[5]), textLine[6]);

        logger.info("orderDate holds the value: " + formatter.parse(textLine[0]));
        logger.info("shipmentDate holds the value: " + shipmentDate.parse(textLine[5]));
        System.out.print(order.toString());
        System.out.println(issueID + " " + supplierID);

        return order;
    }
}
