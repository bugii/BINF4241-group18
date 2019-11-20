public class MicrowaveSwitchOnCommand implements Command {
    Microwave microwave;
    public MicrowaveSwitchOnCommand (Microwave microwave){
        this.microwave = microwave;
    }
    @Override
    public void execute(){
        microwave.switchOn();
    }
    @Override
    public String getName(){
        return "switch on";
    }
}
