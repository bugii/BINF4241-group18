public class DishwasherStartCommand implements Command {

    private Dishwasher dishwasher;

    public DishwasherStartCommand(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {
        if (!dishwasher.getSwitchedOn()) {
            System.out.println("Please switch on before using");
        }
        else if (dishwasher.getThread() != null) {
            System.out.println("There is already a program running, make sure to stop the old one before" +
                    "starting a new one");
        }
        else {
            dishwasher.setThread(new Thread(dishwasher));
            dishwasher.getThread().start();
        }
    }

    @Override
    public String getName() {
        return "start";
    }
}
