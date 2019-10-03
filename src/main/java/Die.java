import java.util.Random;

public class Die {
    public static int roll() {
        int randomNumber = new Random().nextInt(6) + 1;
        return randomNumber;
    }
}
