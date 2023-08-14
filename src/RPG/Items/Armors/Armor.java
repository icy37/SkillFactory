package RPG.Items.Armors;

import RPG.Items.Item;

public abstract class Armor extends Item {

    private int bonusProtection;
    private int bonusHealth;

    public Armor(String name, int gold, int bonusProtection, int bonusHealth) {
        super(name, gold);
        this.bonusProtection = bonusProtection;
        this.bonusHealth = bonusHealth;
    }

    public int getProtectionBonus() {
        return bonusProtection;
    }

    public int getBonusHealth() {
        return bonusHealth;
    }
}
