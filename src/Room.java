public class Room {
    private double roomNumber;
    private String roomName;
    private String desc;
    private double[] directions = new double[4];

    public Room(double roomNumber, String roomName, String desc) {
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.desc = desc;
        //this.puzzleID = puzzleID;
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

    public double[] getDirections() {
        return this.directions;
    }

    public void setDirections(double[] array) {
        this.directions = array;
    }

    ;

    public StringBuilder formatDesc() {
        StringBuilder tmp = new StringBuilder();
        String[] paragraph = this.desc.split("\\.+\\s");
        for (String s : paragraph) {
            tmp.append(s).append("\n");
        }
        return tmp;
    }
}
