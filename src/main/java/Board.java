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
        return s;
    }

    private void setAllsnake(){
        for(int i = 6; i < size-1; i += 7){
            int d = i - (new Random().nextInt(i-1) +1);
            if (d < 1){ d = 1; } //technically shouldn't be needed, but still here for safety
            Field desti = gameboard.get(d-1);
            ((Snake)gameboard.get(i)).setDestination(desti);
        }
    }
    
    private Ladder MakeLadder(int n, Start f){
        Ladder l = new Ladder(n, f);
        return l;
    }

    private void setAllLadders(){
        for(int i = 4; i < size-1; i += 5){
            if((i+1)%7==0){
                continue;
            }
            int d = 6;
            while((d+1)%7==0 || d>size) {
                d = i + (new Random().nextInt(size - i) + 1);
            }
            Field desti = gameboard.get(d-1);
            ((Ladder)gameboard.get(i)).setDestination(desti);
        }
    }

    private void MakeBoard () {
        Start firstF = new Start(1, players);
        gameboard.add(firstF);
        for (int i = 2; i < size; i++) {
            if(i % 7 == 0){
                Snake s = MakeSnake(i, firstF);
                gameboard.add(s);
            }
            else if (i % 5 == 0){
                Ladder l = MakeLadder(i, firstF);
                gameboard.add(l);
            }
            else {
                Field nextF = new Field(i, firstF);
                gameboard.add(nextF);
            }
        }
        Goal lastF = new Goal(size);
        gameboard.add(lastF);
        //Now make Snakes and Ladders, every 5th field = Ladder, every 7th = Snake, Snakes have priority
        setAllLadders();
        setAllsnake();

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
