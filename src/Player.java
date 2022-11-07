import java.util.ArrayList;
import java.util.LinkedList;

public class Player {
    private double location;

    // [HOLLY] player strength, speed and health
    private int strength;
    private int speed;
    private int health;

    // [HOLLY] inventory -> collection of all pick up items
    private  LinkedList<Item> inventory = new LinkedList<>();

    // [HOLLY] equippedItems -> collection of all equipped items on player
    private  LinkedList<String> equippedItems = new LinkedList<>();

    public Player (double initRoom){
        this .location = initRoom;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    // [HOLLY] -> addToInventory: adds specified item to player inventory
    public void addToInventory(Item item){
        inventory.add(item);
    }

    // [HOLLY] -> removeFromInventory: removes specified item from player's inventory
    public void removeFromInventory(Item item) {
        inventory.remove(item);
    }

    // [HOLLY] -> getPlayerInventory: returns list of items in player's inventory
    public LinkedList<Item> getPlayerInventory(){
        return this.inventory;
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
    // [HOLLY] -> getSpeed: returns player's current speed
    public int getSpeed() {
        return speed;
    }
    // [HOLLY] -> setSpeed: sets player's current speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    // [HOLLY] -> getHealth: returns player's current health
    public int getHealth() {
        return health;
    }
    // [HOLLY] -> setHealth: sets player's current health
    public void setHealth(int health) {
        this.health = health;
    }

    public Item inventoryContains(String itemName){
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public String getStats() {
        return "Health: " + health + "\nStrength: " + strength +"\nSpeed: " + speed;
    }
}
