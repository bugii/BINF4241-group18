package Assignment1;

import java.util.Random;

public class Die_ex1 {
    public static int roll() {
        int randomNumber = new Random().nextInt(6) + 1;
        return randomNumber;
    }
}
