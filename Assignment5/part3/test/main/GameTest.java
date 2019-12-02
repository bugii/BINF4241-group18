package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    ArrayList<Player> players;
    Player startingPlayer;


    @BeforeEach
    public void setUp(){
        players = new ArrayList<>();
        for( int i = 0; i < 5; ++i){
            players.add(new Player());
        }
        startingPlayer = players.get(0);
        game = new Game(players,startingPlayer);
    }


    /////////////////
    //creation Tests
    /////////////////
    @Test
    public void testGameCreationCorrect(){

        for(int i = 2; i < 11; ++i){

            //creat i players
            ArrayList<Player> testPlayers = new ArrayList<>();
            for(int j = 0; j < i; ++j){
                testPlayers.add(new Player());
            }

            //starting player
            Random r = new Random();
            Player testStartingPlayer = testPlayers.get(r.nextInt(i));

            //make Game
            Game testGame = new Game(testPlayers,testStartingPlayer);

            assertEquals(players,testGame.getPlayers());
            assertEquals(testStartingPlayer,testGame.getCurrentPlayer());
        }
    }

    @Test
    public void testGameCreationWithTooManyPlayers(){

        //creat i players
        ArrayList<Player> testPlayers = new ArrayList<>();
        for(int j = 0; j < 11; ++j){
            testPlayers.add(new Player());
        }

        //starting player
        Random r = new Random();
        Player testStartingPlayer = testPlayers.get(r.nextInt(10));

        //make Game
        assertThrows(IllegalArgumentException.class , () -> new Game(testPlayers,testStartingPlayer));
    }

    @Test
    public void testGameCreationWithTooFewPlayers(){

        //creat i players
        ArrayList<Player> testPlayers = new ArrayList<>();
        for(int j = 0; j < 1; ++j){
            testPlayers.add(new Player());
        }

        //starting player
        Random r = new Random();
        Player testStartingPlayer = testPlayers.get(0);

        //make Game
        assertThrows(IllegalArgumentException.class , () -> new Game(testPlayers,testStartingPlayer));
    }

    @Test
    public void testGameCreationWithNoPlayers(){

        //creat i players
        ArrayList<Player> testPlayers = new ArrayList<>();

        //starting player
        Player testStartingPlayer = new Player();

        //make Game
        assertThrows(IllegalArgumentException.class , () -> new Game(testPlayers,testStartingPlayer));
    }

    @Test
    public void testGameCreationWithExternalStartingPlayer(){

        //external starting player
        Player testStartingPlayer = new Player();

        //Throwcheck
        assertThrows(IllegalArgumentException.class , () -> new Game(players,testStartingPlayer));

    }

    ///////////////////
    //StartTurn
    ///////////////////
    @Test
    public void testStartTurnWithIllegalPlayer(){
        assertThrows(IllegalArgumentException.class , () -> game.startTurn(new Player()));
    }

    @Test
    public void testStartTurnWithPlayerWhosTurnItIsnt(){
        assertThrows(IllegalArgumentException.class , ()->game.startTurn(players.get(2)));
    }

    @Test
    public void testStartTurnWithNormalCard(){
        Card topCard = new Card(Cardtype.five,Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(startingPlayer);
        assertEquals(startingPlayer,game.getCurrentPlayer());
        assertEquals(7,startingPlayer.getCards().size());
        assertEquals(TurnState.afterEffect,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.left, game.getDirection());
    }

    @Test
    public void testStartingTurnWithSkipCard(){
        Card topCard = new Skip(Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(startingPlayer);
        assertNotEquals(startingPlayer,game.getCurrentPlayer());
        assertEquals(players.get(2),game.getCurrentPlayer());
        assertEquals(7,startingPlayer.getCards().size());
        assertEquals(TurnState.beginning,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.left, game.getDirection());
    }

    @Test
    public void testStartingTurnWithReverseCard(){
        Card topCard = new Card(Cardtype.five,Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(startingPlayer);
        assertEquals(startingPlayer,game.getCurrentPlayer());
        assertEquals(7,startingPlayer.getCards().size());
        assertEquals(TurnState.afterEffect,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.right, game.getDirection());
    }

    @Test
    public void testStartTurnWithDrawTwo(){
        Card topCard = new Card(Cardtype.five,Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(startingPlayer);
        assertEquals(startingPlayer,game.getCurrentPlayer());
        assertEquals(7,startingPlayer.getCards().size());
        assertEquals(TurnState.afterEffect,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.left, game.getDirection());
    }
}