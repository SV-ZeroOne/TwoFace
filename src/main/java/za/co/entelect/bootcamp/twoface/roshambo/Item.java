package za.co.entelect.bootcamp.twoface.roshambo;

import java.util.LinkedList;

/**
 * Created by quinton.weenink on 2017/01/12.
 */
public class Item {
    public String name;
    public LinkedList<Item> beats;

    public Item(String n)
    {
        this.name = n;
        this.beats = new LinkedList();
    }

    public void addBeats(Item i)
    {
        this.beats.add(i);
    }

    public boolean canBeat(Item other)
    {
        for(int x = 0; x < beats.size(); x++){
            if(beats.get(x) == other)
                return true;
        }
        return false;
    }
}
