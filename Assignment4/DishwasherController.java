public class DishwasherController extends DeviceControler {

    public DishwasherController(Dishwasher dishwasher) {
        this.deviceCommands = dishwasher.getCommands();
        this.name = "dishwasher";
    }
}

