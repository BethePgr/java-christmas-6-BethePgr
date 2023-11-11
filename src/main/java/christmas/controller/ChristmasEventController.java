package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.ChristmasEvent;
import christmas.domain.ChristmasMenu;
import christmas.view.OutputView;

public class ChristmasEventController {

    public void run() {
        OutputView.printStartEventPlanner();
        int day = InputController.inputDayOfDecember();
        String menus = InputController.inputMenuNameAndQuantity();
        OutputView.printShowEvent(day);
        ChristmasMenu christmasMenu = new ChristmasMenu(menus);
        int originalPrice = christmasMenu.calculateBeforeEventPrice();
        ChristmasEvent christmasEvent = new ChristmasEvent(christmasMenu);
        christmasEvent.buildEventMap(originalPrice, day);

        OutputView.printAllMenus(christmasMenu.getMenuMap());
        OutputView.printBeforeEventPrice(originalPrice);
        OutputView.printGiftEvent(christmasEvent.isGiftEventExist());
        OutputView.printEvents(christmasEvent.getEventMap());

        int discount = christmasEvent.calculateDiscount();
        OutputView.printDiscount(discount);
        int finalPay = christmasEvent.calculateFinalPayAmount(originalPrice);
        OutputView.printFinalPayAmount(finalPay);
        Badge badge = christmasEvent.calculateBadge(discount);
        OutputView.printBadge(badge);
    }
}
