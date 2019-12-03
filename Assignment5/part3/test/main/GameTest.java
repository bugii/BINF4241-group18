package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;
    ArrayList<Player> players;
    Player currentPlayer;


    @BeforeEach
    public void setUp(){
        players = new ArrayList<>();
        for( int i = 0; i < 5; ++i){
            players.add(new Player());
        }
        currentPlayer = players.get(0);
        game = new Game(players, currentPlayer);
        game.setFirstTurn(false);
        game.getDiscardPile().add(0, new Card(Cardtype.six, Color.blue));

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
        game.startTurn(currentPlayer);
        assertEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(7, currentPlayer.getCards().size());
        assertEquals(TurnState.afterEffect,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.left, game.getDirection());
    }

    @Test
    public void testStartingTurnWithSkipCard(){
        Card topCard = new Skip(Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(currentPlayer);
        assertNotEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(players.get(2),game.getCurrentPlayer());
        assertEquals(7, currentPlayer.getCards().size());
        assertEquals(TurnState.beginning,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.left, game.getDirection());
    }

    @Test
    public void testStartingTurnWithReverseCard(){
        Card topCard = new Reverse(Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(currentPlayer);
        assertEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(7, currentPlayer.getCards().size());
        assertEquals(TurnState.afterEffect,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.right, game.getDirection());
    }

    @Test
    public void testStartTurnWithDrawTwo(){
        Card topCard = new DrawTwo(Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(currentPlayer);
        assertEquals(players.get(2),game.getCurrentPlayer());
        assertEquals(7, currentPlayer.getCards().size());
        assertEquals(TurnState.beginning,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.left, game.getDirection());
    }

    @Test
    public void testStartTurnWithWild(){
        Card topCard = new Wild();
        topCard.setColor(Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(currentPlayer);
        assertEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(7, currentPlayer.getCards().size());
        assertEquals(TurnState.afterEffect,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.left, game.getDirection());
    }

    @Test
    public void testStartTurnWithWildDrawFour() {
        Card topCard = new WildDrawFour();
        topCard.setColor(Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(currentPlayer);
        assertEquals(players.get(1), game.getCurrentPlayer());
        assertEquals(11, currentPlayer.getCards().size());
        assertEquals(TurnState.beginning, game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size() - 1));
        assertEquals(Direction.left, game.getDirection());
    }

    ///////////////
    //playable
    ///////////////

    @Test
    public void testPlayableNormalCaseColor(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    @Test
    public void testPlayableNormalCaseNumber(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.six, Color.red);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    @Test
    public void testPlayableNormalCaseNeitherNumberNorColor(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.green);
        assertEquals(false, game.playable(card,currentPlayer));
    }

    @Test
    public void testPlayableOnWildCard(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        Wild wild = new Wild();
        wild.setColor(Color.blue);
        game.getDiscardPile().add(0,wild);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    @Test
    public void testPlayableOnWildCardWrongColor(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.green);
        Wild wild = new Wild();
        wild.setColor(Color.yellow);
        game.getDiscardPile().add(0,wild);
        assertEquals(false, game.playable(card,currentPlayer));
    }

    @Test
    public void testPlayableOnSpecialSameColor(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        DrawTwo wild = new DrawTwo(Color.blue);
        game.getDiscardPile().add(0,wild);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    @Test
    public void testPlayableOnSpecialSameSpecial(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Skip(Color.yellow);
        Skip wild = new Skip(Color.red);
        game.getDiscardPile().add(0,wild);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    @Test
    public void testPlayableOnSpecialNothingInCommon(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new DrawTwo(Color.yellow);
        Reverse wild = new Reverse(Color.red);
        game.getDiscardPile().add(0,wild);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    @Test
    public void testPlayableWild(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Wild();
        assertEquals(true, game.playable(card,currentPlayer));
    }

    @Test
    public void testPlayableIlligalPlayer(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        assertThrows(IllegalArgumentException.class, ()-> game.playable(card,new Player()));
    }

    @Test
    public void testPlayableWrongPlayer(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        assertEquals(false, game.playable(card,players.get(3)));
    }

    /////////////////
    //playCard
    ////////////////

    @Test
    public void testPlayCardWrongPlayer(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.five,Color.blue);
        assertThrows(IllegalArgumentException.class,()-> game.playCard(card,currentPlayer));
    }

    @Test
    public void testPlayCardIllegalPlayer(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.five,Color.blue);
        assertThrows(IllegalArgumentException.class,()-> game.playCard(card,new Player()));
    }

    @Test
    public void testPlayCardWrongGameState(){
        game.setTurnState(TurnState.played);
        Card card = new Card(Cardtype.five,Color.blue);
        assertThrows(IllegalArgumentException.class,()-> game.playCard(card,currentPlayer));
    }

    @Test
    public void testPlayCardNormalCase(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.five,Color.blue);
        game.playCard(card,currentPlayer);
        assertEquals(card,game.getDiscardPile().get(0));
        assertEquals(TurnState.played, game.getTurnState());
        assertEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(Direction.left,game.getDirection());
    }

    @Test
    public void testPlayCardAfterDrawing(){
        game.setTurnState(TurnState.drawn);
        Card card = new Card(Cardtype.five,Color.blue);
        game.playCard(card,currentPlayer);
        assertEquals(card,game.getDiscardPile().get(0));
        assertEquals(TurnState.played, game.getTurnState());
        assertEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(Direction.left,game.getDirection());
    }

    @Test
    public void testPlayCardSpecialCard(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new DrawTwo(Color.blue);
        game.playCard(card,currentPlayer);
        assertEquals(card,game.getDiscardPile().get(0));
        assertEquals(TurnState.played, game.getTurnState());
        assertEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(Direction.left,game.getDirection());
    }

    @Test
    public void testPlayCardReverse(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Reverse(Color.blue);
        game.playCard(card,currentPlayer);
        assertEquals(card,game.getDiscardPile().get(0));
        assertEquals(TurnState.played, game.getTurnState());
        assertEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(Direction.right,game.getDirection());
    }

    @Test
    public void testPlayCardIllegalCard(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.five,Color.green);
        assertThrows(IllegalArgumentException.class,()-> game.playCard(card,currentPlayer));
    }

    @Test
    public void testPlayCardWildDrawFour()
    {
        game.setTurnState(TurnState.afterEffect);
        Card card = new WildDrawFour();

        //suimluate user input
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("blue\n".getBytes());
        System.setIn(in);

        game.playCard(card,currentPlayer);

        //reset System.in to its original
        System.setIn(sysInBackup);

        assertEquals(TurnState.played, game.getTurnState());
        assertEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(Direction.left,game.getDirection());
        assertEquals(Color.blue, game.getDiscardPile().get(0).getColor());
        assertEquals(Cardtype.wildDrawFour, game.getDiscardPile().get(0).getCardtype());
    }

    ////////////////
    //SayUno
    ////////////////

    @Test
    public void testSayUnoTrueCaseOwnTurn(){
        game.setTurnState(TurnState.played);
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);
        game.sayUno(currentPlayer);
        assertEquals(true, game.getCurrentPlayer().isSaidUno());
    }

    @Test
    public void testSayUnoTrueCaseNextTurn(){
        game.setTurnState(TurnState.beginning);
        game.setCurrentPlayer(players.get(1));
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);
        game.sayUno(currentPlayer);
        assertEquals(true, game.getCurrentPlayer().isSaidUno());
    }

    @Test
    public void testSayUnoIllegalCaseTwoManyCards(){
        game.setTurnState(TurnState.played);
        assertThrows( IllegalStateException.class ,()->game.sayUno(currentPlayer) );
    }

    @Test
    public void testSayUnoIllegalBeforePlayedCards(){
        game.setTurnState(TurnState.beginning);
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);
        assertThrows( IllegalStateException.class ,()->game.sayUno(currentPlayer) );
    }

    @Test
    public void testSayUnoIllegalnextPlayerPlayedAllready(){
        game.setTurnState(TurnState.beginning);
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);

        Card card = new Card(Cardtype.thre,Color.blue);
        game.setCurrentPlayer(players.get(1));
        game.playCard(card, players.get(1));

        assertThrows( IllegalStateException.class ,()->game.sayUno(currentPlayer) );
    }

    ///////////////////
    //accuseUno
    //////////////////

    @Test
    public void testAccuseUnoCorrectAcusation(){
        game.setTurnState(TurnState.beginning);
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);

        Card card = new Card(Cardtype.thre,Color.blue);
        game.setCurrentPlayer(players.get(1));
        game.playCard(card, players.get(1));

        game.accuseUno(players.get(4), currentPlayer);

        assertEquals(3,currentPlayer.getCards().size());
    }

    @Test
    public void testAccuseUnoCorrectAcusationLastMoment(){
        game.setTurnState(TurnState.beginning);
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);

        Card card = new Card(Cardtype.thre,Color.blue);
        game.setCurrentPlayer(players.get(1));
        game.playCard(card, players.get(1));

        game.setCurrentPlayer(currentPlayer);
        game.setTurnState(TurnState.afterEffect);
        game.accuseUno(players.get(4), currentPlayer);

        assertEquals(3,currentPlayer.getCards().size());
    }

    @Test
    public void testAccuseTooEarly(){
        game.setTurnState(TurnState.beginning);
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);

        Card card = new Card(Cardtype.thre,Color.blue);
        game.setCurrentPlayer(players.get(1));
        //game.playCard(card, players.get(1));

        assertThrows(IllegalStateException.class, ()->game.accuseUno(players.get(4), currentPlayer));
    }

    @Test
    public void testAccuseUnoTooManyCards(){
        game.setTurnState(TurnState.beginning);

        Card card = new Card(Cardtype.thre,Color.blue);
        game.setCurrentPlayer(players.get(1));
        game.playCard(card, players.get(1));

        assertThrows(IllegalStateException.class, ()->game.accuseUno(players.get(4), currentPlayer));
    }

    @Test
    public void testAccuseUnoSaidUno(){
        game.setTurnState(TurnState.played);
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);
        game.sayUno(currentPlayer);

        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.thre,Color.blue);
        game.setCurrentPlayer(players.get(1));
        game.playCard(card, players.get(1));

        assertThrows(IllegalStateException.class, ()->game.accuseUno(players.get(4), currentPlayer));
    }


    /////////////////
    //drawCard
    ////////////////

    @Test
    public void testdrawCardNoral(){
        game.setTurnState(TurnState.afterEffect);
        game.drawCard(currentPlayer);
        assertEquals(8,currentPlayer.getCards().size());
        assertEquals(TurnState.drawn,game.getTurnState());
    }

    @Test
    public void testdrawCardIllegalAfterDrawingAlready(){
        game.setTurnState(TurnState.drawn);
        assertThrows(IllegalStateException.class,()->game.drawCard(currentPlayer));
    }

    @Test
    public void testdrawCardIllegalPlayer(){
        game.setTurnState(TurnState.afterEffect);
        assertThrows(IllegalStateException.class,()->game.drawCard(new Player()));
    }

    @Test
    public void testdrawCardWrongPlayer(){
        game.setTurnState(TurnState.afterEffect);
        assertThrows(IllegalStateException.class,()->game.drawCard(players.get(2)));
    }

    ////////////////
    //endTurn
    ///////////////

    @Test
    public void testEndTurnNormal(){
        game.setTurnState(TurnState.played);
        game.endTurn(currentPlayer);
        assertNotEquals(currentPlayer,game.getCurrentPlayer());
    }

    @Test
    public void testEndTurnOnlyDrawn(){
        game.setTurnState(TurnState.drawn);
        game.endTurn(currentPlayer);
        assertNotEquals(currentPlayer,game.getCurrentPlayer());
    }

    @Test
    public void testEndTurnAtWrongState(){
        game.setTurnState(TurnState.afterEffect);
        assertThrows(IllegalStateException.class,()->game.endTurn(currentPlayer));
    }

    @Test
    public void testEndTurnWrongPlayer(){
        game.setTurnState(TurnState.played);
        assertThrows(IllegalStateException.class,()->game.endTurn(players.get(2)));
    }

    @Test
    public void testEndTurnIllegalPlayer(){
        game.setTurnState(TurnState.played);
        assertThrows(IllegalStateException.class,()->game.endTurn(new Player()));
    }

    @Test
    public void testEndTurnAndWon(){
        game.setTurnState(TurnState.played);
        currentPlayer.setCards(new ArrayList<Card>());
        game.endTurn(currentPlayer);
        assertEquals(true,game.isGameOver());
    }
}