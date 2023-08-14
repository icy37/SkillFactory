package RPG.Items.Weapons;

import RPG.Items.Item;

public abstract class Weapon extends Item {


    private int damage;


    public Weapon(String name, int gold, int damage) {
        super(name, gold);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

}
