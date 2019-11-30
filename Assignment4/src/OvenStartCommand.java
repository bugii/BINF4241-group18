public class OvenStartCommand implements Command {
    Oven oven;
    public OvenStartCommand (Oven oven){
        this.oven = oven;
    }
    @Override
    public void execute(){
        oven.startOven();
    }

    @Override
    public String getName(){
        return "start";
    }
}
