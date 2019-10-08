import java.util.ArrayList;
import java.util.Random;

public class Board {

    private ArrayList<Field> gameboard = new ArrayList<>();
    private ArrayList<Player> players;
    int size;

    public Board (ArrayList<Player> players, int size) {
        this.players = players;
        this.size = size;
        MakeBoard();
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
        Field oldField = gameboard.get(n); //get old field
        Ladder l = new Ladder(n, f);
        int d = n + (new Random().nextInt(size-n) +1);
        if (d > size){ d=size; }
        Field desti = gameboard.get(d-1);
        l.setDestination(desti);

        for(int i = 0; i < size; ++i){
            Field itField = gameboard.get(i);
            if(itField instanceof Snake){
                if(((Snake) itField).getDestination() == oldField){
                    ((Snake) itField).setDestination(l);
                }
            }
        }
        return l;
    }

    private void MakeBoard () {
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
        }
        for (int i = size-1; i > 1; i--) {
            if (i % 5 == 0){
                Ladder l = MakeLadder(i, firstF);
                gameboard.set(i-1, l);
            }

        }
        for (Player p : players) {
            p.setField(firstF);
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
