import java.util.ArrayList;
import java.util.Scanner;

public class WashingMachine implements Device, Runnable{
    private String name = "Washing Machine";
    private boolean on = false;
    private boolean washing = false;
    private int degree = -1;
    private WashingMachineWashingTypes type = null;
    private int time = -1;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new WashingMachineSwitchOnCommand(this));
        commands.add(new WashingMachineSwitchOffCommand(this));
        commands.add(new WashingMachineSelectDegreeCommand(this));
        commands.add(new WashingMachineSelectTypeCommand(this));
        commands.add(new WashingMachineStartCommand(this));
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
        if(!washing) {
            on = false;
            System.out.println(name + ": switch false");
        }
        else{
            System.out.println("unable to turn off machine, machine still washing");
        }
    }

    public void selectDegree(){
        degree = -1;
        while (degree < 20 || degree > 60) {
            System.out.println("Washing Machine: Please enter a temperature in Celcius between 20 and 60");
            Scanner scanner = new Scanner(System.in);
            degree = scanner.nextInt();
        }
        System.out.println("Washing Machine: temperature set");
        return;
    }

    public void selectTypeOfWashing(){
        String input = "";
        while (input == "") {
            System.out.println("Washing Machine: Please enter Double Rinse, Intense, Quick or Spin");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            if(input.equals("Double Rinse")){  //    DoubleRins, Intense, Quick, Spin;
                this.type = WashingMachineWashingTypes.DoubleRins;
            }
            else if(input.equals("Intense")){
                this.type = WashingMachineWashingTypes.Intense;
            }
            else if(input.equals("Quick")){
                this.type = WashingMachineWashingTypes.Quick;
            }
            else if(input.equals("Spin")){
                this.type = WashingMachineWashingTypes.Spin;
            }
            else{
                input = "";
            }

        }
        time = this.type.getTiming();
        System.out.println("Washing Machine: type set");
        return;
    }

    public void startMachine(){
        if( washing){
            System.out.println("Already washing"); return;
        }
        if( !on){
            System.out.println("Machine not turned on"); return;
        }
        if( degree < 20 || degree > 60){
            System.out.println("Temperature not set"); return;
        }
        if( type == null){
            System.out.println("No Type set yet");
        }
        time = type.getTiming();
        washing = true;
        Thread thread = new Thread(this);
        System.out.println("Washing Machine: start washing");
        thread.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(time * 60* 1000);
            System.out.println("Washing Machine: finished washing");
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
