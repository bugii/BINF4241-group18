package Assignment2;

import java.util.ArrayList;
import java.util.Arrays;

public class Pawn implements Figure{
    private Field myField;
    private Color color;
    private FieldNumber fieldNumber;
    private Board board;
    private boolean moved;
    private int firstMoveTime;

    public Pawn() {
    }

    public Pawn (Field field, Color color, Board board){
        this.myField = field;
        this.color = color;
        this.fieldNumber = field.getFieldNumber();
        this.board = board;
        moved = false;
        field.placeFigurine(this);
    }

    //array mix: [0]=FigureType, [1] = FigureChar, [2]=FigureInt, [3]=goalChar, [4]=goalint, [5] = eating, [6] = check, [7] = transforming into smoething beautiful
    private ArrayList<Character> distill(String stringOriginal){
        String string = new String(stringOriginal);
        string = string.toUpperCase();
        ArrayList<Character> comands = new ArrayList<>(Arrays.asList('\u0000','\u0000','\u0000','\u0000','\u0000','\u0000','\u0000','\u0000'));
        if(string.length() > 0){ comands.set(0,string.charAt(0) );}

        //cheking for the evolution of the pawn into a butterfly
        char tr = string.charAt(stringOriginal.length()-1);
        switch (tr){
            case 'Q': comands.set(7,tr); break;
            case 'B': comands.set(7,tr); break;
            case 'N': comands.set(7,tr); break;
            case 'T': comands.set(7,tr); break;
        }
        if( ! comands.get(7).equals('\u0000')){
            string = string.substring(0,string.length()-1);
        }

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
            if(string.charAt(1) == 'X'){
                comands.set(5,'x');
                string = string.charAt(0) + string.substring(2,5);
            }
            else if(string.charAt(2) == 'X'){
                comands.set(5,'x');
                string = string.substring(0,2) + string.substring(3,5);
            }
            else if(string.charAt(4) == '+' || string.charAt(4) == '#'){
                comands.set(6,string.charAt(4));
                string = string.substring(0,4);
            }
            else if(Character.isLetter(string.charAt(1) )){
                comands.set(1,string.charAt(1));
                string = string.charAt(0) + string.substring(2,5);
            }
            else {
                comands.set(2,string.charAt(1));
                string = string.charAt(0) + string.substring(2,5);
            }
        }
        if(string.length() == 4){
            if(string.charAt(1) == 'X'){
                comands.set(5,'x');
                string = string.charAt(0) + string.substring(2,4);
            }
            else if(string.charAt(3) == '+' || string.charAt(3) == '#'){
                comands.set(6,string.charAt(3));
                string = string.substring(0,3);
            }
            else if(Character.isLetter(string.charAt(1) )){
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
            if(goalField.isOccupied()) {
                Figure eatenFigurine = goalField.byWhom();
                goalField.removeFigurine();
                player.addToEaten(eatenFigurine);
            }
            else { //enpassant
                FieldNumber eatenFieldNumber = new FieldNumber(goalField.getFieldNumber().getCharacter(),goalField.getFieldNumber().getInt()-color.moveDirection());
                Field eatenField = board.getField(eatenFieldNumber);
                Figure eatenFigurine = eatenField.byWhom();
                eatenField.removeFigurine();
                player.addToEaten(eatenFigurine);
            }
        }

        if(Math.abs(fieldNumber.getInt()-goalField.getFieldNumber().getInt()) == 2){
            firstMoveTime = turnNumber;
        }

        myField.removeFigurine();
        goalField.placeFigurine(this);

        moved = true;

        if(comands.get(7) != '\u0000'){
            Figure newIdentity = this;  //this is not just a phase-mom!
            System.out.println("Whats this? \"Pawn\" is evolving");
            myField.removeFigurine();
            Field myOldField = myField;
            myField = myField = null;
            fieldNumber = null;
            switch (comands.get(7)){
                case 'Q': newIdentity= new Queen(myOldField,this.color,board); break;
                case 'N': newIdentity= new Knight(myOldField,this.color,board); break;
                case 'B': newIdentity= new Bishop(myOldField,this.color,board); break;
                case 'T': newIdentity= new Tower(myOldField,this.color,board); break;
            }
            System.out.println("Pawn evolved into "+ newIdentity.getClass().toString());
            player.transformPawn(this,newIdentity); //this is the end for our old pawn. May a flight of angels take you to your grave
        }
    }

    @Override
    public boolean canPerformMove(String commandOriginal, int turNumber) {

        ArrayList<Character> command= this.distill(commandOriginal);

        //check if correct figurine Type
        if(command.get(0) != 'P'){
            return false;
        }
        //check if correct figurine placement
        if( !( (command.get(1) == '\u0000' || command.get(1) == fieldNumber.getCharacter()) && (command.get(2) == '\u0000' || ((int)command.get(2))-48 == fieldNumber.getInt()) ) ){
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

        if( !( Math.abs(row_diff) == 1 || (Math.abs(row_diff) == 2 && !moved) ) ){
            return false;
        }

        //some variables
        Field goalField = null;
        Iterable<FieldNumber> direction;

        //right
        if(color.moveDirection() == row_diff && col_diff == 0 && command.get(5) != 'x'){ //just one step forward
            goalField = board.getField(new FieldNumber(fieldNumber.getCharacter(),fieldNumber.getInt()+color.moveDirection()));
        }
        else if(color.moveDirection()*2 == row_diff && col_diff == 0 && command.get(5) != 'x' && moved == false){ //two steps forward
            if(board.getField(new FieldNumber(fieldNumber.getCharacter(),fieldNumber.getInt()+color.moveDirection())).isOccupied() == false ){
                goalField = board.getField(new FieldNumber(fieldNumber.getCharacter(),fieldNumber.getInt()+color.moveDirection()*2));
            }
        }
        else if(color.moveDirection() == row_diff && command.get(5) == 'x' && Math.abs(col_diff) == 1){
            goalField = board.getField(new FieldNumber(fieldNumber.getCharAsInt()+col_diff,fieldNumber.getInt()+color.moveDirection()));
        }
        else {
            return false;
        }

        //check if still no field
        if(goalField == null){
            return false;
        }
        //check if eating/not Eating match
        if(command.get(5) == 'x' && !goalField.isOccupied()){ //check for enpassant
            FieldNumber eatenFieldNumber = new FieldNumber(goalField.getFieldNumber().getCharacter(),goalField.getFieldNumber().getInt()-color.moveDirection());
            Field eatenField = board.getField(eatenFieldNumber);
            if(eatenField.isOccupied()) {
                Figure enPasfigure = eatenField.byWhom();
                if(enPasfigure.getColor() != this.color && enPasfigure instanceof Pawn && ((Pawn) enPasfigure).getFirstMoveTime() == turNumber-1){
                    return true;
                }
            }
        }

        if ( !( (command.get(5) == 'x' && goalField.isOccupied() && goalField.byWhom().getColor() != color) || (command.get(5) != 'x' && !goalField.isOccupied())) ){
            return false;
        }

        //chech for transformation
        if(command.get(7) != '\u0000'){
            if(zahl != 8 && zahl != 1){
                return false;
            }
        }
        else if(zahl == 8 || zahl == 1){
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
        return ((color==Color.BLACK)?"B":"W")+"P";
    }

    public int getFirstMoveTime(){
        return firstMoveTime;
    }

    @Override
    public boolean canEatKing(King king, int turnNumber) {
        String command = "T";
        command = command +  'x' + king.getFieldNumber().getCharacter()+ "king.getFieldNumber().getInt()+48";
        return this.canPerformMove(command,turnNumber);
    }
}
