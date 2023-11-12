package christmas.controller;

import christmas.domain.Menu;
import christmas.valid.DayValid;
import christmas.valid.MenusValid;
import christmas.view.InputView;
import java.util.Map;

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

    public static Map<Menu, Integer> inputMenuNameAndQuantity() {
        String menus = InputView.inputMenuNameAndQuantity();
        try {
            return MenusValid.validMenus(menus);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputMenuNameAndQuantity();
        }
    }
}
