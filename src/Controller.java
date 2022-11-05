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
            //[HOLLY] ->  Pick Up Command
            else if (input.contains("pick ")){
                String itemName = input.replaceAll("pick ", "");
                Model.pickUpItem(itemName);
            }
            //[HOLLY] ->  Drop Command
            else if (input.contains("drop ")){
                String itemName = input.replaceAll("drop ", "");
                Model.dropItem(itemName);
            }
            // [HOLLY] -> Unequip Item Command
            else if(input.contains("unequip ")){
                System.out.println("unequip detected");
                String itemName = input.replaceAll("unequip ", "");
                Model.unequipItem(itemName);
            }
            // [HOLLY] -> Inspect Room Command (NOT REQUIRED IN SRS) being used for testing purposes
            else if(input.contains("inspect room")){
                System.out.println(Model.currentRoom.inspectRoom());
            }
            // [HOLLY] -> Inspect Item Command
            else if(input.contains("inspect ")){
                String itemName = input.replaceAll("inspect ", "");
                Model.inspectItem(itemName);
            }
            // [HOLLY] -> Equip Item Command
            else if(input.contains("equip ")){
                String itemName = input.replaceAll("equip ", "");
                Model.equipItem(itemName);
            }
            // [HOLLY] -> Check Inventory Command
            else if(input.contains("check inventory")){
                Model.checkInventory();
            }
            else if (input.contains("exit")){
                play = false;
            }
        }
        ConsoleView.quitMessage();
        Model.quitGame();
    }
}
