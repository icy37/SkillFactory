package RPG.Items.Weapons;

public class Sword extends Weapon {

    private int bonusStrength;
    private int bonusAgility;



    public Sword(String name, int gold, int damage, int bonusStrength) {
        super(name, gold, damage);
        this.bonusStrength = bonusStrength;
        this.bonusAgility = 0;
    }

    public Sword(String name, int gold, int damage, int bonusStrength, int bonusAgility) {
        super(name, gold, damage);
        this.bonusStrength = bonusStrength;
        this.bonusAgility = bonusAgility;
    }

    public int getBonusStrength() {
        return bonusStrength;
    }

    public int getBonusAgility() {
        return bonusAgility;
    }


    @Override
    public String toString() {
        return getName() + " - урон: " + getDamage() +
                ", бонус силы: +" + bonusStrength + ", бонус ловкости: " +  bonusAgility + ", стоимость: " + getGold();
    }
}
