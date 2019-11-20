public class OvenSwitchOnCommand implements Command {
    Oven oven;
    public OvenSwitchOnCommand (Oven oven){
        this.oven = oven;
    }

    @Override
    public void execute(){
        oven.switchOn();
    }

    @Override
    public String getName(){
        return "switch on";
    }
}
