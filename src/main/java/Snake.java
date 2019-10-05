public class Snake extends Field {

    private Field destination;

    public Snake(int fieldNumber, Field startField) {
        super(fieldNumber, startField);
    }

    public Field getDestination() {
        return destination;
    }

    public void setDestination(Field destination) {
        this.destination = destination;
    }

    @Override
    public Field putOn(Player player) {
        // put the player on the destination field
        return destination.putOn(player);
    }

    @Override
    public void printField() {
        System.out.printf("[%d<-%d]", this.getDestination().getFieldNumber(), this.getFieldNumber());
    }
}

