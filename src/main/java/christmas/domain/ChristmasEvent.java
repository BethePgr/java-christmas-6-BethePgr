package christmas.domain;

import static christmas.constant.EventConst.CHRISTMAS_D_DAY;
import static christmas.constant.EventConst.CHRISTMAS_D_DAY_EVENT_BASIC_DISCOUNT;
import static christmas.constant.EventConst.CHRISTMAS_D_DAY_EVENT_PER_DAY_DISCOUNT;
import static christmas.constant.EventConst.DECEMBER_SPECIAL_DAY;
import static christmas.constant.EventConst.DECEMBER_WEEKDAY;
import static christmas.constant.EventConst.DECEMBER_WEEKEND;
import static christmas.constant.EventConst.DESSERT_DISCOUNT;
import static christmas.constant.EventConst.EVENT_LEAST_APPLY_PRICE;
import static christmas.constant.EventConst.GIFT_EVENT_LEAST_PRICE;
import static christmas.constant.EventConst.MAIN_DISCOUNT;
import static christmas.constant.EventConst.SPECIAL_DISCOUNT;
import static christmas.constant.GiftConst.GIFT_EVENT_ITEM;
import static christmas.constant.GiftConst.GIFT_EVENT_QUANTITY;
import static christmas.domain.Badge.findBadgeByPrice;
import static christmas.domain.Event.D_DAY_EVENT;
import static christmas.domain.Event.GIFT_EVENT;
import static christmas.domain.Event.SPECIAL_EVENT;
import static christmas.domain.Event.WEEKDAY_EVENT;
import static christmas.domain.Event.WEEKEND_EVENT;

import java.util.HashMap;
import java.util.Map;

public class ChristmasEvent {

    private final ChristmasMenu christmasMenu;
    private final Map<Event, Integer> eventMap = new HashMap<>();

    public ChristmasEvent(ChristmasMenu christmasMenu) {
        this.christmasMenu = christmasMenu;
    }

    public void buildEventMap(int price, VisitDay visitDay) {
        int day = visitDay.getDay();
        if (price >= EVENT_LEAST_APPLY_PRICE) {
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
        if (price >= GIFT_EVENT_LEAST_PRICE) {
            eventMap.put(GIFT_EVENT, GIFT_EVENT_ITEM.getPrice() * GIFT_EVENT_QUANTITY);
        }
    }

    private void checkDDayEvent(int day) {
        if (day <= CHRISTMAS_D_DAY) {
            int discount = CHRISTMAS_D_DAY_EVENT_BASIC_DISCOUNT + CHRISTMAS_D_DAY_EVENT_PER_DAY_DISCOUNT * (day - 1);
            eventMap.put(D_DAY_EVENT, discount);
        }
    }

    private void checkWeekdayEvent(int day) {
        if (DECEMBER_WEEKDAY.contains(day)) {
            eventMap.put(WEEKDAY_EVENT, DESSERT_DISCOUNT * christmasMenu.countDessert());
        }
    }

    private void checkWeekendEvent(int day) {
        if (DECEMBER_WEEKEND.contains(day)) {
            eventMap.put(WEEKEND_EVENT, MAIN_DISCOUNT * christmasMenu.countMain());
        }
    }

    private void checkSpecialEvent(int day) {
        if (DECEMBER_SPECIAL_DAY.contains(day)) {
            eventMap.put(SPECIAL_EVENT, SPECIAL_DISCOUNT);
        }
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

    public Map<Event, Integer> getEventMap() {
        return eventMap;
    }
}
