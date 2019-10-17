public class Field {

    public FieldNumber getFieldNumber(){
        return null;
    }

    public Boolean isOccupied(){
        return false;
    }

    public Figure byWhom(){
        return null;
    }

    //places Figurine on Field. If Field is already occupied returns False. Changes also the myField variable in figurine
    public Boolean placeFigurine(Figure figure){
        return false;
    }

    //removes Figurine from Field. Also changes the myField variable in figurine to null
    public void removeFigruine(){
        return;
    }
}
