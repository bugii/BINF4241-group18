import java.util.Scanner;

public class Player {

    private String name;
    private Boolean winner = false;
    private Field myField; //will get rid of this, as soon as it's no longer needed

    public Player (int playerNumber){
        System.out.println("Player " + Integer.toString(playerNumber) + " what is your name?: ");
        Scanner scanner = new Scanner(System.in);
        this.name = scanner.nextLine();
        winner = false;
    }

    public Field getField() { //provisorisch
        return myField;
    }

    public void setField(Field myField) { //provisorisch
        this.myField = myField;
    }

    public Player(String name) {
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
