import java.util.ArrayList;
import java.util.Iterator;

public class FieldNumber {
    private int number;
    private int character;

    public FieldNumber (char a, int b){
        number = b;
        character = ((int)a)-64;
    }

    public FieldNumber (int charac, int num){
        character = charac;
        number = num;
    }

    public FieldNumber (FieldNumber fieldNumber){
        number =fieldNumber.getInt();
        character = fieldNumber.getCharacter();
    }

    public int getInt(){
        return number;
    }

    public char getCharacter(){
        return (char)(character+64);
    }

    public int getCharAsInt(){
        return character;
    }

    public Iterator<FieldNumber> lineLeft() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = character - 1; i > 0; i--) {
            list.add(new FieldNumber(i, number));
        }
        return list.iterator();
    }

    public Iterator<FieldNumber> lineRight() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = character + 1; i < 9; i++) {
            list.add(new FieldNumber(i, number));
        }
        return list.iterator();
    }

    public Iterator<FieldNumber> lineUp() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = number + 1; i < 9; i++) {
            list.add(new FieldNumber(character,i));
        }
        return list.iterator();
    }

    public Iterator<FieldNumber> lineDown() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = number - 1; i > 0; i--) {
            list.add(new FieldNumber(character,i));
        }
        return list.iterator();
    }
}
