public class DishwasherSwitchOffCommand implements Command {

    private Dishwasher dishwasher;

    public DishwasherSwitchOffCommand(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {
        dishwasher.getCommands().get(4).execute();
        dishwasher.setSwitchedOn(false);
        System.out.println("Switched off");
    }

    @Override
    public String getName() {
        return "switch off";
    }
}
