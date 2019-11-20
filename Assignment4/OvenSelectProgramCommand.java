public class OvenSelectProgramCommand implements Command {
    Oven oven;
    public OvenSelectProgramCommand (Oven oven){
        this.oven = oven;
    }
    @Override
    public void execute(){
        oven.selectProgram();
    }

    @Override
    public String getName(){
        return "select program";
    }
}
