package RPG.Items.Armors;


public class LightArmor extends Armor {
    private int bonusAgility;


    public LightArmor(String name, int gold, int bonusProtection, int healthBonus, int bonusAgility) {
        super(name, gold, bonusProtection, healthBonus);
        this.bonusAgility = bonusAgility;

    }

    public int getBonusAgility() {
        return bonusAgility;
    }

    public void setBonusAgility(int bonusAgility) {
        this.bonusAgility = bonusAgility;
    }


    @Override
    public String toString() {
        return getName() + " - защита: +" + getProtectionBonus() + ", бонус очков жизни: +" + getBonusHealth()
                + ", бонус ловкости: +" + getBonusAgility() + ", стоимость: " + getGold();
    }

}
