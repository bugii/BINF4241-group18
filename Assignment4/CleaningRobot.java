import java.util.ArrayList;

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
                    this.setBatteryStatus(i);
                    //System.out.println("new battery " + this.getBatteryStatus());
                    Thread.sleep(200);
                }
                System.out.println("Cleaning Robot: Fully charged");
                this.setCharging(false);
                this.thread = null;
            }

            else if (this.isContinuing) {

                float timeLeft = this.timeToCleanEntireHouse - this.timeSpentCleaning;
                if (timeLeft != 0) {
                    System.out.println("Cleaning Robot: Continuing cleaning... (Time left: " + timeLeft + ")");
                    // starts using up the battery. Assume we lose 2% battery per second
                    for (int i=0; i<timeLeft; i++) {
                        Thread.sleep(1000);
                        this.timeSpentCleaning += 1;
                        int currentBattery = this.getBatteryStatus();
                        int newBattery = currentBattery - 2;
                        this.setBatteryStatus(newBattery);
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
                    this.thread = null;
                }
            }

            else {

                // starts using up the battery. Assume we lose 2% battery per second
                for (int i=0; i<this.getTimeToCleanEntireHouse(); i++) {
                    Thread.sleep(1000);
                    this.timeSpentCleaning += 1;
                    int currentBattery = this.getBatteryStatus();
                    int newBattery = currentBattery - 2;
                    this.setBatteryStatus(newBattery);
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


    public int getTimeToCleanEntireHouse() {
        return timeToCleanEntireHouse;
    }

    public void setTimeToCleanEntireHouse(int timeToCleanEntireHouse) {
        this.timeToCleanEntireHouse = timeToCleanEntireHouse;
    }

    public Boolean getCharging() {
        return isCharging;
    }

    public void setCharging(Boolean charging) {
        isCharging = charging;
    }

    public void returnToBase() {
        System.out.println("Returning back to base");
        // Remove old thread to start new thread (in order to charge the robot)
        this.thread = null;
        this.isCharging = true;
        this.isContinuing = false;
        this.setThread(new Thread(this));
        thread.start();
    }

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public Boolean getContinuing() {
        return isContinuing;
    }

    public void setContinuing(Boolean continuing) {
        isContinuing = continuing;
    }

    public int getTimeSpentCleaning() {
        return timeSpentCleaning;
    }

    public void setTimeSpentCleaning(int timeSpentCleaning) {
        this.timeSpentCleaning = timeSpentCleaning;
    }
}
