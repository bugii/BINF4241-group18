public class MicrowaveController extends DeviceControler{
    public MicrowaveController(Microwave microwave){
        this.deviceCommands = microwave.getCommands();
        this.name = microwave.getName();
    }
}
