public class MicrowaveSetTemperatureCommand implements Command {
    Microwave microwave;
    public MicrowaveSetTemperatureCommand (Microwave microwave){
        this.microwave = microwave;
    }
    @Override
    public void execute(){
        microwave.setTemperature();
    }
    @Override
    public String getName(){
        return "set temperature";
    }
}
