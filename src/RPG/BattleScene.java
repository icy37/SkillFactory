package RPG;


import RPG.Interfaces.FightCallback;
import RPG.Players.Entity;
import RPG.Players.Player;

import java.util.concurrent.atomic.AtomicBoolean;

public class BattleScene {
    //Метод, который вызывается при начале боя, сюда мы передаем ссылки на нашего героя и монстра, который встал у него на пути

    public void fight(Player player, Entity monster, boolean startAutoBattle, FightCallback fightCallback) {

        AtomicBoolean isFightEnded = new AtomicBoolean(false);

        // Поток для автоматического боя
        Runnable autoBattle = () -> {
            while (!isFightEnded.get()) {
                isFightEnded.set(makeHit(monster, player, fightCallback));
                if (isFightEnded.get()) {
                    break;
                }
                isFightEnded.set(makeHit(player, monster, fightCallback));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        if (startAutoBattle) {
            Thread autoBattleThread = new Thread(autoBattle);
            autoBattleThread.start();
            try {
                autoBattleThread.join(); // Ждем, пока автобой завершится
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }

        while (!isFightEnded.get()) {
            printNavigationBattle(player.hasHealthPotion());

            int playerChoice;
            while (true) {
                System.out.print("Введите номер выбранного действия: ");
                playerChoice = Realm.readIntegerInput();
                if (player.hasHealthPotion() && playerChoice == 2) {
                    break;
                } else if (playerChoice == 1 || playerChoice == 3) {
                    break;
                } else {
                    System.out.println("Недопустимый выбор. Пожалуйста, выберите снова.");
                }
            }

            switch (playerChoice) {
                case 1:
                    isFightEnded.set(makeHit(monster, player, fightCallback));
                    break;
                case 2:
                    player.useHealthPotion();
                    break;
                case 3:
                    // Запускаем поток для автоматического боя
                    Thread autoBattleThread = new Thread(autoBattle);
                    autoBattleThread.start();
                    try {
                        autoBattleThread.join(); // Ждем, пока автобой завершится
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Недопустимый выбор. Пожалуйста, выберите снова.");
                    break;
            }

            if (isFightEnded.get()) {
                break;
            }

            System.out.println("----Ход монстра----");
            isFightEnded.set(makeHit(player, monster, fightCallback));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //Метод для совершения удара
    private Boolean makeHit(Entity defender, Entity attacker, FightCallback fightCallback) {
        //Получаем силу удара
        int hit = attacker.attack() - defender.getProtection();
        //Отнимаем количество урона из здоровья защищающегося
        int defenderHealth = defender.getCurrentHealtPoints() - hit;
        //Если атака прошла, выводим в консоль сообщение об этом
        if (hit != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), hit));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
        } else {
            //Если атакующий промахнулся (то есть урон не 0), выводим это сообщение
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Player) {
            //Если здоровье меньше 0 и если защищающейся был героем, то игра заканчивается
            System.out.println("Извините, вы пали в бою...");
            //Вызываем коллбэк, что мы проиграли
            fightCallback.fightLost();
            return true;
        } else if (defenderHealth <= 0) {
            //Если здоровья больше нет и защищающийся – это монстр, то мы забираем от монстра его опыт и золото
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold()));
            //attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.gainExperience(defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            //вызываем коллбэк, что мы победили
            fightCallback.fightWin();
            return true;
        } else {
            //если защищающийся не повержен, то мы устанавливаем ему новый уровень здоровья
            defender.setCurrentHealtPoints(defenderHealth);
            return false;
        }
    }


    private static void printNavigationBattle(boolean health) {

        System.out.println("Ваши действия: ");

        System.out.println("1. Атаковать");
        if (health) {
            System.out.println("2. Использовать Зелье");
        }
        System.out.println("3. Автобой");

    }


}
