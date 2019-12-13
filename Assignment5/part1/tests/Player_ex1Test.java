import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class Player_ex1Test {

    private Player_ex1 player;

    @Before
    public void setup(){
        player = new Player_ex1("Dan");
    }

    @Test
    public void testGetWinner(){
        assertEquals(false, player.getWinner());
    }

    @Test
    public void testSetWinner(){
        /* also assumes get methods work */
        player.setWinner();
        assertEquals(true, player.getWinner());
    }

}