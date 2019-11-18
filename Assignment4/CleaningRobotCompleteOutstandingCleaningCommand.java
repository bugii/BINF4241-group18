public class CleaningRobotCompleteOutstandingCleaningCommand implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotCompleteOutstandingCleaningCommand(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {
        cleaningRobot.completeOutstandingCleaning();
    }

    @Override
    public String getName() {
        return "complete outstanding cleaning";
    }
}
