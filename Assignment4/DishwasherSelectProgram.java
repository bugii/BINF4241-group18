import java.util.Scanner;

public class DishwasherSelectProgram implements Command {

    private Dishwasher dishwasher;

    public DishwasherSelectProgram(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {
        dishwasher.selectProgram();
    }

    @Override
    public String getName() {
        return "select program";
    }


}
