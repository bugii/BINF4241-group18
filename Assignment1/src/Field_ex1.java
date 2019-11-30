import java.util.ArrayList;

public class Field_ex1 {

    private int fieldNumber;
    private ArrayList<Player_ex1> playerEx1s = new ArrayList<>();
    private Field_ex1 startField;

    public Field_ex1(int fieldNumber, Field_ex1 startField) {
        this.fieldNumber = fieldNumber;
        this.startField = startField;
    }

    public Field_ex1(int fieldNumber, ArrayList<Player_ex1> playerEx1s) {
        this.fieldNumber = fieldNumber;
        //this.playerEx1s = playerEx1s; Will create a direct link, so if you delete something here, will also be deleted in calling array
        this.playerEx1s = new ArrayList<Player_ex1>(playerEx1s);
    }

    public Field_ex1(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public ArrayList<Player_ex1> getPlayerEx1s() {
        return playerEx1s;
    }

    public void addPlayer(Player_ex1 playerEx1) {
        this.playerEx1s.add(playerEx1);
    }

    public void printField() {
        System.out.printf("[%d", this.getFieldNumber());
        for (Player_ex1 playerEx1 : this.getPlayerEx1s()) {
            System.out.printf("<%s>", playerEx1.getName());
        }
        System.out.print("]");
    }

    public Field_ex1 putOn(Player_ex1 playerEx1) {
        if (this.playerEx1s.size() < 1) {
            this.addPlayer(playerEx1);
            playerEx1.setField(this);
            return this;
        }
        else {
            return this.startField.putOn(playerEx1);
        }
    }
}
