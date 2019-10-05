public class Player {

    private String name;
    private Field field;
    private Boolean winner = false;

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

    public void setWinner() {
        this.winner = true;
    }
}
