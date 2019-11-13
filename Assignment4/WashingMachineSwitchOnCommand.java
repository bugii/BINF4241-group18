public class WashingMachineSwitchOnCommand implements Command {

    WashingMachine washingMachine;

    public WashingMachineSwitchOnCommand(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void execute() {
        washingMachine.switchOn();
    }

    @Override
    public String getName() {
        return "Switch On";
    }
}
