import java.util.ArrayList;

public class Player {

    private double location;
    private int strength;
    private int health;
    private double lastVisited;
    private double speed;




    public Player (double initRoom,int strength,int health,double lastVisited,double speed){
        this .location = initRoom;
        this.strength=strength;
        this.health=health;
        this.lastVisited=lastVisited;
        this.speed=speed;
    }

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

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
