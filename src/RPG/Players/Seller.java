package RPG.Players;

import RPG.Items.Armors.Armor;
import RPG.Items.Armors.HeavyArmor;
import RPG.Items.Armors.LightArmor;
import RPG.Items.Bag;
import RPG.Items.Item;
import RPG.Items.Potions.Health;
import RPG.Items.Weapons.*;
import RPG.Realm;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Seller {

    private Player player;

    private Bag inventory;

    public Seller() {
        inventory = new Bag();
    }

    public List<Item> getItemsInBag() {
        return inventory.getItems();
    }

    public void generateInventory() {
        inventory.clear();

        Random random = new Random();
        int itemCount = random.nextInt(6) + 2;

        IntStream.range(0, itemCount)
                .forEach(i -> inventory.addItem(random.nextBoolean() ? generateRandomWeapon() : generateRandomArmor(), false));

        inventory.addItem(new Health("Зелье исцеления", 50, 20), false); // Обязательное зелье исцеления
    }

    public void printItemsInBag() {
        System.out.print("Предметы в сумке торговца " + inventory.getItems().size() + "/10 : \n");

        IntStream.range(0, inventory.getItems().size())
                .mapToObj(index -> (index + 1) + ". " + inventory.getItem(index))
                .forEach(System.out::println);

        System.out.println();
    }

    public void removeItemFromBag(Item item) {
        inventory.removeItem(item);
    }

    public void printNavigationSeller() {
        System.out.println("1. Купить");
        System.out.println("2. Продать");
        System.out.println("3. Посмотреть свой инвентарь");
        System.out.println("4. Выйти в город\n");

        processChoiceSeller(Realm.readIntegerInput());
    }

    public void trade(Player player) {
        this.player = player;
        player.printItemsInBag();
    }

    public void buyFromPlayer(int index) {
        Item item = player.getItemsInBag().get(index - 1);
        if (item == player.getWeapon()){
            System.out.println(item + " нельзя продать, т.к. используется");
        } else if (item == player.getArmor()) {
            System.out.println(item + " нельзя продать, т.к. надето");
        } else {
            player.setBonusGold(item.getGold());
            player.removeItemFromInvenrory(item);
            System.out.println("Вы успешно продали : " + item);
            System.out.println("Ваше золото: " + player.getGold());
        }
    }

    public void sellToPlayer(int index) {
        Item item = getItemsInBag().get(index - 1);
        if (player.getGold() < item.getGold()) {
            System.out.println("У вас не достаточно золота");
        } else {
            player.setBonusGold(-item.getGold());
            player.addItemToInventory(item);
            removeItemFromBag(item);
        }
    }

    private void processChoiceSeller(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Какой предмет ходите купить? : ");
                printItemsInBag();
                sellToPlayer(Realm.readIntegerInput());
                printNavigationSeller();

                break;
            case 2:
                System.out.println("Какой предмет ходите продать? : ");
                player.printItemsInBag();
                if (player.getItemsInBag().size() == 0) {
                    System.out.println("Вам нечего продать");
                } else {
                    buyFromPlayer(Realm.readIntegerInput());
                }
                printNavigationSeller();
                break;
            case 3:
                System.out.println("Посмотреть свой инвентарь");
                player.printItemsInBag();
                printNavigationSeller();
                break;
            case 4:
                Realm.printNavigationWorld();
                break;
        }
    }

    public static Weapon generateRandomWeapon() {
        Random random = new Random();
        int choice = random.nextInt(100);

        if (choice < 2) {
            if (random.nextBoolean()) {
                return new Sword("Королевский Меч", new Random().nextInt(50) + 550, new Random().nextInt(50) + 50, new Random().nextInt(8) + 7, new Random().nextInt(3) + 3);
            } else {
                return new Bow("Арбалет", new Random().nextInt(50) + 650, new Random().nextInt(70) + 50, new Random().nextInt(15) + 7, new Random().nextInt(3) + 3);
            }
        } else if (choice < 7) {
            if (random.nextBoolean()) {
                return new Sword("Острый Мечь", new Random().nextInt(50) + 100, new Random().nextInt(15) + 10, new Random().nextInt(3) + 1);
            } else {
                return new Bow("Длинный Лук", new Random().nextInt(50) + 140, new Random().nextInt(20) + 15, new Random().nextInt(3) + 1);
            }
        } else if (choice < 50) { // 45% шанс
            return new Bow("Лук", new Random().nextInt(10) + 50, new Random().nextInt(10) + 10, 0);
        } else { // 50% шанс
            return new Sword("Меч", new Random().nextInt(10) + 40, new Random().nextInt(10) + 7, 0);
        }
    }

    public static Armor generateRandomArmor() {
        Random random = new Random();
        int choice = random.nextInt(100);
        if (choice < 2) {
            if (random.nextBoolean()) {
                return new HeavyArmor("Легендарный Тяжелый Доспех", new Random().nextInt(50) + 350, new Random().nextInt(15) + 15, new Random().nextInt(30) + 15, new Random().nextInt(5) + 5);
            } else {
                return new LightArmor("Легендарный Легкий Доспех", new Random().nextInt(50) + 350, new Random().nextInt(10) + 10, new Random().nextInt(20) + 10, new Random().nextInt(13) + 3);
            }
        } else if (choice < 7) {
            if (random.nextBoolean()) {
                return new HeavyArmor("Усиленный Тяжелый Доспех", new Random().nextInt(30) + 220, new Random().nextInt(7) + 7, new Random().nextInt(20) + 8, new Random().nextInt(3) + 3);
            } else {
                return new LightArmor("Эластичный Легкий Доспех", new Random().nextInt(30) + 220, new Random().nextInt(5) + 5, new Random().nextInt(15) + 7, new Random().nextInt(7) + 3);
            }
        } else if (choice < 50) {
            return new HeavyArmor("Тяжелый Доспех", new Random().nextInt(15) + 110, new Random().nextInt(3) + 3, new Random().nextInt(10) + 4, new Random().nextInt(3));
        } else {
            return new LightArmor("Легкий Доспех", new Random().nextInt(15) + 110, new Random().nextInt(1) + 3, new Random().nextInt(7) + 5, new Random().nextInt(4));
        }
    }


}
