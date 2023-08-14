package RPG.Players;


import RPG.Items.Armors.Armor;
import RPG.Items.Armors.HeavyArmor;
import RPG.Items.Bag;
import RPG.Items.Item;
import RPG.Items.Potions.Health;
import RPG.Items.Weapons.Sword;
import RPG.Items.Weapons.Weapon;
import RPG.Realm;

import java.util.List;

public class Player extends Entity {
    private Bag inventory;

    private Sword equippedSword;
    private HeavyArmor equippedHeavyArmor;


    public Player(String name, int healthPoints, int strength, int agility, int xp, int gold) {
        super(name, healthPoints, strength, agility, xp, gold);
        inventory = new Bag();
    }


    public List<Item> getItemsInBag() {
        return inventory.getItems();
    }

    public void addItemToInventory(Item item) {
        inventory.addItem(item, true);
    }

    public void removeItemFromInvenrory(Item item) {
        inventory.removeItem(item);
    }

    public void printItemsInBag() {
        System.out.print("Предметы в сумке игрока " + inventory.getItems().size() + "/10, Золото( " + getGold() + " ) : \n");
        int count = 1;
        for (Item item : getItemsInBag()) {
            System.out.print(count + ". " + item + " ");
            if (item.equals(equippedSword)) {
                System.out.print("(Используется)");
            }
            System.out.println(" ");
            count++;
        }

        System.out.println("\n");
    }

    public void equipWeapon(int index) {
        Item item = inventory.getItem(index - 1);
        if (equippedSword == null) {
            if (item instanceof Sword) {
                System.out.println("Вы взяли: " + item + "\n");
                equippedSword = (Sword) item;
                setBonusDamage(+equippedSword.getDamage());
                setBonusStrange(+equippedSword.getBonusStrength());
                setBonusAgility(+equippedSword.getBonusAgility());
                calculateDamage();
            } else {
                System.out.println("Этот предмет нельзя использовать как оружие.\n");
            }
        } else {
            unequipWeapon();
            equipWeapon(index);
        }


    }

    public void unequipWeapon() {
        if (equippedSword != null) {
            System.out.println("Вы убрали: " + equippedSword);
            setBonusDamage(-equippedSword.getDamage());
            setBonusStrange(-equippedSword.getBonusStrength());
            setBonusAgility(-equippedSword.getBonusAgility());
            calculateDamage();
            equippedSword = null;
        } else {
            System.out.println("У вас нет в руках меча");
        }
    }


    public void equipArmor(int index) {
        Item item = inventory.getItem(index - 1);
        if (equippedHeavyArmor == null) {
            if (item instanceof HeavyArmor) {
                System.out.println("Вы одели доспех.");
                equippedHeavyArmor = (HeavyArmor) item;
                setBonusStrange(+equippedHeavyArmor.getBonusStrange());
                setBonusProtection(+equippedHeavyArmor.getProtectionBonus());
                setHealthPoints(+equippedHeavyArmor.getBonusHealth());
                calculateDamage();
            } else {
                System.out.println("Этот предмет нельзя надет ");
            }
        } else {
            unequipArmor();
            equipArmor(index);
        }
    }

    public void unequipArmor() {
        if (equippedHeavyArmor != null) {
            System.out.println("Вы сняли: " + equippedHeavyArmor);
            setBonusStrange(-equippedHeavyArmor.getBonusStrange());
            setBonusProtection(-equippedHeavyArmor.getProtectionBonus());
            setHealthPoints(-equippedHeavyArmor.getBonusHealth());
            calculateDamage();
            equippedHeavyArmor = null;
        } else {
            System.out.println("Вы голый!");
        }

    }


    public boolean hasHealthPotion() {
        for (Item item : inventory.getItems()) {
            if (item instanceof Health) {
                return true;
            }
        }
        return false;
    }

    public void useHealthPotion() {
        for (Item item : inventory.getItems()) {
            if (item instanceof Health) {
                int healthPoint = ((Health) item).getPoints();
                System.out.println("Вы использовали зелье исцеления.");
                if (getCurrentHealtPoints() + healthPoint <= getHealthPoints()) {
                    setCurrentHealtPoints(getCurrentHealtPoints() + healthPoint);
                } else {
                    setCurrentHealtPoints(getHealthPoints());
                }
                inventory.removeItem(item);
                return;
            }
        }
        System.out.println("У вас нет зелья исцеления в инвентаре.\n");
    }


    public void printNavigationInventory() {
        System.out.println("1. Взять оружие");
        System.out.println("2. Снять оружие");
        System.out.println("3. Надеть защиту");
        System.out.println("4. Снять защиту");
        System.out.println("5. Использовать зелье");
        System.out.println("6. Показать инвентарь");
        System.out.println("7. Показать статистику игрока");
        System.out.println("8. Закрыть инвентарь\n");

        processChoiceInventory(Realm.readIntegerInput());
    }

    private void processChoiceInventory(int choice) {
        switch (choice) {
            case 1:
                printItemsInBag();
                equipWeapon(Realm.readIntegerInput());
                printNavigationInventory();
                break;
            case 2:
                unequipWeapon();
                printNavigationInventory();
                break;
            case 3:
                printItemsInBag();
                equipArmor(Realm.readIntegerInput());
                printNavigationInventory();
                break;
            case 4:
                unequipArmor();
                printNavigationInventory();
                break;
            case 5:
                useHealthPotion();
                printNavigationInventory();
                break;
            case 6:
                printItemsInBag();
                printNavigationInventory();
                break;

            case 7:
                getStatistics();
                printNavigationInventory();
                break;
            case 8:
                Realm.printNavigationWorld();
                break;

        }
    }


    public Weapon getWeapon() {
        return equippedSword;
    }

    public Armor getArmor() {
        return equippedHeavyArmor;
    }


    @Override
    public String toString() {
        return super.toString() + ", золото: " + getGold() + ", опыт: " + getXp();
    }
}
