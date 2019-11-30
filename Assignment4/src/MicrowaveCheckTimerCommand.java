public class MicrowaveCheckTimerCommand implements Command {
    Microwave microwave;
    public MicrowaveCheckTimerCommand(Microwave microwave){
        this.microwave = microwave;
    }
    @Override
    public void execute(){
        microwave.checkTimer();
    }
    @Override
    public String getName(){
        return "check timer";
    }
}