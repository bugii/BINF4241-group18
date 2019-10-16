public class Goal_ex1 extends Field_ex1 {

    public Goal_ex1(int fieldNumber) {
        super(fieldNumber);
    }

    @Override
    public Field_ex1 putOn(Player_ex1 playerEx1) {
        this.addPlayer(playerEx1);
        playerEx1.setField(this);
        playerEx1.setWinner();
        return this;
    }
}
