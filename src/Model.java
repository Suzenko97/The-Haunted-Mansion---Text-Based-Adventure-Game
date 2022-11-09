import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Model {
    static HashMap<Double, Room> map;
    static Player p1 = new Player(1,0, 4500,1,.5);
    Monster monster = new Monster();
    static Room currentRoom;
    // [HOLLY] TreasureChestMap -> Maps treasure chests to a room
    static HashMap<Double, TreasureChest> treasureChestMap = new HashMap<>();
    static Puzzle puzzle = null;

    public static void assignPuzzle(){
        Random rand = new Random();
        int number = rand.nextInt(Puzzle.puzzleList.size());
        System.out.println(number);
        puzzle = Puzzle.puzzleList.get(number);
        Puzzle.puzzleList.remove(puzzle);
    }

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
            // Read item name, description, strength points and room number
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

        // Omar Puzzle SetUp //
        fileName = "puzzle_data.txt";
        theFile = new File(fileName);
        inputFile = new Scanner(theFile);

        while(inputFile.hasNextLine()){
            int PuzzleID = Integer.parseInt(inputFile.nextLine());
            String PuzzleQues = inputFile.nextLine();
            String PuzzleAns = inputFile.nextLine();
            int Attempts = Integer.parseInt(inputFile.nextLine());
            Puzzle PuzzleOBJ = new Puzzle(PuzzleID, PuzzleQues, PuzzleAns, Attempts);
            Puzzle.puzzleList.add(PuzzleOBJ);
        }
        inputFile.close();

        // [HOLLY] -> Treasure Chest File Reading
        fileName = "treasure_data.txt";
        theFile = new File(fileName);
        inputFile = new Scanner(theFile);
        while (inputFile.hasNextLine()) {
            String treasureChestName = inputFile.nextLine();
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
                int points = Integer.parseInt(inputFile.nextLine());
                PowerUp powerUp = new PowerUp(itemName,itemDescription,type,points);
                powerUps.add(powerUp);
                try{
                    readLine = inputFile.nextLine();
                }catch (NoSuchElementException e){
                    // reached end of file
                }
            }
            TreasureChest treasureChest = new TreasureChest(powerUps,treasureChestName);
            for(Double roomNumber : rooms){
                treasureChestMap.put(roomNumber, treasureChest);
            }
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
        boolean inInventory = false;
        for (Item item : currentRoom.getRoomInventory()) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                inInventory = true;
                p1.addToInventory(item);
                // remove item from room
                currentRoom.removeItem(item);
                ConsoleView.successMessage(itemName + " has been picked up");
            }
        }
        if(!inInventory){
            ConsoleView.showErrorMessage("Item not in room");
        }
    }

    //[HOLLY] Drop Item -> drops item from inventory
    public static void dropItem(String itemName) {
        Item itemToRemove = null;
        boolean inInventory = false;
        for (Item item : p1.getPlayerInventory()) {
            //if item in inventory + not equipped
            if (item.getItemName().equalsIgnoreCase(itemName) && !p1.getEquippedItems().contains(itemName.toUpperCase())) {
                inInventory = true;
                itemToRemove = item;
            }
        }
        if(inInventory) {
            p1.removeFromInventory(itemToRemove);
            // add item to room
            currentRoom.addItem(itemToRemove);
        }else{
            ConsoleView.showErrorMessage("item not in inventory");
        }
    }

    //[HOLLY] Equip Item -> Equips an inventory item
    public static void equipItem(String itemName) {
        boolean equipped = p1.getEquippedItems().contains(itemName.toUpperCase());
        if(!equipped){
            for (Item item : p1.getPlayerInventory()) {
                // if item in inventory -> equip
                if (item.getItemName().equalsIgnoreCase(itemName)) {
                    p1.addToEquipped(item.getItemName().toUpperCase());
                    // if item is weapon, increase strength points
                    boolean isWeapon = item instanceof Weapon;
                    if (isWeapon) {
                        p1.setStrength(p1.getStrength() + ((Weapon) item).strengthPoints);
                        ConsoleView.successMessage("Player Strength has been increased " + p1.getStrength());
                    }
                    ConsoleView.successMessage(itemName + " has been equipped\nEquipped Items: " + p1.getEquippedItems().toString());
                }
            }
        }
        else{
            ConsoleView.showErrorMessage("Item already equipped");
        }
    }

    //[HOLLY] unequipItem -> Unequips an inventory item
    public static void unequipItem(String itemName) {
        boolean equipped = p1.getEquippedItems().contains(itemName.toUpperCase());
        // if item is equipped
        if(equipped){
            for (Item item : p1.getPlayerInventory()) {
                // if item in inventory
                if (item.getItemName().equalsIgnoreCase(itemName)) {
                    p1.removeFromEquipped(item.getItemName().toUpperCase());
                    // if item is weapon, remove strength points
                    boolean isWeapon = item instanceof Weapon;
                    if (isWeapon) {
                        p1.setStrength(p1.getStrength() - ((Weapon) item).strengthPoints);
                        ConsoleView.successMessage(itemName + " has been unequipped\nEquipped Items:"  + p1.getEquippedItems().toString()+ "\n[NOTE: Unequipping a weapon decreases the player's strength]");
                    }else{
                        ConsoleView.successMessage(itemName + " has been unequipped\nEquipped Items:" + p1.getEquippedItems().toString() );
                    }
                }
            }
        }else{
            ConsoleView.showErrorMessage("Item not equipped");
        }
    }

    // [HOLLY] -> Inspect Item -> Inspects item if it is inventory
    public static void inspectItem(String itemName) {
        if(p1.inventoryContains(itemName)){
            ConsoleView.showItemDesc( p1.inventory.get(itemName).inspect());
        }
    }
    // [HOLLY] chestCheck -> checks if current room has treasure chest
    public static boolean chestCheck(){
        return treasureChestMap.containsKey(Model.currentRoom.getRoomNumber());
    }
    // [HOLLY] -> opens treasure chest if it in the current room [NOTE: ADD CHECK MONSTER TO IF CONDITIONAL]
    public static void openChest(){
            ConsoleView.treasureMessage(treasureChestMap.get(currentRoom.getRoomNumber()).open());
    }

    // [HOLLY] -> activates chosen power up
    public static void activatePowerup(String powerUp){
        LinkedList<PowerUp> avaliblePowerups = treasureChestMap.get(currentRoom.getRoomNumber()).getPowerups();
        for(PowerUp p : avaliblePowerups){
            if(p.type.equalsIgnoreCase(powerUp)){
                p.activate(p1);
                ConsoleView.successMessage(p1.getStats());
                treasureChestMap.remove(currentRoom.getRoomNumber());
            }
        }

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
                            if (!(Monster.monsterList.get(i).getHealth() <= 0)) {
                                System.out.println("\nMonster current health: " + Monster.monsterList.get(i).getHealth());
                                System.out.println("Your current health: " + p1.getHealth());
                            }
                            else {
                                p1.addKeyPieces(1);
                                System.out.println("Yay you killed it.");
                                System.out.println("You've acquired 1/4 of the key.\n");
                            }
                        }
                    }
                }
                else{
                    ConsoleView.monsterInRoomMessage();
                }
            }
        }
        if (!hasMonster){
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

    /*[NAJEE]*/
    public static void quitGame() {
        System.exit(0);
    }

    /* Omar Puzzle */
    public static void solvePuzzle(){
        int chances = 0;
        Scanner scan = new Scanner(System.in);
        String puzzleChoiceString="";
            System.out.println("Solve puzzle?");
        puzzleChoiceString = scan.nextLine();
        if (puzzleChoiceString.toLowerCase().contains("yes")) {
            System.out.println(puzzle.getPuzzleQues());
            while (chances != puzzle.getAttempts()) {
                String answer = scan.nextLine();
                if (puzzle.getPuzzleAns().equalsIgnoreCase(answer)) {
                    System.out.println("The answer is correct.");
                    openChest();
                    String chosenPowerUp = scan.nextLine();
                    chosenPowerUp = chosenPowerUp.toLowerCase();
                    activatePowerup(chosenPowerUp);
                    break;
                } else {
                    System.out.println("The answer is incorrect, try again!");
                    chances += 1;
                }
            }
            if (chances == puzzle.getAttempts()) {
                System.out.println("You're out of attempts.\n");

            }
        }
    }
}
