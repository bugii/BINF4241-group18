public class Bishop implements  Figure{
    Field myField;
    Color color;
    FieldNumber fieldNumber;
    Board board;

    @Override
    public void perfromMove(String command, Player player, int turnNumber) {

    }

    @Override
    public boolean canPerformMove(String command) {
        if(command.length() < 3 &&command.charAt(0) != 'B'){
            return false;
        }
        int charac = ((int)command.charAt(1))-64;
        int zahl = command.charAt(2);

        //check not on my own field or not on diagonal
        if(zahl == myField.getFieldNumber().getCharacter() ){
            return false;
        }
        int col_diff = charac - fieldNumber.getCharAsInt();
        int row_diff = zahl - fieldNumber.getInt();
        if( col_diff/row_diff != 1 && col_diff/row_diff != -1){
            return false;
        }

        //right
        if(col_diff > 0){

            if(row_diff > 0){ //upperRight

                for(FieldNumber fn :fieldNumber.upRight()){
                    Field currentField = board.get
                }

            } else { //lowerRight

            }

        } else { //left

            if(row_diff > 0){ //upperLeft
            } else { //lowerLeft

            }

        }

        return false;
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
    public FieldNumber getFieldNumber() {
        return myField.getFieldNumber();
    }

    @Override
    public String getName() {
        return ((color==Color.BLACK)?"B":"W")+"B";
    }

    void setMyField(Field field){
        myField = field;
        fieldNumber = field.getFieldNumber();
    }
}
