package Assignment1;

import java.util.Scanner;

public class Player_ex1 {

    private String name;
    private Boolean winner = false;
    private Field_ex1 myField; //will get rid of this, as soon as it's no longer needed

    public Player_ex1(int playerNumber){
        System.out.println("Player_ex1 " + Integer.toString(playerNumber) + " what is your name?: ");
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.nextLine();
        winner = false;
    }

    public Field_ex1 getField() { //provisorisch
        return myField;
    }

    public void setField(Field_ex1 myField) { //provisorisch
        this.myField = myField;
    }

    public Player_ex1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner() {
        this.winner = true;
    }
}
