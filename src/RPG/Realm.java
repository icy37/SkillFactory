package RPG;

import RPG.Interfaces.FightCallback;
import RPG.Items.Potions.Health;
import RPG.Items.Weapons.Bow;
import RPG.Items.Weapons.Sword;
import RPG.Players.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class Realm {
    private static BufferedReader br;
    private static Player player;
    private static BattleScene battleScene;

    private static int currentLocation;

   private static boolean dragoEnable;
    private static Entity monster;

    private static Seller seller;

    public static void main(String[] args) {

        br = new BufferedReader(new InputStreamReader(System.in));
        //Инициализируем класс для боя
        battleScene = new BattleScene();
        seller = new Seller();
        //Первое, что нужно сделать при запуске игры, это создать персонажа, поэтому мы предлагаем ввести его имя
        System.out.println("Введите имя персонажа:");
        //Далее ждем ввод от пользователя
        try {
            createHero(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createHero(String string) throws IOException {

        System.out.println("За кого хотите играть:\n1. Мечник\n2. Лучник\n");
        switch (readIntegerInput()) {
            case 1:
                if (player == null) {
                    player = new Player(
                            string,
                            70,
                            5,
                            3,
                            0,
                            20
                    );
                }
                break;
            case 2:
                if (player == null) {
                    player = new PlayerArcher(
                            string,
                            50,
                            3,
                            7,
                            0,
                            20
                    );
                }
                break;
            default:
                System.out.println("Введите правильное число");
                break;
        }


            System.out.println(String.format("""
                    \n
                    Бла, Бла, Бла .... Да спасет всех нас %s""", player.getName() + "\n"));


            player.addItemToInventory(new Health("Зелье исцеления", 50, 20));
            seller.generateInventory();

            printNavigationWorld();


        }


    public static int readIntegerInput() {
        while (true) {
            try {
                System.out.print("Введите число: ");
                return Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите корректное число.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void printNavigationWorld() {
        System.out.println("Куда вы хотите пойти?\n");

        System.out.println("1. Лес");
        System.out.println("2. Долина");
        System.out.println("3. Горы");
        System.out.println("4. Торговец");
        System.out.println("5. Открыть инвентарь");
        System.out.println("6. Выход из игры\n");

        processChoiceWorld(readIntegerInput());

    }

   private static void processChoiceBattle(int location) {
        monster = createMonster(location);
       System.out.println("На вас напал: " + monster);
       System.out.println("Хотите сами сражаться или доверить бой глупому PC\n1. Буду сам сражаться\n2. Доверюсь глупому PC\n");
        switch (readIntegerInput()) {
            case 1:
                commitFight(monster, location,false);
                break;
            case 2:
                commitFight(monster, location,true);
                break;
            default:
                System.out.println("Некорректный выбор\n");
                processChoiceBattle(location);
        }
   }


    private static void processChoiceWorld(int choice) {
        switch (choice) {
            case 1:
                //Код для мира FOREST
                //printNavigationBattle();
                currentLocation = 1;
                System.out.println("Вы вошли в лес");
                processChoiceBattle(currentLocation);

                break;
            case 2:
                // Код для мира VALLEY
                currentLocation = 2;
                System.out.println("Вы пришли на равнины");
                processChoiceBattle(currentLocation);
                break;
            case 3:
                // Код для мира MOUNTAINS
                currentLocation = 3;
                System.out.println("Вы забрались на гору");
                processChoiceBattle(currentLocation);
                break;
            case 4:
                System.out.println("Вы пришли к торговцу\n");
                seller.trade(player);
                seller.printItemsInBag();
                seller.printNavigationSeller();
                break;
            case 5:
                System.out.println("Вы открыли инвентарь\n");
                player.getStatistics();
                player.printItemsInBag();
                player.printNavigationInventory();

                break;
            case 6:
                System.exit(0); // Завершение программы
                break;
            default:
                System.out.println("Некорректный выбор\n");
                printNavigationWorld();
                break;
        }
    }

    public static void commitFight(Entity monster, int location, boolean startAutoBattle) {
        battleScene.fight(player, monster, startAutoBattle, new FightCallback() {
            int answer;
            @Override
            public void fightWin() {

                System.out.println(String.format("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d единиц здоровья.", player.getName(), player.getXp(), player.getGold(), player.getCurrentHealtPoints()));
                if ((player.getLevel() >= 7 && location == 3 && new Random().nextInt(100) > 90) || dragoEnable) {
                    System.out.println("Перед вами появился путь на самую вершину горы, откуда исходит устрашающий вой");
                    System.out.println("Решитесь ли пойти по этой тропе или испугались\n1. Пойду\n2. Наложил в штаны\n");
                    dragoEnable = true;
                    answer = readIntegerInput();
                    while (true) {
                        if (answer == 1) {
                            commitFight(createDragon(),location,startAutoBattle);
                            dragoEnable = false;
                        } else if (answer == 2) {
                            printNavigationWorld();
                            dragoEnable = false;
                        } else {
                            System.out.println("Введите правильное число");
                            answer = readIntegerInput();
                        }
                    }

                } else {
                    System.out.println("Желаете продолжить поход или вернуться в город? \n1. Продолжить поход\n2. Вернуться в город\n");

                    answer = readIntegerInput();

                    while (true) {
                        if (answer == 1) {
                            processChoiceBattle(location);
                        } else if (answer == 2) {
                            seller.generateInventory();
                            printNavigationWorld();
                        } else {
                            System.out.println("Введите правильное число");
                            answer = readIntegerInput();
                        }
                    }

                   /* switch (answer) {
                        case 1:
                            processChoiceBattle(location);
                            break;
                        case 2:
                            seller.generateInventory();
                            printNavigationWorld();
                            break;
                        default:
                            System.out.println("Некорректный выбор\n");
                            fightWin();
                            break;
                    }*/
                }

            }

            @Override
            public void fightLost() {
                System.out.println("Начать сначала :\n1. Да\n2. Нет\n");
                int answer = readIntegerInput();
                switch (answer) {
                    case 1:
                        System.out.println("Введите имя персонажа:");
                        try {
                            player = null;
                            createHero(br.readLine());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case 2:
                        System.exit(0); // Завершение программы
                        break;

                    default:
                        System.out.println("Некорректный выбор\n");
                        fightLost();
                        break;
                }

            }
        });
    }

    private static Entity createDragon() {
        return new Dragon("Драго первый",700,40,25,3000,1000);
    }

    private static Entity createMonster(int location) {

        Random random = new Random();

        int choice = random.nextInt(100);
        switch (location) {

            case 1:
                if (choice < 5) {
                    return random.nextBoolean() ? new Wolf("Сильный Волк", 75, 5, 7, 70, 35) : new Goblin("Сильный Гоблин", 100, 7, 12, 95, 50);
                } else if (choice < 99) {
                    return random.nextBoolean() ? new Wolf("Волк", 30, 2, 2, 20, 10) : new Goblin("Гоблин", 50, 2, 5, 30, 15);
                }
                break;
            case 2:
                if (choice < 5) {
                    return random.nextBoolean() ? new Wolf("Серый Волк", 95, 7, 10, 130, 65) : new Goblin("Быстрый Гоблин", 100, 8, 15, 130, 85);

                } else if (choice < 99) {
                    return random.nextBoolean() ? new Wolf("Волк", 45, 2, 5, 30, 15) : new Goblin("Гоблин", 65, 3, 6, 50, 35);
                }
                break;
            case 3:
                if (choice < 5) {
                    int monsterChoice = random.nextInt(3);
                    switch (monsterChoice) {
                        case 0:
                            return new Skeleton("Воин Скелет", 130, 10, 4, 90, 75);
                        case 1:
                            return new Goblin("Сильный Гоблин", 150, 13, 7, 120, 90);
                        case 2:
                            return new SkeletonArcher("Скелет Лучник", 70, 3, 12, 30,35);
                    }

                } else if (choice < 99) {
                    int monsterChoice = random.nextInt(3);
                    switch (monsterChoice) {
                        case 0:
                            return new Skeleton("Скелет", 75, 6, 3, 35, 30);
                        case 1:
                            return new Goblin("Гоблин", 50, 2, 5, 30, 15);
                        case 2:
                            return new SkeletonArcher("Скелет Лучник", 70, 3, 12, 30,35);
                    }
                }
        }
        return null;
    }

}

