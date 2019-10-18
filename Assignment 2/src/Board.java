import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {

    private ArrayList<ArrayList<Field>> board;

    public Board(){
        // initialize empty board 2D Array
        this.board = new ArrayList<>();
        for(int i=0; i<8; i++){
            ArrayList<Field> row = new ArrayList<>();
            board.add(row);
            for (int j=0; j<8; j++){
                Field field = new Field(new FieldNumber(i+1, j+1));
                row.add(field);
            }
        }
    }

    public Field getField(FieldNumber fn) {
        return this.board.get(fn.getCharAsInt()-1).get(fn.getInt()-1);
    }

    public Field getField(int i, int j) {
        return this.board.get(i-1).get(j-1);
    }

    public void printBoard(){
        // make copy of board before reversing it (A1 on the board should be bottom left corner)
        ArrayList<ArrayList<Field>> copiedBoard = new ArrayList<>(this.board);
        Collections.reverse(copiedBoard);

        for (ArrayList<Field> row : copiedBoard) {
            for (Field field : row) {
                FieldNumber fn = field.getFieldNumber();
                System.out.printf("[" + String.valueOf(fn.getCharacter()) + fn.getInt() + "]");
            }
            System.out.printf("\n");
        }
    }

    public void placeFigurine(Figure fig, int i, int j) {
        this.getField(i,j).placeFigurine(fig);
    }
}
