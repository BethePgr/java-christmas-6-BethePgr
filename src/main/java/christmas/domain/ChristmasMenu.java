package christmas.domain;

import java.util.Map;

public class ChristmasMenu {

    private final Map<Menu, Integer> menuMap;

    public ChristmasMenu(Map<Menu, Integer> menuMap) {
        this.menuMap = menuMap;
    }

    public int calculateBeforeEventPrice() {
        int price = 0;
        for (Menu menu : menuMap.keySet()) {
            price += menu.getPrice() * menuMap.get(menu);
        }
        return price;
    }

    public int countDessert() {
        int count = 0;
        for (Menu menu : menuMap.keySet()) {
            if (menu.getType() == Type.DESSERT) {
                count += menuMap.get(menu);
            }
        }
        return count;
    }

    public int countMain() {
        int count = 0;
        for (Menu menu : menuMap.keySet()) {
            if (menu.getType() == Type.MAIN) {
                count += menuMap.get(menu);
            }
        }
        return count;
    }

    public Map<Menu, Integer> getMenuMap() {
        return menuMap;
    }
}
