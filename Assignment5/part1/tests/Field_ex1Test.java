import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class Field_ex1Test {

    private Field_ex1 field;
    private Field_ex1 startfield;

    @Before
    public void setup(){
        startfield = new Field_ex1(1);
        field = new Field_ex1(2, startfield);
    }

    @Test
    public void checkPlayerAdding() {
        /* Player array should be empty */
        assertTrue(field.getPlayerEx1s().isEmpty());

        /* now let's add a player: */
        Player_ex1 player1 = new Player_ex1("Dan");
        field.putOn(player1);
        assertEquals(player1, field.getPlayerEx1s().get(0));

        /* now let's try to add another, this should still return the first player*/
        Player_ex1 player2 = new Player_ex1("Joe");
        field.putOn(player2);
        assertEquals(1, field.getPlayerEx1s().size());
        /* And check that the player2 was placed on startField*/
        assertEquals(player2, startfield.getPlayerEx1s().get(0));
    }
}