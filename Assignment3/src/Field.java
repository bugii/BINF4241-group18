package Assignment3.src;

public class Field {

    private Boolean isOccupied = false;
    private Figure figure;
    private FieldNumber fieldNumber;

    public Field(FieldNumber fn){
        this.fieldNumber = fn;
        this.isOccupied = false;
    }

    public FieldNumber getFieldNumber(){
        return this.fieldNumber;
    }

    public Boolean isOccupied(){
        return this.isOccupied;
    }

    public Figure byWhom(){
        return figure;
    }

    //places Figurine on Field. If Field is already occupied returns False. Changes also the myField variable in figurine
    public Boolean placeFigurine(Figure figure){
        if(this.isOccupied) {
            System.out.println("This field is already occupied");
            return false;
        }
        else{
            this.figure = figure;
            figure.setField(this);
            this.isOccupied = true;
            return true;
        }
    }

    //removes Figurine from Field. Also changes the myField variable in figurine to null
    public void removeFigurine(){
        this.isOccupied = false;
        this.figure = null;
    }
}
