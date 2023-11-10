package christmas.domain;

import static christmas.domain.Menu.CHAMPAGNE;

import christmas.util.Convert;
import java.util.HashMap;
import java.util.Map;

public class ChristmasEvent {

    private final Map<Menu, Integer> menuMap;
    private final Map<Event, Integer> eventMap = new HashMap<>();

    public ChristmasEvent(String input) {
        menuMap = Convert.convertToMenuMap(input);
    }

    public int calculateBeforeEventPrice() {
        int price = 0;
        for (Menu menu : menuMap.keySet()) {
            price += menu.getPrice() * menuMap.get(menu);
        }
        return price;
    }

    public void checkGiftEvent(int price) {
        if (price >= 120000) {
            eventMap.put(Event.GIFT_EVENT, CHAMPAGNE.getPrice());
        }
    }
}
