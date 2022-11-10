//[HOLLY]//
public class PowerUp extends Item {
    int points;
    String type;

    public PowerUp(String itemName, String itemDescription, String type, int points) {
        super(itemName, itemDescription);
        this.points = points;
        this.type = type.toLowerCase();
    }

    public void activate(Player player) {
        switch (type){
            case "strength":
                player.setStrength(player.getStrength() + points);
                break;
            case "speed":
                player.setSpeed(player.getSpeed() + points);
                break;
            case "health":
                player.setHealth(player.getHealth() + points);
                break;
        }
    }

}
