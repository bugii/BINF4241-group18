public class OvenSetTimerCommand implements Command {
    Oven oven;
    public OvenSetTimerCommand (Oven oven){
        this.oven = oven;
    }
    @Override
    public void execute(){
        oven.setTimer();
    }

    @Override
    public String getName(){
        return "set timer";
    }
}
