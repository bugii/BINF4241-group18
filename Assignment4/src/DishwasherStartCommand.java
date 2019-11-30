public class DishwasherStartCommand implements Command {

    private Dishwasher dishwasher;

    public DishwasherStartCommand(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {
        dishwasher.start();
    }

    @Override
    public String getName() {
        return "start";
    }
}
