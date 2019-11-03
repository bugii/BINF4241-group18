package Assignment1;

import java.util.ArrayList;
import java.util.Scanner;

public class Game_ex1 {
    private int numberOfPlayers;
    private int boardSize;
    private ArrayList<Player_ex1> playerEx1s;
    private Board_ex1 board;
    private int currentRound = 0;
    private Die_ex1 die;
    private Player_ex1 winner;

    //aks the initial field size and player amount of user
    private void getInitialValues(){
        //#playerEx1s
        System.out.println("How many playerEx1s will participate?");
        Scanner scanner = new Scanner(System.in);
        numberOfPlayers = scanner.nextInt();
        while(numberOfPlayers < 2 || numberOfPlayers > 4){
            System.out.println("Invalid number of Players. You need to choose a bumber between 1 and 4. How many playerEx1s will participate?");
            numberOfPlayers = scanner.nextInt();
        }

        //#Fields
        System.out.println("How Big do you want the field to be?");
        boardSize = scanner.nextInt();
        while(boardSize < 2){
            System.out.println("Field_ex1 to small, please Enter an Integer > 1");
            boardSize = scanner.nextInt();
        }
    }

    //Initiate all game Objects by using user input
    public Game_ex1( ) {
        getInitialValues();

        //initializing Players
        playerEx1s = new ArrayList<Player_ex1>();
        for( int i = 1; i <= numberOfPlayers; ++i){
            playerEx1s.add(new Player_ex1(i));
        }

        //initializing Board_ex1
        board = new Board_ex1(playerEx1s,boardSize);

        //initialzing die
        die = new Die_ex1();

        //no round yet
        currentRound = 0;
        winner = null;
    }

    public ArrayList<Player_ex1> getPlayerEx1s() {
        return playerEx1s;
    }

    private void printGameState() {
        String message = "";
        if(winner != null){
            message = "Final state: ";
        }
        else if(currentRound == 0){
            message = "Initial State: ";
        } else {
            message = "Game_ex1 in Round " + Integer.toString(currentRound) + ": ";
        }
        System.out.printf(message);
        board.PrintBoard();
    }

    private void printAfterRoll(Player_ex1 playerEx1, int roll){
        System.out.printf(playerEx1.getName() + " rolls " + Integer.toString(roll) + ": ");
        board.PrintBoard();
    }

    private void playTurn(Player_ex1 playerEx1){
        int diceRoll = die.roll();
        board.MoveFig(playerEx1,diceRoll);
        printAfterRoll(playerEx1,diceRoll);
    }

    private boolean didWin(){
        for(int i = 0; i < numberOfPlayers; ++i){
            if(playerEx1s.get(i).getWinner()){
                winner = playerEx1s.get(i);
                return true;
            }
        }
        return false;
    }

    private void play() {
        int currentPlayer = 0;
        printGameState();
        while(!didWin()){
            playTurn(playerEx1s.get(currentPlayer));
            currentPlayer = (currentPlayer + 1)%numberOfPlayers;
            currentRound += 1;
        }
        printGameState();
        System.out.println(winner.getName() + " wins!");
    }

    public static void main(String[] args) {
        Game_ex1 game = new Game_ex1();
        game.play();
    }
}