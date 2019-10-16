public class Bishop implements  Figure{
    Field myField;
    Color color;

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

        //check not on my own field
        if(zahl == myField.getFieldNumber().getCharacter()){
            return false;
        }

        //right
        if(charac > myField.getFieldNumber().getCharAsInt()){

            if(zahl > myField.getFieldNumber().getInt()){ //upperRight

            } else { //lowerRight

            }

        } else { //left

            if(zahl > myField.getFieldNumber().getInt()){ //upperLeft

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
}
