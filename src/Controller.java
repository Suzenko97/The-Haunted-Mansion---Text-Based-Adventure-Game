import java.io.FileNotFoundException;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner userInput = new Scanner(System.in);
        Model.setup();

        boolean play = true;
        String input;
        ConsoleView.introMessage();
        ConsoleView.showRoom(Model.getRoom());
        while (play){
            ConsoleView.directionList(Model.getDirectionList());
            ConsoleView.navRequest();
            input = userInput.nextLine();
            input = input.toLowerCase();
            if (input.contains("enter room")){
                String tmp = input.substring(11);
                boolean success = Model.movePlayer(tmp);
                    if (success){
                        ConsoleView.showRoom(Model.getRoom());
                    }
                    else{
                        ConsoleView.navDenial();
                    }
            }
            else if (input.contains("pick up")){
                String itemName = input.replaceAll("pick up ", "");
            }
            else if (input.contains("exit")){
                play = false;
            }
        }
        ConsoleView.quitMessage();
        Model.quitGame();
    }
}
