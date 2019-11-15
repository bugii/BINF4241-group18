public class DishwasherSwitchOnCommand implements Command {

    private Dishwasher dishwasher;

    public DishwasherSwitchOnCommand(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {
        dishwasher.setSwitchedOn(true);
        System.out.println("Dishwasher switched on");
    }

    @Override
    public String getName() {
        return "switch on";
    }
}
