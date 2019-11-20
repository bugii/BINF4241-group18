public class MicrowaveSwitchOffCommand implements Command {
    Microwave microwave;
    public MicrowaveSwitchOffCommand (Microwave microwave){
        this.microwave = microwave;
    }
    @Override
    public void execute(){
        microwave.switchOff();
    }
    @Override
    public String getName(){
        return "switch off";
    }
}
