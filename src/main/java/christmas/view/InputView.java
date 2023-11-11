package christmas.view;

import static christmas.constant.ViewMessage.INPUT_MENU_NAME_QUANTITY;
import static christmas.constant.ViewMessage.INPUT_VISIT_DAY_MESSAGE;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String inputDayOfDecember() {
        System.out.println(INPUT_VISIT_DAY_MESSAGE);
        return Console.readLine();
    }

    public static String inputMenuNameAndQuantity() {
        System.out.println(INPUT_MENU_NAME_QUANTITY);
        return Console.readLine();
    }
}
