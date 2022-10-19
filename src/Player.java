import java.util.ArrayList;

public class Player {
    private double location;

    public Player (double initRoom){
        this .location = initRoom;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
