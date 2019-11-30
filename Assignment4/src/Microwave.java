import java.util.ArrayList;
import java.util.Scanner;

public class Microwave implements Device, Runnable {
    private ArrayList<Command> commands = new ArrayList<>();
    private Boolean on = false;
    private Thread thread = null;
    private int timer = -1;
    private int temperature = -1;
    private long threadStartTimestamp;

    public Microwave(){
        commands.add(new MicrowaveSwitchOnCommand(this));
        commands.add(new MicrowaveSetTimerCommand(this));
        commands.add(new MicrowaveSetTemperatureCommand(this));
        commands.add(new MicrowaveStartCommand(this));
        commands.add(new MicrowaveCheckTimerCommand(this));
        commands.add(new MicrowaveInterruptCommand(this));
        commands.add(new MicrowaveSwitchOffCommand(this));
    }

    @Override
    public String getName() {
        return "microwave";
    }

    @Override
    public ArrayList<Command> getCommands() {
        return commands;
    }

    @Override
    public DeviceControler generateDeviceControler() {
        return new MicrowaveController(this);
    }

    @Override
    public void run() {
        try {
            System.out.println("Microwave will run at " + temperature + " Watts for " + timer + " seconds.");
            threadStartTimestamp = System.currentTimeMillis();
            Thread.sleep(timer * 1000);
            System.out.println("Microwave: finished nuking");
            thread = null;
        } catch (InterruptedException e) {
            thread = null;
        }
    }

    public void switchOn() {
        if (!on) {
            on = true;
            System.out.println("Microwave switched on");
        } else {
            System.out.println("Microwave is already on");
        }
    }

    public void switchOff() {
        if (on) {
            on = false;
            timer = -1;
            temperature = -1;
            System.out.println("Microwave switched off");
        } else {
            System.out.println("Microwave is already off");
        }
    }

    public void setTimer() {
        if (!on) {
            System.out.println("Microwave must be switched on to do this");
        } else if (thread != null) {
            System.out.println("Microwave is currently in use, please wait until it is finished or use the interrupt command before setting a new timer.");
        } else {
            timer = -1;
            while (timer < 1) { // minimum time is 1 second...
                System.out.println("Please set the Timer in seconds:");
                Scanner scanner = new Scanner(System.in);
                while (!scanner.hasNextInt()) {
                    scanner.nextLine();
                }
                timer = scanner.nextInt();
            }
            System.out.println("Microwave: time set.");
        }
    }

    public void setTemperature(){
        if(!on){
            System.out.println("Microwave must be switched on to do this");
        }
        else if (thread != null){
            System.out.println("Microwave is currently in use, please wait until it is finished or use the interrupt command before setting a new temperature.");
        }
        else {
            temperature = -1;
            while (temperature < 600 || temperature > 1200) { // using Watts instead of °C because that's what MVs use...
                System.out.println("Please input the temperature in Watts:\n(Oven Range 600-1200)");
                Scanner scanner = new Scanner(System.in);
                while (!scanner.hasNextInt()) {
                    scanner.nextLine();
                }
                temperature = scanner.nextInt();
            }
            System.out.println("Microwave: temperature set.");
        }
    }

    public void startMV(){
        if(!on){
            System.out.println("Microwave must be switched on to do this");
        }
        else if (thread != null){
            System.out.println("Microwave is already running...");
        }
        else if (timer < 1 || temperature == -1){
            System.out.println("Microwave cannot be started: Please set the timer and temperature.");
        }
        else {
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public void checkTimer() {
        if(!on){
            System.out.println("Microwave must be switched on to do this");
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
            System.out.println("The next Microwave program will run for " + timer + " seconds.");
        }
    }

    public void interrupt() {
        if(!on){
            System.out.println("Microwave must be switched on to do this");
        }
        else if (thread != null){
            thread.interrupt();
            System.out.println("Microwave interrupted.");
        }
        else {
            System.out.println("Microwave isn't running anyway...");
        }
    }
}