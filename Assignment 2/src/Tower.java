import java.util.ArrayList;
import java.util.Arrays;

public class Tower implements Figure{
    private Field myField;
    private Color color;
    private FieldNumber fieldNumber;
    private Board board;
    private boolean moved;

    public Tower() {
    }

    public Tower (Field field, Color color, Board board){
        this.myField = field;
        this.color = color;
        this.fieldNumber = field.getFieldNumber();
        this.board = board;
        moved = false;
        field.placeFigurine(this);
    }

    //array mix: [0]=FigureType, [1] = FigureChar, [2]=FigureInt, [3]=goalChar, [4]=goalint, [5] = eating, [6] = check
    private ArrayList<Character> distill(String stringOriginal){
        String string = new String(stringOriginal);
        ArrayList<Character> comands = new ArrayList<>(Arrays.asList('\u0000','\u0000','\u0000','\u0000','\u0000','\u0000','\u0000'));
        if(string.length() > 0){ comands.set(0,string.charAt(0) );}

        if(string.length() == 7){
            comands.set(6,string.charAt(6));
            string = string.substring(0,6);
        }
        if(string.length() == 6) {
            if(string.charAt(5) == '+' || string.charAt(5) == '#'){
                comands.set(6,string.charAt(5));
                string = string.substring(0,5);
            }
            else {
                comands.set(1,string.charAt(1));
                string = string.charAt(0) + string.substring(2,6);
            } //only 1 can be missing
        }
        if(string.length() == 5) {
            if(string.charAt(1) == 'x'){
                comands.set(5,'x');
                string = string.charAt(0) + string.substring(2,5);
            }
            else if(string.charAt(2) == 'x'){
                comands.set(5,'x');
                string = string.substring(0,2) + string.substring(3,5);
            }
            else if(string.charAt(4) == '+' || string.charAt(4) == '#'){
                comands.set(6,string.charAt(4));
                string = string.substring(0,4);
            }
            else if(Character.isAlphabetic(string.charAt(1) )){
                comands.set(1,string.charAt(1));
                string = string.charAt(0) + string.substring(2,5);
            }
            else {
                comands.set(2,string.charAt(1));
                string = string.charAt(0) + string.substring(2,5);
            }
        }
        if(string.length() == 4){
            if(string.charAt(1) == 'x'){
                comands.set(5,'x');
                string = string.charAt(0) + string.substring(2,4);
            }
            else if(string.charAt(3) == '+' || string.charAt(3) == '#'){
                comands.set(6,string.charAt(3));
                string = string.substring(0,3);
            }
            else if(Character.isAlphabetic(string.charAt(1) )){
                comands.set(1,string.charAt(1));
                string = string.charAt(0) + string.substring(2,4);
            }
            else {
                comands.set(2,string.charAt(1));
                string = string.charAt(0) + string.substring(2,4);
            }
        }
        if(string.length() == 3){
            comands.set(3,string.charAt(1));
            comands.set(4,string.charAt(2));
        }

        return comands;
    }

    @Override
    public void perfromMove(String command, Player player, int turnNumber) {
        ArrayList<Character> comands = this.distill(command);
        Field goalField = board.getField(new FieldNumber(comands.get(3),((int)comands.get(4))-48));
        if(comands.get(5) == 'x'){
            Figure eatenFigurine = goalField.byWhom();
            goalField.removeFigurine();
            player.addToEaten(eatenFigurine);
        }

        myField.removeFigurine();
        goalField.placeFigurine(this);

        moved = true;
    }

    @Override
    public boolean canPerformMove(String commandOriginal) {

        ArrayList<Character> command= this.distill(commandOriginal);

        //check if correct figurine Type
        if(command.get(0) != 'T'){
            return false;
        }
        //check if correct figurine placement
        if( !( (command.get(1) == '\u0000' || command.get(1) == fieldNumber.getCharacter()) && (command.get(2) == '\u0000' || ((int)command.get(2))-80 == fieldNumber.getInt()) ) ){
            return false;
        }

        int charac = ((int)command.get(3))-64;
        int zahl = ((int)command.get(4))-48;

        //check not on my own field or not on diagonal
        if(zahl == myField.getFieldNumber().getCharacter() && zahl == fieldNumber.getInt()){
            return false;
        }
        int col_diff = charac - fieldNumber.getCharAsInt();
        int row_diff = zahl - fieldNumber.getInt();

        if( col_diff * row_diff != 0){
            return false;
        }
        if( col_diff == 0 && row_diff == 0){
            return false;
        }

        //some variables
        Field goalField = null;
        Iterable<FieldNumber> direction;

        //right

        if(col_diff == 0){  //vertical

            if(row_diff > 0){
                direction = fieldNumber.lineUp();
            } else {
                direction = fieldNumber.lineDown();
            }

        }
        else { //horizontal
            if(col_diff > 0){
                direction = fieldNumber.lineRight();
            } else {
                direction = fieldNumber.lineLeft();
            }
        }
        for(FieldNumber i:direction){
            if(i.equals(new FieldNumber(charac,zahl))){
                goalField = board.getField(i);
                break;
            }
            if(board.getField(i).isOccupied()){
                return false;
            }
        }

        //security check, sould not be needed
        if(goalField == null){
            return false;
        }

        //check if eating/not Eating match
        if ( !( (command.get(5) == 'x' && goalField.isOccupied() && goalField.byWhom().getColor() != color) || (command.get(5) != 'x' && !goalField.isOccupied())) ){
            return false;
        }

        //does not check for check or checkMate yet

        return true;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Field getField() {
        return myField;
    }

    @Override
    public void setField(Field field) {
        this.myField = field;
        this.fieldNumber = field.getFieldNumber();
    }

    @Override
    public FieldNumber getFieldNumber() {
        return myField.getFieldNumber();
    }

    @Override
    public String getName() {
        return ((color==Color.BLACK)?"B":"W")+"T";
    }
}
