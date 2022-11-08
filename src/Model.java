import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Model {
    static HashMap<Double, Room> map;
    static Player p1 = new Player(1,9000, 4500,1,.5);
    Monster monster = new Monster();
    static Room currentRoom;


    /*[NAJEE]*/public static StringBuilder getRoom(){
        return currentRoom.getDesc();
    }

    /*[NAJEE]*/public static StringBuilder getDirectionList() throws FileNotFoundException {
        double[] tmpArr = currentRoom.getDirections();
        StringBuilder dirList = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (tmpArr[i] == 0) {
                dirList.append("Dead End\n");
            } else {
                dirList.append(map.get(tmpArr[i]).getRoomName());
                dirList.append("\n");
            }
        }
        return dirList;
    }

    public static boolean checkForMonster(){
        boolean hasMonster = false;
        for (Monster m : Monster.monsterList){
            if (currentRoom.getRoomNumber() == m.getLocation()) {
                hasMonster = true;
                break;
            }
        }
        return hasMonster;
    }

    /*[NAJEE]*/
    public static boolean movePlayer(String direction) {
        double[] directionOptions = currentRoom.getDirections();
        boolean success = false;

        switch (direction.toLowerCase()) {
            case "north":
                if (directionOptions[0] != (double) 0) {
                    p1.setLastVisited(currentRoom.getRoomNumber());
                    currentRoom = map.get(directionOptions[0]);
                    success = true;
                }
                break;
            case "south":
                if (directionOptions[1] != (double) 0) {
                    p1.setLastVisited(currentRoom.getRoomNumber());
                    currentRoom = map.get(directionOptions[1]);
                    success = true;
                }
                break;
            case "east":
                if (directionOptions[2] != (double) 0) {
                    p1.setLastVisited(currentRoom.getRoomNumber());
                    currentRoom = map.get(directionOptions[2]);
                    success = true;
                }
                break;
            case "west":
                if (directionOptions[3] != (double) 0) {
                    p1.setLastVisited(currentRoom.getRoomNumber());
                    currentRoom = map.get(directionOptions[3]);
                    success = true;
                }
                break;
        }
        return success;
    }

    /*[All]*/
    public static void setup() throws FileNotFoundException {
        String fileName;
        File theFile;
        Scanner inputFile;
        HashMap<Double, Room> tmpMap = new HashMap<>();

        //////Room setup [NAJEE]/////
        fileName = "room_data.txt";
        theFile = new File(fileName);
        inputFile = new Scanner(theFile);
        while (inputFile.hasNextLine()) {
            String name = inputFile.nextLine();
            double number = Double.parseDouble(inputFile.nextLine());
            String desc = inputFile.nextLine();

            Room tmpRoom = new Room(number, name, desc);
            tmpMap.put(tmpRoom.getRoomNumber(), tmpRoom);
        }
        inputFile.close();

        /////connection setup [NAJEE]/////
        fileName = "connection_data.txt";
        theFile = new File(fileName);
        inputFile = new Scanner(theFile);
        while (inputFile.hasNextLine()) {
            double roomNumber = Double.parseDouble(inputFile.nextLine());
            double forward = Double.parseDouble(inputFile.nextLine());
            double backward = Double.parseDouble(inputFile.nextLine());
            double right = Double.parseDouble(inputFile.nextLine());
            double left = Double.parseDouble(inputFile.nextLine());

            Room tmp2 = tmpMap.get(roomNumber);
            double[] tmpArr = {forward, backward, right, left};
            tmp2.setDirections(tmpArr);
        }
        inputFile.close();

        ////monster setup [KELVIN]////
        fileName = "monster_data.txt";
        theFile = new File(fileName);
        inputFile = new Scanner(theFile);
        while (inputFile.hasNextLine()) {
            String name = inputFile.nextLine();
            double location = Double.parseDouble(inputFile.nextLine());
            String description = inputFile.nextLine();
            int health = Integer.parseInt(inputFile.nextLine());
            int damage = Integer.parseInt(inputFile.nextLine());
            String item=inputFile.nextLine();

            Monster monster = new Monster(name, location, description, health, damage, item);
            Monster.monsterList.add(monster);
        }
        inputFile.close();

        map = tmpMap;
        currentRoom = map.get(p1.getLocation());
    }

    ////[NAJEE]/////
    public static void quitGame() {
        System.exit(0);
    }

    ////[KELVIN]////
    public static String inspectMonster() {
        String monsterDesc = "";
        for (int i = 0; i < Monster.monsterList.size(); i++) {
            if (currentRoom.getRoomNumber() == Monster.monsterList.get(i).getLocation()) {
                 monsterDesc = Monster.monsterList.get(i).getName() + "\n"
                        + Monster.monsterList.get(i).getDescription();
                 i=10;
            } else {
                monsterDesc= "No monster in the room";
            }
        }
        return monsterDesc;
    }

    ////[KELVIN]////
    public static void startCombat() {
        Scanner combatChoice=new Scanner(System.in);
        String combatChoiceString;
        boolean hasMonster=true;
        boolean inCombat=false;
        int monsterToRemove = 0;
        int monsterHP;
        int playerHP=p1.getHealth();
        double dodgeCompare;
        for (int i = 0; i <Monster.monsterList.size();i++){
            if (currentRoom.getRoomNumber() == Monster.monsterList.get(i).getLocation()) {
                hasMonster=true;
                String tempName = Monster.monsterList.get(i).getName();
                ConsoleView.startCombatMessage(tempName);
                combatChoiceString=combatChoice.nextLine();
                if(combatChoiceString.toLowerCase().contains("yes"))
                {
                    inCombat=true;
                    combatChoiceString="";
                    while(inCombat) {
                        while ((Monster.monsterList.get(i).getHealth() >= 0 || p1.getHealth()>=0) && inCombat) {
                            ConsoleView.inCombatMessage();
                            combatChoiceString=combatChoice.nextLine();
                            if (combatChoiceString.toLowerCase().contains("attack")) {
                                monsterHP=Monster.monsterList.get(i).getHealth()-p1.getStrength();
                                System.out.println(Monster.monsterList.get(i).getName() + " hits you for "+Monster.monsterList.get(i).getDamage()+" HP");
                                playerHP=playerHP-Monster.monsterList.get(i).getDamage();
                                p1.setHealth(playerHP);
                                Monster.monsterList.get(i).setHealth(monsterHP);
                                    if(monsterHP<=0){
                                    monsterToRemove=i;
                                    hasMonster=false;
                                    inCombat=false;
                                    }
                            } else if (combatChoiceString.toLowerCase().contains("block")) {
                                playerHP=playerHP-(Monster.monsterList.get(i).getDamage()/2);
                                p1.setHealth(playerHP);
                                System.out.println("Reduced damage by half");
                            } else if (combatChoiceString.toLowerCase().contains("run")) {
                                inCombat=false;
                                ConsoleView.playerEscapeCombat(Monster.monsterList.get(i).getName());
                                currentRoom=map.get(p1.getLastVisited());
                            } else if (combatChoiceString.toLowerCase().contains("dodge")) {
                                dodgeCompare=Math.random();
                                if(dodgeCompare>p1.getSpeed()) {
                                    System.out.println("Attack Avoided");
                                }
                                else {
                                    System.out.println("Dodge failed! Full Damage taken!");
                                    playerHP=playerHP-Monster.monsterList.get(i).getDamage();
                                    p1.setHealth(playerHP);
                                }
                            } else {
                                ConsoleView.invalidCombatOption();
                            }
                            if (p1.getHealth()==0){
                                Model.gameOver(Monster.monsterList.get(i).getName());

                            }
                            System.out.println("\nMonster current health: "+ Monster.monsterList.get(i).getHealth());
                            System.out.println("Your current health: "+p1.getHealth());
                        }
                    }
                }
                else{
                    ConsoleView.monsterInRoomMessage();
                }
            }
        }
        if (hasMonster==false){
            Monster.monsterList.remove(monsterToRemove);
            ConsoleView.noMonsterInRoomMessage();
            String retryString="";
            Scanner retryChoice=new Scanner(System.in);

            retryString=retryChoice.nextLine();

            if (retryString.toLowerCase().contains("yes")){
                ConsoleView.gameRestartedFromCheckpoint();
                currentRoom=map.get(p1.getLastVisited());
                p1.setHealth(4500);
            } else if (retryString.toLowerCase().contains("no")) {
                ConsoleView.thankYouForPlaying();
            }
        }
    }

    public static void gameOver( String monsterName){
        ConsoleView.gameOverMessage(monsterName);
    }

}
