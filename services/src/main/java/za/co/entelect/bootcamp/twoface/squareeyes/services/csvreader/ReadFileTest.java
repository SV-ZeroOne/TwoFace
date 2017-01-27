package za.co.entelect.bootcamp.twoface.squareeyes.services.csvreader;

import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by mpho.mahase on 2017/01/21.
 */
public class ReadFileTest {
    public static void main(String[] args) throws IOException, ParseException {

        String[] stringArray;
        String base = new File(".").getCanonicalPath();
        String filepath = "\\services\\src\\main\\java\\za\\co\\entelect\\bootcamp\\twoface\\squareeyes\\services\\List of orders.csv";

        String baseFilePath = base + filepath;
        System.out.println(baseFilePath);

        ReadFile readFile = new ReadFile(baseFilePath);
        //readFile.outputFileContents(stringArray);
        //stringArray = readFile.fileOpener();
        List<Order> list = readFile.fileOpener();
        System.out.println(list.get(0).toString());
        /*readFile.outputFileContents(stringArray);*/

        /*System.out.println(stringArray.length);*/
        /*Order orders = readFile.assignRelevantDataToIssueObject();
        orders.getDeliveryStatus();
        System.out.print(orders.getDeliveryStatus());
        System.out.println();*/


    }
}
