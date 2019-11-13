import java.util.ArrayList;
import java.util.Scanner;

public abstract class DeviceControler {

    String name;
    ArrayList<Command> deviceCommands;
    Device device;

    public String getName(){
        return this.name;
    }

    public void start(){
        while(true) {

            //print all options
            System.out.println("please select a command");
            for (int i = 0; i < deviceCommands.size(); i++) {
                System.out.println(deviceCommands.get(i).getName());
            }
            System.out.println("back");

            //read in the command
            Scanner scanner = new Scanner(System.in);
            String command_to_ex = scanner.nextLine();


            if(command_to_ex.equals("back")){
                return;
            }

            Command command = null;
            for (int i = 0; i < deviceCommands.size(); i++) {
                if(command_to_ex.equals(deviceCommands.get(i).toString())){
                    command = deviceCommands.get(i);
                    break;
                }
            }
            if(command == null){
                System.out.println("invalid command");
            }
            else{
                command.execute();
            }
        }
    }
}
