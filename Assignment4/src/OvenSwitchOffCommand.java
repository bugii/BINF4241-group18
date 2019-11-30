public class OvenSwitchOffCommand implements Command {
    Oven oven;
    public OvenSwitchOffCommand (Oven oven){
        this.oven = oven;
    }
    @Override
    public void execute(){
        oven.switchOff();
    }

    @Override
    public String getName(){
        return "switch off";
    }
}
