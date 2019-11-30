public class WashingMachineStartCommand implements Command {
    WashingMachine washingMachine;

    public WashingMachineStartCommand(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void execute() {
        washingMachine.startMachine();
    }

    @Override
    public String getName() {
        return "start machine";
    }
}
