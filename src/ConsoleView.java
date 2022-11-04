public class ConsoleView {

    public static void introMessage(){
        System.out.println("Welcome to the Haunted Mansion game! Have fun!\n");
    }

    public static void navRequest (){
        System.out.println("Which direction would you like to go or do you want to exit?");
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
}
