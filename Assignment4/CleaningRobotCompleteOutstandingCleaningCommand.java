public class CleaningRobotCompleteOutstandingCleaningCommand implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotCompleteOutstandingCleaningCommand(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {
        if (this.cleaningRobot.getThread() != null) {
            if (this.cleaningRobot.getCharging()) {
                System.out.println("Cleaning Robot: Currently charging, please wait until it is fully charged.");
            }
            else {
                System.out.println("Cleaning Robot: Already cleaning. Either wait until done or stop the process.");
            }
        }

        else {
            cleaningRobot.setContinuing(true);
            cleaningRobot.setThread(new Thread(cleaningRobot));
            cleaningRobot.getThread().start();
        }
    }

    @Override
    public String getName() {
        return "complete outstanding cleaning";
    }
}
