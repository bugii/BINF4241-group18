public class CleaningRobotCheckBatteryCommand implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotCheckBatteryCommand(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {
        System.out.println(this.cleaningRobot.getBatteryStatus());
    }

    @Override
    public String getName() {
        return "check battery";
    }
}
