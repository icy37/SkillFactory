package RPG.Players;


public class Entity implements Fighter {

    private String name;

    private int healthPoints;
    private int strength;
    private int agility;
    private int level = 1;
    private int protection;
    private int xp;
    private int gold;
    private int damage;
    private int bonusDamage;
    private int currentHealtPoints;
    private static final double R = Math.pow(10000.0, 1.0 / 9.0);


    public Entity(String name, int healthPoints, int strength, int agility, int xp, int gold) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.strength = strength;
        this.agility = agility;
        this.xp = xp;
        this.gold = gold;
        protection = 0;
        currentHealtPoints = healthPoints;
        damage = calculateDamage();
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public int getCurrentHealtPoints() {
        return currentHealtPoints;
    }

    public void setCurrentHealtPoints(int currentHealtPoints) {
        this.currentHealtPoints = currentHealtPoints;

    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp += xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setBonusGold(int gold) {
        this.gold += gold;
    }

    private int getRandomValue() {
        return (int) (Math.random() * 100);
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public void setBonusProtection (int protection) {
        this.protection += protection;
    }

    public void setBonusDamage(int bonusDamage) {
        this.bonusDamage += bonusDamage;
    }

    public int getBonusDamage () {
        return bonusDamage;
    }

    public void setBonusStrange(int bonusStrange) {
        this.strength += bonusStrange;
    }

    public void setBonusAgility(int bonusAgility) {
        this.agility += bonusAgility;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public int calculateDamage() {
        damage = getStrength() + (getAgility() / 2) + bonusDamage;
        return damage;
    }

    public int getLevel() {
        return level;
    }

    protected void setLevel() {
        this.level += 1;
    }

    protected void levelUp() {
        setBonusAgility(1);
        setBonusStrange(3);
        setHealthPoints(30);
        if (getCurrentHealtPoints() < getHealthPoints()) {
            setCurrentHealtPoints(getHealthPoints());
        }
        calculateDamage();
        setLevel();
        System.out.println("Уровень повышен! Теперь уровень героя: ");
        getStatistics();
    }

    private int getExperienceRequiredForNextLevel() {
        // Используем формулу геометрической прогрессии
        return (int) (Math.pow(R, level - 1) + 0.5);
    }

    public void gainExperience(int experiencePoints) {
        setXp(experiencePoints);
        if (level < 10 && getXp() >= getExperienceRequiredForNextLevel()) {
            levelUp();
        } else {
            System.out.println("Достигнут максимальный уровень");
        }
    }

    public void getStatistics() {
        System.out.println("Имя: " + getName() + " " + getCurrentHealtPoints() + "/" + getHealthPoints() + ", опыт: " + getXp() + ", уровень " + getLevel() + ", сила: " + getStrength() + ", ловкость: " + getAgility() + ", сила удара: " + getDamage() + ", золото: " + getGold() + "\n");
    }

    @Override
    public String toString() {
        return String.format("%s здоровье: %d/%d, сила удара: %s, защита: %s", name, currentHealtPoints, healthPoints, damage, protection);
    }

    @Override
    public int attack() {
        if (agility * 3 > 20 && getRandomValue() > 80) return damage * 2;

        else return damage;
    }
}
