package RPG.Items.Weapons;

public class Bow extends Weapon {

    private int bonusAgility;
    private int bonusStrength;


    public Bow(String name, int gold, int damage, int bonusAgility) {
        super(name, gold, damage);
        this.bonusAgility = bonusAgility;
        this.bonusStrength = 0;
    }

    public Bow(String name, int gold, int damage, int bonusAgility, int bonusStrength) {
        super(name, gold, damage);
        this.bonusAgility = bonusAgility;
        this.bonusStrength = bonusStrength;
    }

    public int getBonusAgility() {
        return bonusAgility;
    }


    public int getBonusStrength() {
        return bonusStrength;
    }


    @Override
    public String toString() {
        return getName() + " - урон: " + getDamage() +
                ", бонус ловкости: +" + bonusAgility + ", бонус силы: +" + bonusStrength + ", стоимость: " + getGold();
    }
}
