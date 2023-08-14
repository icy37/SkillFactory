package RPG.Items.Potions;

import RPG.Items.Item;

public class Health extends Item {
       private int points;

    public Health(String name, int points, int gold) {
        super(name, gold);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return getName() + " - лечение: +" + points + ", стоимость: " + getGold();
    }
}
