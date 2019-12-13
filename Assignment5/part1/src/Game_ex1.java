import java.util.ArrayList;
import java.util.Scanner;

public class Game_ex1 {
    private int numberOfPlayers = 0;
    public int boardSize = 0;
    private ArrayList<Player_ex1> playerEx1s;
    private Board_ex1 board;
    private int currentRound = 0;
    private Die_ex1 die;
    private Player_ex1 winner;
    public ArrayList<String> playerNames;

    //aks the initial field size and player amount of user, as well as player names
    private void getInitialValues(){
        String input;
        //#playerEx1s
        System.out.println("How many players will participate?");
        Scanner scanner = new Scanner(System.in);
        while(numberOfPlayers == 0){
            input = scanner.nextLine();
            if(input.matches("\\d+")){ //check if input is a positive integer
                numberOfPlayers = Integer.parseInt(input, 10);
                if(numberOfPlayers < 2 || numberOfPlayers > 4){
                    numberOfPlayers = 0;
                    System.out.println("You must have 2-4 players... How many players will participate?");
                }
            }
            else{
                System.out.println("Invalid input... How many players will participate?");
            }
        }

        //#Fields
        System.out.println("How Big do you want the field to be?");
        while (boardSize == 0){
            input = scanner.nextLine();
            if(input.matches("\\d+")){
                boardSize = Integer.parseInt(input, 10);
                if (boardSize < 2){
                    boardSize = 0;
                    System.out.println("Minimum size = 2... How Big do you want the field to be?");
                }
            }
            else{
                System.out.println("Invalid input... How Big do you want the field to be?");
            }
        }

        //#Names:
        playerNames = new ArrayList<>();
        for(int i = 1; i <= numberOfPlayers; i++){
            System.out.println("Player " + i + " please state your name:");
            input = scanner.nextLine();
            while(playerNames.contains(input) || input.isEmpty()){
                System.out.println("Name is already in use, please try another...");
                input = scanner.nextLine();
            }
            playerNames.add(input);
        }
    }

    //Initiate all game Objects by using user input
    public Game_ex1( ) {
        getInitialValues();

        //initializing Players
        playerEx1s = new ArrayList<Player_ex1>();
        for(String name : playerNames){
            playerEx1s.add(new Player_ex1(name));
        }

        //initializing Board_ex1
        board = new Board_ex1(playerEx1s,boardSize);

        //initialzing die
        die = new Die_ex1();

        //no round yet
        currentRound = 0;
        winner = null;
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
        System.out.print(message);
        board.PrintBoard();
    }

    private void printAfterRoll(Player_ex1 playerEx1, int roll){
        System.out.print(playerEx1.getName() + " rolls " + Integer.toString(roll) + ": ");
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

    protected void play() {
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