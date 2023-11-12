package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.ChristmasEvent;
import christmas.domain.ChristmasMenu;
import christmas.domain.Menu;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasEventController {

    public void run() {
        int day = visitDay();

        ChristmasMenu christmasMenu = visitorChristmasMenu(day);
        int originalPrice = christmasMenu.calculateBeforeEventPrice();

        ChristmasEvent christmasEvent = visitorChristmasEvent(day, christmasMenu, originalPrice);
        visitorApplyEvents(christmasMenu, originalPrice, christmasEvent);

        int discount = visitorDiscount(christmasEvent);
        visitorFinalPay(originalPrice, christmasEvent);
        visitorBadge(christmasEvent, discount);
    }

    private static int visitDay() {
        OutputView.printStartEventPlanner();
        return InputController.inputDayOfDecember();
    }

    private static ChristmasMenu visitorChristmasMenu(int day) {
        Map<Menu, Integer> menus = InputController.inputMenuNameAndQuantity();
        OutputView.printShowEvent(day);
        return new ChristmasMenu(menus);
    }

    private static ChristmasEvent visitorChristmasEvent(int day, ChristmasMenu christmasMenu, int originalPrice) {
        ChristmasEvent christmasEvent = new ChristmasEvent(christmasMenu);
        christmasEvent.buildEventMap(originalPrice, day);
        return christmasEvent;
    }

    private static void visitorApplyEvents(ChristmasMenu christmasMenu, int originalPrice,
                                           ChristmasEvent christmasEvent) {
        OutputView.printAllMenus(christmasMenu.getMenuMap());
        OutputView.printBeforeEventPrice(originalPrice);
        OutputView.printGiftEvent(christmasEvent.isGiftEventExist());
        OutputView.printEvents(christmasEvent.getEventMap());
    }

    private static int visitorDiscount(ChristmasEvent christmasEvent) {
        int discount = christmasEvent.calculateDiscount();
        OutputView.printDiscount(discount);
        return discount;
    }

    private static void visitorFinalPay(int originalPrice, ChristmasEvent christmasEvent) {
        int finalPay = christmasEvent.calculateFinalPayAmount(originalPrice);
        OutputView.printFinalPayAmount(finalPay);
    }

    private static void visitorBadge(ChristmasEvent christmasEvent, int discount) {
        Badge badge = christmasEvent.calculateBadge(discount);
        OutputView.printBadge(badge);
    }

}
