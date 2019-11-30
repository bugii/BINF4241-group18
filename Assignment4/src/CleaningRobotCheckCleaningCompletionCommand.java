public class CleaningRobotCheckCleaningCompletionCommand implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotCheckCleaningCompletionCommand(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {
        cleaningRobot.checkCleaningCompletion();
    }

    @Override
    public String getName() {
        return "get cleaning completion";
    }
}
