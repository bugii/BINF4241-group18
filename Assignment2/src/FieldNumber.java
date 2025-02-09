import java.util.ArrayList;

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

    public Iterable<FieldNumber> lineLeft() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = character - 1; i > 0; i--) {
            list.add(new FieldNumber(i, number));
        }
        return list;
    }

    public Iterable<FieldNumber> lineRight() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = character + 1; i < 9; i++) {
            list.add(new FieldNumber(i, number));
        }
        return list;
    }

    public Iterable<FieldNumber> lineUp() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = number + 1; i < 9; i++) {
            list.add(new FieldNumber(character,i));
        }
        return list;
    }

    public Iterable<FieldNumber> lineDown() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = number - 1; i > 0; i--) {
            list.add(new FieldNumber(character,i));
        }
        return list;
    }

    public Iterable<FieldNumber> upRight() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = 1; (number+i)<9&&(character+i)<9; i++) {
            list.add(new FieldNumber(character+i,number+i));
        }
        return list;
    }

    public Iterable<FieldNumber> upLeft() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = 1; (number+i)<9 && (character-i)>0; i++) {
            list.add(new FieldNumber(character-i,number+i));
        }
        return list;
    }

    public Iterable<FieldNumber> downRight() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = 1; (number-i)>0 && (character+i)<9; i++) {
            list.add(new FieldNumber(character+i,number-i));
        }
        return list;
    }

    public Iterable<FieldNumber> downLeft() {
        ArrayList<FieldNumber> list = new ArrayList<FieldNumber>();
        for (int i = 1; (number-i)>0 && (character-i)>0; i++) {
            list.add(new FieldNumber(character-i,number-i));
        }
        return list;
    }

    public Boolean equals(FieldNumber fieldNumber){
        return number ==fieldNumber.getInt() && character == fieldNumber.getCharAsInt();
    }

    public void printFieldNumber() {
        System.out.printf(String.valueOf(this.getCharacter()), this.number);
    }
}
