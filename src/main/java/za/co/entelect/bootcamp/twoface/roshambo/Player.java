package za.co.entelect.bootcamp.twoface.roshambo;

import java.util.LinkedList;

/**
 * Created by mpho.mahase on 2017/01/12.
 */
public abstract class Player {
    public Item selected;

    public abstract void selectItem(LinkedList<Item> items);
}
