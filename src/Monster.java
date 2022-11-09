import java.util.ArrayList;

public class Monster {


    private String name;
    private double location;
    private String description;
    private int health;
    private int damage;
    private String items;
    static ArrayList<Monster> monsterList= new ArrayList<Monster>();

    public Monster(String name, double location, String description, int health, int damage, String items){
        this.name=name;
        this.location=location;
        this.description=description;
        this.health=health;
        this.damage=damage;
        this.items=items;
    }

    public Monster() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
}
