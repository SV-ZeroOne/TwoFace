package za.co.entelect.bootcamp.twoface.roshambo;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by mpho.mahase on 2017/01/12.
 */
public class BotPlayer extends Player {

    public void selectItem(LinkedList<Item> items){
        Random selectorOfElement = new Random(1 + items.size());

        this.selected = items.get(selectorOfElement.nextInt());
    }
}
