import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int numberOfPlayers;
    private int fieldSize;
    private ArrayList<Player> players;
    private Field field;
    private int currentRound = 0;

    //aks the initial field size and player amount of user
    private void getInitialValues(){
        //#players
        System.out.println("How many players will participate?");
        Scanner scanner = new Scanner(System.in);
        numberOfPlayers = scanner.nextInt();
        while(numberOfPlayers < 1 || numberOfPlayers > 4){
            System.out.println("Invalid number of Players. You need to choose a bumber between 1 and 4. How many players will participate?");
            numberOfPlayers = scanner.nextInt();
        }

        //#Fields
        System.out.println("How Big do you want the field to be?");
        fieldSize = scanner.nextInt();
        while(fieldSize < 2){
            System.out.println("Field to small, please Enter an Integer > 1");
            fieldSize = scanner.nextInt();
        }
    }

    //Initiate all game Objects by using user input
    public Game( ) {
        getInitialValues();

        //initializing Players
        players = new ArrayList<Player>();
        for( int i = 0; i < numberOfPlayers; ++i){
            players.add(new Player(i));
        }

        //initializing Field
        Field field = new Field(fieldSize,players);

        //initialzing die

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    private void printBoard() {
    }

    private void play() {
    }

    public static void main(String[] args) {
    }
}