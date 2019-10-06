import java.util.ArrayList;
import java.util.Random;

public class Board {

    private ArrayList<Field> gameboard = new ArrayList<>();
    private ArrayList<Player> players;
    int size;

    public Board (ArrayList<Player> players, int size) {
        this.players = players;
        this.size = size;
    }
        private Snake MakeSnake(int n, Start f){
        Snake s = new Snake(n, f);
        int d = n - (new Random().nextInt(n-1) +1);
        if (d < 1){ d = 1; } //technically shouldn't be needed, but still here for safety
        Field desti = gameboard.get(d-1);
        s.setDestination(desti);
        return s;
    }
    private Ladder MakeLadder(int n, Start f){
        Ladder l = new Ladder(n, f);
        int d = n + (new Random().nextInt(size-n) +1);
        if (d > size){ d=size; }
        Field desti = gameboard.get(d-1);
        l.setDestination(desti);
        return l;
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
            //Now make Snakes and Ladders, every 5th field = Ladder, every 7th = Snake, Snakes have priority
            for (int i = 2; i < size; i++) {
                 if(i % 7 == 0){
                     Snake s = MakeSnake(i, firstF);
                     gameboard.set(i-1, s);
                 }
                 else if (i % 5 == 0){
                     Ladder l = MakeLadder(i, firstF);
                     gameboard.set(i-1, l);
                 }
            }
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
