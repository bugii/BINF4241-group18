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
        if (gameboard.isEmpty()) {
            Start firstF = new Start(1, players);
            gameboard.add(firstF);
            for (int i = 2; i < size; i++) {
                Field nextF = new Field(i, firstF);
                gameboard.add(nextF);
            }
            Goal lastF = new Goal(size);
            gameboard.add(lastF);
            //add ladders & snakes
            for (Player p: players) {
                p.setField(firstF);
            }
        }
        else {
            System.out.println("You have already created this board!");
        }
    }
    
    public void MoveFig (Player currPlayer, int distance) {
        //moves player a certain distance on the board
        Field currField = currPlayer.getField();
        int desti = currField.getFieldNumber() + distance;
        if (desti > size){
            desti = size - (desti - size);
        }
        Field newF = gameboard.get(desti-1);
        currField.getPlayers().remove(currPlayer);
        newF.putOn(currPlayer);
    }

    public void PrintBoard () {
        for (Field f : gameboard){
            System.out.print(" ");
            f.printField();
        }
        System.out.println();
    }
}
