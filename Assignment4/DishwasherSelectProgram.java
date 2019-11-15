import java.util.Scanner;

public class DishwasherSelectProgram implements Command {

    private Dishwasher dishwasher;

    public DishwasherSelectProgram(Dishwasher dishwasher) {
        this.dishwasher = dishwasher;
    }

    @Override
    public void execute() {

        if (!dishwasher.getSwitchedOn()) {
            System.out.println("Please switch on before using");
        }
        else if (dishwasher.getThread() != null) {
            System.out.println("There is already a program running, make sure to stop the old one before" +
                    "starting a new one");
        }

        else {
            String input = "";
            while (input.equals("")) {
                System.out.println("Dishwashwer: Please enter Glasses, Plates, Pans, or Mixed");
                Scanner scanner = new Scanner(System.in);
                input = scanner.nextLine();
                switch (input) {
                    case "Glasses":
                        dishwasher.setChosenProgram(DishwasherProgramTypes.Glasses);
                        break;
                    case "Plates":
                        dishwasher.setChosenProgram(DishwasherProgramTypes.Plates);
                        break;
                    case "Pans":
                        dishwasher.setChosenProgram(DishwasherProgramTypes.Pans);
                        break;
                    case "Mixed":
                        dishwasher.setChosenProgram(DishwasherProgramTypes.Mixed);
                        break;
                    default:
                        input = "";
                        break;
                }
            }
            System.out.println("Dishwasher: type set");
        }
    }

    @Override
    public String getName() {
        return "select program";
    }


}
