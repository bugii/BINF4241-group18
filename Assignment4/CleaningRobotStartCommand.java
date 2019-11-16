public class CleaningRobotStartCommand implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotStartCommand(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {

        // check if charging. If yes -> don't allow to start. Note: If there is no thread active, this means that the robot is not vacuum cleaning
        // and not charging. This implies that it is fully charged.

        if (this.cleaningRobot.getThread() != null) {
            if (this.cleaningRobot.getCharging()) {
                System.out.println("Cleaning Robot: Currently charging, please wait until it is fully charged.");
            }
            else {
                System.out.println("Cleaning Robot: Already cleaning. Either wait until done or stop the process.");
            }
        }
        else if (cleaningRobot.getTimeToCleanEntireHouse() == 0) {
            System.out.println("Please specify how long the cleaning takes before starting.");
        }

        else {
            System.out.println("Cleaning Robot: Starting to vacuum clean");
            this.cleaningRobot.setThread(new Thread(cleaningRobot));
            this.cleaningRobot.getThread().start();
        }
    }

    @Override
    public String getName() {
        return "start";
    }
}
