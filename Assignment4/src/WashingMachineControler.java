public class WashingMachineControler extends DeviceControler {

    public WashingMachineControler(WashingMachine washingMachine) {
        this.deviceCommands = washingMachine.getCommands();
        this.name = "washing machine";
    }
}
