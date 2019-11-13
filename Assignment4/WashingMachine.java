import java.util.ArrayList;

public class WashingMachine implements Device {
    String name = "Washing Machine";
    boolean on = false;
    boolean running = false;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new WashingMachineSwitchOnCommand(this));
        return commands;
    }

    @Override
    public DeviceControler generateDeviceControler() {
        return new WashingMachineControler(this);
    }

    public void switchOn(){
        on = true;
        System.out.println(name +": switch on");
    }

    public void switchOff(){
        if(!running) {
            on = false;
            System.out.println(name + ": switch false");
        }
        else{
            System.out.println("unable to turn off machine, machine still running");
        }
    }


}
