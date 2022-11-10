import java.util.LinkedList;

//[NAJEE, HOLLY]//
public class Room {
    private  double roomNumber;
    private  String roomName;
    private  String desc;
    // [HOLLY] Room Inventory Attribute ->  list of items available in room
    private final LinkedList<Item> roomInventory = new LinkedList<>();
    private double[] directions = new double[4];
    private boolean visited = false;

    public Room(double roomNumber, String roomName, String desc) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.desc = desc;
    }

    public double getRoomNumber() {
        return roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public StringBuilder getDesc() {
        return formatDesc();
    }

    //[NAJEE]//
    public void setVisitedStatus (){
        if (!visited){
            this.visited = true;
            Model.compass += 1;
        }
    }

    public double[] getDirections() {
        return this.directions;
    }

    public void setDirections(double[] array) {
        this.directions = array;
    }

    // [HOLLY] addItem -> Add item to Room's Inventory
    public void addItem(Item item) { this.roomInventory.add(item);}
    // [HOLLY] removeFromRoom -> Remove item from Room's Inventory
    public void removeItem(Item item) {
        this.roomInventory.remove(item);
    }
    // [HOLLY] removeFromRoom -> Remove item from Room's Inventory
    public String inspectRoom() {
        return roomName + " : " + this.roomInventory;
    }

    // [HOLLY] getRoomInventory -> Retrieve Room's Inventory
    public LinkedList<Item> getRoomInventory() {
        return roomInventory;
    }

    //[NAJEE]//
    public StringBuilder formatDesc() {
        StringBuilder tmp = new StringBuilder();
        String[] paragraph = this.desc.split("\\.+\\s");
        for (String s : paragraph) {
            tmp.append(s).append("\n");
        }
        return tmp;
    }
}
