package za.co.entelect.bootcamp.twoface.roshambo;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by mpho.mahase on 2017/01/12.
 */
public class BotPlayer extends Player {

    public BotPlayer()
    {
        System.out.println("Creating BotPlayer");
    }

    public void selectItem(LinkedList<Item> items){
        Random selectorOfElement = new Random(items.size());

        this.selected = items.get(0);
    }
}
