package christmas.view;

import static christmas.constant.GiftConst.GIFT_EVENT_ITEM;
import static christmas.constant.GiftConst.GIFT_EVENT_QUANTITY;
import static christmas.constant.ViewMessageConst.AFTER_DISCOUNT_PRICE_MESSAGE;
import static christmas.constant.ViewMessageConst.ALL_DISCOUNT_AMOUNT;
import static christmas.constant.ViewMessageConst.ALL_DISCOUNT_MESSAGE;
import static christmas.constant.ViewMessageConst.BEFORE_DISCOUNT_PRICE_MESSAGE;
import static christmas.constant.ViewMessageConst.DECEMBER_BADGE;
import static christmas.constant.ViewMessageConst.EVENT_DISCOUNT_AMOUNT;
import static christmas.constant.ViewMessageConst.EVENT_LIST_MESSAGE;
import static christmas.constant.ViewMessageConst.EVENT_PLANNER_MESSAGE;
import static christmas.constant.ViewMessageConst.GIFT_MENU_MESSAGE;
import static christmas.constant.ViewMessageConst.MENU_QUANTITY;
import static christmas.constant.ViewMessageConst.NOTHING;
import static christmas.constant.ViewMessageConst.ORDERED_MENU_MESSAGE;
import static christmas.constant.ViewMessageConst.PRICE_WON;
import static christmas.constant.ViewMessageConst.SHOW_EVENT_MESSAGE;
import static christmas.constant.ViewMessageConst.ZERO;
import static christmas.constant.ViewMessageConst.ZERO_WON;
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
            System.out.printf(MENU_QUANTITY, GIFT_EVENT_ITEM.getName(), GIFT_EVENT_QUANTITY);
        }
        if (!giftEventExist) {
            System.out.println(NOTHING);
        }
    }

    public static void printEvents(Map<Event, Integer> eventMap) {
        System.out.println(EVENT_LIST_MESSAGE);
        if (eventMap.size() == ZERO) {
            System.out.println(NOTHING);
        }
        if (eventMap.size() != ZERO) {
            for (Event event : eventMap.keySet()) {
                System.out.printf(EVENT_DISCOUNT_AMOUNT, event.getName(), convertAmount(eventMap.get(event)));
            }
        }
    }

    public static void printDiscount(int price) {
        System.out.println(ALL_DISCOUNT_MESSAGE);
        if (price == ZERO) {
            System.out.println(ZERO_WON);
        }
        if (price != ZERO) {
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
