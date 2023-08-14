package RPG.Items.Armors;


public class HeavyArmor extends Armor {

    private int bonusStrange;

    public HeavyArmor(String name, int gold, int bonusProtection, int bonusHealth, int bonusStrange) {
        super(name, gold, bonusProtection, bonusHealth);
        this.bonusStrange = bonusStrange;
    }

    public int getBonusStrange() {
        return bonusStrange;
    }

    public void setBonusStrange(int bonusStrange) {
        this.bonusStrange = bonusStrange;
    }


    @Override
    public String toString() {
        return getName() + " - защита: +" + getProtectionBonus() + ", бонус очков жизни: +" + getBonusHealth() +
                ", бонус силы: +" + getBonusStrange() + ", стоимость: " + getGold();
    }

}
