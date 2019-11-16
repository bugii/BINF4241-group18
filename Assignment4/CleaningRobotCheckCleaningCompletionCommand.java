public class CleaningRobotCheckCleaningCompletionCommand implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotCheckCleaningCompletionCommand(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {
        int totalTimeNeeded = cleaningRobot.getTimeToCleanEntireHouse();
        int timeSpent = cleaningRobot.getTimeSpentCleaning();
        System.out.println((float) timeSpent / totalTimeNeeded * 100 + "%");
    }

    @Override
    public String getName() {
        return "get cleaning completion";
    }
}
