import java.util.ArrayList;

public class Field {

    private int fieldNumber;
    private ArrayList<Player> players;

    public Field(int fieldNumber) {
        this.fieldNumber = fieldNumber;
        this.players = new ArrayList<>();
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
}
