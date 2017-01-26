package za.co.entelect.bootcamp.twoface.squareeyes.services.csvreader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mpho.mahase on 2017/01/20.
 */
public class ReadFile {

    private final static Logger logger = LoggerFactory.getLogger(ReadFile.class);
    private String filePath;
    private String[] textLine;
    String line = "";
    private int lineCount;
    int total;

    public ReadFile(String file) throws FileNotFoundException{
        logger.info("Beginning the logging for ReadFile");
        filePath = file;
        logger.debug("Assigning the file path to the constructor with value: {}", filePath.getClass().getSimpleName());
    }

    /*public int returnNumberOfLinesInCsvFile() throws IOException {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader textReader = new BufferedReader(fileReader);
        while (textReader.readLine() != null){
            lineCount++;
        }

        return lineCount;
    }*/

    public int count(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }

    public List<Order> fileOpener() throws IOException, ParseException {
        List<Order> orderList = new ArrayList<Order>();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader textReader = new BufferedReader(fileReader);

        logger.info("FileReader holds the following: " + fileReader.getClass().getSimpleName());
        logger.info("BufferedReader holds the following: " + textReader.getClass().getSimpleName());
        String csvSplitBySemiColon = ";";
        String csvSplitByComman = ",";
        int count = 0;
        total = count(filePath);
        //textLine = textReader.readLine().split(csvSplitBySemiColon);
        while (((line = textReader.readLine()) != null) && (count < (total - 1))){
        //while (textLine != null){
            count++;
           String[] temptextLine = line.split(csvSplitBySemiColon);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

            int issueID = Integer.parseInt(temptextLine[1]);
            logger.info("issueID holds the value: " + issueID);

            short quantity = Short.parseShort(temptextLine[2]);
            logger.info("quantity holds the value: " + quantity);

            BigDecimal total = new BigDecimal(temptextLine[3]);
            logger.info("total holds the value: " + total);

            SimpleDateFormat shipmentDate = new SimpleDateFormat("yyyy-MM-DD");

            int supplierID = Integer.parseInt(temptextLine[7]);
            logger.info("supplierID holds the value: " + supplierID);

            logger.info("orderDate holds the value: " + formatter.parse(temptextLine[0]));
            logger.info("shipmentDate holds the value: " + shipmentDate.parse(temptextLine[5]));
            /*System.out.print(order.toString());
            System.out.println(issueID + " " + supplierID);*/

            Order orderCreatedFromCsv = new Order(formatter.parse(temptextLine[0]), quantity, total, temptextLine[4], shipmentDate.parse(temptextLine[5]), temptextLine[6]);
            orderList.add(orderCreatedFromCsv);
        }

        return orderList;
    }

   /* public void outputFileContents(List<Order> stringArray) throws IOException {
        for (String iterator : stringArray){
            System.out.println("column: " + iterator);
        }

    }*/





    /*public Order assignRelevantDataToIssueObject() throws ParseException {
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
    }*/
}
