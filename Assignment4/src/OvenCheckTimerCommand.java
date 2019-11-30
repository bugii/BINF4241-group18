public class OvenCheckTimerCommand implements Command {
    Oven oven;
    public OvenCheckTimerCommand (Oven oven){
        this.oven = oven;
    }
    @Override
    public void execute(){
        oven.checkTimer();
    }

    @Override
    public String getName(){
        return "check timer";
    }
}
