import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Field> gameBoard;
    private int currentRound = 0;

    public Game(ArrayList<Player> players, ArrayList<Field> gameBoard) {
        this.players = players;
        this.gameBoard = gameBoard;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Field> getGameBoard() {
        return gameBoard;
    }

    public Field getField(int fieldNumber) {
        return gameBoard.get(fieldNumber);
    }

    private void printBoard() {
        for (Field field : this.gameBoard) {
            field.printField();
        }
        System.out.println();
    }

    private void play() {
        // print initial state
        System.out.print("Initial state: ");
        this.printBoard();

        while (true) {
            // get the current player
            int currentPlayerIndex = this.currentRound % this.players.size();
            Player currentPlayer = players.get(currentPlayerIndex);

            int rolledNumber = Die.roll();
            System.out.printf("%s rolled a %d: ", currentPlayer.getName(), rolledNumber);

            // remove player from current field
            Field currentField = currentPlayer.getField();
            currentField.getPlayers().remove(currentPlayer);

            // Figure out the correct destination field
            int destinationFieldNumber = currentField.getFieldNumber() + rolledNumber;

            // simplified winning condition
            if (destinationFieldNumber >= this.gameBoard.size()) {
                currentPlayer.setWinner();
                System.out.println(currentPlayer.getName() + " has won!");
                break;
            }

            // move the player
            Field destinationField = gameBoard.get(destinationFieldNumber - 1);
            destinationField.putOn(currentPlayer);

            this.printBoard();

            this.currentRound++;
        }
    }

    public static void main(String[] args) {
        Player dario = new Player("Dario");
        Player nora = new Player("Nora");

        // Add player to the list
        ArrayList<Player> players = new ArrayList<>();
        players.add(dario);
        players.add(nora);

        ArrayList<Player> startingPlayers = new ArrayList<>();
        startingPlayers.add(dario);
        startingPlayers.add(nora);

        ArrayList<Field> gameBoard = new ArrayList<>();

        Field field1 = new Start(1, startingPlayers);
        gameBoard.add(field1);
        dario.setField(field1);
        nora.setField(field1);
        Ladder field2 = new Ladder(2, field1);
        gameBoard.add(field2);
        Field field3 = new Field(3, field1);
        gameBoard.add(field3);
        Snake field4 = new Snake(4, field1);
        gameBoard.add(field4);
        Field field5 = new Field(5, field1);
        gameBoard.add(field5);
        Field field6 = new Field(6, field1);
        gameBoard.add(field6);
        Ladder field7 = new Ladder(7, field1);
        gameBoard.add(field7);
        Snake field8 = new Snake(8, field1);
        gameBoard.add(field8);
        Field field9 = new Field(9, field1);
        gameBoard.add(field9);
        Field field10 = new Field(10, field1);
        gameBoard.add(field10);
        Field field11 = new Field(11, field1);
        gameBoard.add(field11);
        Field field12 = new Goal(12);
        gameBoard.add(field12);

        // add destinations of ladders/snakes
        field2.setDestination(field4);
        field4.setDestination(field1);
        field7.setDestination(field10);
        field8.setDestination(field6);

        // create game and start it
        Game game = new Game(players, gameBoard);
        game.play();
    }
}