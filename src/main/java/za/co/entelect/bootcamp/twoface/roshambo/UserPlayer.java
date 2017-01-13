package za.co.entelect.bootcamp.twoface.roshambo;

import java.util.LinkedList;
import java.util.Scanner;
/**
 * Created by mpho.mahase on 2017/01/12.
 */
public class UserPlayer extends Player {

    public UserPlayer()
    {}

    public void selectItem(LinkedList<Item> items){
        while(true) {
            System.out.println("Items: ");
            for (int i = 0; i < items.size(); i++) {
                System.out.println("\t" + i + ". " + items.get(i).name);
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your value: ");
            int input = scanner.nextInt();
            if(input >= 0 && input < items.size()) {
                this.selected = items.get(input);
                break;
            }
            System.out.println("Incorrect input");
        }
    }


}
