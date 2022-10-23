import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Model {
    static HashMap<Double, Room> map;
    static Player p1 = new Player(1);
    Monster monster = new Monster();
    static Room currentRoom;

    public static StringBuilder getRoom() {
        return currentRoom.getDesc();
    }

    public static StringBuilder getDirectionList() {
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

    /*[NAJEE]*/
    public static boolean movePlayer(String direction) {
        double[] directionOptions = currentRoom.getDirections();
        boolean success = false;
        switch (direction.toLowerCase()) {
            case "north":
                if (directionOptions[0] != (double) 0) {
                    currentRoom = map.get(directionOptions[0]);
                    p1.setLocation(currentRoom.getRoomNumber());
                    success = true;
                }
                break;
            case "south":
                if (directionOptions[1] != (double) 0) {
                    currentRoom = map.get(directionOptions[1]);
                    p1.setLocation(currentRoom.getRoomNumber());
                    success = true;
                }
                break;
            case "east":
                if (directionOptions[2] != (double) 0) {
                    currentRoom = map.get(directionOptions[2]);
                    p1.setLocation(currentRoom.getRoomNumber());
                    success = true;
                }
                break;
            case "west":
                if (directionOptions[3] != (double) 0) {
                    currentRoom = map.get(directionOptions[3]);
                    p1.setLocation(currentRoom.getRoomNumber());
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
    public static void  inspectMonster() {
        String monsterStatus = "";
        for (int i = 0; i < Monster.monsterList.size(); i++) {
//            System.out.println(p1.getLocation());
//            System.out.println(Monster.monsterList.get(i).getLocation());
            if (p1.getLocation() == Monster.monsterList.get(i).getLocation()) {
                 monsterStatus = Monster.monsterList.get(i).getName() + "\n"
                        + Monster.monsterList.get(i).getDescription();
                 i=10;
            } else {
                monsterStatus= "No monster in the room";
            }
        }
         ConsoleView.monsterInspectMessage(monsterStatus);
    }

    ////[KELVIN]////
    public void startCombat() {
        for (int i = 0; i <=Monster.monsterList.size();i++){
            if (p1.getLocation() == Monster.monsterList.get(i).getLocation()) {
                String tempName = Monster.monsterList.get(i).getName();
                ConsoleView.startCombatMessage(tempName);
            }
        }
    }

}
