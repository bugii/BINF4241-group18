public class WashingMachineSelectTypeCommand implements Command {
    WashingMachine washingMachine;

    public WashingMachineSelectTypeCommand(WashingMachine washingMachine) {
        this.washingMachine = washingMachine;
    }

    @Override
    public void execute() {
        washingMachine.selectTypeOfWashing();
    }

    @Override
    public String getName() {
        return "set washing type";
    }
}
