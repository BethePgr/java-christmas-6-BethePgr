package christmas.view;

import static christmas.constant.ViewMessage.AFTER_DISCOUNT_PRICE_MESSAGE;
import static christmas.constant.ViewMessage.ALL_DISCOUNT_AMOUNT;
import static christmas.constant.ViewMessage.ALL_DISCOUNT_MESSAGE;
import static christmas.constant.ViewMessage.BEFORE_DISCOUNT_PRICE_MESSAGE;
import static christmas.constant.ViewMessage.DECEMBER_BADGE;
import static christmas.constant.ViewMessage.EVENT_DISCOUNT_AMOUNT;
import static christmas.constant.ViewMessage.EVENT_LIST_MESSAGE;
import static christmas.constant.ViewMessage.EVENT_PLANNER_MESSAGE;
import static christmas.constant.ViewMessage.GIFT_MENU_MESSAGE;
import static christmas.constant.ViewMessage.MENU_QUANTITY;
import static christmas.constant.ViewMessage.NOTHING;
import static christmas.constant.ViewMessage.ORDERED_MENU_MESSAGE;
import static christmas.constant.ViewMessage.PRICE_WON;
import static christmas.constant.ViewMessage.SHOW_EVENT_MESSAGE;
import static christmas.constant.ViewMessage.ZERO_WON;
import static christmas.util.Convert.convertAmount;

import christmas.domain.Badge;
import christmas.domain.Event;
import christmas.domain.Menu;
import java.util.Map;

public class OutputView {

    public static void printStartEventPlanner() {
        System.out.println(EVENT_PLANNER_MESSAGE);
    }

    public static void printShowEvent(int day) {
        System.out.printf(SHOW_EVENT_MESSAGE, day);
    }

    public static void printAllMenus(Map<Menu, Integer> menuMap) {
        System.out.println(ORDERED_MENU_MESSAGE);
        for (Menu menu : menuMap.keySet()) {
            System.out.printf(MENU_QUANTITY, menu.getName(), menuMap.get(menu));
        }
    }

    public static void printBeforeEventPrice(int price) {
        System.out.println(BEFORE_DISCOUNT_PRICE_MESSAGE);
        System.out.printf(PRICE_WON, convertAmount(price));
    }

    public static void printGiftEvent(boolean giftEventExist) {
        System.out.println(GIFT_MENU_MESSAGE);
        if (giftEventExist) {
            System.out.println("샴페인 1개");
        }
        System.out.println(NOTHING);
    }

    public static void printEvents(Map<Event, Integer> eventMap) {
        System.out.println(EVENT_LIST_MESSAGE);
        if (eventMap.size() == 0) {
            System.out.println(NOTHING);
        }
        if (eventMap.size() != 0) {
            for (Event event : eventMap.keySet()) {
                System.out.printf(EVENT_DISCOUNT_AMOUNT, event.getName(), convertAmount(eventMap.get(event)));
            }
        }
    }

    public static void printDiscount(int price) {
        System.out.println(ALL_DISCOUNT_MESSAGE);
        if (price == 0) {
            System.out.println(ZERO_WON);
        }
        if (price != 0) {
            System.out.printf(ALL_DISCOUNT_AMOUNT, convertAmount(price));
        }
    }

    public static void printFinalPayAmount(int price) {
        System.out.println(AFTER_DISCOUNT_PRICE_MESSAGE);
        System.out.printf(PRICE_WON, convertAmount(price));
    }

    public static void printBadge(Badge badge) {
        System.out.println(DECEMBER_BADGE);
        if (badge == null) {
            System.out.println(NOTHING);
        }
        if (badge != null) {
            System.out.println(badge.getName());
        }
    }
}
