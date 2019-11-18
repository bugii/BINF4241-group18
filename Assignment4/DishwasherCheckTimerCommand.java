public class DishwasherCheckTimerCommand implements Command {

    private Dishwasher dishwasher;

    public DishwasherCheckTimerCommand(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {
        dishwasher.checkTimer();
    }

    @Override
    public String getName() {
        return "check timer";
    }
}
