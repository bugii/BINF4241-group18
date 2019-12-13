import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Random;

public class Game_ex1Test {

    private Game_ex1 game;

    private void simulateInput(String input){
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    public void testingTheActualGame() {
        /* First let's test the setup, for that we'll have to overwrite input*/
        simulateInput("2\n12\nDan\nJoe");
        game = new Game_ex1();

        /*Now let's first input incorrect information and then correct...
        * Explanation: first we input an invalid information, later correct (to allow the program to continue)
        * Expected result: 2 players, 12 tile board, player names: Dan, Joe*/
        simulateInput("5\n2\n\n-1\n\n1\n12\n\n\n\n\n\nDan\nDan\nDan\n\nJoe");
        game = new Game_ex1();
        ArrayList<String> expectedNames = new ArrayList<>();
        expectedNames.add("Dan"); expectedNames.add("Joe");
        assertEquals(2, game.playerNames.size());
        assertEquals(12, game.boardSize);
        assertEquals(expectedNames, game.playerNames);
    }

    @Test
    public void testPlaying() {
        /* So now let's just have it run to make sure no errors pop up... */
        String str;
        Random rand = new Random();
        for(int i = 0; i < 10; i++){
            str = "";
            str += (rand.nextInt(2) + 2) + "\n";
            str += (rand.nextInt(90) + 2) + "\nDan\nJoe\nRachel\nFred";
            simulateInput(str);
            game = new Game_ex1();
            game.play();
        }
    }
}