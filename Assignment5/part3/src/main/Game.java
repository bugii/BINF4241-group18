package main;

import java.util.ArrayList;

public class Game {
    private boolean gameOver;
    private TurnState turnState;
    private ArrayList<Card> deck;
    private ArrayList<Card> discardPile;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Direction direction

    public Direction getDirection() {
        return direction;
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Game(ArrayList<Player> players, Player startingPlayer) {

    }

    public boolean isGameOver() {
        return gameOver;
    }

    protected void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public TurnState getTurnState() {
        return turnState;
    }

    protected void setTurnState(TurnState turnState) {
        this.turnState = turnState;
    }

    protected ArrayList<Card> getDeck() {
        return deck;
    }

    protected void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    protected void setDiscardPile(ArrayList<Card> discardPile) {
        this.discardPile = discardPile;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    protected void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    protected void setCurrentPlayer(Player startingPlayer) {
        this.currentPlayer = startingPlayer;
    }

    public void startTurn(Player player){}

    public boolean playable(Card card, Player player){ return false; }

    public void playCard (Card card, Player player){ }

    public void sayUno(Player player) { }

    public void accuseUno(Player player, Player notSaidPlayer){ }

    public void drawCard(Player player){ }

    public void endTurn(Player player){ }
}
