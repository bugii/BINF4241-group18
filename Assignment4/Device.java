import java.util.ArrayList;

public interface Device {
    public String getName();
    public ArrayList<Command> getCommands();
    public DeviceControler generateDeviceControler();
}
