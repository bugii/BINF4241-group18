import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int numberOfPlayers;
    private int boardSize;
    private ArrayList<Player> players;
    private Board board;
    private int currentRound = 0;
    private Die die;
    private Player winner;

    //aks the initial field size and player amount of user
    private void getInitialValues(){
        //#players
        System.out.println("How many players will participate?");
        Scanner scanner = new Scanner(System.in);
        numberOfPlayers = scanner.nextInt();
        while(numberOfPlayers < 2 || numberOfPlayers > 4){
            System.out.println("Invalid number of Players. You need to choose a bumber between 1 and 4. How many players will participate?");
            numberOfPlayers = scanner.nextInt();
        }

        //#Fields
        System.out.println("How Big do you want the field to be?");
        boardSize = scanner.nextInt();
        while(boardSize < 2){
            System.out.println("Field to small, please Enter an Integer > 1");
            boardSize = scanner.nextInt();
        }
    }

    //Initiate all game Objects by using user input
    public Game( ) {
        getInitialValues();

        //initializing Players
        players = new ArrayList<Player>();
        for( int i = 1; i <= numberOfPlayers; ++i){
            players.add(new Player(i));
        }

        //initializing Board
        board = new Board(players,boardSize);

        //initialzing die
        die = new Die();

        //no round yet
        currentRound = 0;
        winner = null;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private void printGameState() {
        String message = "";
        if(winner != null){
            message = "Final state: ";
        }
        else if(currentRound == 0){
            message = "Initial State: ";
        } else {
            message = "Game in Round " + Integer.toString(currentRound) + ": ";
        }
        System.out.printf(message);
        board.PrintBoard();
    }

    private void printAfterRoll(Player player,int roll){
        System.out.printf(player.getName() + " rolls " + Integer.toString(roll) + ": ");
        board.PrintBoard();
    }

    private void playTurn(Player player){
        int diceRoll = die.roll();
        board.MoveFig(player,diceRoll);
        printAfterRoll(player,diceRoll);
    }

    private boolean didWin(){
        for(int i = 0; i < numberOfPlayers; ++i){
            if(players.get(i).getWinner()){
                winner = players.get(i);
                return true;
            }
        }
        return false;
    }

    private void play() {
        int currentPlayer = 0;
        printGameState();
        while(!didWin()){
            playTurn(players.get(currentPlayer));
            currentPlayer = (currentPlayer + 1)%numberOfPlayers;
            currentRound += 1;
        }
        printGameState();
        System.out.println(winner.getName() + " wins!");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}