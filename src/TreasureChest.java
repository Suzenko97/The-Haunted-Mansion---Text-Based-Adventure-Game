import java.util.HashMap;
import java.util.LinkedList;

//[HOLLY]//
public class TreasureChest {
    private String treasureChestName;
    private LinkedList<PowerUp> powerups;


    public TreasureChest(LinkedList<PowerUp> powerups, String treasureChestName) {
        this.powerups = powerups;
        this.treasureChestName = treasureChestName;

    }
    public LinkedList<PowerUp> open(){
        return powerups;
    }

    public LinkedList<PowerUp> getPowerups() {
        return powerups;
    }

    @Override
    public String toString() {
        return treasureChestName + ": " + powerups.toString();
    }
}
