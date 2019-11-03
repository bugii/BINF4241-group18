package Assignment1;

import java.util.ArrayList;

public class Start_ex1 extends Field_ex1 {

    public Start_ex1(int fieldNumber, ArrayList<Player_ex1> playerEx1s) {
        super(fieldNumber, playerEx1s);
    }

    @Override
    public Field_ex1 putOn(Player_ex1 playerEx1) {
        // is always possible to land on
        this.addPlayer(playerEx1);
        playerEx1.setField(this);
        return this;
    }
}
