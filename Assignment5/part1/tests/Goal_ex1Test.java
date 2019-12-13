import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Goal_ex1Test {
    private Player_ex1 player1 = new Player_ex1("Dan");
    private Player_ex1 player2  = new Player_ex1("Joe");

    @Test
    public void putOn() {
        Goal_ex1 goal = new Goal_ex1(7);
        assertTrue(goal.getPlayerEx1s().isEmpty());

        /* add a player: */
        goal.putOn(player1);
        assertEquals(player1, goal.getPlayerEx1s().get(0));
        assertTrue(player1.getWinner());

        /* if we add another */
        goal.putOn(player2);
        assertEquals(player2, goal.getPlayerEx1s().get(goal.getPlayerEx1s().size()-1));
        assertTrue(player2.getWinner());
    }
}