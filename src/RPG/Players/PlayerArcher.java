package RPG.Players;

import RPG.Items.Armors.LightArmor;
import RPG.Items.Item;
import RPG.Items.Weapons.Bow;


public class PlayerArcher extends Player{

    private Bow equippedBow;
    private LightArmor equippedLightArmor;

    public PlayerArcher(String name, int healthPoints, int strength, int agility, int xp, int gold) {
        super(name, healthPoints, strength, agility, xp, gold);
    }


    @Override
    public int calculateDamage() {
        return getAgility() + (getStrength() / 2) + getBonusDamage();
    }

    @Override
     public void levelUp() {
        setBonusAgility(3);
        setBonusStrange(1);
        setHealthPoints(20);
        if (getCurrentHealtPoints() < getHealthPoints()) {
            setCurrentHealtPoints(getHealthPoints());
        }
        calculateDamage();
        setLevel();
        System.out.println("Уровень повышен! Теперь уровень героя: " + getLevel() + ", сила: " + getStrength() + ", ловкость: " + getAgility() + ", сила удара: " + getDamage());
    }
    @Override
    public void equipWeapon(int index) {
        Item item = getItemsInBag().get(index - 1);
        if (equippedBow == null) {
            if (item instanceof Bow) {
                System.out.println("Вы взяли: " + item + "\n");
                equippedBow = (Bow) item;
                setBonusDamage(+equippedBow.getDamage());
                setBonusStrange(+equippedBow.getBonusStrength());
                setBonusAgility(+equippedBow.getBonusAgility());
                calculateDamage();
            } else {
                System.out.println("Этот предмет нельзя использовать как оружие.\n");
            }
        } else {
            unequipWeapon();
            equipWeapon(index);
        }


    }
    @Override
    public void unequipWeapon() {
        if (equippedBow != null) {
            System.out.println("Вы убрали: " + equippedBow);
            setBonusDamage(-equippedBow.getDamage());
            setBonusStrange(-equippedBow.getBonusStrength());
            setBonusAgility(-equippedBow.getBonusAgility());
            calculateDamage();
            equippedBow = null;
        } else {
            System.out.println("У вас нет в руках меча");
        }
    }


    @Override
    public void equipArmor(int index) {
        Item item = getItemsInBag().get(index - 1);
        if (equippedLightArmor == null) {
            if (item instanceof LightArmor) {
                System.out.println("Вы одели доспех.");
                equippedLightArmor = (LightArmor) item;
                setBonusAgility(+equippedLightArmor.getBonusAgility());
                setBonusProtection(+equippedLightArmor.getProtectionBonus());
                setHealthPoints(+equippedLightArmor.getBonusHealth());
                calculateDamage();
            } else {
                System.out.println("Этот предмет нельзя надет ");
            }
        } else {
            unequipArmor();
            equipArmor(index);
        }
    }
    @Override
    public void unequipArmor() {
        if (equippedLightArmor != null) {
            System.out.println("Вы сняли: " + equippedLightArmor);
            setBonusAgility(-equippedLightArmor.getBonusAgility());
            setBonusProtection(-equippedLightArmor.getProtectionBonus());
            setHealthPoints(-equippedLightArmor.getBonusHealth());
            calculateDamage();
            equippedLightArmor = null;
        } else {
            System.out.println("Вы голый!");
        }

    }
}
