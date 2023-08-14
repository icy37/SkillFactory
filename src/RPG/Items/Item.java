package RPG.Items;

public abstract class Item {
    private String name;
    private int gold;

    public Item(String name, int gold) {
        this.name = name;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }
}
