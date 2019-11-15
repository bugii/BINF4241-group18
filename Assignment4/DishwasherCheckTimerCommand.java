public class DishwasherCheckTimerCommand implements Command {

    private Dishwasher dishwasher;

    public DishwasherCheckTimerCommand(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {

        if (dishwasher.getThread() != null) {
            long passedTimeMillis = System.currentTimeMillis() - dishwasher.getThreadStartTimestamp();
            int programDurationMillis = dishwasher.getChosenProgram().getProgramDuration() * 60 * 1000;
            System.out.println("Time left: approx. " + ((programDurationMillis - passedTimeMillis) / 1000 / 60) + " minutes");
        }
        else if (dishwasher.getChosenProgram() != null){
            System.out.println("Not currently running, but the chosen program would take " + dishwasher.getChosenProgram().getProgramDuration() + " minutes");
        }
        else {
            System.out.println("Nothing is running");
        }
    }

    @Override
    public String getName() {
        return "check timer";
    }
}
