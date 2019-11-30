public class MicrowaveSetTimerCommand implements Command {
    Microwave microwave;
    public MicrowaveSetTimerCommand (Microwave microwave){
        this.microwave = microwave;
    }
    @Override
    public void execute(){
        microwave.setTimer();
    }
    @Override
    public String getName(){
        return "set timer";
    }
}
