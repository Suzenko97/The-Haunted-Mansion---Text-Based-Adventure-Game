import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Model {
    public static Room currentRoom;
    public static final String ANSI_PURPLE = "\033[0;35m";
    public static final String ANSI_RED = "\033[0;31m";
    public static final String ANSI_RESET = "\u001B[0m";
    static HashMap<Double, Room> map;
    // [HOLLY] TreasureChestMap -> Maps treasure chests to a room
    //static HashMap<Double, TreasureChest> treasureChestMap = new HashMap<>();
    static HashMap<Double, TreasureChest> treasureChestMap = new HashMap<>();

    static Player p1 = new Player(1);

    public static StringBuilder getRoom() {
        return currentRoom.getDesc();
    }

    public static StringBuilder getDirectionList() {
        double[] tmpArr = currentRoom.getDirections();
        StringBuilder dirList = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            //go get the room name for the corresponding direction, add to the string with new line after
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
                    success = true;
                }
                break;
            case "south":
                if (directionOptions[1] != (double) 0) {
                    currentRoom = map.get(directionOptions[1]);
                    success = true;
                }
                break;
            case "east":
                if (directionOptions[2] != (double) 0) {
                    currentRoom = map.get(directionOptions[2]);
                    success = true;
                }
                break;
            case "west":
                if (directionOptions[3] != (double) 0) {
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

        // [HOLLY] -> Item File Reading
        fileName = "item_data.txt";
        theFile = new File(fileName);
        inputFile = new Scanner(theFile);

        while (inputFile.hasNextLine()) {
            // Read item name, description, and room number
            String itemName = inputFile.nextLine();
            String itemDescription = inputFile.nextLine();
            Double roomNumber = Double.parseDouble(inputFile.nextLine());
            Room itemRoom = tmpMap.get(roomNumber);
            // Create item
            Item item = new Item(itemName, itemDescription);
            // add Item to room
            itemRoom.addItem(item);
        }
        inputFile.close();

        // [HOLLY] -> Weapon [Item] File Reading
        fileName = "weapon_data.txt";
        theFile = new File(fileName);
        inputFile = new Scanner(theFile);

        while (inputFile.hasNextLine()) {
            // Read item name, description, and room number
            String itemName = inputFile.nextLine();
            String itemDescription = inputFile.nextLine();
            int strengthPoints = Integer.parseInt(inputFile.nextLine());
            Double roomNumber = Double.parseDouble(inputFile.nextLine());
            Room itemRoom = tmpMap.get(roomNumber);
            // Create item
            Item item = new Weapon(itemName, itemDescription, strengthPoints);
            // add Item to room
            itemRoom.addItem(item);
        }
        inputFile.close();

        // [HOLLY] -> Treasure Chest File Reading
        fileName = "treasure_data.txt";
        theFile = new File(fileName);
        inputFile = new Scanner(theFile);
        while (inputFile.hasNextLine()) {
            String treasureChestName = inputFile.nextLine();
            //Double roomNumber = Double.parseDouble(inputFile.nextLine());
            LinkedList<Double> rooms = new LinkedList<Double>();

            String roomsString = inputFile.nextLine();
            if(roomsString.contains(",")){
                String[] listOfRooms = roomsString.split(",");
                for(String room : listOfRooms){
                    rooms.add(Double.parseDouble(room));
                }
            }else{
                rooms.add(Double.parseDouble(roomsString));
            }

            LinkedList<PowerUp> powerUps = new LinkedList<>();
            String readLine = inputFile.nextLine();
            while(!readLine.equals("----")){
                String itemName = readLine;
                String type = itemName;
                String itemDescription = inputFile.nextLine();
                Integer points = Integer.parseInt(inputFile.nextLine());
                PowerUp powerUp = new PowerUp(itemName,itemDescription,type,points);
                powerUps.add(powerUp);
                try{
                    readLine = inputFile.nextLine();
                }catch (NoSuchElementException e){
                    // reached end of file
                }
            }
            TreasureChest treasureChest = new TreasureChest(powerUps,treasureChestName);
            //treasureChestMap.put(roomNumber,treasureChest);
            for(Double roomNumber : rooms){
                treasureChestMap.put(roomNumber, treasureChest);
            }
            // TEST
//            treasureChestMap.put(treasureChest, rooms);
//            System.out.println(treasureChest);
//            System.out.println(treasureChestMap.toString());
        }


        map = tmpMap;
        currentRoom = map.get(p1.getLocation());
    }

    // [HOLLY]  Check Player Inventory
    public static void checkInventory() {
        ConsoleView.showInventory(p1.getPlayerInventory());
    }

    //[HOLLY]  Pick up Item - >  Add item to inventory
    public static void pickUpItem(String itemName) {
        for (Item item : currentRoom.getRoomInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                p1.addToInventory(item);
                // remove item from room
                currentRoom.removeItem(item);
                ConsoleView.successMessage(ANSI_PURPLE + itemName + " has been picked up" + ANSI_RESET);
            }
        }
    }

    //[HOLLY] Drop Item -> drops item from inventory
    public static void dropItem(String itemName) {
        LinkedList<Item> copyOfInventory = p1.getPlayerInventory();
        for (Item item : copyOfInventory ){
            //if item in inventory
            if (item.getItemName().equalsIgnoreCase(itemName) && !p1.getEquippedItems().contains(itemName.toUpperCase())) {
                    p1.removeFromInventory(item);
                    // add item to room
                    currentRoom.addItem(item);
                }
            }
    }

    //[HOLLY] Equip Item -> Equips an inventory item
    public static void equipItem(String itemName) {
        boolean inInventory = false;
        for (Item item : p1.getPlayerInventory()) {
            // if item in inventory -> equip
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                inInventory = true;
                p1.addToEquipped(item.getItemName().toUpperCase());
                // if item is weapon, increase strength points
                boolean isWeapon = item instanceof Weapon;
                if (isWeapon) {
                    p1.setStrength(p1.getStrength() + ((Weapon) item).strengthPoints);
                    // TEST LINE -> DELETE LATER
                    System.out.println("Player Strength: " + p1.getStrength());
                }
                // TEST LINE -> DELETE LATER
                System.out.println("Equipped: " + p1.getEquippedItems().toString());
            }
        }
        if (!inInventory) {
            ConsoleView.showErrorMessage("Item not in inventory");
        }
    }

    //[HOLLY] unequipItem -> Unequips an inventory item
    public static void unequipItem(String itemName) {
        boolean equipped = p1.getEquippedItems().contains(itemName.toUpperCase());
        // checks if item is equipped
        if(equipped){
            for (Item item : p1.getPlayerInventory()) {
                // if item in inventory
                if (item.getItemName().equalsIgnoreCase(itemName)) {
                    p1.removeFromEquipped(item.getItemName().toUpperCase());
                    // if item is weapon, remove strength points
                    boolean isWeapon = item instanceof Weapon;
                    if (isWeapon) {
                        p1.setStrength(p1.getStrength() - ((Weapon) item).strengthPoints);
                        // TEST LINE -> DELETE LATER
                        System.out.println("Player Strength: " + p1.getStrength());
                    }
                }
            }
        }else{
            ConsoleView.showErrorMessage(ANSI_PURPLE + "Item not equipped" + ANSI_RESET);
        }
    }

    // [HOLLY] -> Inspect Item -> Inspects item if it is inventory
    public static void inspectItem(String itemName) {
        for (Item item : p1.getPlayerInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                ConsoleView.showItemDesc( ANSI_PURPLE + item.inspect() + ANSI_RESET);
            }
        }

    }
    // [HOLLY] chestCheck -> checks if current room has treasure chest
    public static boolean chestCheck(){
        return treasureChestMap.containsKey(Model.currentRoom.getRoomNumber());
    }
    // [HOLLY] -> opens treasure chest if it in the current room [NOTE: ADD CHECK MONSTER TO IF CONDITIONAL]
    public static void openChest(){
        if(chestCheck()){
            ConsoleView.treasureMessage(treasureChestMap.get(currentRoom.getRoomNumber()).open());
        }
    }

    // [HOLLY] -> activates chosen power up
    public static void activatePowerup(String powerUp){
        LinkedList<PowerUp> avaliblePowerups = treasureChestMap.get(currentRoom.getRoomNumber()).getPowerups();
        for(PowerUp p : avaliblePowerups){
            if(p.type.equalsIgnoreCase(powerUp)){
                p.activate(p1);
                System.out.println(ANSI_PURPLE + p1.getStats() + ANSI_RESET);
                treasureChestMap.remove(currentRoom.getRoomNumber());
            }
        }

    }
    /*[NAJEE]*/
    public static void quitGame() {
        System.exit(0);
    }

}
