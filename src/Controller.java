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
                if (!(Model.checkForMonster())){
                    boolean success = Model.movePlayer(tmp);
                    if (success){
                        ConsoleView.showRoom(Model.getRoom());
                    }
                    else{
                        ConsoleView.navDenial();
                    }
                }
                else {
                    ConsoleView.printMonsterDenial();
                }
            }
            else if(input.contains("inspect monster")){
                ConsoleView.printMonsterDesc(Model.inspectMonster());
            }
            else if (input.contains("attack monster")){
                Model.startCombat();
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
                ConsoleView.successMessage(Model.currentRoom.inspectRoom());
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
            // [HOLLY] opens chest
            else if (input.contains("open chest")){
                if(Model.chestCheck()){
                    Model.openChest();
                    String chosenPowerUp = userInput.nextLine();
                    chosenPowerUp = chosenPowerUp.toLowerCase();
                    Model.activatePowerup(chosenPowerUp);
                }else{
                    ConsoleView.showErrorMessage("There is no treasure chest in this room");
                }
            }
            else if (input.contains("exit")){
                play = false;
            }

        }
        ConsoleView.quitMessage();
        Model.quitGame();
    }
}
