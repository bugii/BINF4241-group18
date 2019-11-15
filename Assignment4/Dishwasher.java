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
            System.out.println("Started " + this.chosenProgram + " which takes " + chosenProgram.getProgramDuration() + " minutes" );
            threadStartTimestamp = System.currentTimeMillis();
            Thread.sleep(chosenProgram.getProgramDuration() * 60 * 1000);
            System.out.println("Dishwasher: Program finished");
            this.thread = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setSwitchedOn(Boolean switchedOn) {
        this.switchedOn = switchedOn;
    }

    public Boolean getSwitchedOn() {
        return switchedOn;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public long getThreadStartTimestamp() {
        return threadStartTimestamp;
    }

    public DishwasherProgramTypes getChosenProgram() {
        return chosenProgram;
    }

    public void setChosenProgram(DishwasherProgramTypes chosenProgram) {
        this.chosenProgram = chosenProgram;
    }
}
