public class DishwasherStopCommand implements Command {

    private Dishwasher dishwasher;

    public DishwasherStopCommand(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {
        dishwasher.stop();
    }

    @Override
    public String getName() {
        return "stop";
    }
}
