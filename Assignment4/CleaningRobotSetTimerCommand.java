import java.util.Scanner;

public class CleaningRobotSetTimerCommand implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotSetTimerCommand(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {
        cleaningRobot.setTimer();
    }

    @Override
    public String getName() {
        return "set timer";
    }
}
