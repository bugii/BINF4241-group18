import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Board_ex1Test {
    private ArrayList<Player_ex1> players;
    private Board_ex1 board;

    @Before
    public void setup(){
        players = new ArrayList<>();
        Player_ex1 player = new Player_ex1("Dan");
        players.add(player);
        player = new Player_ex1("Joe");
        players.add(player);
    }

    @Test
    public void testMoveFig() {
        board = new Board_ex1(players, 8);

        /* first let's try out a normal movement: */
        board.MoveFig(players.get(0), 2);
        assertEquals(board.gameboard.get(2), players.get(0).getField());
        assertEquals(players.get(0), board.gameboard.get(2).getPlayerEx1s().get(0));

        /* we know that the fifth field is a ladder, place Joe there*/
        board.MoveFig(players.get(1), 4);
        Field_ex1 playerLocation1 = players.get(1).getField();
        Field_ex1 expectedLocation = ((Ladder_ex1) board.gameboard.get(4)).getDestination();
        if(expectedLocation.getFieldNumber() == 3){expectedLocation = board.gameboard.get(0);}
        assertEquals(expectedLocation, playerLocation1);
        int p = expectedLocation.getFieldNumber();

        /* now let's place Dan on the snake: */
        board.MoveFig(players.get(0), 4);
        Field_ex1 playerLocation0 = players.get(0).getField();
        expectedLocation = ((Snake_ex1) board.gameboard.get(6)).getDestination();
        if(expectedLocation instanceof Ladder_ex1){
            expectedLocation = ((Ladder_ex1) expectedLocation).getDestination();
        }
        if(expectedLocation.getFieldNumber() == p && p != board.size){expectedLocation = board.gameboard.get(0);}
        assertEquals(expectedLocation, playerLocation0);
        /* Test case for what happens when one player is already on move-location in Field_ex1Test
        *  Test case for player being placed on last field is in Goal_ex1Test*/

        /* What happens if one would move past the Goal?
        * ->They'll turn around and move the rest of the distance back.
        *  In this case Joe should end up just where he started out */
        board.MoveFig(players.get(1), (board.size-p)*2);
        expectedLocation = playerLocation1;
        playerLocation1 = players.get(1).getField();
        assertEquals(expectedLocation, playerLocation1);

        /* Now one more: if they would move back further than the first square
        *  -> They should just stop on the start field*/
        board.MoveFig(players.get(1), board.size*2);
        playerLocation1 = players.get(1).getField();
        assertEquals(board.gameboard.get(0), playerLocation1);

    }
}