import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private ArrayList<Field> gameBoard;
    private int currentRound = 0;
    private Player winner;

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
            System.out.println(field.getFieldNumber() + ": " + field.getPlayers());
        }
    }

    private void play() {
        System.out.printf("Game with %d fields and %d players has just started \n", this.gameBoard.size(), this.players.size());

        while (true) {
            // get the current player
            System.out.println("player array size: " + this.players.size());
            int currentPlayerIndex = this.currentRound % this.players.size();
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.printf("It's %s's turn \n", currentPlayer.getName());

            int rolledNumber = currentPlayer.rollDice();
            Field currentField = currentPlayer.getField();
            int destinationFieldNumber = currentField.getFieldNumber() + rolledNumber;

            // simplified winning condition
            if (destinationFieldNumber >= this.gameBoard.size()) {
                this.winner = currentPlayer;
                System.out.println(currentPlayer.getName() + " has won!");
                break;
            }
            Field destinationField = gameBoard.get(destinationFieldNumber - 1);
            currentPlayer.move(currentField, destinationField);

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

        ArrayList<Field> gameBoard = new ArrayList<>();
        Field field1 = new Field(1);
        field1.addPlayer(dario);
        field1.addPlayer(nora);
        dario.setField(field1);
        nora.setField(field1);
        gameBoard.add(field1);
        // Create as many fields as needed
        for (int i=2; i < 13; i++) {
            Field field = new Field(i);
            gameBoard.add(field);
        }

        Game game = new Game(players, gameBoard);
        game.play();

    }
}