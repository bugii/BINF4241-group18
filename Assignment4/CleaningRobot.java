import java.util.ArrayList;
import java.util.Scanner;

public class CleaningRobot implements Device, Runnable {

    private ArrayList<Command> commands = new ArrayList<>();
    private int timeToCleanEntireHouse;
    private int timeSpentCleaning = 0;
    private Boolean isCharging = false;
    private Boolean isContinuing = false;
    private int batteryStatus = 100;
    private Thread thread = null;

    public CleaningRobot() {
        commands.add(new CleaningRobotSetTimerCommand(this));
        commands.add(new CleaningRobotStartCommand(this));
        commands.add(new CleaningRobotCheckBatteryCommand(this));
        commands.add(new CleaningRobotCheckCleaningCompletionCommand(this));
        commands.add(new CleaningRobotCompleteOutstandingCleaningCommand(this));
        commands.add(new CleaningRobotEndCleaning(this));
    }

    @Override
    public String getName() {
        return "Cleaning Robot";
    }

    @Override
    public ArrayList<Command> getCommands() {
        return commands;
    }

    @Override
    public DeviceControler generateDeviceControler() {
        return new CleaningRobotController(this);
    }

    @Override
    public void run() {
        try {
            if (this.isCharging) {
                System.out.println("Cleaning Robot: Charging...");
                // Update battery each time you gain 1% (which is done by dividing the total time it takes into
                // 100 equally big time intervals)
                for (int i=0; i<101; i++) {
                    this.batteryStatus = i;
                    //System.out.println("new battery " + this.getBatteryStatus());
                    Thread.sleep(200);
                }
                System.out.println("Cleaning Robot: Fully charged");
                this.isCharging = false;
                this.thread = null;
            }

            else if (this.isContinuing) {

                float timeLeft = this.timeToCleanEntireHouse - this.timeSpentCleaning;
                if (timeLeft != 0 && timeLeft != this.timeToCleanEntireHouse) {
                    System.out.println("Cleaning Robot: Continuing cleaning... (Time left: " + timeLeft + ")");
                    // starts using up the battery. Assume we lose 2% battery per second
                    for (int i=0; i<timeLeft; i++) {
                        Thread.sleep(1000);
                        this.timeSpentCleaning += 1;
                        int currentBattery = this.batteryStatus;
                        int newBattery = currentBattery - 2;
                        this.batteryStatus = newBattery;
                        //System.out.println("new battery " + newBattery);

                        if (batteryStatus == 0) {
                            this.returnToBase();
                            return;
                        }
                    }
                    System.out.println("Cleaning Robot: Finished the continued cleaning.");
                    this.timeSpentCleaning = 0;
                    this.returnToBase();


                }
                else {
                    System.out.println("Nothing to continue.");
                    this.isContinuing = false;
                    this.thread = null;
                }
            }

            else {

                // starts using up the battery. Assume we lose 2% battery per second
                for (int i=0; i<this.timeToCleanEntireHouse; i++) {
                    Thread.sleep(1000);
                    this.timeSpentCleaning += 1;
                    int currentBattery = this.batteryStatus;
                    int newBattery = currentBattery - 2;
                    this.batteryStatus = newBattery;
                    //System.out.println("new battery " + newBattery);

                    if (batteryStatus == 0) {
                        this.returnToBase();
                        return;
                    }
                }

                System.out.println("Cleaning Robot: finished cleaning");
                this.timeSpentCleaning = 0;
                this.returnToBase();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void returnToBase() {
        System.out.println("Returning back to base");
        // Remove old thread to start new thread (in order to charge the robot)
        this.thread = null;
        this.isCharging = true;
        this.isContinuing = false;
        this.thread = new Thread(this);
        thread.start();
    }


    public void checkBattery() {
        System.out.println(this.batteryStatus + "%");
    }

    public void checkCleaningCompletion() {
        int totalTimeNeeded = this.timeToCleanEntireHouse;
        int timeSpent = this.timeSpentCleaning;
        System.out.println((float) timeSpent / totalTimeNeeded * 100 + "%");
    }

    public void completeOutstandingCleaning() {
        if (this.thread != null) {
            if (this.isCharging) {
                System.out.println("Cleaning Robot: Currently charging, please wait until it is fully charged.");
            }
            else {
                System.out.println("Cleaning Robot: Already cleaning. Either wait until done or stop the process.");
            }
        }

        else {
            this.isContinuing = true;
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public void endCleaning() {
        this.returnToBase();
    }

    public void setTimer() {
        int time = 0;

        while (time == 0) {
            System.out.println("Cleaning Robot: Please enter the time it takes to clean (in seconds).");
            Scanner scanner = new Scanner(System.in);
            time = scanner.nextInt();
            this.timeToCleanEntireHouse = time;
            System.out.println("Cleaning Robot: Time set.");
        }
    }

    public void start() {

        // check if charging. If yes -> don't allow to start. Note: If there is no thread active, this means that the robot is not vacuum cleaning
        // and not charging. This implies that it is fully charged.

        if (this.thread != null) {
            if (this.isCharging) {
                System.out.println("Cleaning Robot: Currently charging, please wait until it is fully charged.");
            }
            else {
                System.out.println("Cleaning Robot: Already cleaning. Either wait until done or stop the process.");
            }
        }
        else if (this.timeToCleanEntireHouse == 0) {
            System.out.println("Please specify how long the cleaning takes before starting.");
        }

        else {
            System.out.println("Cleaning Robot: Starting to vacuum clean");
            this.thread = new Thread(this);
            this.thread.start();
        }
    }
}
