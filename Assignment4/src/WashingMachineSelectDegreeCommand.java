public class WashingMachineSelectDegreeCommand implements Command {
    WashingMachine washingMachine;

    public WashingMachineSelectDegreeCommand(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void execute() {
        washingMachine.selectDegree();
    }

    @Override
    public String getName() {
        return "set temperature";
    }
}
