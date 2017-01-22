package za.co.entelect.bootcamp.twoface.squareeyes.services.csvreader;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.suppliers.SupplierPaymentsRepositoryIMP;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by mpho.mahase on 2017/01/20.
 */
public class ReadFile {

    String filePath;
    String[] textLine;
    String[] tempStringArray;

    public ReadFile(String file) throws FileNotFoundException{
        filePath = file;
    }

    public String[] fileOpener() throws IOException {


        FileReader fileReader = new FileReader(filePath);
        BufferedReader textReader = new BufferedReader(fileReader);
        System.out.println(textReader.toString());
        String csvSplit = ";";

        tempStringArray = textReader.readLine().split(csvSplit);

        textLine = tempStringArray;

        return textLine;

    }

    public void outputFileContents(String[] stringArray){
        int counter = 0;
        for (String iterator : stringArray){
            System.out.println(++counter + " " + iterator);
        }
        System.out.println();
    }

    public Order assignReleventDataToIssueObject() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

        int issueID = Integer.parseInt(textLine[1]);

        short quantity = Short.parseShort(textLine[2]);

        BigDecimal total = new BigDecimal(textLine[3]);

        SimpleDateFormat dater = new SimpleDateFormat("yyyy-MM-DD");

        int supplierID = Integer.parseInt(textLine[7]);

        Order order = new Order(formatter.parse(textLine[0]), quantity, total, textLine[4], dater.parse(textLine[5]), textLine[6]);

        System.out.print(order.toString());
        System.out.println(issueID + " " + supplierID);

        return order;
    }
}
