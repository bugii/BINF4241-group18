import java.util.ArrayList;
import java.util.Random;

public class Board_ex1 {

    private ArrayList<Field_ex1> gameboard = new ArrayList<>();
    private ArrayList<Player_ex1> playerEx1s;
    int size;

    public Board_ex1(ArrayList<Player_ex1> playerEx1s, int size) {
        this.playerEx1s = playerEx1s;
        this.size = size;
        MakeBoard();
    }
    
    private Snake_ex1 MakeSnake(int n, Start_ex1 f){
        Snake_ex1 s = new Snake_ex1(n, f);
        return s;
    }

    private void setAllsnake(){
        for(int i = 6; i < size-1; i += 7){
            int d = i - (new Random().nextInt(i-1) +1);
            if (d < 1){ d = 1; } //technically shouldn't be needed, but still here for safety
            Field_ex1 desti = gameboard.get(d-1);
            ((Snake_ex1)gameboard.get(i)).setDestination(desti);
        }
    }
    
    private Ladder_ex1 MakeLadder(int n, Start_ex1 f){
        Ladder_ex1 l = new Ladder_ex1(n, f);
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
            Field_ex1 desti = gameboard.get(d-1);
            ((Ladder_ex1)gameboard.get(i)).setDestination(desti);
        }
    }

    private void MakeBoard () {
        Start_ex1 firstF = new Start_ex1(1, playerEx1s);
        gameboard.add(firstF);
        for (int i = 2; i < size; i++) {
            if(i % 7 == 0){
                Snake_ex1 s = MakeSnake(i, firstF);
                gameboard.add(s);
            }
            else if (i % 5 == 0){
                Ladder_ex1 l = MakeLadder(i, firstF);
                gameboard.add(l);
            }
            else {
                Field_ex1 nextF = new Field_ex1(i, firstF);
                gameboard.add(nextF);
            }
        }
        Goal_ex1 lastF = new Goal_ex1(size);
        gameboard.add(lastF);
        //Now make Snakes and Ladders, every 5th field = Ladder_ex1, every 7th = Snake_ex1, Snakes have priority
        setAllLadders();
        setAllsnake();

        for (Player_ex1 p : playerEx1s) {
            p.setField(firstF);
        }
    }
    
    public void MoveFig (Player_ex1 currPlayerEx1, int distance) {
        //moves player a certain distance on the board
        Field_ex1 currField = currPlayerEx1.getField();
        int desti = currField.getFieldNumber() + distance;
        if (desti > size){
            desti = size - (desti - size);
        }
        Field_ex1 newF = gameboard.get(desti-1);
        currField.getPlayerEx1s().remove(currPlayerEx1);
        newF.putOn(currPlayerEx1);
    }

    public void PrintBoard () {
        for (Field_ex1 f : gameboard){
            System.out.print(" ");
            f.printField();
        }
        System.out.println();
    }
}
