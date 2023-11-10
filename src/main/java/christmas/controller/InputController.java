package christmas.controller;

import christmas.valid.DayValid;
import christmas.valid.MenusValid;
import christmas.view.InputView;

public class InputController {

    public static int inputDayOfDecember() {
        String day = InputView.inputDayOfDecember();
        try {
            return DayValid.validDay(day);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputDayOfDecember();
        }
    }

    public static String inputMenuNameAndQuantity() {
        String menus = InputView.inputMenuNameAndQuantity();
        try {
            MenusValid.validMenus(menus);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMenuNameAndQuantity();
        }
        return menus;
    }
}
