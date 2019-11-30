public class MicrowaveStartCommand implements Command {
    Microwave microwave;
    public MicrowaveStartCommand (Microwave microwave){
        this.microwave = microwave;
    }
    @Override
    public void execute(){
        microwave.startMV();
    }
    @Override
    public String getName(){
        return "start";
    }
}
