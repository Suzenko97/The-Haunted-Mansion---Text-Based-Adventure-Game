import java.util.LinkedList;

public class ConsoleView {

    public static void introMessage(){
        System.out.println("Welcome to the Haunted Mansion game! Have fun!\n");
    }
    public static void navRequest (){
        System.out.println("Which direction would you like to go or do you want to exit?");
    }

    public static void showInventory (LinkedList<Item> inventory){
        System.out.println(inventory.toString());
    }

    public static void directionList(StringBuilder list){
        System.out.println("These are the rooms to the North, South, East, and West:");
        System.out.println(list);
    }

    public static void showRoom(StringBuilder desc){
        System.out.println(desc);
    }

    public static void navDenial(){
        System.out.println("You cannot go in that direction. Try something else....");
    }

    public static void quitMessage(){
        System.out.println("Thank you for playing!");
    }

}
