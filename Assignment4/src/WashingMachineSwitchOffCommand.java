public class WashingMachineSwitchOffCommand implements Command {

    WashingMachine washingMachine;

    public WashingMachineSwitchOffCommand(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void execute() {
        washingMachine.switchOn();
    }

    @Override
    public String getName() {
        return "Switch Off";
    }
}
