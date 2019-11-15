import java.util.ArrayList;
import java.util.Scanner;

public class Smartphone {
    private ArrayList<Device> devices = new ArrayList<>();
    private ArrayList<DeviceControler> deviceControlers = new ArrayList<>();

    public Smartphone() {
    }

    public void start(){
        devices.add(new WashingMachine());
        devices.add(new Dishwasher());

        for(int i = 0; i < devices.size(); ++i){
            Device device = devices.get(i);
            deviceControlers.add(device.generateDeviceControler());
        }


        while(true) {

            //print all options
            System.out.println("please select a device");
            for (int i = 0; i < deviceControlers.size(); i++) {
                System.out.println(deviceControlers.get(i).getName());
            }
            System.out.println("exit");

            //read in the command
            Scanner scanner = new Scanner(System.in);
            String command_to_ex = scanner.nextLine();


            if(command_to_ex.equals("exit")){
                return;
            }

            DeviceControler deviceControler = null;
            for (int i = 0; i < deviceControlers.size(); i++) {
                if(command_to_ex.equals(deviceControlers.get(i).getName())){
                    deviceControler = deviceControlers.get(i);
                    break;
                }
            }
            if(deviceControler == null){
                System.out.println("invalid command");
            }
            else{
                deviceControler.start();
            }
        }
    }

    public static void main(String args []){

        Smartphone smartphone = new Smartphone();

        smartphone.start();

    }
}
