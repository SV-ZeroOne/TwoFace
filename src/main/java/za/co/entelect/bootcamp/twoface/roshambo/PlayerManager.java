package za.co.entelect.bootcamp.twoface.roshambo;

import java.util.LinkedList;

/**
 * Created by quinton.weenink on 2017/01/12.
 */
public class PlayerManager {

    public LinkedList<Player> players;
    public LinkedList<Item> items;

    public PlayerManager(LinkedList<Item> i, int aiNum, int usrNum)
    {
        System.out.println("Initialising player manager...");
        this.items = i;
        this.players = new LinkedList<Player>();
        for(int x = 0; x < aiNum; x++)
        {
            players.add(new BotPlayer());
        }
        for(int x = 0; x < usrNum; x++)
        {
            players.add(new UserPlayer());
        }
    }
    
    public void play()
    {
        System.out.println("Stating selection process...");
        for (Player p : players) {
            p.selectItem(this.items);
        }
    }
}
