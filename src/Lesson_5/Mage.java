package Lesson_5;

public class Mage {
    String name, type;
    int level, damage;

    public Mage(String name, int level, int damage, String type) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.type = type;
    }

    String getInfo() {
        return name + ", " + level + ", " + damage + ", " + type;
    }

    public String fight(Mage mage) {
        // Определяем стихию текущего мага и мага-противника
        String myType = this.type;
        String enemyType = mage.type;

        // Проверяем, кто побеждает по стихиям
        if (myType.equals("fire")) {
            if (enemyType.equals("ice")) {
                return this.name;
            } else if (enemyType.equals("earth")) {
                return mage.name;
            }
        } else if (myType.equals("ice")) {
            if (enemyType.equals("earth")) {
                return this.name;
            } else if (enemyType.equals("fire")) {
                return mage.name;
            }
        } else if (myType.equals("earth")) {
            if (enemyType.equals("fire")) {
                return this.name;
            } else if (enemyType.equals("ice")) {
                return mage.name;
            }
        }

        // Если стихии равны, то сравниваем уровни магов
        if (this.level > mage.level) {
            return this.name;
        } else if (this.level < mage.level) {
            return mage.name;
        }

        // Если уровни равны, то сравниваем урон
        if (this.damage > mage.damage) {
            return this.name;
        } else if (this.damage < mage.damage) {
            return mage.name;
        }

        // В остальных случаях ничья
        return "draw";
    }
}