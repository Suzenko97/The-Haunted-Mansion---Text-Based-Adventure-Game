/*An object in the game that can be collected to help improve certain aspects of the player,
help to defend against enemies, or initiate  puzzles.
 */

public class Item {
    String itemName;
    String itemDescription;


    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }
    public String toString() {
        return itemName;
    }
    public String inspect() {
        return itemDescription;
    }


}
