public class Goal extends Field {

    public Goal(int fieldNumber) {
        super(fieldNumber);
    }

    @Override
    public Field putOn(Player player) {
        this.addPlayer(player);
        player.setField(this);
        player.setWinner();
        return this;
    }
}
