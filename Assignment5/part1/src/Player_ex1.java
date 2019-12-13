public class Player_ex1 {

    private String name = "";
    private Boolean winner = false;
    private Field_ex1 myField; //will get rid of this, as soon as it's no longer needed

    public Player_ex1(String playerName){
        this.name = playerName;
        winner = false;
    }

    public Field_ex1 getField() { //provisorisch
        return myField;
    }

    public void setField(Field_ex1 myField) { //provisorisch
        this.myField = myField;
    }

    public String getName() {
        return name;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner() {
        this.winner = true;
    }
}
