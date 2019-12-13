import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Snake_ex1Test {
    private Field_ex1 start;
    private Field_ex1 destination;
    private Snake_ex1 snake;
    private Player_ex1 player1;
    private Player_ex1 player2;

    @Before
    public void setup(){
        start = new Field_ex1(1);
        destination = new Field_ex1(2, start);
        snake = new Snake_ex1(3, start);
        /* One point of contention could be that one has to add destination separately*/
        snake.setDestination(destination);
        player1 = new Player_ex1("Dan");
        player2 = new Player_ex1("Joe");
    }
    @Test
    public void SnakePutOnTest() {
        /* So let's add the first player*/
        snake.putOn(player1);
        assertTrue(snake.getPlayerEx1s().isEmpty());
        assertEquals(player1, destination.getPlayerEx1s().get(0));

        /* Now let's try to add another, this one should land on the starting field*/
        snake.putOn(player2);
        assertTrue(snake.getPlayerEx1s().isEmpty());
        assertEquals(player1, destination.getPlayerEx1s().get(0));
        assertEquals(player2, start.getPlayerEx1s().get(0));
    }
}