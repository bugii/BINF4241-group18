package Assignment2;

public enum Color {
    BLACK, WHITE;

    public int moveDirection(){
        if(this == BLACK){
            return -1;
        }
        else{
            return 1;
        }
    }
}
