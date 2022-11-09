import java.util.LinkedList;

public class ConsoleView {
    public static final String ANSI_PURPLE = "\033[0;35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\033[0;31m";

    public static void introMessage(){
        System.out.println("Welcome to the Haunted Mansion game! Have fun!\n");
    }

    public static void navRequest (){
        System.out.println("Which direction would you like to go or do you want to exit?");
    }
    // [HOLLY] successMessage -> prints out success statements (i.e item succesffuly dropped/picked etc)
    public static void successMessage(String message){System.out.println(ANSI_PURPLE + message + ANSI_RESET);}
    // [HOLLY] showInventory -> prints out list of items in inventory
    public static void showInventory (LinkedList<Item> inventory){
        System.out.println(ANSI_PURPLE + "Inventory: "  + inventory.toString() + ANSI_RESET);
    }
    // [HOLLY] TreasureMessage -> prints out list of treasure chest items to choose from
    public static void treasureMessage (LinkedList<PowerUp> powerUps){
        System.out.println(ANSI_PURPLE +"Choose a powerup:" + ANSI_RESET);
        for(PowerUp powerUp : powerUps){
            System.out.println(ANSI_PURPLE + "-" + powerUp + ANSI_RESET);
        }
    }

    // [HOLLY] showItemDescription -> prints out description
    public static void showItemDesc (String itemDesc){System.out.println(ANSI_PURPLE + itemDesc + ANSI_RESET);}

    // [HOLLY] showErrorMessage -> prints out description
    public static void showErrorMessage (String errorMessage){System.out.println(ANSI_RED + errorMessage + ANSI_RESET);}

    public static void directionList(StringBuilder list){
        System.out.println("These are the rooms to the North, South, East, and West:");
        System.out.println(list);
    }

    public static void showRoom(StringBuilder desc){
        System.out.println(desc);
    }

    public static void printCompassInfo(int compass){
        System.out.println("You've visited " + compass + " rooms out of a total of 31.\n");
    }

    public static void navDenial(){
        System.out.println("You cannot go in that direction. Try something else....");
    }

    public static void quitMessage(){
        System.out.println("Thank you for playing!");
    }

    public static void startCombatMessage(String monsterName){System.out.println("Would you like to begin combat? " + monsterName);}

    public static void monsterInspectMessage(String monsterInspect){System.out.println("Monster Info: " + monsterInspect);}

    public static void inCombatMessage(){
        System.out.println("You are currently in combat, what would you like to do?( Attack , Block, Run , Dodge)");
    }
    public static void monsterInRoomMessage(){
        System.out.println("There is currently a monster in this room, defeat it before you can explore!");
    }
    public static void noMonsterInRoomMessage(){
        System.out.println("There are no monsters in this room. Explore as you wish!");
    }
    public static void invalidCombatOption(){
        System.out.println("Invalid combat option!");
    }

    public static void playerEscapeCombat(String monster){System.out.println("You ran from " + monster);}

    public static void printMonsterDesc(String desc){
        System.out.println(desc + "\n");
    }

    public static void printMonsterDenial(){
        System.out.println("Cannot proceed, monster is in this room. You will not be able to leave unless you fight.\nAttack monster to start combat.");
    }

    public static void gameOverMessage(String monsterName){
        System.out.println("You were slain by " + monsterName);
        System.out.println("Would you like to start over from most recent checkpoint?");
    }
    public static void gameRestartedFromCheckpoint(){
        System.out.println("Game restarted from checkpoint!");
    }
    public static void thankYouForPlaying(){
        System.out.println("Thank you for playing!!");
    }

    public static void printInspectDenial(){
        System.out.println("You cannot look around when there's a monster nearby.\n");
    }

}
