package christmas.domain;

import static christmas.domain.Badge.findBadgeByPrice;
import static christmas.domain.Event.D_DAY_EVENT;
import static christmas.domain.Event.GIFT_EVENT;
import static christmas.domain.Event.SPECIAL_EVENT;
import static christmas.domain.Event.WEEKDAY_EVENT;
import static christmas.domain.Menu.CHAMPAGNE;

import christmas.util.Convert;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChristmasEvent {

    private static final List<Integer> WEEKDAY = List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25,
            26, 27, 28, 31);
    private static final List<Integer> WEEKEND = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);
    private static final List<Integer> SPECIAL_DAY = List.of(3, 10, 17, 24, 25, 31);
    private static final Menu GIFT_EVENT_ITEM = CHAMPAGNE;
    private static final int GIFT_EVENT_QUANTITY = 1;

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

    public void buildEventMap(int price, int day) {
        if (price >= 10000) {
            checkGiftEvent(price);
            checkDDayEvent(day);
            checkWeekdayEvent(day);
            checkWeekendEvent(day);
            checkSpecialEvent(day);
        }
    }

    public boolean isGiftEventExist() {
        return eventMap.get(GIFT_EVENT) != null;
    }

    private void checkGiftEvent(int price) {
        if (price >= 120000) {
            eventMap.put(GIFT_EVENT, GIFT_EVENT_ITEM.getPrice() * GIFT_EVENT_QUANTITY);
        }
    }

    private void checkDDayEvent(int day) {
        if (day <= 25) {
            int discount = 1000 + 100 * (day - 1);
            eventMap.put(D_DAY_EVENT, discount);
        }
    }

    private void checkWeekdayEvent(int day) {
        if (WEEKDAY.contains(day)) {
            eventMap.put(WEEKDAY_EVENT, 2023 * countDessert());
        }
    }

    private void checkWeekendEvent(int day) {
        if (WEEKEND.contains(day)) {
            eventMap.put(WEEKDAY_EVENT, 2023 * countMain());
        }
    }

    private void checkSpecialEvent(int day) {
        if (SPECIAL_DAY.contains(day)) {
            eventMap.put(SPECIAL_EVENT, 1000);
        }
    }

    private int countDessert() {
        int count = 0;
        for (Menu menu : menuMap.keySet()) {
            if (menu.getType() == Type.DESSERT) {
                count += menuMap.get(menu);
            }
        }
        return count;
    }

    private int countMain() {
        int count = 0;
        for (Menu menu : menuMap.keySet()) {
            if (menu.getType() == Type.MAIN) {
                count += menuMap.get(menu);
            }
        }
        return count;
    }

    public int calculateDiscount() {
        return eventMap.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int calculateFinalPayAmount(int price) {
        if (eventMap.get(GIFT_EVENT) == null) {
            return price - calculateDiscount();
        }
        return price - calculateDiscount() + eventMap.get(GIFT_EVENT);
    }

    public Badge calculateBadge(int price) {
        return findBadgeByPrice(price);
    }

    public Map<Menu, Integer> getMenuMap() {
        return menuMap;
    }

    public Map<Event, Integer> getEventMap() {
        return eventMap;
    }
}
