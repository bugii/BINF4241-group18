public class OvenInterruptProgramCommand implements Command {
    Oven oven;
    public OvenInterruptProgramCommand (Oven oven){
        this.oven = oven;
    }
    @Override
    public void execute(){
        oven.interrupt();
    }

    @Override
    public String getName(){
        return "interrupt program";
    }
}
