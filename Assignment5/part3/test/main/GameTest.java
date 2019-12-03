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


    /**
     * set-up game, so that I can test on a running game with 5 players, and player 0 as the current player
     */
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

    /**
     * tests that the constructor of the game creates a game with all players, gives each 7 cards and a discard pile with one card, and that the starting player is assigned correctly
     */
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
            for(int k = 0; k < players.size(); ++k){
                assertEquals(7,players.get(i).getCards().size());
            }
            assertEquals(1,game.getDiscardPile().size());
        }
    }

    /**
     * assert that the constructor throws an error when initializing with too many players
     */
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

    /**
     * assert that the constructor throws an error when initializing with too few players
     */
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

    /**
     * assert that the constructor throws an error when initializing with no players
     */
    @Test
    public void testGameCreationWithNoPlayers(){

        //creat i players
        ArrayList<Player> testPlayers = new ArrayList<>();

        //starting player
        Player testStartingPlayer = new Player();

        //make Game
        assertThrows(IllegalArgumentException.class , () -> new Game(testPlayers,testStartingPlayer));
    }


    /**
     * assert that the constructor throws an error when initializing with a players as starting player that is not in the game
     */
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

    /**
     * asserts that "startTurn" throws an error when called with player who's not in the game
     */
    @Test
    public void testStartTurnWithIllegalPlayer(){
        assertThrows(IllegalArgumentException.class , () -> game.startTurn(new Player()));
    }


    /**
     * asserts that "startTurn" throws an error when called with player who's turn it isn't
     */
    @Test
    public void testStartTurnWithPlayerWhosTurnItIsnt(){
        assertThrows(IllegalArgumentException.class , ()->game.startTurn(players.get(2)));
    }



    /**
     * asserts that nothing happens when the top card on the pile is a normal card
     */
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


    /**
     * asserts that the player gets skipped if the top card is a skip card, and the game moves on to the corresponding player and state
     */
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

    /**
     * asserts that nothing happens, if the top card is a reverse card
     */
    @Test
    public void testStartingTurnWithReverseCard(){
        Card topCard = new Reverse(Color.blue);
        game.getDiscardPile().add(topCard);
        game.startTurn(currentPlayer);
        assertEquals(currentPlayer,game.getCurrentPlayer());
        assertEquals(7, currentPlayer.getCards().size());
        assertEquals(TurnState.afterEffect,game.getTurnState());
        assertEquals(topCard, game.getDiscardPile().get(game.getDiscardPile().size()-1));
        assertEquals(Direction.left, game.getDirection());
    }

    /**
     * asserts that the player gets skipped if the top card is a draw two card, and the game moves on to the corresponding player and state and the current player draws 2 cards
     */
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

    /**
     * asserts that nothing happens, if the top card is a wild card
     */
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

    /**
     * asserts that the player gets skipped if the top card is a wild draw four card, and the game moves on to the corresponding player and state and the player draws four cards
     */
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

    /**
     * Assures that a normal card of the same color can be placed on top of another card of the same color
     */
    @Test
    public void testPlayableNormalCaseColor(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    /**
     * Assures that a normal card of the same number can be placed on top of another card of the same number
     */
    @Test
    public void testPlayableNormalCaseNumber(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.six, Color.red);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    /**
     * Assures that a normal card ocan't be placed on top of another card of different number and color
     */
    @Test
    public void testPlayableNormalCaseNeitherNumberNorColor(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.green);
        assertEquals(false, game.playable(card,currentPlayer));
    }

    /**
     * Assures that a normal card can be placed on top of a wild card where the same color was chosen
     */
    @Test
    public void testPlayableOnWildCard(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        Wild wild = new Wild();
        wild.setColor(Color.blue);
        game.getDiscardPile().add(0,wild);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    /**
     * Assures that a normal card can't be placed on top of a wild card where another color was chosen
     */
    @Test
    public void testPlayableOnWildCardWrongColor(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.green);
        Wild wild = new Wild();
        wild.setColor(Color.yellow);
        game.getDiscardPile().add(0,wild);
        assertEquals(false, game.playable(card,currentPlayer));
    }

    /**
     * Assures that a normal card can be placed on top of a special card of the same color
     */
    @Test
    public void testPlayableOnSpecialSameColor(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        DrawTwo wild = new DrawTwo(Color.blue);
        game.getDiscardPile().add(0,wild);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    /**
     * Assures that a special card can be placed on top of another special card of the same type
     */
    @Test
    public void testPlayableOnSpecialSameSpecial(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Skip(Color.yellow);
        Skip wild = new Skip(Color.red);
        game.getDiscardPile().add(0,wild);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    /**
     * Assures that a special card can't be placed on top of a different special card of another color
     */
    @Test
    public void testPlayableOnSpecialNothingInCommon(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new DrawTwo(Color.yellow);
        Reverse wild = new Reverse(Color.red);
        game.getDiscardPile().add(0,wild);
        assertEquals(true, game.playable(card,currentPlayer));
    }

    /**
     * Assures that a wild card can always be played
     */
    @Test
    public void testPlayableWild(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Wild();
        assertEquals(true, game.playable(card,currentPlayer));
    }

    /**
     * Assures that a player who isn't in the game can't play a card
     */
    @Test
    public void testPlayableIlligalPlayer(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        assertThrows(IllegalArgumentException.class, ()-> game.playable(card,new Player()));
    }

    /**
     * Assures that when a player who's turn it isn't get's the information that he can't play a card
     */
    @Test
    public void testPlayableWrongPlayer(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.one, Color.blue);
        assertEquals(false, game.playable(card,players.get(3)));
    }

    /////////////////
    //playCard
    ////////////////

    /**
     * Assures that a player who isn't in the game can't play a card
     */
    @Test
    public void testPlayCardWrongPlayer(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.five,Color.blue);
        assertThrows(IllegalArgumentException.class,()-> game.playCard(card,players.get(1)));
    }

    /**
     * assures that a player who't turn it isn't can't play a card
     */
    @Test
    public void testPlayCardIllegalPlayer(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.five,Color.blue);
        assertThrows(IllegalArgumentException.class,()-> game.playCard(card,new Player()));
    }

    /**
     * Asserts that a card can only be played when the gamestate is correct
     */
    @Test
    public void testPlayCardWrongGameState(){
        game.setTurnState(TurnState.played);
        Card card = new Card(Cardtype.five,Color.blue);
        assertThrows(IllegalArgumentException.class,()-> game.playCard(card,currentPlayer));
    }

    /**
     * asserts that a card can be played, after the beginning effect happened
     */
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

    /**
     * Asserts that a card can be played after a card has been drawn
     */
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

    /**
     * Asserts that special cards can be played
     */
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

    /**
     * asserts that playing the reverse card reverses the order
     */
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

    /**
     * asserts that an error gets thrown, if the player tires to play an illigal card
     */
    @Test
    public void testPlayCardIllegalCard(){
        game.setTurnState(TurnState.afterEffect);
        Card card = new Card(Cardtype.five,Color.green);
        assertThrows(IllegalArgumentException.class,()-> game.playCard(card,currentPlayer));
    }

    /**
     * asserts that the player can choose a color, when he plays the draw 4 card
     */
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

    /**
     * Asserts that a player can say uno and it gets registered, after he played his second to last card
     */
    @Test
    public void testSayUnoTrueCaseOwnTurn(){
        game.setTurnState(TurnState.played);
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);
        game.sayUno(currentPlayer);
        assertEquals(true, game.getCurrentPlayer().isSaidUno());
    }

    /**
     * Asserts that a player can say uno and it gets registered, aon his succesors turn, before he did anything
     */
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

    /**
     * asserts that an error gets thrown, if a player sais uno, with too many cards in hand
     */
    @Test
    public void testSayUnoIllegalCaseTwoManyCards(){
        game.setTurnState(TurnState.played);
        assertThrows( IllegalStateException.class ,()->game.sayUno(currentPlayer) );
    }

    /**
     * asserts that a player can't say uno, right before he played a card
     */
    @Test
    public void testSayUnoIllegalBeforePlayedCards(){
        game.setTurnState(TurnState.beginning);
        ArrayList<Card> playerhand = new ArrayList<Card>();
        playerhand.add(new Card(Cardtype.eight, Color.yellow) );
        currentPlayer.setCards(playerhand);
        assertThrows( IllegalStateException.class ,()->game.sayUno(currentPlayer) );
    }

    /**
     * aserts that a player can't say uno, if the player after him already played a card
     */
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

    /**
     * Asserts that a player can say uno, if a player has one card in hand, the player afterwards has already played, and he hasn't said uno. Also checks that the player gets penalized.
     */
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

    /**
     * Asserts that a Accusation can also be made, right before the last card gets played, if all other conditions are met
     */
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

    /**
     * Asserts that an error gets thrown, if a player tries to accuse, before the next player took his turn
     */
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

    /**
     * asserts that an error gets thrown, if an accusation get made against a player with more than 1 card in hand
     */
    @Test
    public void testAccuseUnoTooManyCards(){
        game.setTurnState(TurnState.beginning);

        Card card = new Card(Cardtype.thre,Color.blue);
        game.setCurrentPlayer(players.get(1));
        game.playCard(card, players.get(1));

        assertThrows(IllegalStateException.class, ()->game.accuseUno(players.get(4), currentPlayer));
    }

    /**
     * asserts that an error gets thrown, if a player tries to accuse a player who said uno
     */
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

    /**
     * asserts that a player gets an additional card, when he draws a card under the rigght conditions
     */
    @Test
    public void testdrawCardNoral(){
        game.setTurnState(TurnState.afterEffect);
        game.drawCard(currentPlayer);
        assertEquals(8,currentPlayer.getCards().size());
        assertEquals(TurnState.drawn,game.getTurnState());
    }

    /**
     * checks that an error gets thrown, if a player tries to draw again, after having drawn once
     */
    @Test
    public void testdrawCardIllegalAfterDrawingAlready(){
        game.setTurnState(TurnState.drawn);
        assertThrows(IllegalStateException.class,()->game.drawCard(currentPlayer));
    }

    /**
     * checks that an error gets thrown, if another player tries to draw a card
     */
    @Test
    public void testdrawCardIllegalPlayer(){
        game.setTurnState(TurnState.afterEffect);
        assertThrows(IllegalStateException.class,()->game.drawCard(new Player()));
    }

    /**
     * checks that an error gets thrown, if another player tries to draw a card
     */
    @Test
    public void testdrawCardWrongPlayer(){
        game.setTurnState(TurnState.afterEffect);
        assertThrows(IllegalStateException.class,()->game.drawCard(players.get(2)));
    }

    ////////////////
    //endTurn
    ///////////////

    /**
     * asserts that a player can end his turn and the next player becomes the current player, and that the state is changed acordingly
     */
    @Test
    public void testEndTurnNormal(){
        game.setTurnState(TurnState.played);
        game.endTurn(currentPlayer);
        assertNotEquals(currentPlayer,game.getCurrentPlayer());
    }

    /**
     * asserts (checks for thrown error) that a turn can only be ended, if a player has only drawn a card this turn
     */
    @Test
    public void testEndTurnOnlyDrawn(){
        game.setTurnState(TurnState.drawn);
        game.endTurn(currentPlayer);
        assertNotEquals(currentPlayer,game.getCurrentPlayer());
    }

    /**
     * Checks that an error gets thrown, if a player tires to end his turn before drawing or playing
     */
    @Test
    public void testEndTurnAtWrongState(){
        game.setTurnState(TurnState.afterEffect);
        assertThrows(IllegalStateException.class,()->game.endTurn(currentPlayer));
    }

    /**
     * checks that an error gets thrown, if another player tries to end the turn
     */
    @Test
    public void testEndTurnWrongPlayer(){
        game.setTurnState(TurnState.played);
        assertThrows(IllegalStateException.class,()->game.endTurn(players.get(2)));
    }

    /**
     * checks that an error gets thrown, if a player from outisde the game tries to end the turn
     */
    @Test
    public void testEndTurnIllegalPlayer(){
        game.setTurnState(TurnState.played);
        assertThrows(IllegalStateException.class,()->game.endTurn(new Player()));
    }

    /**
     * asserts that the game ends, when a player ends his turn with zero cards
     */
    @Test
    public void testEndTurnAndWon(){
        game.setTurnState(TurnState.played);
        currentPlayer.setCards(new ArrayList<Card>());
        game.endTurn(currentPlayer);
        assertEquals(true,game.isGameOver());
    }
}