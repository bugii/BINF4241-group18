import java.util.Scanner;

public class CleaningRobotSetTimerCommand implements Command {

    private CleaningRobot cleaningRobot;

    public CleaningRobotSetTimerCommand(CleaningRobot cleaningRobot) {
        this.cleaningRobot = cleaningRobot;
    }

    @Override
    public void execute() {
        int time = 0;

        while (time == 0) {
            System.out.println("Cleaning Robot: Please enter the time it takes to clean (in seconds).");
            Scanner scanner = new Scanner(System.in);
            time = scanner.nextInt();
            this.cleaningRobot.setTimeToCleanEntireHouse(time);
            System.out.println("Cleaning Robot: Time set.");
        }
    }

    @Override
    public String getName() {
        return "set timer";
    }
}
