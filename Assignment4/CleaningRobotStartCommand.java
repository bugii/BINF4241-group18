public class CleaningRobotStartCommand implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotStartCommand(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {
        cleaningRobot.start();
    }

    @Override
    public String getName() {
        return "start";
    }
}
