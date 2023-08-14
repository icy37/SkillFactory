package RPG.Items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bag {

    private List<Item> items;

    public Bag() {
        items = new ArrayList<>();
    }

    public boolean addItem(Item item, boolean isPlayer) {
        if (items.size() >= 10) {
            System.out.println("Сумка переполнена, предмет " + item.getName() + " не может быть положен.");
            return false;
        } else {
            items.add(item);
            if (isPlayer) {
                System.out.println(item.getName() + " добавлен в инвентарь.");
            }
            return true;
        }
    }

    public void removeItem(Item item) {
        if (items.size() == 0) {
        } else {
            items.remove(item);
        }
    }
    public void clear() {
        items.clear();
    }

    public boolean removeFirstItem(Item item) {
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == item) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Item> getItems(){
        return items;
    }

    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        } else {
            System.out.println("Недопустимый индекс предмета.");
            return null;
        }
    }


}

