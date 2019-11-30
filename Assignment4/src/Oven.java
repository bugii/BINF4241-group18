import java.util.ArrayList;
import java.util.Scanner;

public class Oven implements Device, Runnable {
    private ArrayList<Command> commands = new ArrayList<>();
    private OvenProgramTypes program = null;
    private Boolean on = false;
    private Thread thread = null;
    private int timer = -1;
    private int temperature = -1;
    private long threadStartTimestamp;

    public Oven(){
        commands.add(new OvenSwitchOnCommand(this));
        commands.add(new OvenSetTimerCommand(this));
        commands.add(new OvenSetTemperatureCommand(this));
        commands.add(new OvenSelectProgramCommand(this));
        commands.add(new OvenStartCommand(this));
        commands.add(new OvenCheckTimerCommand(this));
        commands.add(new OvenInterruptProgramCommand(this));
        commands.add(new OvenSwitchOffCommand(this));
    }

    @Override
    public String getName() {
        return "oven";
    }

    @Override
    public ArrayList<Command> getCommands(){
        return commands;
    }

    @Override
    public DeviceControler generateDeviceControler(){
        return new OvenController(this);
    }

    public void switchOn(){
        if(!on){
            on = true;
            System.out.println("Oven switched on");
        }
        else {
            System.out.println("Oven is already on");
        }
    }

    public void switchOff(){
        if(on){
            on = false;
            timer = -1;
            temperature = -1;
            program = null;
            System.out.println("Oven switched off");
        }
        else {
            System.out.println("Oven is already off");
        }
    }

    @Override
    public void run(){
        try {
            System.out.println("Oven will run "+ program + " at " + temperature + "°C for " + timer + " seconds.");
            threadStartTimestamp = System.currentTimeMillis();
            Thread.sleep(timer * 1000);
            System.out.println("Oven: finished cooking");
            thread = null;
        } catch (InterruptedException e) {
            thread = null;
        }
    }

    public void selectProgram(){
        if(!on){
            System.out.println("Oven must be switched on to do this");
        }
        else if (thread != null){
            System.out.println("Oven is currently in use, please wait until the current programm is finished or use the interrupt command before starting a new program.");
        }
        else {
            String input = "";
            while (input.equals("")) {
                System.out.println("Please type in one of the following Oven programs:" + "Conventional, Ventilated, Grill, Bottom");
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine();
                if (input.equals("Conventional")) {
                    program = OvenProgramTypes.Conventional;
                } else if (input.equals("Venilated")) {
                    program = OvenProgramTypes.Ventilated;
                } else if (input.equals("Grill")) {
                    program = OvenProgramTypes.Grill;
                } else if (input.equals("Bottom")) {
                    program = OvenProgramTypes.Bottom;
                }
                else {
                    input = "";
                }
            }
            System.out.println("Oven: type set");
        }
    }

    public void setTimer(){
        if(!on){
            System.out.println("Oven must be switched on to do this");
        }
        else if (thread != null){ // while it would make sense to be able to change the timer while the oven is still cooking, one normally also can start the oven without setting the timer, so...
            System.out.println("Oven is currently in use, please wait until the current programm is finished or use the interrupt command before setting a new timer.");
        }
        else {
            timer = -1;
            while (timer < 1) { // minimum time is 1 second...
                System.out.println("Please set the Timer in seconds:");
                Scanner scanner = new Scanner(System.in);
                while (!scanner.hasNextInt()) {
                    scanner.nextLine();
                }
                timer = scanner.nextInt();
            }
            System.out.println("Oven: time set.");
        }
    }

    public void setTemperature(){
        if(!on){
            System.out.println("Oven must be switched on to do this");
        }
        else if (thread != null){
            System.out.println("Oven is currently in use, please wait until the current programm is finished or use the interrupt command before setting a new temperature.");
        }
        else {
            temperature = -1;
            while (temperature < 30 || temperature > 300) {
                System.out.println("Please input the temperature in °C:\n(Oven Range 30-300)");
                Scanner scanner = new Scanner(System.in);
                while (!scanner.hasNextInt()) {
                    scanner.nextLine();
                }
                temperature = scanner.nextInt();
            }
            System.out.println("Oven: temperature set.");
        }
    }

    public void startOven(){
        if(!on){
            System.out.println("Oven must be switched on to do this");
        }
        else if (thread != null){
            System.out.println("Oven is already running...");
        }
        else if (timer < 1 || temperature == -1 || program == null){
            System.out.println("Oven cannot be started: Not all required parameters have been input.\nPlease set the timer, temperature and program.");
        }
        else {
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public void checkTimer() {
        if(!on){
            System.out.println("Oven must be switched on to do this");
        }
        else if(thread != null){
            long passedTimeMillis = System.currentTimeMillis() - threadStartTimestamp;
            int programDurationMillis = timer * 1000;
            System.out.println("Time left: approx. " + ((programDurationMillis - passedTimeMillis) / 1000) + " seconds");
        }
        else if (timer == -1){
            System.out.println("No timer has been set.");
        }
        else {
            System.out.println("The next Oven program will run for " + timer + " seconds.");
        }
    }

    public void interrupt() {
        if(!on){
            System.out.println("Oven must be switched on to do this");
        }
        else if (thread != null){
            thread.interrupt();
            System.out.println("Oven program interrupted.");
        }
        else {
            System.out.println("Oven isn't running anyway...");
        }
    }
}
