import java.util.*;

public class Player {
    private double location;
    private int strength;
    private int health;
    private double lastVisited;
    private double speed;


    public Player (double initRoom,int strength,int health,double lastVisited,double speed) {
        this.location = initRoom;
        this.strength = strength;
        this.health = health;
        this.lastVisited = lastVisited;
        this.speed = speed;
    }

    // [HOLLY] inventory -> collection of all pick up items
    public HashMap<String , Item > inventory = new HashMap();

    // [HOLLY] equippedItems -> collection of all equipped items on player
    private  LinkedList<String> equippedItems = new LinkedList<>();

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(double lastVisited) {
        this.lastVisited = lastVisited;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    // [HOLLY] -> addToInventory: adds specified item to player inventory
    public void addToInventory(Item item){
        inventory.put(item.getItemName(), item);
    }

    // [HOLLY] -> removeFromInventory: removes specified item from player's inventory
    public void removeFromInventory(Item item) {
        inventory.remove(item.getItemName());
    }

    // [HOLLY] -> getPlayerInventory: returns list of items in player's inventory
    public LinkedList<Item> getPlayerInventory(){
        LinkedList<Item> itemsInInventory = new LinkedList<>();
        for (Map.Entry<String, Item> item :inventory.entrySet()) {
            itemsInInventory.add(item.getValue());
        }
        return itemsInInventory;
    }

    // [HOLLY] -> addToEquipped: adds specified item to equippedItems
    public void addToEquipped(String item){
        equippedItems.add(item);
    }

    // [HOLLY] -> removeFromEquipped: removes specified item to equippedItems
    public void removeFromEquipped(String itemName){
        equippedItems.remove(itemName);
    }
    // [HOLLY] -> getEquippedItems: returns list of equipped items
    public LinkedList<String> getEquippedItems(){
        return this.equippedItems;
    }

    // [HOLLY] -> getStrength: returns player's current strength
    public int getStrength() {
        return strength;
    }
    // [HOLLY] -> setStrength: sets player's current strength
    public void setStrength(int strength) {
        this.strength = strength;
    }
    // [HOLLY] -> getHealth: returns player's current health
    public int getHealth() {
        return health;
    }
    // [HOLLY] -> setHealth: sets player's current health
    public void setHealth(int health) {
        this.health = health;
    }

    public boolean inventoryContains(String itemName){
        return inventory.containsKey(itemName);
    }

    public String getStats() {
        return "Health: " + health + "\nStrength: " + strength +"\nSpeed: " + speed;
    }
}
