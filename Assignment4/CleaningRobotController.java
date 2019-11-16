public class CleaningRobotController extends DeviceControler {

    public CleaningRobotController(CleaningRobot cleaningRobot) {
        this.deviceCommands = cleaningRobot.getCommands();
        this.name = "cleaning robot";
    }

}
