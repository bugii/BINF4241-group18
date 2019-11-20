public class OvenSetTemperatureCommand implements Command {
    Oven oven;
    public OvenSetTemperatureCommand (Oven oven){
        this.oven = oven;
    }
    @Override
    public void execute(){
        oven.setTemperature();
    }

    @Override
    public String getName(){
        return "set temperature";
    }
}
