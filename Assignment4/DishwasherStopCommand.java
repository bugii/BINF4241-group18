public class DishwasherStopCommand implements Command {

    private Dishwasher dishwasher;

    public DishwasherStopCommand(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {

        if (dishwasher.getThread() != null) {
            dishwasher.setThread(null);
            System.out.println("Stopped");
        }
        else {
            System.out.println("Nothing was running anyways");
        }

    }

    @Override
    public String getName() {
        return "stop";
    }
}
