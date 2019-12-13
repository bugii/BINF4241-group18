import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class Start_ex1Test {

    private ArrayList<Player_ex1> players = new ArrayList<>();
    private Start_ex1 start;

    private void addPlayer(String playerName){
        Player_ex1 player = new Player_ex1(playerName);
        players.add(player);
    }
    @Before
    public void setup(){
        addPlayer("Dan");
        addPlayer("Rachel");
        addPlayer("Joe");
        start = new Start_ex1(1,players);
    }

    @Test
    public void sPutOnTest() {
        /* since we've tested the putOn() method in the parent class we just have to test the unique feature of this method
        One can add more players to this*/
        for(int i = 0; i < players.size(); i++){
            assertEquals(players.get(i), start.getPlayerEx1s().get(i));
        }
        /* Let's see if the putOn() method works as it should: */
        addPlayer("Mark");

    }

    @After
    public void cleanup(){players.clear();}
}