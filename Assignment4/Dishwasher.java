import java.util.ArrayList;
import java.util.Scanner;

public class Dishwasher implements Device, Runnable {

    private Boolean switchedOn = false;
    private DishwasherProgramTypes chosenProgram = null;
    private ArrayList<Command> commands = new ArrayList<>();
    private Thread thread = null;
    private long threadStartTimestamp;

    public Dishwasher() {
        commands.add(new DishwasherSwitchOnCommand(this));
        commands.add(new DishwasherSelectProgram(this));
        commands.add(new DishwasherStartCommand(this));
        commands.add(new DishwasherCheckTimerCommand(this));
        commands.add(new DishwasherStopCommand(this));
        commands.add(new DishwasherSwitchOffCommand(this));
    }

    @Override
    public String getName() {
        return "Dishwasher";
    }

    @Override
    public ArrayList<Command> getCommands() {
        return commands;
    }

    @Override
    public DeviceControler generateDeviceControler() {
        return new DishwasherController(this);
    }

    @Override
    public void run() {
        try {
            System.out.println("Started " + this.chosenProgram + " which takes " + chosenProgram.getProgramDuration() + " seconds" );
            threadStartTimestamp = System.currentTimeMillis();
            Thread.sleep(chosenProgram.getProgramDuration() * 1000);
            System.out.println("Dishwasher: Program finished");
            this.thread = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void checkTimer() {

        if (this.thread != null) {
            long passedTimeMillis = System.currentTimeMillis() - this.threadStartTimestamp;
            int programDurationMillis = this.chosenProgram.getProgramDuration() * 1000;
            System.out.println("Time left: approx. " + ((programDurationMillis - passedTimeMillis) / 1000) + " seconds");
        }
        else if (this.chosenProgram != null){
            System.out.println("Not currently running, but the chosen program would take " + this.chosenProgram.getProgramDuration() + " seconds");
        }
        else if (!this.switchedOn) {
            System.out.println("Machine not running, turn on first please");
        }
        else {
            System.out.println("Nothing is running");
        }
    }

    public void selectProgram() {
        if (!this.switchedOn) {
            System.out.println("Please switch on before using");
        }
        else if (this.thread != null) {
            System.out.println("There is already a program running, make sure to stop the old one before" +
                    "starting a new one");
        }

        else {
            String input = "";
            while (input.equals("")) {
                System.out.println("Dishwashwer: Please enter Glasses, Plates, Pans, or Mixed");
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine();
                switch (input) {
                    case "Glasses":
                        this.chosenProgram = DishwasherProgramTypes.Glasses;
                        break;
                    case "Plates":
                        this.chosenProgram = DishwasherProgramTypes.Plates;
                        break;
                    case "Pans":
                        this.chosenProgram = DishwasherProgramTypes.Pans;
                        break;
                    case "Mixed":
                        this.chosenProgram = DishwasherProgramTypes.Mixed;
                        break;
                    default:
                        input = "";
                        break;
                }
            }
            System.out.println("Dishwasher: type set");
        }
    }

    public void start() {
        if (!this.switchedOn) {
            System.out.println("Please switch on before using");
        }
        else if (this.thread != null) {
            System.out.println("There is already a program running, make sure to stop the old one before" +
                    "starting a new one");
        }
        else {
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public void stop() {

        if (this.thread != null) {
            this.thread = null;
            System.out.println("Stopped");
        }
        else {
            System.out.println("Nothing was running anyways");
        }
    }

    public void switchOff() {
        this.commands.get(4).execute();
        this.switchedOn = false;
        System.out.println("Switched off");
    }

    public void switchOn() {
        this.switchedOn = true;
        System.out.println("Dishwasher switched on");
    }
}
