package za.co.entelect.bootcamp.twoface.roshambo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by sean.vienings on 2017/01/12.
 */
public class console {

    public static void main(String[] s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("  \n" +
                "                      @    `' ;                  \n" +
                "                 #@@@;    `..;:' ,               \n" +
                "               @#'@#:#`` `   @',:                \n" +
                "              +:'@@;@`@+,.;;  ;+  +`             \n" +
                "             @@#';+@@@,:''',.  `':@.'; `         \n" +
                "            `@@;.....,;:,.,'##;;:.#.@            \n" +
                "             @@@`......,.+;+;@':'@@'@ '          \n" +
                "             @,,`;..,..,;;`@@@@+'@@'             \n" +
                "            @@@`@@+@.`.``@:,.,#;+@@; :+          \n" +
                "           ..`@`#, # @;.':....;#;:+              \n" +
                "            `.:`...@,,`.#+####'.##@              \n" +
                "             ..`;....'..@,,##'##:'               \n" +
                "              @``....@:;#@;,..:'                 \n" +
                "              @.`'....@+@ ,@+::'                 \n" +
                "              @'``.+.@@@@@#'@,++                 \n" +
                "              @.@``...+'+;:@;''#                 \n" +
                "              @.::``.....#;;+':+                 \n" +
                "              @..:,,#`:@`.:#'':, #.              \n" +
                "              @@,::,`+#@@@;;#::, '@',            \n" +
                "          + `  @@@@:.``;';::::@ : @#'@@          \n" +
                "        @  #.  .@@@'@@@`;;:;`  @'@@##@@@;        \n" +
                "      @ :       @@@@++@+.'@#  :#@@@@###@@:#@ ';  \n" +
                "    @    `       @@@#@  `##@@ . ,@##@@@@###+#@## \n" +
                "@  @              @@@@   ,##,@  #@#@@@'##########\n" +
                "  ;        #  .   @@@@,  @#  `;+###@##.;+########\n" +
                "         . `      :@@@+  @#@   @###+####:########\n" +
                "          +,                .                    \n" +
                "+------------------------------------------+\n" +
                "|               TEAM TWO FACE              |\n" +
                "+------------------------------------------+\n");
        LinkedList<Item> itemList = readXml("config.xml");
        System.out.print("Enter number of AI players: ");
        int aiPlayers = scanner.nextInt();
        System.out.print("Enter number of User players: ");
        int userPlayers = scanner.nextInt();
        PlayerManager pm = new PlayerManager(itemList, aiPlayers, userPlayers);
        while(true){
            pm.play();
            Scanner intScanner = new Scanner(System.in);
            System.out.println("Would you like to continue? [Y|N]");
            String input = intScanner.nextLine();
            if(input.contains("n") || input.contains("N"))
                break;
        }
    }


    public static LinkedList<Item> readXml(String xmlLocation) {
        LinkedList<Item> items = new LinkedList<Item>();

        try {
            File xmlFile = new File(xmlLocation);
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();
            NodeList itemList = doc.getElementsByTagName("name");

            for (int nameLoop = 0; nameLoop < itemList.getLength(); nameLoop++) {
                Item temp = new Item(itemList.item(nameLoop).getTextContent());
                items.add(temp);
            }

            NodeList childList = doc.getElementsByTagName("beats");

            for (int x = 0; x < items.size(); x++) {
                Element child = (Element) childList.item(x);
                NodeList temp = child.getElementsByTagName("itemName");
                for (int j = 0; j < temp.getLength(); j++) {
                    Element node = (Element)temp.item(j);
                    String itemName = node.getTextContent();
                    for (Item i : items) {
                        if (itemName.equals(i.name)) {
                            items.get(x).addBeats(i);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return items;
    }
}
