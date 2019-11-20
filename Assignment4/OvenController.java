public class OvenController extends DeviceControler{
    public OvenController(Oven oven){
        this.deviceCommands = oven.getCommands();
        this.name = oven.getName();
    }
}
