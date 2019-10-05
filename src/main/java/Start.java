import java.util.ArrayList;

public class Start extends Field{

    public Start(int fieldNumber, ArrayList<Player> players) {
        super(fieldNumber, players);
    }

    @Override
    public Field putOn(Player player) {
        // is always possible to land on
        this.addPlayer(player);
        player.setField(this);
        return this;
    }
}
