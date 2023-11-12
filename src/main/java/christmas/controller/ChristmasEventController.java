package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.ChristmasEvent;
import christmas.domain.ChristmasMenu;
import christmas.domain.Menu;
import christmas.domain.VisitDay;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasEventController {

    public void run() {
        VisitDay visitDay = visitDay();

        ChristmasMenu christmasMenu = visitorChristmasMenu(visitDay);
        int originalPrice = christmasMenu.calculateBeforeEventPrice();

        ChristmasEvent christmasEvent = visitorChristmasEvent(visitDay, christmasMenu, originalPrice);
        visitorApplyEvents(christmasMenu, originalPrice, christmasEvent);

        int discount = visitorDiscount(christmasEvent);
        visitorFinalPay(originalPrice, christmasEvent);
        visitorBadge(christmasEvent, discount);
    }

    private VisitDay visitDay() {
        OutputView.printStartEventPlanner();
        return new VisitDay(InputController.inputDayOfDecember());
    }

    private ChristmasMenu visitorChristmasMenu(VisitDay visitDay) {
        Map<Menu, Integer> menus = InputController.inputMenuNameAndQuantity();
        OutputView.printShowEvent(visitDay.getDay());
        return new ChristmasMenu(menus);
    }

    private ChristmasEvent visitorChristmasEvent(VisitDay day, ChristmasMenu christmasMenu, int originalPrice) {
        ChristmasEvent christmasEvent = new ChristmasEvent(christmasMenu);
        christmasEvent.buildEventMap(originalPrice, day);
        return christmasEvent;
    }

    private void visitorApplyEvents(ChristmasMenu christmasMenu, int originalPrice,
                                    ChristmasEvent christmasEvent) {
        OutputView.printAllMenus(christmasMenu.getMenuMap());
        OutputView.printBeforeEventPrice(originalPrice);
        OutputView.printGiftEvent(christmasEvent.isGiftEventExist());
        OutputView.printEvents(christmasEvent.getEventMap());
    }

    private int visitorDiscount(ChristmasEvent christmasEvent) {
        int discount = christmasEvent.calculateDiscount();
        OutputView.printDiscount(discount);
        return discount;
    }

    private void visitorFinalPay(int originalPrice, ChristmasEvent christmasEvent) {
        int finalPay = christmasEvent.calculateFinalPayAmount(originalPrice);
        OutputView.printFinalPayAmount(finalPay);
    }

    private void visitorBadge(ChristmasEvent christmasEvent, int discount) {
        Badge badge = christmasEvent.calculateBadge(discount);
        OutputView.printBadge(badge);
    }

}
