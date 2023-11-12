package christmas.controller;

import christmas.valid.DayValid;
import christmas.valid.MenusValid;
import christmas.view.InputView;

public class InputController {

    public static String inputDayOfDecember() {
        String day = InputView.inputDayOfDecember();
        try {
            DayValid.validDay(day);
            return day;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputDayOfDecember();
        }
    }

    public static String inputMenuNameAndQuantity() {
        String menus = InputView.inputMenuNameAndQuantity();
        try {
            MenusValid.validMenus(menus);
            return menus;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMenuNameAndQuantity();
        }
    }
}
