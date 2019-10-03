public class Player {

    private String name;
    private Field field;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void move(Field currentField, Field destinationField) {
        this.field = destinationField;
        currentField.getPlayers().remove(this);
        destinationField.addPlayer(this);
        System.out.println(this.name + " moved from field " + (currentField.getFieldNumber()) + " to " + (destinationField.getFieldNumber()));
    }

    public int rollDice() {
        int rolledNumber = Die.roll();
        System.out.printf("%s rolled a %d \n", this.getName(), rolledNumber);
        return rolledNumber;
    }
}
