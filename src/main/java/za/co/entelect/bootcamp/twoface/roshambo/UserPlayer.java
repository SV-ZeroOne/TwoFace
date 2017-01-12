package za.co.entelect.bootcamp.twoface.roshambo;

import java.util.LinkedList;
import java.util.Scanner;
/**
 * Created by mpho.mahase on 2017/01/12.
 */
public class UserPlayer extends Player {

    public UserPlayer()
    {
        //System.out.println("Creating UserPlayer");
    }

    public void selectItem(LinkedList<Item> items){
        System.out.println("Items: ");
        for (int i = 0; i < items.size(); i++){
            System.out.println("\t" + i + ". " + items.get(i).name);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your value: ");
        int input = scanner.nextInt();
        this.selected = items.get(input);

        //System.out.println("UserPlayer selected " + selected.name);
    }


}
