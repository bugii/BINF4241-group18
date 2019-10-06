import java.util.ArrayList;

public class Board {

    private ArrayList<Field> gameboard = new ArrayList<>();
    private ArrayList<Player> players;
    int size;

    public Board (ArrayList<Player> players, int size) {
        this.players = players;
        this.size = size;
    }

    public void MakeBoard () {
        Start firstF = new Start(1,players);
        gameboard.add(firstF);
        for (int i=2;i<size;i++) {
            Field nextF = new Field(i, firstF);
            gameboard.add(nextF);
        }
        Goal lastF = new Goal(size);
        gameboard.add(lastF);
        //add ladders & snakes
    }

    public void MoveFig (Player fig, int dist) {
        //Moves Figure
    }

    public void PrintBoard () {
        for (Field f : gameboard){
            f.printField();
        }
    }
}
