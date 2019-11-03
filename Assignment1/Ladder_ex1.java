package Assignment1;

public class Ladder_ex1 extends Field_ex1 {

    private Field_ex1 destination;

    public Ladder_ex1(int fieldNumber, Field_ex1 startField) {
        super(fieldNumber, startField);
    }

    public Field_ex1 getDestination() {
        return destination;
    }

    public void setDestination(Field_ex1 destination) {
        this.destination = destination;
    }

    @Override
    public Field_ex1 putOn(Player_ex1 playerEx1) {
        // put the playerEx1 on the destination field
        return destination.putOn(playerEx1);
    }

    @Override
    public void printField() {
        System.out.printf("[%d->%d]", this.getFieldNumber(), this.getDestination().getFieldNumber());
    }
}
