package za.co.entelect.bootcamp.twoface.roshambo;

import java.util.LinkedList;

/**
 * Created by mpho.mahase on 2017/01/12.
 */
public abstract class Player {
    public Item selected;

    public abstract void selectItem(LinkedList<Item> items);

    public void didWin(LinkedList<Player> players)
    {
        System.out.println("Did you win?");
        for (Player p : players) {
            for (Item i : selected.beats) {
                if(i == p.selected){
                    System.out.println("Player's " + selected.name + " beats " + p.selected.name);
                }
                else{
                    System.out.println("Player's " + selected.name + " lost against " + p.selected.name);
                }
            }
        }
    }
}
