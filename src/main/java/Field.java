import java.util.ArrayList;

public class Field {

    private int fieldNumber;
    private ArrayList<Player> players = new ArrayList<>();
    private Field startField;

    public Field(int fieldNumber, Field startField) {
        this.fieldNumber = fieldNumber;
        this.startField = startField;
    }

    public Field(int fieldNumber, ArrayList<Player> players) {
        this.fieldNumber = fieldNumber;
        this.players = players;
    }

    public Field(int fieldNumber) {
        this.fieldNumber = fieldNumber;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void printField() {
        System.out.printf("[%d", this.getFieldNumber());
        for (Player player: this.getPlayers()) {
            System.out.printf("<%s>", player.getName());
        }
        System.out.print("]");
    }

    public Field putOn(Player player) {
        if (this.players.size() < 1) {
            this.addPlayer(player);
            player.setField(this);
            return this;
        }
        else {
            System.out.println("field busy, send back to start");
            startField.putOn(player);
            return this.startField;
        }
    }
}
