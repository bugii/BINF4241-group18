package Assignment3.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board implements Observer {

    private static Board uniqueInstance = new Board();

    public static Board getInstance() {
        return uniqueInstance;
    }

    private ArrayList<ArrayList<Field>> boardList;

    private Board() {
        // initialize empty board 2D Array
        this.boardList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ArrayList<Field> row = new ArrayList<>();
            boardList.add(row);
            for (int j = 0; j < 8; j++) {
                Field field = new Field(new FieldNumber(i+1, j+1));
                row.add(field);
            }
        }
    }

    public Field getField(FieldNumber fn) {
        try {
            return this.boardList.get(fn.getCharAsInt()-1).get(fn.getInt()-1);
        } catch (Exception e) {
            return null;
        }
    }

    public Field getField(int i, int j) {
        try {
            return this.boardList.get(i-1).get(j-1);
        } catch (Exception e) {
            return null;
        }
    }

    public void printBoard() {
        FieldNumber beginning = new FieldNumber(1,8);
        Iterable<FieldNumber> down = beginning.lineDown();

        //first line
        System.out.print("[");
        if(this.getField(beginning).isOccupied()){
            System.out.print(this.getField(beginning).byWhom().getName());
        } else {
            System.out.print("  ");
        }
        System.out.print("]");
        for(FieldNumber i:beginning.lineRight()){
            System.out.print("[");
            if(this.getField(i).isOccupied()){
                System.out.print(this.getField(i).byWhom().getName());
            } else {
                System.out.print("  ");
            }
            System.out.print("]");
        }
        System.out.print("\n");

        //rest
        for(FieldNumber j:down){
            System.out.print("[");
            if(this.getField(j).isOccupied()){
                System.out.print(this.getField(j).byWhom().getName());
            } else {
                System.out.print("  ");
            }
            System.out.print("]");
            for(FieldNumber i:j.lineRight()){
                System.out.print("[");
                if(this.getField(i).isOccupied()){
                    System.out.print(this.getField(i).byWhom().getName());
                } else {
                    System.out.print("  ");
                }
                System.out.print("]");
            }
            System.out.print("\n");

        }
        for(int i = 1; i < 9; ++i){
            System.out.print(" " + (char)(i+64) + "  ");
        }
        System.out.print("\n");
    }

    public void placeFigurine (Figure fig,int i, int j){
        this.getField(i, j).placeFigurine(fig);
    }

    @Override
    public void update() {
        this.printBoard();
    }

    @Override
    public void update(String figure) {
        this.printBoard();
    }
}
