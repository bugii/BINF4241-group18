public class CleaningRobotEndCleaning implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotEndCleaning(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {
        this.cleaningRobot.returnToBase();
    }

    @Override
    public String getName() {
        return "end";
    }
}
